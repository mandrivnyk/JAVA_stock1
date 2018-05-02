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

        String pathToInnerSkladFile = "D:/SKLAD/Sklad_2017.xlsx";
        String pathToJsonFolder = "D:/JSON/";
        String pathToTerraIncognitaStock = "D:/SKLAD/terra-rest.xlsx";
        String pathToTerraIncognitaExisting = "D:/SKLAD/terra-barcodes.xlsx";
        String pathToTrampStock = "D:/SKLAD/tramp-rest.xlsx";
        String pathToTrampExisting = "D:/SKLAD/tramp-barcodes.xlsx";
        String pathToGorganyStock = "D:/SKLAD/gorgany-rest.xlsx";
        String pathToGorganyExisting = "D:/SKLAD/Lasting-barcodes.xlsx";

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
        updaterFascade.saveOuterStock(pathToTerraIncognitaStock, pathToTerraIncognitaExisting, Supplier.Name.TERRA_INCOGNITA.toString());
        updaterFascade.saveOuterStock(pathToTrampStock, pathToTrampExisting, Supplier.Name.TRAMP.toString());
        updaterFascade.saveOuterStock(pathToGorganyStock, pathToGorganyExisting, Supplier.Name.GORGANY.toString());

        updaterFascade.InnerStock(pathToInnerSkladFile);
        genJson.process();
        updaterFascade.updateProductsFromVariants(50, 1);

        //        updaterFascade.UpdaterPriceLasting();


    }



}



