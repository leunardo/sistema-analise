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
import java.util.function.Supplier;

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
            System.out.println("An error ocurred while reading a file.");
        }
        
        return null;
    }
    
    public static void writeFile(String filepath, ArrayList<IData> data) throws IOException {
        Path file = Paths.get(filepath);
 
        
       String dataToWrite = getDataParsedToWrite(data);
       
       Files.write(file, dataToWrite.getBytes());
    }
    
    private static String getDataParsedToWrite (ArrayList<IData> data) {
        StringBuilder sb = new StringBuilder();
        long numberOfClients = data.stream().filter(d -> d instanceof ClientModel).count();
        
        ArrayList<SalesmanModel> salesmen = data.stream()
                .filter(d -> d instanceof SalesmanModel)
                .map(d -> (SalesmanModel) d)
                .collect(Collectors.toCollection(ArrayList::new));
        
        long numberOfSalesmen = salesmen.size();
        
        Supplier<Stream<SalesModel>> salesStream = () -> data.stream()
                .filter(d -> d instanceof SalesModel)
                .map(d -> (SalesModel) d);
        
        SalesModel mostExpensiveSale = salesStream.get().max(SalesService::compareSales).get();
       
        SalesmanModel worstSalesman = SalesmanService.getWorstSalesman(salesStream.get(), salesmen);
                
        sb.append("Number of clients: ").append(numberOfClients).append("\n");
        sb.append("Number of salesmen: ").append(numberOfSalesmen).append("\n");
        sb.append("Most expensive sale ID: ").append(mostExpensiveSale.getSaleId()).append("\n");
        sb.append("Worst salesman: ").append(worstSalesman).append("\n");
        
        return sb.toString();
    }
        

}
