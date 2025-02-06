//package controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import entity.Product;
//import service.product.ProductService;
//import service.product.ProductServiceImpl;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet("/products")
//public class ProductController extends HttpServlet {
//
//    private final ProductService productService = new ProductServiceImpl();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    // CREATE - POST /products
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Product product = objectMapper.readValue(req.getInputStream(), Product.class);
//        productService.create(product);
//        resp.setContentType("application/json");
//        resp.setStatus(HttpServletResponse.SC_CREATED);
//        objectMapper.writeValue(resp.getOutputStream(), product);
//    }
//
//    // READ - GET /products?id=123
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idParam = req.getParameter("id");
//        if (idParam == null) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
//            return;
//        }
//        try {
//            Long id = Long.valueOf(idParam);
//            Optional<Product> productOptional = productService.getById(id);
//            if (productOptional.isPresent()) {
//                resp.setContentType("application/json");
//                objectMapper.writeValue(resp.getOutputStream(), productOptional.get());
//            } else {
//                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
//            }
//        } catch (NumberFormatException e) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format");
//        }
//    }
//
//    // UPDATE - PUT /products?id=123
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idParam = req.getParameter("id");
//        if (idParam == null) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
//            return;
//        }
//        try {
//            Long id = Long.valueOf(idParam);
//            Product productUpdate = objectMapper.readValue(req.getInputStream(), Product.class);
//            Product updatedProduct = productService.update(id, productUpdate);
//            resp.setContentType("application/json");
//            objectMapper.writeValue(resp.getOutputStream(), updatedProduct);
//        } catch (NumberFormatException e) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format");
//        }
//    }
//
//    // DELETE - DELETE /products?id=123
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idParam = req.getParameter("id");
//        if (idParam == null) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
//            return;
//        }
//        try {
//            Long id = Long.valueOf(idParam);
//            productService.delete(id);
//            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//        } catch (NumberFormatException e) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format");
//        }
//    }
//}

//not usable
