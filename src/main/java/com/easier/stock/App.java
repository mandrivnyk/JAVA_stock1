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

//        ProductInStock product = new ProductInStock();
//        product.getProduct("alfa_3");
//        product.addBarcodeIntoProduct("test");
//        product.updateProduct();

        UpdaterFascade updaterFascade = new UpdaterFascade();
        ProductsBarcodes productsBarcodes = new ProductsBarcodes();
        productsBarcodes.saveBarcodesFromProductsTable();
        updaterFascade.saveOuterStock(pathToTerraIncognitaStock, pathToTerraIncognitaExisting, "Terra Incognita");

        updaterFascade.InnerStock(pathToInnerSkladFile);
        genJson.process();
        updaterFascade.updateInStockOfProducts(50, 1);





        //        updaterFascade.UpdaterPriceLasting();


    }



}



