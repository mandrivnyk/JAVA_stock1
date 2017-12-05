package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Updater {

    List<XSSFRow> data;
    Connection conn;


    public Updater(List<XSSFRow> data) throws SQLException {
        this.data = data;
        this.conn = new ConnectionDB(1).getConn();
    }

    public void process() throws SQLException {
        ProductsVariants productsVariants = new ProductsVariants();
        for (XSSFRow row : data) {

            //System.out.println(item.getMake() + " " + item.getReg());
//            if(row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING){
//                String productCode = row.getCell(0).toString();
//                System.out.println("productCode : " + productCode);
//
//            }
//            else
            if(row != null && row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
//                Double doubleProductCode = row.getCell(0).getNumericCellValue();
//                System.out.println("productCode : " + String.format("%.0f",doubleProductCode));

                Double doubleBarCode = row.getCell(6).getNumericCellValue();
                System.out.println("doubleBarCode : " + String.format("%.0f",doubleBarCode));

                //System.out.println("productCode : " + productCode);
                if(row.getCell(0) != null ) {
                    productsVariants.insertRow(row);
                }

            }
        }

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
