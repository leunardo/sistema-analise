
package io.github.leunardo.sistemaanalise.model;

import java.util.ArrayList;

/**
 *
 * @author leonardo
 */
public class SalesModel implements IData {
    private int saleId;
    private ArrayList<ItemModel> items;
    private String salesmanName;

    public SalesModel(int saleId, ArrayList<ItemModel> items, String salesmanName) {
        this.saleId = saleId;
        this.items = items;
        this.salesmanName = salesmanName;
    }

    public int getSaleId() {
        return saleId;
    }

    public ArrayList<ItemModel> getItems() {
        return items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }
}
