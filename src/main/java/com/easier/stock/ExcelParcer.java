package com.easier.stock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelParcer {

    List<XSSFRow> dataFromFile = new ArrayList();




    public static void readFromExcelOldVersions(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("Craft");
        HSSFRow row = myExcelSheet.getRow(0);

        if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
            String name = row.getCell(0).getStringCellValue();
            System.out.println("name : " + name);
        }

        myExcelBook.close();

    }

    public int getNumberOfSheets(String file) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        return myExcelBook.getNumberOfSheets();
    }

    public List readFromExcelNewVersions(String file, int sheetIndex) throws IOException{
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        dataFromFile.clear();
//        String sheetName = myExcelBook.getSheetName(sheetIndex);
//        XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(sheetIndex);
        System.out.println("getPhysicalNumberOfRows : " + myExcelSheet.getPhysicalNumberOfRows());

        for(int i=0; i< myExcelSheet.getPhysicalNumberOfRows()-1; i++) {
            XSSFRow row = myExcelSheet.getRow(i);
            dataFromFile.add(row);
        }

        //System.out.println(Arrays.toString(RowsList.toArray()));


        myExcelBook.close();
        return  dataFromFile;
    }
}
