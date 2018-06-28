package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SupplierTE extends Supplier implements iSupplier {


    public SupplierTE() {

        supplierBrends.add(Brend.BCA);
        supplierBrends.add(Brend.COMPRESSPORT);
        supplierBrends.add(Brend.CONTOUR);
        supplierBrends.add(Brend.DIAMIR);
        supplierBrends.add(Brend.EDELWEISS);
        supplierBrends.add(Brend.FERRINO);
        supplierBrends.add(Brend.FIRE_MAPLE);
        supplierBrends.add(Brend.FIRST_ASCENT);
        supplierBrends.add(Brend.FRITSCHI);
        supplierBrends.add(Brend.GIPRON);
        supplierBrends.add(Brend.GREEN_HERMIT);
        supplierBrends.add(Brend.KAMA);
        supplierBrends.add(Brend.KOHLA);
        supplierBrends.add(Brend.LOAP);
        supplierBrends.add(Brend.SILVA);
        supplierBrends.add(Brend.TACTICAL_EXTREME);
        supplierBrends.add(Brend.TRAVEL_EXTREME);
    }



    @Override
    public List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException {
        List<Product> list = new ArrayList<>();



        int productNameCell = 2;
        int vendorCodeCell = 1;
        int inStockCell = 4;
        int productColorCell = 3;
        int priceOptCell = 8;
        int priceRRZCell = 10;



        for (XSSFRow row : data) {
            if(row != null && row.getCell(vendorCodeCell) != null && row.getCell(vendorCodeCell).getCellType() == HSSFCell.CELL_TYPE_STRING && row.getCell(priceRRZCell) != null && row.getCell(priceRRZCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();
                product.setName(getAttributeString(row, productNameCell));
                product.setVendorCode(getAttributeString(row, vendorCodeCell));

                String vendorCodeString = getAttributeString(row, vendorCodeCell);
                product.setBarcode(getAttributeString(row, vendorCodeCell));
                product.setProductCode(getProductCode(vendorCodeString));
                product.setColor(getAttributeString(row, productColorCell));
                product.setInStock(getAttributelNumeric(row, inStockCell).intValue());
                product.setPriceOpt(getAttributeDouble(row, priceOptCell));
                product.setPriceRRZ(getAttributeDouble(row, priceRRZCell)*26.3);
//                if(outerExisting.size()>0) {
//                    List<Product> result  = outerExisting.stream()
//                            .filter(a -> Objects.equals(a.getBarcode(), product.getBarcode()))
//                            .collect(Collectors.toList());
//                    if(result.size() > 0) {
//                        if(product.getColor() != null || product.getColor().isEmpty()){
//                            product.setColor(result.get(0).getColor());
//                        }
//                        product.setSize(result.get(0).getMoreInfo());
//                    }
//                }
                list.add(product);
            }
        }

        return list;
    }



    @Override
    public List createListExisting(List<XSSFRow> data) throws SQLException {
        int productNameCell = 2;
        int vendorCodeCell = 1;

        int productColorCell = 4;
        int moreInfoCell = 3;

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

