package br.com.leotosin.restaurantapp.viewModels;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.OrderLine;

public class ServiceViewModel extends BaseViewModel {

    public ServiceViewModel() {
        super();
    }

    public String findTableNumber() {
        return this.repository.getOrder().getTable().getNumber();
    }

    public Double orderSubtotal() { return this.repository.getOrderSubtotal(); }

    public void invoiceOrder() {
        this.repository.invoiceOrder(this.repository.getOrder());
    }

    public ArrayList<OrderLine> listAllOrderProducts() {
        return this.repository.getAllOrderProducts();
    }
}
