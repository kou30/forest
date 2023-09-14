package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Sample2_1{
  public static void main(String[] args){
    FileInputStream in = null;
    Workbook wb = null;

    try{
      in = new FileInputStream("excel/sample.xls");
      wb = WorkbookFactory.create(in);
    }catch(IOException e){
      System.out.println(e.toString());
    }catch(InvalidFormatException e){
      System.out.println(e.toString());
    }finally{
      try{
        in.close();
      }catch (IOException e){
        System.out.println(e.toString());
      }
    }

    Sheet sheet = wb.getSheetAt(0);

    Row row = sheet.createRow(1);
    Cell cell = row.createCell(1);
    cell.setCellValue(123);

    FileOutputStream out = null;
    try{
      out = new FileOutputStream("excel/sample2_1.xls");
      wb.write(out);
    }catch(IOException e){
      System.out.println(e.toString());
    }finally{
      try {
        out.close();
      }catch(IOException e){
        System.out.println(e.toString());
      }
    }
  }
}