package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierEleyus extends Supplier implements iSupplier {


    public SupplierEleyus() {
        supplierBrends.add(Brend.MOUSSON);
        supplierBrends.add(Brend.ELEYUS);
    }



    @Override
    public List createListStock(List<XSSFRow> data, List<Product> outerExisting) throws SQLException, UnsupportedEncodingException {
        List<Product> list = new ArrayList<>();

        int productNameCell = 4;
        int barcodeCell = 10;
        int vendorCodeCell = 1;
        int priceRRZCell = 8;
        int brendCell = 2;
        int colorCell = 5;
        int descriptionCell = 6;

        for (XSSFRow row : data) {
            if(row != null && row.getCell(barcodeCell) != null && row.getCell(barcodeCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC && row.getCell(priceRRZCell) != null && row.getCell(priceRRZCell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                Product product = new ProductInStock();

                //Charset.forName("cp1251").encode(getAttributeString(row, descriptionCell));
                // value = new String(getAttributeString(row, descriptionCell).getBytes("cp1251"));
                product.setDescription("");
                product.setBrend(getAttributeString(row, brendCell));
                product.setName(getAttributeString(row, productNameCell));
                if(!product.getBrend().equals("")) {
                    product.cleanNameFromBrend();
                }



                product.setColor(getAttributeString(row, colorCell));
                product.setVendorCode(getAttributeString(row, vendorCodeCell));

                String barCodeString = getAttributeString(row, barcodeCell);
                product.setBarcode(getAttributeString(row, barcodeCell));
                String prodCode = getProductCode(barCodeString);
//                if(prodCode.equals("1905332")){
//                    System.out.println("here");
//                }
                product.setProductCode(getProductCode(barCodeString));

                product.setInStock(50);
                product.setPriceRRZ(getAttributeDouble(row, priceRRZCell));
                product.checkIfNewProduct();
                if( product.isNew()) {

                    // just id of category named "new products"
//                "(categoryID, url_name, name, producer, description, Price, in_stock," +
//                        " enabled, list_price, product_code, sort_order, " +
//                        " meta_description, meta_keywords " +
//                        "barcodes, type)


                    product.setUrlName(product.createUrlName());
                    product.setCategoryID(635); // just id of category of new products
                    product.setEnabled(1);
                    product.setMetaDescription(product.createMetaDescription());
                    product.setMetaKeywords(product.createMetaKeywords());
                    product.setType(ProductInStock.TYPE_NEW);
                    product.setProductCode(product.getBrend()+"_"+product.getVendorCode());
                    product.setTitleOne("");
                    product.setTitleTwo("");
                    product.setNameSupplier(Supplier.Name.ELEYUS.toString());
                    product.setPriceUE(0);
                    product.setSkidka(0);
                    product.setNumTopsale(0);
                }


                list.add(product);
            }
        }

        return list;
    }







}

