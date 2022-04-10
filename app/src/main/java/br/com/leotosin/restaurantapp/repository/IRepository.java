package br.com.leotosin.restaurantapp.repository;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Order;
import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.models.Table;

public interface IRepository {
    ArrayList<Table> getAvailableTables();
    ArrayList<Product> getAvailableProducts();
    Table findTableByPosition(int position);
    void changeTableStatus(int position, boolean isAvailable);
    Order getOrder();
}
