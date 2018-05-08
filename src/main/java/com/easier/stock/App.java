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
        String pathToSkladFolder = "D:/__SKLAD/";
        String barcodesString = "barcodes/";
        String pathToInnerSkladFile = pathToSkladFolder + "Sklad_2017.xlsx";
        String pathToJsonFolder = pathToSkladFolder + "JSON/";
        String pathToTerraIncognitaStock = pathToSkladFolder + "terra-rest.xlsx";
        String pathToTerraIncognitaExisting[] = {pathToSkladFolder + barcodesString + "terra-barcodes.xlsx"};
        String pathToTrampStock = pathToSkladFolder + "tramp-rest.xlsx";
        String pathToTrampExisting[] = {pathToSkladFolder + barcodesString + "tramp-barcodes.xlsx"};
        String pathToGorganyStock = pathToSkladFolder + "gorgany-rest.xlsx";
        String pathToGorganyExisting[] = {  pathToSkladFolder + barcodesString + "lasting-barcodes.xlsx",
                                            pathToSkladFolder + barcodesString + "osprey-barcodes.xlsx",
                                            pathToSkladFolder + barcodesString + "salewa-barcodes.xlsx",
                                            pathToSkladFolder + barcodesString + "turbat-barcodes.xlsx",
        };

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
//        updaterFascade.saveOuterStock(pathToTerraIncognitaStock, pathToTerraIncognitaExisting, Supplier.Name.TERRA_INCOGNITA.toString());
//        updaterFascade.saveOuterStock(pathToTrampStock, pathToTrampExisting, Supplier.Name.TRAMP.toString());
        updaterFascade.saveOuterStock(pathToGorganyStock, pathToGorganyExisting, Supplier.Name.GORGANY.toString());

        updaterFascade.InnerStock(pathToInnerSkladFile);
        genJson.process();
        updaterFascade.updateProductsFromVariants(50, 1);

        //        updaterFascade.UpdaterPriceLasting();


    }



}



