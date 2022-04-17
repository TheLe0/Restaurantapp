package br.com.leotosin.restaurantapp.viewModels;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.models.ProductType;

public class ProductViewModel extends BaseViewModel {

    public ArrayList<Product> listProductsByType(String productTypeName) {

        ProductType productType;

        switch(productTypeName) {
            case "Bebida":
                productType = ProductType.DRINK;
                break;
            case "Comida":
                productType = ProductType.FOOD;
                break;
            default:
                return new ArrayList<Product>();
        }

        return this.repository.getAvailableProductsByType(productType);
    }

    public void addProduct(String productName) {

        this.repository.addTransitoryProduct(this.repository.findProductByName(productName));
    }
}
