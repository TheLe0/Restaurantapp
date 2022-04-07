package br.com.leotosin.restaurantapp.repository;

public abstract class DataStorageFactory {

    public static IRepository  getRepository(DatabaseType type) {

        if (type == DatabaseType.IN_MEMORY) {
            return new LocalStorage();
        }
        return null;
    }
}
