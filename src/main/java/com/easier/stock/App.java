package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
        List<XSSFRow> dataFromFile;
        Connection conn = new ConnectionDB(1).getConn();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SHOW TABLES");

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

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
                updater.process();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        genJson.process();




    }



}



