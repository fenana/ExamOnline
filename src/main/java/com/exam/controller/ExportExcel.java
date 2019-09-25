package com.exam.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档
 * @param <T> 应用泛型，代表任意一个符合javabean风格的类
 * 注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 * byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> {
	   /**
	    * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	    * @param title  表格标题名
	    * @param headers  表格属性列名数组
	    * @param dataset  需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	    * javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	    * @param out   与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	    * @param pattern  如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	    */

	   @SuppressWarnings("unchecked")

	   public void exportExcel(String title, String[] headers,
	         Collection<T> dataset, OutputStream out, String pattern) {
		   
	      XSSFWorkbook workbook = new XSSFWorkbook();// 声明一个工作薄
	      XSSFSheet sheet = workbook.createSheet(title);// 生成一个表格
	      sheet.setDefaultColumnWidth((short) 15);// 设置表格默认列宽度为15个字节
	      
	      // 生成一个样式并设置---一些无关紧要的已经被删掉了
	      XSSFCellStyle style = workbook.createCellStyle();
	      // 生成并设置另一个样式
	      XSSFCellStyle style2 = workbook.createCellStyle();

	      // 声明一个画图的顶级管理器
	      //HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	 
	      //产生表格标题行
	      XSSFRow row = sheet.createRow(0);
	      for (short i = 0; i < headers.length; i++) {
	         XSSFCell cell = row.createCell(i);
	         cell.setCellStyle(style);
	         XSSFRichTextString text = new XSSFRichTextString(headers[i]);
	         cell.setCellValue(text);
	      }

	      //遍历集合数据，产生数据行
	      Iterator<T> it = dataset.iterator();
	      int index = 0;
	      while (it.hasNext()) {
	         index++;
	         row = sheet.createRow(index);
	         T t = (T) it.next();
	         //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	         Field[] fields = t.getClass().getDeclaredFields();
	         for (short i = 0; i < fields.length; i++) {
	            XSSFCell cell = row.createCell(i);
	            cell.setCellStyle(style2);
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String getMethodName = "get"
	                   + fieldName.substring(0, 1).toUpperCase()
	                   + fieldName.substring(1);
	            try {
	                Class tCls = t.getClass();
	                Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
	                Object value = getMethod.invoke(t, new Object[] {});
	                //判断值的类型后进行强制类型转换
	                String textValue = null;
/*	                if (value instanceof Boolean) {
	                   boolean bValue = (Boolean) value;
	                   textValue = "男";
	                   if (!bValue) {
	                      textValue ="女";
	                   }
	                } else */
	                if (value instanceof Date) {
	                   Date date = (Date) value;
	                   SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                   textValue = sdf.format(date);
	                } else{
	                   //其它数据类型都当作字符串简单处理
	                   textValue = value.toString();
	                }

	                //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
	                if(textValue!=null){
	                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                   Matcher matcher = p.matcher(textValue);
	                   if(matcher.matches()){
	                      //是数字当作double处理
	                      cell.setCellValue(Double.parseDouble(textValue));
	                   }else{
	                      XSSFRichTextString richString = new XSSFRichTextString(textValue);
	                      XSSFFont font3 = workbook.createFont();
	                      font3.setColor(HSSFColor.BLUE.index);
	                      richString.applyFont(font3);
	                      cell.setCellValue(richString);
	                   }
	                }
	            } catch (SecurityException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (NoSuchMethodException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IllegalArgumentException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } finally {
	                //清理资源
	            }
	         }
	      }

	      try {
	         workbook.write(out);
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	}