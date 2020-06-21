package utils;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class Filesutils {

    public static String getJsonFilePath() {
        try {
            String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                    + File.separator + "java" + File.separator + "testData";
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getOutputFolderPath(){
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                    + File.separator + "resources" + File.separator + "output";
        return path;
    }

    public static void deleteFile(String path){
        try {
            Files.deleteIfExists(Paths.get(path));
        }catch(NoSuchFileException e)
        {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Invalid permissions.");
        }
    }
    public static void addCSV(String filePath, List<String[]> data){
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeAll(data);
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }

}
