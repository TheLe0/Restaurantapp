package br.com.leotosin.restaurantapp.repository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import br.com.leotosin.restaurantapp.models.Order;
import br.com.leotosin.restaurantapp.models.OrderLine;
import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.models.ProductType;
import br.com.leotosin.restaurantapp.models.Table;

public class LocalStorage implements IRepository {

    private ArrayList<Table> tables;
    private ArrayList<Product> availableProducts;
    private Product transitoryProduct;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalStorage() {
        this.populateData();
        this.order = new Order();
    }

    private void populateData() {
        this.populateTableList();
        this.populateAvailableProducts();
    }

    public void addTransitoryProduct(Product product) {
        this.transitoryProduct = product;
    }

    public Product getTransitoryProduct() { return this.transitoryProduct; }

    private OrderLine findProductOnOrder(Product product) {
        for (OrderLine line : this.order.getProducts()) {
            if (line.getProduct().getName().equals(product.getName())) {
                return line;
            }
        }

        return null;
    }

    private void updateProductQtyOnOrder(Product product) {
        for (OrderLine line : this.order.getProducts()) {
            if (line.getProduct().getName().equals(product.getName())) {
                line.setQty(line.getQty() + 1);
            }
        }
    }

    public void deleteProduct(Product product) {
        this.order.getProducts().removeIf(line -> line.getProduct().getName().equals(product.getName()));
    }

    public void updateProduct(Product product, int qty) {
        for (OrderLine line : this.order.getProducts()) {
            if (line.getProduct().getName().equals(product.getName())) {
                line.setQty(qty);
            }
        }
    }

    public void addProductToOrder(Product product, int qty) {

        OrderLine line = this.findProductOnOrder(product);

        if (line == null) {
            line = new OrderLine(qty, product);
            this.order.getProducts().add(line);
        } else {
            this.updateProductQtyOnOrder(product);
        }
    }

    public Double getOrderSubtotal() {
        AtomicReference<Double> totals = new AtomicReference<>(0.00);
        this.order.getProducts().forEach((line) -> {
            totals.updateAndGet(v -> v + line.getQty() * (line.getProduct().getPrice()));
        });

        return totals.get();
    }

    public ArrayList<OrderLine> getAllOrderProducts() {

        return this.order.getProducts();
    }

    private void populateAvailableProducts() {
        this.availableProducts = new ArrayList<Product>();

        this.availableProducts.add(new Product("Carajillo", "Drink espanhol, tradução literal se chama droga. É a mistura de 50 mL do Licor 43 com café", 24.8, "carajillo", ProductType.DRINK));
        this.availableProducts.add(new Product("Cerveja", "Latão Isebanhn Pilsen 500 ml.", 3.99, "cerveja", ProductType.DRINK));
        this.availableProducts.add(new Product("Coca-Cola", "Coca-cola normal lata.", 2.99, "coca_normal_lata", ProductType.DRINK));
        this.availableProducts.add(new Product("Coca-Cola Zero", "Coca-cola sem açúcar lata.", 2.99, "coca_zero_lata", ProductType.DRINK));
        this.availableProducts.add(new Product("Suco de Laranja", "Copo de suco natural de laranja.", 2.99, "suco_de_laranja", ProductType.DRINK));

        this.availableProducts.add(new Product("Croissaint", "Comida de origem austríaca, uma forma de pão. Pode ser recheado tanto doce quanto salgado.", 6.90, "croissants", ProductType.FOOD));
        this.availableProducts.add(new Product("Frango Xadrez", "Frango Kung Pao, também transcrito como Gong Bao ou Kung Po, ou frango xadrez no Brasil, é um prato chinês frito e apimentado feito com frango, amendoim, legumes, e pimenta vermelha.", 29.90, "frango_xadrez", ProductType.FOOD));
        this.availableProducts.add(new Product("Pretzel", "Pretzel é um tipo de pão muito popular entre as populações de língua alemã, sendo portanto bastante difundido na Alemanha, Áustria, Suíça e também nas regiões da Alsácia e do Alto Adige. Em forma de nó, é seco, estaladiço, habitualmente assado, podendo ser doce ou salgado.", 4.99, "pretzel", ProductType.FOOD));
        this.availableProducts.add(new Product("Salada Caezar", "Caesar salad, salada Caesar ou salada César é uma salada preparada com alface romana e molho Caesar. Os temperos usados mais habitualmente para compor este molho são azeite de oliva, suco de limão, anchovas, queijo parmesão, molho inglês, sal, açúcar e pimenta preta.", 14.55, "salada_caezar", ProductType.FOOD));
        this.availableProducts.add(new Product("Yakisoba", "Sōsu yakissoba, também conhecido por yakisoba, é um prato de origem japonesa, cujo nome significa, literalmente, macarrão de sobá frito.", 2.99, "yakisoba", ProductType.FOOD));
    }

    private void populateTableList() {

        this.tables = new ArrayList<Table>();

        for (int i = 1; i <= 30; i++) {

            String strNumber;

            if (i < 10) {
                strNumber = "0"+i;
            }
            else {
                strNumber = String.valueOf(i);
            }

            this.tables.add(new Table(strNumber, true));
        }
    }

    public Table findTableByPosition(int position) {
        return this.tables.get(position);
    }

    public void changeTableStatus(int position, boolean isAvailable) {
        this.tables.get(position).setOpen(isAvailable);
        this.order.setTable(this.tables.get(position));

    }

    public ArrayList<Table> getAvailableTables() {

        ArrayList<Table> availableTables = new ArrayList<Table>();

        this.tables.forEach((t) -> {
            if (t.getIsOpen()) {
                availableTables.add(t);
            }
        });

        return availableTables;
    }

    public Product findProductByName(String productName) {

        for (Product product : this.availableProducts) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }

        return null;
    }

    public ArrayList<Product> getAvailableProductsByType(ProductType type) {

        ArrayList<Product> list = new ArrayList<>();

        for (Product availableProduct : availableProducts) {
            if (availableProduct.getProductType() == type) {
                list.add(availableProduct);
            }
        }

        return list;
    }

}
