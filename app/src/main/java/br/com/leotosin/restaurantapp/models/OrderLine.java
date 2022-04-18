package br.com.leotosin.restaurantapp.models;

public class OrderLine {

    private int qty;
    private Product product;

    public OrderLine(int qty, Product item) {
        this.qty = qty;
        this.product = item;
    }

    public int getQty() {
        return qty;
    }

    public String getQtyFormated() {
        return "Quantidade: "+qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public String getLineTotalBrl() {

        double amount = this.qty*this.product.getPrice();

        return "Valor: R$ "+ amount;
    }

    public void setProduct(Product item) {
        this.product = item;
    }
}
