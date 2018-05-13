package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierElan extends Supplier implements iSupplier {


    public SupplierElan() {

        supplierBrends.add(Brend.CRAFT);
        supplierBrends.add(Brend.X_BIONIC);
        supplierBrends.add(Brend.X_SOCKS);
        supplierBrends.add(Brend.DAINESE);
        supplierBrends.add(Brend.BOLLE);
        supplierBrends.add(Brend.REUSCH);
        supplierBrends.add(Brend.OAKLEY);
        supplierBrends.add(Brend.ELAN);
        supplierBrends.add(Brend.TREKSTA);
        supplierBrends.add(Brend.DOLOMITE);
        supplierBrends.add(Brend.THE_NORTH_FACE);
    }

    @Override
    public List<String> getSupplierBrends() {

        return supplierBrends;
    }

    @Override
    public List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException {
        List<Product> list = new ArrayList<>();

        int productNameCell = 2;
        int barcodeCell = 1;
        int vendorCodeCell = 0;
        int priceOptCell = 8;
        int priceRRZCell = 10;
        int brendCell = 3;
        int colorCell = 5;
        int sizeCell = 6;
        int seasonCell = 7;
        int inStockCell = 12;

        for (XSSFRow row : data) {
            if(row != null && row.getCell(barcodeCell) != null && row.getCell(barcodeCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC && row.getCell(priceRRZCell) != null && row.getCell(priceRRZCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
                Product product = new ProductInStock();
                product.setName(getAttributeString(row, productNameCell));
                product.setBrend(getAttributeString(row, brendCell));
                product.setColor(getAttributeString(row, colorCell));
                product.setSize(getAttributeString(row, sizeCell));
                product.setVendorCode(getAttributeString(row, vendorCodeCell));

                String barCodeString = getAttributeString(row, barcodeCell);
                product.setBarcode(getAttributeString(row, barcodeCell));
                product.setProductCode(getProductCode(barCodeString));

                product.setInStock(getAttributelNumeric(row, inStockCell).intValue());
                product.setPriceOpt(getAttributeDouble(row, priceOptCell));
                product.setPriceRRZ(getAttributeDouble(row, priceRRZCell));

                list.add(product);
            }
        }

        return list;
    }







}

