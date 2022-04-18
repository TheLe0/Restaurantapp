package br.com.leotosin.restaurantapp.viewModels;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.OrderLine;
import br.com.leotosin.restaurantapp.models.Product;

public class ServiceViewModel extends BaseViewModel {

    public ServiceViewModel() {
        super();
    }

    public String findTableNumber() {
        return this.repository.getOrder().getTable().getNumber();
    }

    public Double orderSubtotal() { return this.repository.getOrderSubtotal(); }

    public ArrayList<OrderLine> listAllOrderProducts() {
        return this.repository.getAllOrderProducts();
    }
}
