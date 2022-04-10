package br.com.leotosin.restaurantapp.viewModels;

import br.com.leotosin.restaurantapp.repository.DataStorageSingleton;
import br.com.leotosin.restaurantapp.repository.DatabaseType;
import br.com.leotosin.restaurantapp.repository.IRepository;

public abstract class BaseViewModel {

    protected final IRepository repository;

    protected BaseViewModel() {
        this.repository = DataStorageSingleton.getInstance(DatabaseType.IN_MEMORY);
    }
}
