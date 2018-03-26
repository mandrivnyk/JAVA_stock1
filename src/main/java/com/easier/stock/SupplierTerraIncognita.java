package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SupplierTerraIncognita implements Supplier {

    public List<Product> createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException {
        List<Product> list = new ArrayList<Product>();
        ProductsBarcodes productsBarcodes = new ProductsBarcodes();
        for (XSSFRow row : data) {
            if(row != null && row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC && row.getCell(11) != null && row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();

                if(row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
                    String productNanme = row.getCell(0).toString();
                    product.setName(productNanme);
                }
                if(row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double barcode = row.getCell(3).getNumericCellValue();
                    String barcodeString = String.format("%.0f", barcode);
                    product.setBarcode(barcodeString);
                    try {
                        ResultSet rs = productsBarcodes.getProductCode(barcodeString);
                        if(rs.next()) {
                            String product_code = rs.getString("product_code");
                            product.setProductCode(product_code);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if(row.getCell(4) != null && row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double inStock = row.getCell(4).getNumericCellValue();
                    product.setInStock(inStock.intValue());
                }
                if(row.getCell(10) != null && row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double priceOpt = row.getCell(10).getNumericCellValue();
                    product.setPriceOpt(priceOpt);
                }
                if(row.getCell(11) != null && row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double priceRRZ = row.getCell(11).getNumericCellValue();
                    product.setPriceRRZ(priceRRZ);
                }

                List<Product> result  = outerExisting.stream()
                        .filter(a -> Objects.equals(a.getBarcode(), product.getBarcode()))
                        .collect(Collectors.toList());
                if(result.size() > 0) {
                    product.setColor(result.get(0).getColor());
                    product.setSize(result.get(0).getMoreInfo());
                    product.setVendorCode(result.get(0).getVendorCode());
                }

                list.add(product);
            }
        }

        return list;
    }

    public List<Product> createListExisting(List<XSSFRow> data){
        List<Product> list = new ArrayList<Product>();
        for (XSSFRow row : data) {
            if(row != null && row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();

                if(row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
                    String productNanme = row.getCell(0).toString();
                    product.setName(productNanme);
                }
                if(row.getCell(1) != null && row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING){
                    String productColor = row.getCell(1).toString();
                    product.setColor(productColor);
                }
                if(row.getCell(2) != null && row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING){
                    String productMoreInfo = row.getCell(2).toString();
                    product.setMoreInfo(productMoreInfo);
                }

                if(row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                    Double barcode = row.getCell(3).getNumericCellValue();
                    String barcodeString = String.format("%.0f", barcode);
                    product.setBarcode(barcodeString);
                }


                if(row.getCell(4) != null && row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING){
                    String productColor = row.getCell(4).toString();
                    product.setVendorCode(productColor);
                }
                list.add(product);
            }
        }

        return list;

    }

    public List<Product> createListStockExtended(List<Product> listStock, List<Product> listExisting) {
        for (Product product : listStock) {
            List<Product> result  = listExisting.stream()
                    .filter(a -> Objects.equals(a.getBarcode(), product.getBarcode()))
                    .collect(Collectors.toList());
            if(result.size() > 0) {
                product.setColor(result.get(0).getColor());
                product.setSize(result.get(0).getMoreInfo());
            }
        }

        return listStock;

    }
}