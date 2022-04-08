package br.com.leotosin.restaurantapp.models;

public class Table {

    private String number;
    private boolean isOpen;

    public Table(String num, boolean open) {
        this.number = num;
        this.isOpen = open;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
