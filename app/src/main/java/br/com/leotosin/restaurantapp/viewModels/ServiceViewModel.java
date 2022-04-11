package br.com.leotosin.restaurantapp.viewModels;

public class ServiceViewModel extends BaseViewModel {

    public ServiceViewModel() {
        super();
    }

    public String findTableNumber() {
        return this.repository.getOrder().getTable().getNumber();
    }

    public Double orderSubtotal() { return this.repository.getOrderSubtotal(); }
}
