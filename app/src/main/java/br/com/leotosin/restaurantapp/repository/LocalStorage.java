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
    private final ArrayList<Order> orders;
    private ArrayList<Product> availableProducts;
    private Product transitoryProduct;

    public LocalStorage() {
        this.populateData();
        this.orders = new ArrayList<>();
    }

    private void populateData() {
        this.populateTableList();
        this.populateAvailableProducts();
    }

    public void addTransitoryProduct(Product product) {
        this.transitoryProduct = product;
    }

    public Product getTransitoryProduct() { return this.transitoryProduct; }

    private OrderLine findProductOnOrder(String orderId, Product product) {

        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                for (OrderLine line : order.getProducts()) {
                    if (line.getProduct().getName().equals(product.getName())) {
                        return line;
                    }
                }
            }
        }

        return null;
    }

    private void updateProductQtyOnOrder(String orderId, Product product) {

        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                for (OrderLine line : order.getProducts()) {
                    if (line.getProduct().getName().equals(product.getName())) {
                        line.setQty(line.getQty() + 1);
                    }
                }
            }
        }
    }

    public void invoiceOrder(String orderId) {

        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setInvoiced(true);
                for (Table table : this.tables) {
                    if (table.getNumber().equals(order.getTable().getNumber())) {
                        table.setOpen(true);
                    }
                }
            }
        }
    }

    public void deleteProduct(String orderId, Product product) {

        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                order.getProducts().removeIf(line -> line.getProduct().getName().equals(product.getName()));
            }
        }
    }

    public void updateProduct(String orderId, Product product, int qty) {

        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                for (OrderLine line : order.getProducts()) {
                    if (line.getProduct().getName().equals(product.getName())) {
                        line.setQty(qty);
                    }
                }
            }
        }
    }

    public void addProductToOrder(String orderId, Product product, int qty) {

        OrderLine line = this.findProductOnOrder(orderId, product);

        if (line == null) {
            line = new OrderLine(qty, product);

            for (Order order : this.orders) {
                if (order.getOrderId().equals(orderId)) {
                    order.getProducts().add(line);
                }
            }
        } else {
            this.updateProductQtyOnOrder(orderId, product);
        }
    }

    public Double getOrderSubtotal(String orderId) {
        AtomicReference<Double> totals = new AtomicReference<>(0.00);

        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                order.getProducts().forEach((line) -> {
                    totals.updateAndGet(v -> v + line.getQty() * (line.getProduct().getPrice()));
                });
            }
        }

        return totals.get();
    }

    public ArrayList<OrderLine> getAllOrderProducts(String orderId) {

        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                return order.getProducts();
            }
        }

        return null;
    }

    public ArrayList<Order> getAllOrders(){
        return this.orders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : this.orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }

        return null;
    }


    private void populateAvailableProducts() {
        this.availableProducts = new ArrayList<Product>();

        this.availableProducts.add(new Product("Carajillo", "Drink espanhol, tradu????o literal se chama droga. ?? a mistura de 50 mL do Licor 43 com caf??", 24.8, "carajillo", ProductType.DRINK));
        this.availableProducts.add(new Product("Cerveja", "Lat??o Isebanhn Pilsen 500 ml.", 3.99, "cerveja", ProductType.DRINK));
        this.availableProducts.add(new Product("Coca-Cola", "Coca-cola normal lata.", 2.99, "coca_normal_lata", ProductType.DRINK));
        this.availableProducts.add(new Product("Coca-Cola Zero", "Coca-cola sem a????car lata.", 2.99, "coca_zero_lata", ProductType.DRINK));
        this.availableProducts.add(new Product("Suco de Laranja", "Copo de suco natural de laranja.", 2.99, "suco_de_laranja", ProductType.DRINK));

        this.availableProducts.add(new Product("Croissaint", "Comida de origem austr??aca, uma forma de p??o. Pode ser recheado tanto doce quanto salgado.", 6.90, "croissants", ProductType.FOOD));
        this.availableProducts.add(new Product("Frango Xadrez", "Frango Kung Pao, tamb??m transcrito como Gong Bao ou Kung Po, ou frango xadrez no Brasil, ?? um prato chin??s frito e apimentado feito com frango, amendoim, legumes, e pimenta vermelha.", 29.90, "frango_xadrez", ProductType.FOOD));
        this.availableProducts.add(new Product("Pretzel", "Pretzel ?? um tipo de p??o muito popular entre as popula????es de l??ngua alem??, sendo portanto bastante difundido na Alemanha, ??ustria, Su????a e tamb??m nas regi??es da Als??cia e do Alto Adige. Em forma de n??, ?? seco, estaladi??o, habitualmente assado, podendo ser doce ou salgado.", 4.99, "pretzel", ProductType.FOOD));
        this.availableProducts.add(new Product("Salada Caezar", "Caesar salad, salada Caesar ou salada C??sar ?? uma salada preparada com alface romana e molho Caesar. Os temperos usados mais habitualmente para compor este molho s??o azeite de oliva, suco de lim??o, anchovas, queijo parmes??o, molho ingl??s, sal, a????car e pimenta preta.", 14.55, "salada_caezar", ProductType.FOOD));
        this.availableProducts.add(new Product("Yakisoba", "S??su yakissoba, tamb??m conhecido por yakisoba, ?? um prato de origem japonesa, cujo nome significa, literalmente, macarr??o de sob?? frito.", 2.99, "yakisoba", ProductType.FOOD));
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

    public String initOrder(String tableNumber) {

        for (Table table : this.tables) {
            if (table.getNumber().equals(tableNumber)) {
                table.setOpen(false);
                Order order = new Order(table);
                this.orders.add(order);
                return order.getOrderId();
            }
        }

        return "";
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
