package com.easier.stock;

import java.sql.*;
import java.util.List;
import java.util.regex.Pattern;

public class ProductInStock implements Product {
    private int productID;
    private String vendorCode;
    private String productCode;
    private String name;
    private int inStock;
    private int SortOrder;
    private String size;
    private String color;
    private String moreInfo;
    private String barcode;
    private String brend;
    private double priceRRZ;
    private double priceSpec;
    private double priceOpt;
    private int year;
    public static final int TYPE_NEW   = 1;
    public static final int TYPE_PROKAT   = 2;
    public static final int TYPE_BU   = 3;

    Connection conn;

    public ProductInStock() throws SQLException {
        this.conn = ConnectionDB.getInstance().getConn();
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String getProductCode() {
        if (productCode == null) {
            return "";
        }
        return productCode;
    }

    @Override
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMoreInfo() {
        if (moreInfo == null) {
            return "";
        }
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
    public String getVendorCode() {
        if (vendorCode == null) {
            return "";
        }
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(int sortOrder) {
        SortOrder = sortOrder;
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getSize() {
        if (size == null) {
            return "";
        }
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        if (color == null) {
            return "";
        }
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrend() {
        if (brend == null) {
            return "";
        }
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public double getPriceRRZ() {
        return priceRRZ;
    }

    public void setPriceRRZ(double priceRRZ) {
        this.priceRRZ = priceRRZ;
    }

    public double getPriceSpec() {
        return priceSpec;
    }

    public void setPriceSpec(double priceSpec) {
        this.priceSpec = priceSpec;
    }

    public double getPriceOpt() {
        return priceOpt;
    }

    public void setPriceOpt(double priceOpt) {
        this.priceOpt = priceOpt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void getProduct(String productCode) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM SS_products WHERE product_code ='"+productCode+"'");
        if (rs.first()) {
            setProductID(rs.getInt("productId"));
            setProductCode(rs.getString("product_code"));
            setBarcode(rs.getString("barcodes"));
            setBrend(rs.getString("producer"));
            setName(rs.getString("name"));
            setPriceRRZ(rs.getDouble("Price"));
            setInStock(rs.getInt("in_stock"));
            setSortOrder(rs.getInt("sort_order"));
        }
    }

    public void updateProduct() throws SQLException {
        String updateString =
                "UPDATE SS_products " +
                       "set product_code = ?, barcodes = ?, producer = ?, name = ?, Price = ?, in_stock= ?, sort_order = ?  where productID = ?";
        PreparedStatement updateProductInStock = conn.prepareStatement(updateString);
        updateProductInStock.setString(1,  getProductCode());
        updateProductInStock.setString(2,  getBarcode());
        updateProductInStock.setString(3,  getBrend());
        updateProductInStock.setString(4,  getName());
        updateProductInStock.setDouble(5,  getPriceRRZ());
        updateProductInStock.setInt(6,  getInStock());
        updateProductInStock.setInt(7, getSortOrder());
        updateProductInStock.setInt(8, getProductID());

        updateProductInStock.executeUpdate();
    }

    public static void setInStockAll(int inStock, int sortOrder, String nameSupplier) throws SQLException {
        Connection conn = ConnectionDB.getInstance().getConn();
        CreatorSupplier creatorSupplier = new CreatorSupplier();
        Supplier supplier = creatorSupplier.create(nameSupplier);
        List<String> brends = supplier.getSupplierBrends();

        for (String brend: brends) {
            String updateString =
                    "UPDATE SS_products " +
                            "set in_stock= ?, sort_order = ?  where producer = ? AND type= ?";
            PreparedStatement updateProductInStock = conn.prepareStatement(updateString);
            updateProductInStock.setInt(1,  inStock);
            updateProductInStock.setInt(2,  sortOrder);
            updateProductInStock.setString(3,  brend);
            updateProductInStock.setInt(4,  TYPE_NEW);
            updateProductInStock.executeUpdate();
        }
        

    }

    public void addBarcodeIntoProduct(String barcode) throws SQLException {
        if (getBarcode() != null) {
            setBarcode(barcode+"\r\n"+getBarcode());
        }
        else {
            setBarcode(barcode);
        }
    }
}
