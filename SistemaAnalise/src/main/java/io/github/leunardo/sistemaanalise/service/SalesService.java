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
    
        
    public static SalesModel compareSales(SalesModel a, SalesModel b) {
        double priceA = a.getItems().stream().map(SalesService::getFullPriceOfItems).reduce(Double.NaN, Double::sum);
        double priceB = b.getItems().stream().map(SalesService::getFullPriceOfItems).reduce(Double.NaN, Double::sum);
    
        return priceA > priceB ? a : b;
    }
    
    private static double getFullPriceOfItems(ItemModel item) {
        return item.getItemPrice() * item.getItemQuantity();
    }
    
}
