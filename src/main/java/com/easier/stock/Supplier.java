package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Supplier implements iSupplier {

    enum Name {
        TERRA_INCOGNITA{
            public iSupplier create() {
                return new SupplierTerraIncognita();
            }
        },
        GORGANY {
            public iSupplier create() {
                return new SupplierGorgany();
            }
        },
        TRAMP {
            public iSupplier create() {
                return new SupplierTramp();
            }
        },
        SHAMBALA {
            public iSupplier create() {
                return null;
            }
        },
        ELAN {
            public iSupplier create() {
                return new SupplierElan();
            }
        },
        TRAVEL_EXTREME {
            public iSupplier create() {
                return new SupplierTE();
            }
        },
        ELEYUS {
            public iSupplier create() {
                return new SupplierEleyus();
            }
        };

        public static iSupplier create(String name) {
            return  valueOf(name).create();
        }

        public abstract iSupplier create();

    }



    @Override
    public List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException, UnsupportedEncodingException {
        return null;
    }

    @Override
    public List createListExisting(List<XSSFRow> data) throws SQLException {
        return null;
    }

    @Override
    public List<String> getSupplierBrends() {
        return supplierBrends;
    }

    protected Double getAttributeDouble(XSSFRow row, int productAttributeCell){
        return  Double.parseDouble(getAttributeString(row, productAttributeCell));
    }


    protected String getAttributeString(XSSFRow row, int productAttributeCell){
        String AttributeString = "";
        if(row.getCell(productAttributeCell) != null && row.getCell(productAttributeCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            Double attribute = row.getCell(productAttributeCell).getNumericCellValue();
            AttributeString = String.format("%.0f", attribute);
        }
        else if(row.getCell(productAttributeCell) != null && row.getCell(productAttributeCell).getCellType() == HSSFCell.CELL_TYPE_STRING){
            AttributeString = row.getCell(productAttributeCell).toString();
        }
        return   AttributeString;
    }

    protected Double getAttributelNumeric(XSSFRow row, int productAttributeCell){
        Double attribute = 0.0;
        if(row.getCell(productAttributeCell) != null && row.getCell(productAttributeCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            attribute = row.getCell(productAttributeCell).getNumericCellValue();
        }
        return   attribute;
    }



    protected String getProductCode(String barCodeString) throws SQLException {
        ProductsBarcodes productsBarcodes = new ProductsBarcodes();
        String productCode = "";
        try {
            ResultSet rs = productsBarcodes.getProductCode(barCodeString);
            if(rs.next()) {
                productCode = rs.getString("product_code").trim();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCode;
    }
}
