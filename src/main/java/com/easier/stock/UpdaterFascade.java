package com.easier.stock;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UpdaterFascade {


    public UpdaterFascade( ) {}

    public void InnerStock(String pathToInnerSkladFile)  throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {

        List<XSSFRow> dataFromFile;

//        GenProductsVariantsJSON genJson = new GenProductsVariantsJSON(pathToJsonFolder);

        ExcelParcer excelParcer = new ExcelParcer();
        int numberOfSheets = excelParcer.getNumberOfSheets(pathToInnerSkladFile);

        for(int i = 0; i< numberOfSheets; i++) {
            try {
                dataFromFile = excelParcer.readFromExcelNewVersions(pathToInnerSkladFile, i);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+dataFromFile.size());

                Updater updater = new Updater(dataFromFile);
                updater.innerStock();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

//        genJson.process();
    }




    public List<Product> OuterStock(String pathToFile, String nameSupplier, List<Product> outerExisting) throws IOException, SQLException {
        List<XSSFRow> data;
        List<Product> outerStockSupplierList = new ArrayList<Product>();

//        GenProductsVariantsJSON genJson = new GenProductsVariantsJSON(pathToJsonFolder);

        ExcelParcer excelParcer = new ExcelParcer();
        int numberOfSheets = excelParcer.getNumberOfSheets(pathToFile);

        CreatorSupplier creatorSupplier = new CreatorSupplier();
        Supplier supplier = creatorSupplier.create(nameSupplier);

        for(int i = 0; i< numberOfSheets; i++) {
            try {
                data = excelParcer.readFromExcelNewVersions(pathToFile, i);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+data.size());

                outerStockSupplierList = supplier.createListStock(data, outerExisting);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return  outerStockSupplierList;
    }
    public List<Product> OuterExisting(String pathToFile, String nameSupplier) throws IOException {
        List<XSSFRow> data;
        List<Product> outerExistingSupplierList = new ArrayList<Product>();

//        GenProductsVariantsJSON genJson = new GenProductsVariantsJSON(pathToJsonFolder);

        ExcelParcer excelParcer = new ExcelParcer();
        int numberOfSheets = excelParcer.getNumberOfSheets(pathToFile);

        CreatorSupplier creatorSupplier = new CreatorSupplier();
        Supplier supplier = creatorSupplier.create(nameSupplier);

        for(int i = 0; i< numberOfSheets; i++) {
            try {
                data = excelParcer.readFromExcelNewVersions(pathToFile, i);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+data.size());

                outerExistingSupplierList = supplier.createListExisting(data);
               // List<Sample> list = new ArrayList<Sample>();
//                List<Product> result  = outerExistingSupplierList.stream()
//                        .filter(a -> Objects.equals(a.getBarcode(), 4.823081503293E12))
//                        .collect(Collectors.toList());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Result = ");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return outerExistingSupplierList;
    }

    public void saveOuterStock(String pathToSupplierStock, String pathToSupplierExisting, String nameSupplier) throws IOException, SQLException {
        List<Product> outerExisting = OuterExisting(pathToSupplierExisting, nameSupplier);
        List<Product> outerStock =  OuterStock(pathToSupplierStock, nameSupplier, outerExisting);
        for (Product product:outerStock) {
            ProductsVariants productsVariants = new ProductsVariants();
            productsVariants.saveProductVariant(product);
        }

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock= ");
//
//        CreatorSupplier creatorSupplier = new CreatorSupplier();
//        SupplierTerraIncognita  supplier = (SupplierTerraIncognita) creatorSupplier.create("Terra Incognita");
//        List<Product> outerStockExtended = supplier.createListStockExtended(outerStock, outerExisting);

    }
    public void  UpdaterPriceLasting() throws SQLException {
        List<XSSFRow> dataFromFile;

        String fileName = "D:/PriceLasting.xlsx";
        ExcelParcer excelParcer = new ExcelParcer();

            try {
                dataFromFile = excelParcer.readFromExcelNewVersions(fileName, 0);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+dataFromFile.size());

                Updater updater = new Updater(dataFromFile);
                updater.priceLasting();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
