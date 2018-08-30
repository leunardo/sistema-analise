/*
More in github.com/leunardo
 */
package io.github.leunardo.sistemaanalise.service;

import io.github.leunardo.sistemaanalise.model.ItemModel;
import io.github.leunardo.sistemaanalise.model.SalesModel;
import io.github.leunardo.sistemaanalise.model.SalesmanModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author leonardo
 */
public class SalesmanService {
    public static SalesmanModel getWorstSalesman (Stream<SalesModel> salesStream, ArrayList<SalesmanModel> salesmen) {
        Map<String, ArrayList<ItemModel>> itemsBySalesman = ItemService.groupBySalesmanName(salesStream);
        
        SalesmanModel salesmanWithoutSale = getSalesmanWithoutSale(salesmen, itemsBySalesman);
        if (salesmanWithoutSale != null) {
            return salesmanWithoutSale;
        }
        
        return getWorstSalesmanWithSales(itemsBySalesman, salesmen);
    }
    
    private static Boolean soldNothing(Map<String, ArrayList<ItemModel>> salesBySalesman, String salesman) {
        return !salesBySalesman.containsKey(salesman);
    }
    
    private static SalesmanModel getSalesmanWithoutSale(ArrayList<SalesmanModel> salesmen, Map<String, ArrayList<ItemModel>> itemsBySalesmen) {
        for (int i = 0; i < salesmen.size(); i++) {
            String name = salesmen.get(i).getName();
            if (soldNothing(itemsBySalesmen, name)) {
                return salesmen.get(i);
            }
        }
        
        return null;
    }
    
    private static SalesmanModel getWorstSalesmanWithSales(Map<String, ArrayList<ItemModel>> itemsBySalesman, ArrayList<SalesmanModel> salesmen) {
        Map<String, Double> aliquotaSalesman = new HashMap<>();

        itemsBySalesman.forEach((salesman, items) -> {
            // count the total value of sale? or just number of sales? 
            // double sum = items.stream()
            //    .map(d -> d.getItemPrice() * d.getItemQuantity())
            //    .reduce(Double::sum).get();

            double sum = items.size();
            if (aliquotaSalesman.containsKey(salesman)) {
                sum += aliquotaSalesman.get(salesman);
            }
            
            aliquotaSalesman.put(salesman, sum);
        });
        
        Map.Entry<String, Double> worstSalesmanWithSales = Collections.min(
            aliquotaSalesman.entrySet(), Comparator.comparing(Map.Entry::getValue)
        );
        
        return salesmen
                .stream()
                .filter(s -> s.getName().equals(worstSalesmanWithSales.getKey()))
                .findFirst()
                .get();
    }
}
