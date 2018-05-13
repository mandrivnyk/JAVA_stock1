package com.easier.stock;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface iSupplier {

    List<String> supplierBrends = new ArrayList<>();
    List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException;
    List createListExisting(List<XSSFRow> data) throws SQLException;
    List<String> getSupplierBrends();
}
