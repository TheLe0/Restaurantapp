package br.com.leotosin.restaurantapp.viewModels;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Table;

public class TableViewModel extends BaseViewModel  {

    public TableViewModel() {
        super();
    }

    public String chooseTable(String tableNumber) {
        return this.repository.initOrder(tableNumber);
    }

    public ArrayList<Table> getAvailableTables() {
        return repository.getAvailableTables();
    }
}
