package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Order;
import service.order.OrderService;
import service.order.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/orders")
public class OrderController extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * POST /orders
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Читаем JSON из тела запроса и преобразуем в объект Order
        Order order = objectMapper.readValue(req.getInputStream(), Order.class);

        // Вызываем метод создания заказа в сервисном слое
        orderService.create(order);

        // Отправляем созданный заказ обратно в JSON-формате
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        objectMapper.writeValue(resp.getOutputStream(), order);
    }

    /**
     * GET /orders?id=123
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Ожидаем, что id будет передано как параметр запроса
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
            return;
        }
        try {
            Long id = Long.valueOf(idParam);
            Optional<Order> orderOptional = orderService.getById(id);
            if (orderOptional.isPresent()) {
                resp.setContentType("application/json");
                objectMapper.writeValue(resp.getOutputStream(), orderOptional.get());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format");
        }
    }

    /**
     * PUT /orders?id=123
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
            return;
        }
        try {
            Long id = Long.valueOf(idParam);
            // Читаем новый объект заказа из JSON тела запроса
            Order orderUpdate = objectMapper.readValue(req.getInputStream(), Order.class);
            Order updatedOrder = orderService.update(id, orderUpdate);
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getOutputStream(), updatedOrder);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format");
        }
    }

    /**
     * DELETE /orders?id=123
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
            return;
        }
        try {
            Long id = Long.valueOf(idParam);
            orderService.delete(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format");
        }
    }
}
