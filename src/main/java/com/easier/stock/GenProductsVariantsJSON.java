package com.easier.stock;



import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.apache.poi.util.SystemOutLogger;

import javax.json.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenProductsVariantsJSON {

    String pathToJsonFolder;  // = ;

    public GenProductsVariantsJSON(String pathToJsonFolder) {
        this.pathToJsonFolder = pathToJsonFolder;
    }

    public synchronized  void process() throws IOException, SQLException {

        ProductsVariants productsVariants = new ProductsVariants();
        ResultSet rs = productsVariants.getProductsVariants();



        while (rs.next()) {
            String productCode = rs.getString(3);
            System.out.println("JSON generate = "+productCode);
            String pathToFile = pathToJsonFolder+productCode+".json";


            JsonArrayBuilder builder = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("productCode", rs.getString(3))
                            .add("quantity", rs.getString(4))
                            .add("size", rs.getString(5))
                            .add("color", rs.getString(6))
                            .add("price", rs.getString(7))
                            .add("barCode", rs.getString(8))
                            .add("producer", rs.getString(9))
                            .add("sklad", rs.getString(10)));

            JsonArray jsonArrayExistance =  getExistanceOfFileJson(pathToFile);

            if(jsonArrayExistance != null) {
                for (int i = 0; i < jsonArrayExistance.size(); i++) {
                    builder.add(jsonArrayExistance.getJsonObject(i));
                }
            }

            JsonArray jsonArray = builder.build();
            saveAsJsonToFiles(pathToFile, jsonArray);
        }
    }

    private  void saveAsJsonToFiles(String pathToFile, JsonArray jsonArray ) {
        FileWriter file = null;
        try  {
            file = new FileWriter(pathToFile.trim());

            JsonWriter jsonWriter = Json.createWriter(file);
            jsonWriter.writeArray(jsonArray);
            jsonWriter.close();

            System.out.println("Successfully Copied JSON Object to File...");
            // System.out.println("\nJSON Object: " + obj);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JsonArray getExistanceOfFileJson(String pathToFile) throws FileNotFoundException {
        File f = new File(pathToFile);
        JsonArray jsonArrayExistance = null;
        if(f.exists()) {
            InputStream fis = new FileInputStream(pathToFile);
            JsonReader reader = Json.createReader(fis);
            jsonArrayExistance  = reader.readArray();
            reader.close();
            System.out.println("------------- Size of "+pathToFile+" = "+jsonArrayExistance.size());
        }
        return  jsonArrayExistance;
    }

    public void purgeDirectory(File dir) {
        for (File file: dir.listFiles()) {
            //if (file.isDirectory()) purgeDirectory(file);
            file.delete();
        }
    }

}
