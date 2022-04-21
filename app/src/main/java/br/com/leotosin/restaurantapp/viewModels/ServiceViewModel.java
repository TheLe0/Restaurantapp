package br.com.leotosin.restaurantapp.viewModels;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.OrderLine;

public class ServiceViewModel extends BaseViewModel {

    private String orderId;

    public ServiceViewModel() {
        super();
    }

    public Double orderSubtotal() { return this.repository.getOrderSubtotal(this.orderId); }

    public void invoiceOrder() {
        this.repository.invoiceOrder(orderId);
    }

    public String getTableNumber() {
        return this.repository.getOrderById(orderId).getTable().getNumber();
    }

    public ArrayList<OrderLine> listAllOrderProducts() {
        return this.repository.getAllOrderProducts(orderId);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
