package com.ui.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;



public class ExcelUtil {


    public static Object[][] read(String filePath,String sheetName,int  startRownum,int endRownum,int startCellnum,int endCellnum){
        File in = new File(filePath);
        InputStream iStream = null;
        Object[][] datas=null;
        try {
            iStream = new FileInputStream(in);
            Workbook workbook = WorkbookFactory.create(iStream);
            //拿到一个sheet对象,使用表单名称，避免出错和移动表单出错
            Sheet sheet = workbook.getSheet(sheetName);
            //拿到要操作的Row对象

            datas = new Object[endRownum-startRownum+1][endCellnum-startCellnum+1];
            //循环取出行
            for (int i =startRownum;i<=endRownum;i++){
                Row row = sheet.getRow(i-1);
                //循环拿到要操作的Cell对象.取出列
               for(int j=startCellnum;j<=endCellnum;j++){
                   //获取都cell对象，
                  Cell cell =  row.getCell(j-1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                  //将列设置为字符串类型
                  cell.setCellType(CellType.STRING);
                  //取出当前列的值
                  String value = cell.getStringCellValue();
                  //将取到的值放到数组
                  datas[i-startRownum][j-startCellnum]=value;
               }
            }


            } catch (Exception e) {
                e.printStackTrace();
        }finally {
            if(iStream!=null){
                try {
                    iStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return datas;
        //现获取一个sheetbook对象

        //拿到一个sheet对象
        //拿到要操作的Row对象
        //拿到要操作的Cell对象
    }


    //要读取的文件路径，表单名
    /*
    * @param filePath:要读取的文件路径
    * @param sheetName 表单名
    * @param cellNames  要读取的列名 ，设计为数组，要取哪些列，就读取哪些列
    * 读取标题行数据，然后根绝标题行进行拿数据，并且去掉空行。存放于复合数组中，最终将数据放于二维数组返回
    *
    *
    * */
    public static Object[][] read2(String filePath, String sheetName, String[] cellNames) {
        InputStream iStream=null;
        //Object[][] datas=null;


        //每行放到一个内层list中，多行放到外层list中
        ArrayList<ArrayList<String>> groups=null;
        try{
            iStream = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(iStream);
        Sheet sheet = workbook.getSheet(sheetName);
        //存放列标题和索引
        Map<String,Integer> cellNameAndCellnum = new HashMap<String,Integer>();
        //获取所有的标题数据，以及每个标题所在的索引列
        Row titleRow = sheet.getRow(0);
        //取出表单中列的个数
        int lastCellNum = titleRow.getLastCellNum();
        //System.out.println("lastCellNum="+lastCellNum);


        //循环取出标题行的每一列，即每个标题
        for(int i =0;i<lastCellNum;i++){
            Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            //标题
            String title = cell.getStringCellValue();
            //取出当前标题对应的列索引
            int cellnum = cell.getAddress().getColumn();
            cellNameAndCellnum.put(title,cellnum);

        }
        //获取最后一行的索引
            int lastRownum = sheet.getLastRowNum();
        //datas = new Object[lastRownum][cellNames.length];
            groups = new  ArrayList<ArrayList<String>>();
        for(int i =1;i<=lastRownum;i++){
            ArrayList<String> cellValuesPerRow = new ArrayList<String>();
            Row row = sheet.getRow(i);
            if(isEmpty(row)){
                continue;
            }
            //取出行对应的列数据
            for(int j=0;j<cellNames.length;j++){
                String cellName=cellNames[j].trim();
                //根据列名，从map中获取列索引
                int cellnum = cellNameAndCellnum.get(cellName);
                Cell cell = row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String value = cell.getStringCellValue().trim();
                //将值放到数组中
                //datas[i-1][j]=value;
                cellValuesPerRow.add(value);
            }
            groups.add(cellValuesPerRow);

        }
        //取出所有行（标题行除外）
        }catch (Exception e){
          System.out.println("取出数据出错");
        }finally {
            if(iStream!=null){
                try {
                    iStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    //将list集合中的数据保存到二维数组中返回
       return listToArray(groups);
    }
  //集合转换为数组
    private static Object[][] listToArray(ArrayList<ArrayList<String>> groups) {
        //获取总共的组数
        int size1=groups.size();
        //取出第一组的数据个数
        int size2=groups.get(0).size();
        //声明二维数组
        Object[][] datas=new Object[size1][size2];
        //循环
        for(int i=0;i<size1;i++){
            //取出每一组
            ArrayList<String> group = groups.get(i);
            //循环
            for(int j=0;j<size2;j++){
                //取出每组中的每个数据
                String value =  group.get(j).trim();
                //将数据保存到二维数组中
                datas[i][j]=value;
            }

        }
        //返回二维数组
        return datas;
    }

    private static boolean isEmpty(Row row){
        int lastCellnum = row.getLastCellNum();
        for(int i=0;i<lastCellnum;i++){
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String value = cell.getStringCellValue().trim();
            if(value!=null&&value.trim().length()>0){
                return false;
            }
        }
         return true;
        }


    }





