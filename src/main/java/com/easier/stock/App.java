package com.easier.stock;


import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.*;
import java.sql.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {

        String pathToInnerSkladFile = "D:/Sklad_2017.xlsx";
        String pathToJsonFolder = "D:/JSON/";
        String pathToTerraIncognitaStock = "D:/terra-rest.xlsx";
        String pathToTerraIncognitaExisting = "D:/terra-barcodes.xlsx";

        ProductsVariants productsVariants = new ProductsVariants();
        productsVariants.truncateProductsVariants();

        GenProductsVariantsJSON genJson = new GenProductsVariantsJSON(pathToJsonFolder);
        genJson.purgeDirectory(new File(pathToJsonFolder));

        UpdaterFascade updaterFascade = new UpdaterFascade();
        ProductsBarcodes productsBarcodes = new ProductsBarcodes();
        productsBarcodes.genBarcodesFromProducts();
        updaterFascade.saveOuterStock(pathToTerraIncognitaStock, pathToTerraIncognitaExisting, "Terra Incognita");




//        List<Product> outerStock =  updaterFascade.OuterStock(pathToTerraIncognitaStock, "Terra Incognita");
//        List<Product> outerExisting = updaterFascade.OuterExisting(pathToTerraIncognitaExisting, "Terra Incognita");
//        CreatorSupplier creatorSupplier = new CreatorSupplier();
//        SupplierTerraIncognita  supplier = (SupplierTerraIncognita) creatorSupplier.create("Terra Incognita");
//        List<Product> outerStockExtended = supplier.createListStockExtended(outerStock, outerExisting);
//        updaterFascade.InnerStock(pathToInnerSkladFile);
//        genJson.process();
//        updaterFascade.UpdaterPriceLasting();


    }



}



