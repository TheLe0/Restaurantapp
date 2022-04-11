package br.com.leotosin.restaurantapp.models;

import java.util.ArrayList;

public class Order {

    private Table table;
    private ArrayList<OrderLine> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public ArrayList<OrderLine> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderLine> products) {
        this.products = products;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
