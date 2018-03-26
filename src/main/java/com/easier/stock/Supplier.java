package com.easier.stock;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.SQLException;
import java.util.List;

public interface Supplier {
    List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException;
    List createListExisting(List<XSSFRow> data);
}
