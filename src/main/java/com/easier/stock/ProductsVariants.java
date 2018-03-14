package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.*;

public class ProductsVariants {

    Connection conn;

    public ProductsVariants() throws SQLException {
        this.conn = new ConnectionDB(1).getConn();
    }

    public ResultSet getProductsVariants() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM SS_products_variants ORDER BY product_code ASC");
        return rs;
    }
    public  ResultSet getProductsVariantsWithBarCode(Double barCode) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM SS_products_variants WHERE barcode ='"+String.format("%.0f",barCode)+"'");
        return rs;
    }

    public  void setToZeroAllInStock() throws SQLException {
        String updateString =
                "UPDATE SS_products_variants " +
                        "set quantity = ?";
        PreparedStatement updateProductInStock = conn.prepareStatement(updateString);
        updateProductInStock.setInt(1,  0);
        //updateProductInStock.setString(2, String.format("%.0f",doubleBarCode));
        updateProductInStock.executeUpdate();
    }


    public  void updateQuantity(String inStockInFileString, double doubleBarCode) throws SQLException {
        String updateString =
                "UPDATE SS_products_variants " +
                        "set quantity = ? where barcode = ?";
        PreparedStatement updateProductInStock = conn.prepareStatement(updateString);
        updateProductInStock.setInt(1,  Integer.parseInt(inStockInFileString));
        updateProductInStock.setString(2, String.format("%.0f",doubleBarCode));
        updateProductInStock.executeUpdate();
    }


    public  void truncateProductsVariants() throws SQLException {
        String truncateString = "TRUNCATE TABLE SS_products_variants";
        PreparedStatement truncatesProductsVariants = conn.prepareStatement(truncateString);
        truncatesProductsVariants.executeUpdate();
    }

    public void insertRow(XSSFRow row) throws SQLException {
        //Double doubleBarCode = row.getCell(6).getNumericCellValue();

            String productCodeString = "";
            if(row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Double productCode = row.getCell(0).getNumericCellValue();
                productCodeString = String.format("%.0f", productCode);
            }
            else if(row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
                productCodeString = row.getCell(0).toString();
            }

            String colorString = "";
            if(row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Double color = row.getCell(3).getNumericCellValue();
                colorString = String.format("%.0f", color);
            }
            else if(row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING){
                colorString = row.getCell(3).toString();
                System.out.println("color : " + colorString);
            }

        String producerString = "";
        if(row.getCell(10) != null && row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_STRING){
            producerString = row.getCell(10).toString();
            System.out.println("producerString : " + producerString);
        }

        String sizeString = "";
        if(row.getCell(2) != null && row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            Double size = row.getCell(2).getNumericCellValue();
            sizeString = String.format("%.0f", size);
        }
        else if(row.getCell(2) != null && row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING){
            sizeString = row.getCell(2).toString();
        }

//
//        Double inStockInFile = row.getCell(1).getNumericCellValue();
//        String inStockInFileString = String.format("%.0f", inStockInFile);

        Double price = 0.0;
        if(row.getCell(4) != null && row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            price = row.getCell(4).getNumericCellValue();
        }

        String priceString = String.format("%.0f", price);

        Double quantity = 0.0;
        if(row.getCell(1) != null && row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            quantity = row.getCell(1).getNumericCellValue();
        }

        String quantityString = String.format("%.0f", quantity);
        System.out.println("quantityString  : " + quantityString);





            String insertSQL = "INSERT INTO SS_products_variants (product_code, quantity, size, color, price, barcode,producer, sklad ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
            preparedStatement.setString(1, productCodeString ); //product_code
            preparedStatement.setString(2, quantityString); //quantity
            preparedStatement.setString(3, sizeString); //size
            preparedStatement.setString(4, colorString); //color
            preparedStatement.setString(5, priceString); //price
            preparedStatement.setString(6, row.getCell(6).toString()); // barcode
            preparedStatement.setString(7, producerString); // producer
            preparedStatement.setString(8, "1");
            preparedStatement.executeUpdate();


    }





}
