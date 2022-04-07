package br.com.leotosin.restaurantapp.repository;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Table;

public class LocalStorage implements IRepository {

    private ArrayList<Table> tables;

    public LocalStorage() {
        this.populateTableList();
    }

    private void populateTableList() {

        this.tables = new ArrayList<Table>();

        for (int i = 0; i <= 30; i++) {
            this.tables.add(new Table(i, true));
        }
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
