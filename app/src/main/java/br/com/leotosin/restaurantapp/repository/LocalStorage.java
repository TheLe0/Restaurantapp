package br.com.leotosin.restaurantapp.repository;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Order;
import br.com.leotosin.restaurantapp.models.Table;

public class LocalStorage implements IRepository {

    private ArrayList<Table> tables;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalStorage() {
        this.populateTableList();
        this.order = new Order();
    }

    private void populateTableList() {

        this.tables = new ArrayList<Table>();

        for (int i = 1; i <= 30; i++) {

            String strNumber;

            if (i < 10) {
                strNumber = "0"+i;
            }
            else {
                strNumber = String.valueOf(i);
            }

            this.tables.add(new Table(strNumber, true));
        }
    }

    public Table findTableByPosition(int position) {
        return this.tables.get(position);
    }

    public void changeTableStatus(int position, boolean isAvailable) {
        this.tables.get(position).setOpen(isAvailable);
        this.order.setTable(this.tables.get(position));
    }

    public ArrayList<Table> getAvailableTables() {

        ArrayList<Table> availableTables = new ArrayList<Table>();

        this.tables.forEach((t) -> {
            if (t.getIsOpen()) {
                availableTables.add(t);
            }
        });

        return availableTables;
    }

}
