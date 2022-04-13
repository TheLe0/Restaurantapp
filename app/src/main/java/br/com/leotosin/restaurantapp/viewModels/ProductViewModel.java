package br.com.leotosin.restaurantapp.viewModels;

import br.com.leotosin.restaurantapp.models.ProductType;

public class ProductViewModel {
    private ProductType productType;

    public void changeProductType(String productTypeName) {

        switch(productTypeName) {
            case "Bebida":
                productType = ProductType.DRINK;
                break;
            case "Comida":
                productType = ProductType.FOOD;
                break;
        }
    }
}
