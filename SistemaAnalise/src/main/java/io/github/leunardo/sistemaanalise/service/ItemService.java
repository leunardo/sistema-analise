/*
More in github.com/leunardo
 */
package io.github.leunardo.sistemaanalise.service;

import io.github.leunardo.sistemaanalise.model.ItemModel;

/**
 *
 * @author leonardo
 */
public class ItemService {
    public static ItemModel mapItem(String item, String salesmanName) {
        item = item.replace("[", "").replace("]", "");
        String values[] = item.split("-");
        int itemId = Integer.parseInt(values[0]);
        int itemQuantity = Integer.parseInt(values[1]);
        double itemPrice = Double.parseDouble(values[2]);
        
        return new ItemModel(itemId, itemQuantity, itemPrice, salesmanName);
    }
}
