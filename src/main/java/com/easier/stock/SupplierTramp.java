package com.easier.stock;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.SQLException;
import java.util.List;

public class SupplierTramp implements Supplier{


    @Override
    public List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException {
        return null;
    }

    @Override
    public List createListExisting(List<XSSFRow> data) throws SQLException {
        return null;
    }

    @Override
    public List<String> getSupplierBrends() {
        return null;
    }
}
