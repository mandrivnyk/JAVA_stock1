package com.easier.stock;

public class ProductInStock implements Product {
    private String vendorCode;
    private String productCode;
    private String name;
    private int inStock;
    private String size;
    private String color;
    private String moreInfo;
    private String barcode;
    private String brend;
    private double priceRRZ;
    private double priceSpec;
    private double priceOpt;
    private int year;

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
}
