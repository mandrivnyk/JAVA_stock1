package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SupplierTerraIncognita extends Supplier implements iSupplier {




    public SupplierTerraIncognita() {

        supplierBrends.add(Brend.TERRA_INCOGNITA);
        supplierBrends.add(Brend.TREZETA);
        supplierBrends.add(Brend.VASQUE);
        supplierBrends.add(Brend.KAYLAND);
    }


    public List<Product> createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException {
        List<Product> list = new ArrayList<>();

        int productNameCell = 0;
        int barcodeCell = 3;
        int priceOptCell = 9;
        int priceRRZCell = 10;
        int inStockCell = 4;


        for (XSSFRow row : data) {
            if(row != null && row.getCell(barcodeCell) != null && row.getCell(barcodeCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC && row.getCell(priceRRZCell) != null && row.getCell(priceRRZCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();


                product.setName(getAttributeString(row, productNameCell));
                String barCodeString = getAttributeString(row, barcodeCell);
                product.setBarcode(getAttributeString(row, barcodeCell));
                product.setProductCode(getProductCode(barCodeString));


                product.setPriceOpt(getAttributeDouble(row, priceOptCell));
                product.setPriceRRZ(getAttributeDouble(row, priceRRZCell));

                if(outerExisting.size()>0) {
                    List<Product> result  = outerExisting.stream()
                            .filter(a -> Objects.equals(a.getBarcode(), product.getBarcode()))
                            .collect(Collectors.toList());
                    if(result.size() > 0) {
                        product.setColor(result.get(0).getColor());
                        product.setSize(result.get(0).getMoreInfo());
                    }
                }

                int inStock = getAttributelNumeric(row, inStockCell).intValue();
                if(inStock > 0) {
                    product.setInStock(inStock);
                    list.add(product);
                }
            }
        }
        return list;
    }



    public List<Product> createListExisting(List<XSSFRow> data) throws SQLException {
        List<Product> list = new ArrayList<Product>();

        int productNameCell = 0;
        int barcodeCell = 3;
        int moreInfoCell = 2;
        int vendorCodeCell = 4;
        int colorCell = 1;

        for (XSSFRow row : data) {
            if(row != null && row.getCell(barcodeCell) != null && row.getCell(barcodeCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();

                product.setName(getAttributeString(row, productNameCell));
                product.setColor(getAttributeString(row, colorCell));
                product.setBarcode(getAttributeString(row, barcodeCell));
                product.setMoreInfo(getAttributeString(row, moreInfoCell));
                product.setVendorCode(getAttributeString(row, vendorCodeCell));
                list.add(product);
            }
        }

        return list;

    }

}
