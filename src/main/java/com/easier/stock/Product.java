package com.easier.stock;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Product {
    String getMoreInfo();

    void setMoreInfo(String moreInfo);

    String getVendorCode();

    void setVendorCode(String vendorCode);


    String getProductCode();

    void setProductCode(String productCode);


    String getName();

    void setName(String name);

    int getInStock();

    void setInStock(int inStock);

    String getSize();

    void setSize(String size);

    String getColor();

    void setColor(String color);

    String getBarcode();

    void setBarcode(String barcode);

    String getBrend();

    void setBrend(String brend);

    double getPriceRRZ();

    void setPriceRRZ(double priceRRZ);

    double getListPrice();

    void setListPrice(double listPrice);

    double getPriceSpec();

    void setPriceSpec(double priceSpec);

    double getPriceOpt();

    void setPriceOpt(double priceOpt);

    int getYear();

    void setYear(int year);
    void getProduct(String productCode) throws SQLException;
    void updateProduct() throws SQLException;
    static void setInStock(int inStock, String producer){ }
    int getSortOrder();
    void setSortOrder(int sortOrder);


}
