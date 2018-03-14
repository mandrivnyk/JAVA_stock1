package com.easier.stock;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UpdaterFascade {

    public void UpdaterInnerStock()  throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
        List<XSSFRow> dataFromFile;
//        Connection conn = new ConnectionDB(1).getConn();
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery("SHOW TABLES");
//
//        while (rs.next()) {
//            System.out.println(rs.getString(1));
//        }

        //File file = new File("D:/2017.xlsx");
        String fileName = "D:/Sklad_2017.xlsx";
        String pathToJsonFolder = "D:/JSON/";

        ProductsVariants productsVariants = new ProductsVariants();
        productsVariants.truncateProductsVariants();

        GenProductsVariantsJSON genJson = new GenProductsVariantsJSON(pathToJsonFolder);
        genJson.purgeDirectory(new File(pathToJsonFolder));

        ExcelParcer excelParcer = new ExcelParcer();
        int numberOfSheets = excelParcer.getNumberOfSheets(fileName);


        for(int i = 0; i< numberOfSheets; i++) {
            try {
                dataFromFile = excelParcer.readFromExcelNewVersions(fileName, i);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+dataFromFile.size());

                Updater updater = new Updater(dataFromFile);
                updater.innerStock();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        genJson.process();
    }

    public void  UpdaterPriceLasting() throws SQLException {
        List<XSSFRow> dataFromFile;

        String fileName = "D:/PriceLasting.xlsx";
        ExcelParcer excelParcer = new ExcelParcer();

            try {
                dataFromFile = excelParcer.readFromExcelNewVersions(fileName, 0);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ dataFromFile.size() = "+dataFromFile.size());

                Updater updater = new Updater(dataFromFile);
                updater.priceLasting();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
