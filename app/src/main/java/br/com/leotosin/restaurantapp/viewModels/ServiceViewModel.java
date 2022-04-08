package br.com.leotosin.restaurantapp.viewModels;

import br.com.leotosin.restaurantapp.repository.DataStorageSingleton;
import br.com.leotosin.restaurantapp.repository.DatabaseType;
import br.com.leotosin.restaurantapp.repository.IRepository;

public class ServiceViewModel {

    private final IRepository repository;

    public ServiceViewModel() {
        this.repository = DataStorageSingleton.getInstance(DatabaseType.IN_MEMORY);
    }

    public String findTableNumber() {
        return this.repository.getOrder().getTable().getNumber();
    }
}
