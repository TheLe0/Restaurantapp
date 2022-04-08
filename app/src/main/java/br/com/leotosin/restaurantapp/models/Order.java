package br.com.leotosin.restaurantapp.models;

public class Order {

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    private Table table;
}
