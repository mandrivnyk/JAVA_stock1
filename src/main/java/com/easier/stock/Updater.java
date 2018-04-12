package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.*;
import java.util.List;

public class Updater {

    List<XSSFRow> data;
    Connection conn;


    public Updater(List<XSSFRow> data) throws SQLException {
        this.data = data;
        this.conn = ConnectionDB.getInstance().getConn();
    }

    public void innerStock() throws SQLException {
        ProductsVariants productsVariants = new ProductsVariants();

        for (XSSFRow row : data) {
            if(row != null && row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Double doubleBarCode = row.getCell(6).getNumericCellValue();
                System.out.println("doubleBarCode : " + String.format("%.0f",doubleBarCode));

                Double quantity = 0.0;
                if(row.getCell(1) != null && row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    quantity = row.getCell(1).getNumericCellValue();
                }

                if(row.getCell(0) != null && quantity > 0) {
                    productsVariants.insertRow(row);
                }

//                saveBarcodesFromInnerStock(row, "Lasting");
            }
        }
    }

    public void saveBarcodesFromInnerStock(XSSFRow row, String nameSupplier) throws SQLException {
        String productCodeString = "";
        if(row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            Double productCode = row.getCell(0).getNumericCellValue();
            productCodeString = String.format("%.0f", productCode);
        }
        else if(row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
            productCodeString = row.getCell(0).toString();
        }

        String producerString = "";
        if(row.getCell(10) != null && row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_STRING){
            producerString = row.getCell(10).toString();
            System.out.println("producerString : " + producerString);
        }

        String productBarcodeString = "";
        if(row.getCell(11) != null && row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_STRING){
            productBarcodeString = row.getCell(11).toString();
        }

        if(!productCodeString.isEmpty() && !productBarcodeString.isEmpty() && producerString.equals(nameSupplier)) {
            ProductInStock product = new ProductInStock();
            product.getProduct(productCodeString);
            productBarcodeString = productBarcodeString.replaceAll(",", "\r\n");
            product.setBarcode(productBarcodeString);
            product.updateProduct();
        }
    }





    public void priceLasting() throws SQLException {


        //factory.


//        ProductsVariants productsVariants = new ProductsVariants();
//
//        for (XSSFRow row : data) {
//            if(row != null && row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
//                Double doubleBarCode = row.getCell(6).getNumericCellValue();
//                System.out.println("doubleBarCode : " + String.format("%.0f",doubleBarCode));
//
//                if(row.getCell(0) != null ) {
//                    productsVariants.insertRow(row);
//                }
//
//            }
//        }
    }


    public void onlyQuantity() throws SQLException {
        for (XSSFRow row : data) {
            ProductsVariants productsVariants = new ProductsVariants();
            Double doubleBarCode = row.getCell(6).getNumericCellValue();
            ResultSet rs = productsVariants.getProductsVariantsWithBarCode(doubleBarCode);

            while (rs.next()) {

                System.out.println("result = " + rs.getString(1));
                System.out.println("result = " + rs.getString(2));

                // ----- compare size of product -------------------------------------------
                if (row.getCell(2) != null && row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC
                        && row.getCell(1) != null && row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    if (row.getCell(2).toString().equals(rs.getString(5))) {
                        System.out.println("size = " + rs.getString(5));
                        // ----- compare amount in stock  -------------------------------------------
                        Double inStockInFile = row.getCell(1).getNumericCellValue();
                        String inStockInFileString = String.format("%.0f", inStockInFile);
                        System.out.println("inStock in File : " + inStockInFileString);

                        String inDBString = rs.getString(4);
                        System.out.println("in stock in DB = " + inDBString);
                        //                            if(!inStockInFileString.equals(inDBString))
                        //                            {
                        System.out.println("UPDATE DB");
                        productsVariants.updateQuantity(inStockInFileString, doubleBarCode);

                        //                            }
                    }
                }

            }
        }
    }









}
