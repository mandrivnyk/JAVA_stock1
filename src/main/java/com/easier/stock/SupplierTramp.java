package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SupplierTramp extends  Supplier implements iSupplier {


    public SupplierTramp() {

        supplierBrends.add(Brend.TRAMP);
        supplierBrends.add(Brend.SOL);
        supplierBrends.add(Brend.HEYSPORT);
        supplierBrends.add(Brend.TOTEM);
        supplierBrends.add(Brend.DESTROYER);
    }



    @Override
    public List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException {
        List<Product> list = new ArrayList<>();

        int productNameCell = 7;
        int barcodeCell = 4;
        int inStockCell = 12;
        int priceOptCell = 9;
        int priceRRZCell = 11;
        int brendCell = 5;

        for (XSSFRow row : data) {
            if(row != null && row.getCell(barcodeCell) != null && row.getCell(barcodeCell).getCellType() == HSSFCell.CELL_TYPE_STRING && row.getCell(priceRRZCell) != null && row.getCell(priceRRZCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();

                product.setName(getAttributeString(row, productNameCell));
                product.setBrend(getAttributeString(row, brendCell));
                String barCodeString = getAttributeString(row, barcodeCell);
                product.setBarcode(getAttributeString(row, barcodeCell));
                product.setProductCode(getProductCode(barCodeString));
                product.setPriceOpt(getAttributeDouble(row, priceOptCell));
                product.setPriceRRZ(getAttributeDouble(row, priceRRZCell));

                if(product.getBarcode().trim().equals("4743131038288")){
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
                }

                if(row.getCell(inStockCell) != null){
                    String inStock = row.getCell(inStockCell).toString().trim();
                    if(inStock != null && !inStock.isEmpty()){
                        product.setInStock(50);
                    }
                    else {
                        product.setInStock(0);
                    }
                }

                if(outerExisting.size()>0) {
                    List<Product> result  = outerExisting.stream()
                            .filter(a -> Objects.equals(a.getBarcode(), product.getBarcode()))
                            .collect(Collectors.toList());
                    if(result.size() > 0) {
                        product.setColor(result.get(0).getColor());
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
        int productNameCell = 6;
        int barcodeCell = 1;
        int productColorCell = 4;
        int moreInfoCell = 5;
        int vendorCodeCell = 0;


        List<Product> list = new ArrayList<Product>();
        for (XSSFRow row : data) {
            if(row != null && row.getCell(barcodeCell) != null && row.getCell(barcodeCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
                Product product = new ProductInStock();

                product.setName(getAttributeString(row, productNameCell));
                product.setColor(getAttributeString(row, productColorCell));
                product.setBarcode(getAttributeString(row, barcodeCell));
                product.setMoreInfo(getAttributeString(row, moreInfoCell));
                product.setVendorCode(getAttributeString(row, vendorCodeCell));

                list.add(product);
            }
        }

        return list;
    }
}

