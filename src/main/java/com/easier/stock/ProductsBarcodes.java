package com.easier.stock;

import java.sql.*;
import java.util.regex.Pattern;

public class ProductsBarcodes {
    Connection conn;
    int id;
    int productID;
    String product_code;
    String barcode;


    public ProductsBarcodes() throws SQLException {
        this.conn = new ConnectionDB(1).getConn();
    }

    public int getId() {
        return id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void save() throws SQLException {
        String insertSQL = "INSERT INTO SS_products_barcodes (productID, product_code, barcode) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
        preparedStatement.setInt(1, getProductID() ); //
        preparedStatement.setString(2, getProduct_code()); //
        preparedStatement.setString(3, getBarcode()); //
        preparedStatement.executeUpdate();
    }

    public ResultSet getAll() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM SS_products_barcodes ORDER BY productID ASC");
        return rs;
    }

    public ResultSet getBarcodes(int productID) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM SS_products_barcodes WHERE productID ='"+productID+"'");
        return rs;
    }

    public ResultSet getProductCode(String barcode) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT product_code FROM SS_products_barcodes WHERE barcode ='"+barcode+"'");
        return rs;
    }


    public ResultSet getProductID(String barcode) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM SS_products_barcodes WHERE barcode ='"+barcode+"'");
        return rs;
    }

    public void genBarcodesFromProducts() throws SQLException {
        truncateProductsBarcodes();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT productId, product_code, barcodes FROM SS_products WHERE barcodes <> '' ORDER BY productID ASC");
        while (rs.next()) {
            int productId = rs.getInt("productId");
            String product_code = rs.getString("product_code");
            String barcodes = rs.getString("barcodes");

            String[] barcodesArray= barcodes.split(Pattern.quote("\r\n"));
            if(barcodesArray.length > 0){
                for (String barcode: barcodesArray) {
                    setProductID(productId);
                    setProduct_code(product_code);
                    setBarcode(barcode);
                    save();
                }
            }
        }
    }

    public  void truncateProductsBarcodes() throws SQLException {
        String truncateString = "TRUNCATE TABLE SS_products_barcodes";
        PreparedStatement truncatesProductsVariants = conn.prepareStatement(truncateString);
        truncatesProductsVariants.executeUpdate();
    }
}
