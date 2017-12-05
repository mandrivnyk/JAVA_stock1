package com.easier.stock;

public abstract class Product {
    private int vendorCode;
    private String name;
    private int inStock;
    private String size;
    private String color;
    private int barcode;
    private String brend;
    private double priceRRZ;
    private double priceSpec;
    private double priceOpt;
    private int year;

    public int getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(int vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getName() {
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
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getBrend() {
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
