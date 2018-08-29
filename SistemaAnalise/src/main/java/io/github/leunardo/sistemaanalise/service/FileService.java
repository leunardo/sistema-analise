package io.github.leunardo.sistemaanalise.service;

import io.github.leunardo.sistemaanalise.model.IData;
import io.github.leunardo.sistemaanalise.model.ClientModel;
import io.github.leunardo.sistemaanalise.model.SalesmanModel;
import io.github.leunardo.sistemaanalise.model.SalesModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 *
 * @author leonardo
 */
public class FileService {
    
    public static ArrayList<String> getFilepathsFromFolder(String path, String extension) {
        File folder = new File(path);

        if (folder.exists() && folder.isDirectory()) {
            ArrayList<String> files = Arrays.stream(folder.listFiles())
                .filter(file -> file.getName().endsWith(extension))
                .map(file -> file.getAbsolutePath())
                .collect(Collectors.toCollection(ArrayList::new));

           return files;
        }
        return null;
    }
    
    public static ArrayList<IData> readFile(String filepath) {
        try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
            ArrayList<IData> data = lines
                    .map(DataService::getType)
                    .collect(Collectors.toCollection(ArrayList::new));
            return data;
        } catch (IOException exception) {
            
        }
        
        return null;
    }
    
    public static void writeFile(String filepath, ArrayList<IData> data) {
        Path file = Paths.get(filepath);
        Stream<IData> dataStream = data.stream();
        
        long numberOfClients = dataStream.filter(d -> d instanceof ClientModel).count();
        long numberOfSalesmen = dataStream.filter(d -> d instanceof SalesmanModel).count();
        Stream<SalesModel> sales = dataStream
                .filter(d -> d instanceof SalesModel)
                .map(d -> (SalesModel) d);
        
        SalesModel mostExpensiveSale = sales.max(SalesService::compareSales);
   
       
    }
        

}
