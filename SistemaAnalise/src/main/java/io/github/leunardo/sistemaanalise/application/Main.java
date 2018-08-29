package io.github.leunardo.sistemaanalise.application;

import io.github.leunardo.sistemaanalise.service.FileService;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author leonardo
 */
public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        int awaitTime = 10000;
        String home = System.getProperty("user.home");
        String path = home + "/data/in";
        String extension = ".dat";
        
        while (true) {
            ArrayList<String> filepaths = FileService.getFilepathsFromFolder(path, extension);
            filepaths.forEach(FileService::readFile);
            
            Thread.sleep(awaitTime); 
        }

    }
}
