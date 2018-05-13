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
                product.setName(getProductNameFromCell(row, productNameCell));

                product.setBrend(getProductBrendFromCell(row, brendCell));

                product.setColor(getProductColorFromCell(row, colorCell));

                if(row.getCell(vendorCodeCell) != null && row.getCell(vendorCodeCell).getCellType() == HSSFCell.CELL_TYPE_STRING){

                    String vendorCodeString = row.getCell(vendorCodeCell).toString();
                    product.setBarcode(vendorCodeString);
                    product.setProductCode(getProductCode(vendorCodeString));
                }

                if(product.getBarcode().trim().equals("4743131038288")){
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  outerStock ");
                }

                product.setInStock(50);

                if(row.getCell(priceOptCell) != null && row.getCell(priceOptCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double priceOpt = row.getCell(priceOptCell).getNumericCellValue();
                    product.setPriceOpt(priceOpt);
                }
                if(row.getCell(priceRRZCell) != null && row.getCell(priceRRZCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double priceRRZ = row.getCell(priceRRZCell).getNumericCellValue();
                    product.setPriceRRZ(priceRRZ);
                }

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

    private String getProductNameFromCell(XSSFRow row, int productNameCell){
        String productName = "";
        if(row.getCell(productNameCell) != null && row.getCell(productNameCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
            productName = row.getCell(productNameCell).toString();
        }
        return productName;
    }

    private String getProductBrendFromCell(XSSFRow row, int brendCell){
        String productBrend = "";
        if(row.getCell(brendCell) != null && row.getCell(brendCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
            productBrend = row.getCell(brendCell).toString();
        }
        return  productBrend;
    }

    private String getProductColorFromCell(XSSFRow row, int productColorCell){
        String colorString = "";
        if(row.getCell(productColorCell) != null && row.getCell(productColorCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            Double color = row.getCell(productColorCell).getNumericCellValue();
            colorString = String.format("%.0f", color);
        }
        else if(row.getCell(productColorCell) != null && row.getCell(productColorCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
            colorString = row.getCell(productColorCell).toString();
        }
        return   colorString;
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

                product.setName(getProductNameFromCell(row, productNameCell));

                product.setColor(getProductColorFromCell(row, productColorCell));

                if(row.getCell(vendorCodeCell) != null && row.getCell(vendorCodeCell).getCellType() == HSSFCell.CELL_TYPE_STRING) {

                    String vendorCodeString = row.getCell(vendorCodeCell).toString();
                    product.setBarcode(vendorCodeString);
                }

                if(row.getCell(moreInfoCell) != null && row.getCell(moreInfoCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double moreInfo = row.getCell(moreInfoCell).getNumericCellValue();
                    String productMoreInfo = String.format("%.0f", moreInfo);
                    product.setMoreInfo(productMoreInfo);
                }
                else if(row.getCell(moreInfoCell) != null && row.getCell(moreInfoCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
                    String productMoreInfo = row.getCell(moreInfoCell).toString();
                    product.setMoreInfo(productMoreInfo);
                }

                list.add(product);
            }
        }

        return list;
    }
}

