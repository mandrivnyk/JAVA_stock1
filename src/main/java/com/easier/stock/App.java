package com.easier.stock;


import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.*;
import java.sql.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
        UpdaterFascade updaterFascade = new UpdaterFascade();
        updaterFascade.UpdaterInnerStock();
//        updaterFascade.UpdaterPriceLasting();


    }



}



