package br.com.leotosin.restaurantapp.models;

import java.util.UUID;
import java.util.ArrayList;

public class Order {

    private final String orderId;
    private final Table table;
    private final ArrayList<OrderLine> products;
    private boolean isInvoiced;

    public Order(Table table) {

        this.table = table;
        this.orderId = table.getNumber()+UUID.randomUUID().toString();
        this.isInvoiced = false;
        this.products = new ArrayList<>();
    }

    public String getOrderId() {
        return this.orderId;
    }

    public ArrayList<OrderLine> getProducts() {
        return products;
    }

    public Table getTable() {
        return table;
    }

    public boolean isInvoiced() {
        return isInvoiced;
    }

    public void setInvoiced(boolean invoiced) {
        isInvoiced = invoiced;
    }
}
