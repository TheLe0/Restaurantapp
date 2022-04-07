package br.com.leotosin.restaurantapp.repository;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Table;

public interface IRepository {
    ArrayList<Table> getAvailableTables();
}
