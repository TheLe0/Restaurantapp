package br.com.leotosin.restaurantapp.repository;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Order;
import br.com.leotosin.restaurantapp.models.OrderLine;
import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.models.ProductType;
import br.com.leotosin.restaurantapp.models.Table;

public interface IRepository {
    ArrayList<Table> getAvailableTables();
    ArrayList<Product> getAvailableProductsByType(ProductType type);
    String initOrder(String tableNumber);
    Order getOrderById(String orderId);
    Double getOrderSubtotal(String orderId);
    void addProductToOrder(String orderId, Product product, int qty);
    void addTransitoryProduct(Product product);
    Product getTransitoryProduct();
    Product findProductByName(String productName);
    ArrayList<OrderLine> getAllOrderProducts(String orderId);
    void updateProduct(String orderId, Product product, int qty);
    void deleteProduct(String orderId, Product product);
    void invoiceOrder(String orderId);
}
