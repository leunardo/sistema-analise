/*
More in github.com/leunardo
 */
package io.github.leunardo.sistemaanalise.service;

import io.github.leunardo.sistemaanalise.model.ItemModel;
import io.github.leunardo.sistemaanalise.model.SalesModel;

/**
 *
 * @author leonardo
 */
public class SalesService {
    
        
    public static int compareSales(SalesModel a, SalesModel b) {
        Double priceA = a.getItems().stream().map(SalesService::getFullPriceOfItems).reduce(Double.NaN, Double::sum);
        Double priceB = b.getItems().stream().map(SalesService::getFullPriceOfItems).reduce(Double.NaN, Double::sum);
        return priceA.intValue() - priceB.intValue();
    }
    
    private static double getFullPriceOfItems(ItemModel item) {
        return item.getItemPrice() * item.getItemQuantity();
    }
    
}
