package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SupplierGorgany extends Supplier implements iSupplier {


    public SupplierGorgany() {

        supplierBrends.add(Brend.LASTING);
        supplierBrends.add(Brend.SALEWA);
        supplierBrends.add(Brend.OSPREY);
        supplierBrends.add(Brend.TURBAT);
        supplierBrends.add(Brend.ZAMBERLAN);
        supplierBrends.add(Brend.ESBIT);
        supplierBrends.add(Brend.ALPINE_PRO);
        supplierBrends.add(Brend.TRIMM);
        supplierBrends.add(Brend.BIZIONI);
        supplierBrends.add(Brend.WINDXTREME);
        supplierBrends.add(Brend.EXPED);
        supplierBrends.add(Brend.SKINNERS);
        supplierBrends.add(Brend.DYNAFIT);
        supplierBrends.add(Brend.AUSTRIALPIN);
        supplierBrends.add(Brend.TREKMATES);
        supplierBrends.add(Brend.ROCKTECHNOLOGIES);
    }


    @Override
    public List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException {
        List<Product> list = new ArrayList<>();

        int productNameCell = 1;
        int barcodeCell = 7;
        int vendorCodeCell = 0;
        int priceOptCell = 5;
        int priceRRZCell = 2;
        int brendCell = 3;
        int colorCell = 4;

        for (XSSFRow row : data) {
            if(row != null && row.getCell(vendorCodeCell) != null && row.getCell(vendorCodeCell).getCellType() == HSSFCell.CELL_TYPE_STRING && row.getCell(priceRRZCell) != null && row.getCell(priceRRZCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();
                product.setName(getAttributeString(row, productNameCell));
                product.setBrend(getAttributeString(row, brendCell));
                product.setColor(getAttributeString(row, colorCell));

                String vendorCodeString = getAttributeString(row, vendorCodeCell);
                product.setBarcode(vendorCodeString);
                product.setProductCode(getProductCode(vendorCodeString));
                product.setPriceOpt(getAttributeDouble(row, priceOptCell));
                product.setPriceRRZ(getAttributeDouble(row, priceRRZCell));
                product.setInStock(50);

//                if(product.getBarcode().trim().equals("4743131038288")){
//                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
//                }
//

                if(outerExisting.size()>0) {
                    List<Product> result  = outerExisting.stream()
                            .filter(a -> Objects.equals(a.getBarcode(), product.getBarcode()))
                            .collect(Collectors.toList());
                    if(result.size() > 0) {
                        if(product.getColor() != null || product.getColor().isEmpty()){
                            product.setColor(result.get(0).getColor());
                        }
                        product.setSize(result.get(0).getMoreInfo());
                    }
                }

                list.add(product);
            }
        }

        return list;
    }



    @Override
    public List createListExisting(List<XSSFRow> data) throws SQLException {

        int productNameCell = 1;
        int vendorCodeCell = 0;
        int productColorCell = 3;
        int moreInfoCell = 2;

        List<Product> list = new ArrayList<Product>();
        for (XSSFRow row : data) {
            if(row != null && row.getCell(vendorCodeCell) != null && row.getCell(vendorCodeCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
                Product product = new ProductInStock();
                product.setName(getAttributeString(row, productNameCell));
                product.setColor(getAttributeString(row, productColorCell));
                String vendorCodeString = getAttributeString(row, vendorCodeCell);
                product.setBarcode(vendorCodeString);
                product.setMoreInfo(getAttributeString(row, moreInfoCell));

                list.add(product);
            }
        }
        return list;
    }
}

