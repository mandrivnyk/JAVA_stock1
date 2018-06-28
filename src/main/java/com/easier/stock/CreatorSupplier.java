package com.easier.stock;

public class CreatorSupplier implements Creator {

    public iSupplier create(String name) {

       if(name.equals(Supplier.Name.TERRA_INCOGNITA.toString())) {
           return new SupplierTerraIncognita();
       }
       if(name.equals(Supplier.Name.TRAMP.toString())) {
           return new SupplierTramp();
       }
       if(name.equals(Supplier.Name.GORGANY.toString())) {
           return new SupplierGorgany();
       }
        if(name.equals(Supplier.Name.ELAN.toString())) {
            return new SupplierElan();
        }
        if(name.equals(Supplier.Name.TRAVEL_EXTREME.toString())) {
            return new SupplierTE();
        }
        if(name.equals(Supplier.Name.ELEYUS.toString())) {
            return new SupplierEleyus();
        }
        return null;
    }
}
