package io.github.leunardo.sistemaanalise.application;

import io.github.leunardo.sistemaanalise.model.IData;
import io.github.leunardo.sistemaanalise.service.FileService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author leonardo
 */
public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        int awaitTime = 10000;
        String home = System.getProperty("user.home");
        String input = home + "/data/in";
        String extension = ".dat";
        
        while (true) {
            ArrayList<String> filepaths = FileService.getFilepathsFromFolder(input, extension);

            filepaths.forEach(p -> {
                System.out.println(">>> Reading file:" + p.replace(input, ""));
                ArrayList<IData> dataFromFile =  FileService.readFile(p);
                String output = p.replace("in", "out").replace(".dat", ".done.dat");
               
                try {
                    System.out.println("<<< Writing " + output);
                    FileService.writeFile(output, dataFromFile);
                } catch (IOException exception) {
                    System.out.print("Error ocurred while writing file on " + output);
                }
            });
            
            System.out.println("ZzZ Waiting " + awaitTime + "ms to scan directory again...");
            Thread.sleep(awaitTime); 
        }

    }
}
