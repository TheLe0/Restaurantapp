package br.com.leotosin.restaurantapp.viewModels;

import br.com.leotosin.restaurantapp.models.Product;

public class ProductDetailViewModel extends BaseViewModel {

    private final Product product;
    private String orderId;

    public ProductDetailViewModel() {
        super();
        product = this.repository.getTransitoryProduct();
    }

    public Product getSelectedProduct() {
        return product;
    }

    public void addProductToOrder(String qty) {
        int productQty = Integer.parseInt(qty);

        this.repository.addProductToOrder(orderId, product, productQty);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
