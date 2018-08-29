package io.github.leunardo.sistemaanalise.model;

/**
 *
 * @author leonardo
 */
public class ItemModel {
    private int itemId;
    private int itemQuantity;
    private double itemPrice;
    private String salesmanName;

    public ItemModel(int itemId, int itemQuantity, double itemPrice, String salesmanName) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.salesmanName = salesmanName;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getSalesmanName() {
        return salesmanName;
    }
}
