/*
More in github.com/leunardo
 */
package io.github.leunardo.sistemaanalise.service;

import io.github.leunardo.sistemaanalise.model.ItemModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
    
    public static Stream<Map.Entry<String, ArrayList<ItemModel>>> groupBySalesmanName (Stream<ItemModel> stream) {
        Map<String, ArrayList<ItemModel>> map = new HashMap<>();
        stream.forEach(item -> {
            String salesman = item.getSalesmanName();
            if (map.containsKey(salesman)) {
                map.get(salesman).add(item);
            } else {
                map.put(salesman, new ArrayList<>());
            }
        });
        
        return map.entrySet().stream();
    }
    
    public static Map.Entry<String, Double> compareSalesmanAliquota (Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
        if (a.getValue() > b.getValue()) {
            return a;
        } 
        return b;
    }
}
