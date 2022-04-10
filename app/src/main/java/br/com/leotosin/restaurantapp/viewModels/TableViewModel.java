package br.com.leotosin.restaurantapp.viewModels;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Table;

public class TableViewModel extends BaseViewModel  {

    public TableViewModel() {
        super();
    }

    public void chooseTable(int position) {
        this.repository.changeTableStatus(position, false);

    }

    public ArrayList<Table> getAvailableTables() {
        return repository.getAvailableTables();
    }
}
