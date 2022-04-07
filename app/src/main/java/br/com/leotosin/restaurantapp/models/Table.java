package br.com.leotosin.restaurantapp.models;

public class Table {

    private int number;
    private boolean isOpen;

    public Table(int num, boolean open) {
        this.number = num;
        this.isOpen = open;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
