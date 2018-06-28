package com.easier.stock;


import java.sql.SQLException;

public interface Product {
    String getMoreInfo();

    void setMoreInfo(String moreInfo);

    String getDescription();

    void setDescription(String description);

    String getMetaDescription();

    void setMetaDescription(String metaDescription);

    String getMetaKeywords();

    void setMetaKeywords(String metaKeywords);

    String getTitleOne();

    void setTitleOne(String titleOne);

    String getTitleTwo();

    void setTitleTwo(String titleTwo);

    String getVendorCode();

    void setVendorCode(String vendorCode);


    String getProductCode();

    void setProductCode(String productCode);

    String getUrlName();

    void setUrlName(String urlName);

    int getCategoryID();

    void setCategoryID(int categoryID);

    int getType();

    void setType(int type);

    int getNumTopsale();

    void setNumTopsale(int numTopsale);

    String getName();

    void setNameSupplier(String NameSupplier);
    String getNameSupplier();

    void setName(String name);

    int getInStock();

    void setInStock(int inStock);

    int getEnabled();

    void setEnabled(int enabled);

    boolean isNew();

    void setIsNew(boolean isNew);

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

    double getPriceUE();

    void setPriceUE(double priceUE);

    int getSkidka();

    void setSkidka(int skidka);

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
    void add() throws SQLException;
    static void setInStock(int inStock, String producer){ }
    int getSortOrder();
    void setSortOrder(int sortOrder);
    void checkIfNewProduct();
    String createUrlName();
    String createMetaDescription();
    String createMetaKeywords();
    public void cleanNameFromBrend();
}
