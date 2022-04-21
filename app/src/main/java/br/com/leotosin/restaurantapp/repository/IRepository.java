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
    Table findTableByPosition(int position);
    void changeTableStatus(int position, boolean isAvailable);
    Order getOrder();
    Double getOrderSubtotal();
    void addProductToOrder(Product product, int qty);
    void addTransitoryProduct(Product product);
    Product getTransitoryProduct();
    Product findProductByName(String productName);
    ArrayList<OrderLine> getAllOrderProducts();
    void updateProduct(Product product, int qty);
    void deleteProduct(Product product);
    void invoiceOrder(Order order);
}
