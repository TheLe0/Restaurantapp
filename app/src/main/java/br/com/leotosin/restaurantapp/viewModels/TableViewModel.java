package br.com.leotosin.restaurantapp.viewModels;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Table;
import br.com.leotosin.restaurantapp.repository.DataStorageSingleton;
import br.com.leotosin.restaurantapp.repository.DatabaseType;
import br.com.leotosin.restaurantapp.repository.IRepository;

public class TableViewModel {

    private final IRepository repository;

    public TableViewModel() {
        this.repository = DataStorageSingleton.getInstance(DatabaseType.IN_MEMORY);
    }

    public void chooseTable(int position) {
        this.repository.changeTableStatus(position, false);

    }

    public ArrayList<Table> getAvailableTables() {
        return repository.getAvailableTables();
    }
}
