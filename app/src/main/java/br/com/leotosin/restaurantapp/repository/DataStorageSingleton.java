package br.com.leotosin.restaurantapp.repository;

public class DataStorageSingleton {

    private static IRepository instance;

    private DataStorageSingleton() { }

    public static IRepository getInstance(DatabaseType type) {

        if (instance == null) {
            instance = DataStorageFactory.getRepository(type);
        }

        return instance;
    }
}
