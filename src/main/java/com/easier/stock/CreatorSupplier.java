package com.easier.stock;

public class CreatorSupplier implements Creator {

    public Supplier create(String type) {
       if(type.equals("Terra Incognita")) {
           return new SupplierTerraIncognita();
       }
        return null;
    }
}
