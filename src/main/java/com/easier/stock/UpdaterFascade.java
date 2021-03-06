package com.easier.stock;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdaterFascade {


    public UpdaterFascade( ) {}

    public void InnerStock(String pathToInnerSkladFile)  throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {

        List<XSSFRow> dataFromFile;
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

    }




    public List<Product> OuterStock(String pathToFile, String nameSupplier, List<Product> outerExisting) throws IOException, SQLException {
        List<XSSFRow> data;
        List<Product> outerStockSupplierList = new ArrayList<Product>();

        ExcelParcer excelParcer = new ExcelParcer();
        int numberOfSheets = excelParcer.getNumberOfSheets(pathToFile);

        CreatorSupplier creatorSupplier = new CreatorSupplier();
        iSupplier supplier = creatorSupplier.create(nameSupplier);

       // for(int i = 0; i< numberOfSheets; i++) {
            try {
                data = excelParcer.readFromExcelNewVersions(pathToFile, 0);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+data.size());

                outerStockSupplierList = supplier.createListStock(data, outerExisting);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        //}
        return  outerStockSupplierList;
    }


    public List<Product> OuterExisting(String pathToFileArr[], String nameSupplier) throws IOException {
        List<XSSFRow> data;
        List<Product> outerExistingSupplierList = new ArrayList<Product>();

        ExcelParcer excelParcer = new ExcelParcer();
        for( String pathToFile : pathToFileArr){
            int numberOfSheets = excelParcer.getNumberOfSheets(pathToFile);

            CreatorSupplier creatorSupplier = new CreatorSupplier();
            iSupplier supplier = creatorSupplier.create(nameSupplier);

            for(int i = 0; i< numberOfSheets; i++) {
                try {
                    data = excelParcer.readFromExcelNewVersions(pathToFile, i);
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+data.size());

                    outerExistingSupplierList.addAll(supplier.createListExisting(data));

                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Result ");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return outerExistingSupplierList;
    }

    public void saveOuterStock(String pathToSupplierStock, String pathToSupplierExisting[], String nameSupplier) throws IOException, SQLException {

        List<Product> outerExisting = new ArrayList<>();
        if(pathToSupplierExisting != null && pathToSupplierExisting.length >0){
            outerExisting = OuterExisting(pathToSupplierExisting, nameSupplier);
        }

        List<Product> outerStock =  OuterStock(pathToSupplierStock, nameSupplier, outerExisting);

        ProductsVariants productsVariants = new ProductsVariants();
        productsVariants.save(outerStock);
        saveNewProducts(outerStock);
        //ProductInStock product

        ProductInStock.setInStockAll(0, 1000, nameSupplier);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
    }



    public void saveNewProducts(List<Product> products) throws SQLException, MalformedURLException {
        for(Product product: products) {
            if( product.isNew()) {
               product.add();
            }
        }
    }



    public void updateProductsFromVariants(int inStockNum, int sortOrder) throws SQLException {

        ProductsVariants productsVariants = new ProductsVariants();
        ResultSet rs = productsVariants.getProductsVariantsUnique();

        while (rs.next()) {
//            Boolean res = rs.getString("barcode").equals("4743131038288");
//            if(res)
//            {
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
//            }
            if(rs.getInt("quantity") > 0) {
                if(rs.getString("product_code").trim().equals("A42")){ // eng
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
                }
                if(rs.getString("product_code").trim().equals("А42")){ // rus
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
                }
                Product product = new ProductInStock();
                String productCode = rs.getString("product_code").trim();
                product.getProduct(productCode);
                if(rs.getString("product_code").trim() == "A42"){
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
                }

//                if(Integer.parseInt(rs.getString("sklad").trim()) == 2){
                    product.setPriceRRZ(rs.getInt("price"));
//                }

                product.setListPrice(0);
                product.setInStock(inStockNum);
                product.setSortOrder(sortOrder);
                product.updateProduct();
            }

        }
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
