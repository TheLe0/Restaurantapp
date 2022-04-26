package br.com.leotosin.restaurantapp.viewModels;

import br.com.leotosin.restaurantapp.models.Product;

public class ProductEditViewModel extends BaseViewModel {

    private String productName;
    private String orderId;

    private Product product;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        this.product = repository.findProductByName(this.productName);
    }

    public void update(int qty) {
        this.repository.updateProduct(orderId, product, qty);
    }

    public void removeProduct(){this.repository.deleteProduct(orderId, product); }

    public Product getProduct() {return product;}

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
