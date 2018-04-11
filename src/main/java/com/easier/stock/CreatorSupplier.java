package com.easier.stock;

public class CreatorSupplier implements Creator {

    public Supplier create(String name) {
       if(name.equals(Supplier.SupplierName.TERRA_INCOGNITA)) {
           return new SupplierTerraIncognita();
       }
        return null;
    }
}
