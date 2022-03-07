package com.company;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.nio.channels.FileChannel;
import java.security.Key;
import java.util.*;

public class Main {

    public static  void main(String[] args) throws IOException {
	// write your code here
        Scanner in = new Scanner(System.in);
        Map<String, Integer> warehouse = new HashMap<String, Integer>();
        String key;
        Integer value;
        Integer val;
        Integer dif;
        Integer d = 10;
        Integer amount;
        Workbook wh=new HSSFWorkbook();
        HSSFSheet sheet= (HSSFSheet) wh.createSheet("Отчет");
        HSSFRow row=sheet.createRow(0);
        HSSFRow row1=sheet.createRow(1);
        Cell cell=row.createCell(0);
        cell.setCellValue("Товар");
        Cell cell1=row.createCell(1);
        cell1.setCellValue("Количество");

        while (true) {
            System.out.print("Введите команду: ");
            String command = in.nextLine();
            if (command.equals("exit")) break;
            switch (command) {
                case ("add")://Добавление объекта
                    if (warehouse.size() == 10) {
                        System.out.println("Склад заполнен! Вы не можете больше добавить товар");
                    }
//
                    else {
                        System.out.println("Введите наименование товара: ");
                        key = in.nextLine();
                        warehouse.putIfAbsent(key, 0);
                        value = warehouse.get(key);
                        System.out.println("Введите колличество ");
                        value = in.nextInt();
                        val = warehouse.get(key);
                        amount = val + value;

                        if (amount <= 10) {
                            warehouse.put(key, amount);
                        } else {
                            dif = d - val;
                            System.out.println("Вы ввели количество товара " + key + " большее чем может вместить склад." +
                                    " Вы можете внести не более " + dif + " Повторите попытку.");

                        }
                    }

                    break;

                case ("search")://Просмотр количества товаров объекта
                    System.out.println("Введите наименование товара: ");
                    key = in.nextLine();
                    System.out.println("Колличество " + key + " " + warehouse.get(key));
                    break;

                case ("amount")://количество объектов на складе
                    System.out.println("Обещее колличество наименований на складе: " + warehouse.size());
                    break;

                case ("remove"):// Удаление объектов
                    System.out.println("Введите товар который хотите удалить: ");
                    key = in.nextLine();
                    warehouse.remove(key);
                    break;

                case ("set"):
                    System.out.println("Список видов товаров на складе: " + warehouse.keySet());
                    break;

                case ("report"):
                    int g= warehouse.size();
                    int it=1;
                    for(Map.Entry<String, Integer> entry: warehouse.entrySet()) {
                         Row row3=sheet.createRow(it);
                        // get key
                         String f= entry.getKey();
                        row3.createCell(0).setCellValue(f);

                        // get value
                        Integer v = entry.getValue();
                        row3.createCell(1).setCellValue(warehouse.get(f));
                          it++; }
                        FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\Prakticum\\IdeaProjects\\HWmaven/Doc.xls"));
                   wh.write(outFile);
                    wh.close();
                    System.out.println("Составлен отчет в XL");
                     break;

                      }

            }
        in.close();

        }

    }







