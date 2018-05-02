package com.easier.stock;

public class CreatorSupplier implements Creator {

    public Supplier create(String name) {

       if(name.equals(Supplier.Name.TERRA_INCOGNITA.toString())) {
           return new SupplierTerraIncognita();
       }
       if(name.equals(Supplier.Name.TRAMP.toString())) {
           return new SupplierTramp();
       }
       if(name.equals(Supplier.Name.GORGANY.toString())) {
           return new SupplierGorgany();
       }
        return null;
    }
}
