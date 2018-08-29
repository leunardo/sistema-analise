/*
More in github.com/leunardo
 */
package io.github.leunardo.sistemaanalise.service;

import io.github.leunardo.sistemaanalise.enums.DataTypeEnum;
import io.github.leunardo.sistemaanalise.model.ClientModel;
import io.github.leunardo.sistemaanalise.model.IData;
import io.github.leunardo.sistemaanalise.model.ItemModel;
import io.github.leunardo.sistemaanalise.model.SalesModel;
import io.github.leunardo.sistemaanalise.model.SalesmanModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author leonardo
 */
public class DataService {
    
    public static IData getType(String data) {
        String[] dataArray = data.split("ç");
        String dataId = dataArray[0];
        
        if (DataTypeEnum.SalesMan.toString().equals(dataId)) {
            String cpf = dataArray[1];
            String name  = dataArray[2];
            double salary = Double.parseDouble(dataArray[3]);
            return new SalesmanModel(cpf, name, salary);
            
        } else if (DataTypeEnum.Client.toString().equals(dataId)) {
            String cnpj = dataArray[1];
            String name = dataArray[2];
            String businessArea = dataArray[3];
            return new ClientModel(cnpj, name, businessArea);
            
        } else if (DataTypeEnum.Sales.toString().equals(dataId)) {
            int saleId = Integer.parseInt(dataArray[1]);
            String salesmanName = dataArray[3];
            ArrayList<ItemModel> items =  Arrays.stream(dataArray[2].split(","))
                    .map(item -> ItemService.mapItem(item, salesmanName))
                    .collect(Collectors.toCollection(ArrayList::new));
            return new SalesModel(saleId, items, salesmanName);
        }
        
        return null;
    }
    
}
