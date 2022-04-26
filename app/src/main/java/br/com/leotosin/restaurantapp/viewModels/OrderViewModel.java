package br.com.leotosin.restaurantapp.viewModels;

import android.util.Log;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Order;
import br.com.leotosin.restaurantapp.models.OrderLine;

public class OrderViewModel extends BaseViewModel{

    private String orderId;

    public OrderViewModel() {
        super();
    }

    public ArrayList<Order> getAllOrders(){
        return this.repository.getAllOrders();
    }

    public void invoiceOrder() {
        this.repository.invoiceOrder(orderId);
    }

    public ArrayList<OrderLine> listAllOrderProducts() {
        return this.repository.getAllOrderProducts(orderId);
    }

    public Order getOrderById(){
        return this.repository.getOrderById(orderId);
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
