package com.hotent.Stukq.makeex;  
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.text.DecimalFormat; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.List; 
import java.util.Map; 
import java.util.Set; 
  
import org.apache.poi.hssf.usermodel.HSSFCell; 
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow; 
import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.eclipse.jdt.core.dom.ThisExpression;

import com.fr.report.cell.Cell;
import com.hotent.Stukq.dao.Stukq.XskqbDao;
import com.hotent.Stukq.dao.Stuzh.XskqzhbDao;
import com.hotent.Stukq.model.Stuzh.Xskqzhb;
import com.hotent.Stukq.service.Stukq.XskqbService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
  
public class POIExcel { 
	 int  rows;
	 int columns;
	 
	 private XskqzhbDao xskqzhbDao;
	 int j=0,t=0;
 
    //给定一个sheet，获取整个sheet的数据，每一行包装成一个map，key是行号，value是表格的值。然后把map加入list，这样整个sheet是一个list 
	 private static String getValue(HSSFCell hssfCell) {
		 if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
		 // 返回布尔类型的值
		 return String.valueOf(hssfCell.getBooleanCellValue());
		 } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
		// 返回数值类型的值
		 return String.valueOf(hssfCell.getNumericCellValue());
		 } else {
		 // 返回字符串类型的值
		 return String.valueOf(hssfCell.getStringCellValue());
		 }
	 }

			// 选择一个区域，从startRow+1直到最后一行
	 public  List<Xskqzhb> getallExcelDataBySheet(HSSFSheet sheet1,HSSFSheet sheet2) { 
		 List<Xskqzhb> list = new ArrayList<Xskqzhb>(); 
	       try{
	    	   int rows1 = sheet1.getLastRowNum(); 
	        int columns1 = sheet1.getRow(0).getLastCellNum();
	        int rows2 = sheet2.getLastRowNum(); 
	        int columns2 = sheet2.getRow(0).getLastCellNum();
	        System.out.println(rows1);
	        int zrows1,zcolumns1;
	        int zrows2,zcolumns2;
	        HSSFSheet zsheet1;
	        HSSFSheet zsheet2;
	        Xskqzhb[] Xskqzhb=new Xskqzhb[rows1+1];
	        for(int i=0;i<=rows1;i++)
	        {
	        	Xskqzhb[i]=new Xskqzhb();
	        //循环列数 
	        }
	        if(rows1>6)
	        {
	        	zrows1=rows1;
	        	zcolumns1=columns1;
	        	zrows2=rows2;
	        	zcolumns2=columns2;
	        	zsheet1=sheet1;
	        	zsheet2=sheet2;
	        }
	        else {
	        	zrows1=rows2;
	        	zcolumns1=columns2;
	        	zrows2=rows1;
	        	zcolumns2=columns1;
	        	zsheet1=sheet2;
	        	zsheet2=sheet1;
			}
	        for(int i=1;i<zrows1;i=i+3)
	        {
	        	HSSFRow row1 = zsheet1.getRow(i);
	        	HSSFRow row2 = zsheet1.getRow(i+1);
	        	HSSFRow row3 = zsheet1.getRow(i+2);
                HSSFCell xsxm = row1.getCell(6);
        		HSSFCell dysd =row2.getCell(6);
        		HSSFCell qdsj = row3.getCell(6);
        		String c1=getValue(xsxm);
        		if(c1.length()>0){
        		xsxm.setCellValue("03:00");
        		dysd.setCellValue("04:00");
        		qdsj.setCellValue("02:00");
        		}
        		
	        }
	        for(int i=1;i<zrows2;i++)
	        {
	        	for(int j=3;j<6;j++){
	        	HSSFRow row1 = zsheet2.getRow(i);
        		HSSFCell qdsj = row1.getCell(j);
        		String c1=getValue(qdsj);
        		System.out.println(c1);
        		if(c1.length()>5){
        			System.out.println(c1.length());
        			qdsj.setCellValue(c1.substring(0,4));
        		
        		}
	        	}
	        }
	        while(t<zrows2)
	        {
	        	
	        	HSSFRow row1 = zsheet1.getRow(j);
	        	HSSFRow row2 = zsheet2.getRow(t);
	        	HSSFCell xsxm1 = row1.getCell(0);
	        	HSSFCell xsxm2 = row2.getCell(0);
	        	String c1=getValue(xsxm1);
        		System.out.println(c1);
        		String c2=getValue(xsxm2);
        		System.out.println(c2);
        		if(c2.equals(c1)){
        			HSSFRow row = zsheet2.getRow(t); 
            		HSSFCell  sbqjb =row.getCell(3);
            		String cl=getValue(sbqjb);
            		System.out.println(cl);
            		HSSFCell  xbhjb =row.getCell(4);
            		HSSFCell  jbhj =row.getCell(5);
            		Xskqzhb[j].setSbqjb(getValue(sbqjb));
            		Xskqzhb[j].setXbhjb(getValue(xbhjb));
            		Xskqzhb[j].setJbhj(getValue(jbhj));
                    
            		j++;
            		t++;
        		}
        		else {
					j++;
				}
	        	
	        }
	        
	        for(int i = 0; i <=zrows1; i++) 
	        { 
	            
	        	
	                    HSSFRow row = zsheet1.getRow(i);
	                    HSSFCell xsxm = row.getCell(0);
	            		HSSFCell dysd =row.getCell(1);
	            		HSSFCell qdsj = row.getCell(2);
	            		HSSFCell  qtsj =row.getCell(3);
	            		HSSFCell  cdsj =row.getCell(4);
	            		HSSFCell  ztsj =row.getCell(5);
	            		HSSFCell  gzsj =row.getCell(6);
	            		HSSFCell  cqsj =row.getCell(7);
	            		String cString=getValue(gzsj);
	            		System.out.println(cString);
	            		Xskqzhb[i].setXsxm(getValue(xsxm));
	            		Xskqzhb[i].setDysd(getValue(dysd));
	            		Xskqzhb[i].setQdsj(getValue(qdsj));
	            		Xskqzhb[i].setQtsj(getValue(qtsj));
	            		Xskqzhb[i].setCdsj(getValue(cdsj));
	            		Xskqzhb[i].setZtsj(getValue(ztsj));
	            		Xskqzhb[i].setGzsj(getValue(gzsj));
	            		Xskqzhb[i].setCqsj(getValue(cqsj));
	            		list.add(Xskqzhb[i]);
	            		
	        }
	       /*while(t<zrows2)
	        {
	        	
	        	HSSFRow row1 = zsheet1.getRow(j);
	        	HSSFRow row2 = zsheet2.getRow(t);
	        	HSSFCell xsxm1 = row1.getCell(0);
	        	HSSFCell xsxm2 = row2.getCell(0);
	        	String c1=getValue(xsxm1);
        		System.out.println(c1);
        		String c2=getValue(xsxm2);
        		System.out.println(c2);
        		if(c2.equals(c1)){
        			HSSFRow row = zsheet2.getRow(t); 
            		HSSFCell  sbqjb =row.getCell(3);
            		String cl=getValue(sbqjb);
            		System.out.println(cl);
            		HSSFCell  xbhjb =row.getCell(4);
            		HSSFCell  jbhj =row.getCell(5);
            		Xskqzhb[j].setSbqjb(getValue(sbqjb));
            		Xskqzhb[j].setXbhjb(getValue(xbhjb));
            		Xskqzhb[j].setJbhj(getValue(jbhj));
                    
            		j++;
            		t++;
        		}
        		else {
					j++;
				}
	        	
	        }
	      */
	        
	       
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } 
	    return list;
	    
	}

	
	 public static List<Map<Integer,String>> getExcelDataBySheet(HSSFSheet sheet) { 
        List<Map<Integer,String>> table_list = new ArrayList<Map<Integer,String>>(); 
        int rows = sheet.getLastRowNum(); 
        int columns = sheet.getRow(0).getLastCellNum(); 
       
        //循环列数 
        for(int i = 0; i <= rows; i++) 
        { 
            //得到第i行 
            HSSFRow row = sheet.getRow(i); 
            Map<Integer,String> map = new HashMap<Integer,String>(); 
            //循环第i行的数据，从0到columns 
            for(int j = 0; j < columns; j++) 
            { 
                HSSFCell cell = null; 
                try { 
                    //获取cell，如果报异常，说明整个row是空的null，直接在catch里面捕获，并赋值为空 
                    cell = row.getCell(j); 
                } catch (NullPointerException e1) { 
                    map.put(j, ""); 
                    continue; 
                } 
                  
                //如果cell为空 
                if(null == cell) 
                { 
                    map.put(j, ""); 
                    continue; 
                } 
                //获取cell的类型 
                int type = cell.getCellType(); 
                //如果是空白 
                if(type == HSSFCell.CELL_TYPE_BLANK) 
                { 
                    map.put(j, ""); 
                } 
                //如果是数字型 
                else if(type == HSSFCell.CELL_TYPE_NUMERIC) 
                { 
                    //如果cell里面包含E或者e，说明是科学计数法，要用特殊方法处理 
                    if(String.valueOf(cell.getNumericCellValue()).matches(".*[E|e].*")) 
                    { 
                        DecimalFormat df = new DecimalFormat("#.#"); 
                        //指定最长的小数点位为10 
                        df.setMaximumFractionDigits(10); 
                        map.put(j, df.format((cell.getNumericCellValue()))); 
                    } 
                    else
                    { 
                        map.put(j, cell.getNumericCellValue()+""); 
                    } 
                      
                } 
                //如果是字符串 
                else if(type == HSSFCell.CELL_TYPE_STRING) 
                { 
                    map.put(j, cell.getStringCellValue()); 
                } 
                //如果是公式型 
                else if(type == HSSFCell.CELL_TYPE_FORMULA) 
                { 
                    String value; 
                    try { 
                        value = cell.getRichStringCellValue().getString(); 
                        map.put(j,value); 
                    } catch (Exception e) { 
                        value = cell.getNumericCellValue()+""; 
                        map.put(j, value); 
                    } 
                } 
                else
                { 
                    map.put(j, ""); 
                } 
            } 
            table_list.add(map); 
        } 
        return table_list; 
    } 
      
    //给定一个sheet和行号，列号，获取其值 
    public  String getExcelCellData(HSSFSheet sheet,int row,int colnum) { 
          //设计行列大小
         HSSFRow excel_row = sheet.getRow(row); 
        if(excel_row == null) 
            return ""; 
        HSSFCell cell = excel_row.getCell(colnum); 
        if(cell == null) 
            return ""; 
        if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) 
            return ""; 
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
            return cell.getNumericCellValue()+""; 
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
            return cell.getStringCellValue().toString(); 
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) 
        { 
            String value; 
            try { 
                value = cell.getRichStringCellValue().getString(); 
                return value; 
            } catch (Exception e) { 
                value = cell.getNumericCellValue()+""; 
                return value; 
            } 
        } 
        return null; 
    } 
    //写入excel，注意是只写了第一行 
    public static void writeToExcel(String sheetName,String path,Map value,String fileName) throws Exception { 
        HSSFSheet sheet; 
        HSSFRow row; 
        HSSFCell cell; 
        File dir = new File(path); 
        dir.mkdirs(); 
        File file = new File(dir.getCanonicalFile() + "\\" + fileName); 
        if(file.exists()) 
            file.delete(); 
        FileOutputStream fis = new FileOutputStream(file, true); 
        HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(); 
        sheet = hSSFWorkbook.createSheet(sheetName); 
        row = sheet.createRow(0); 
        Iterator l = value.keySet().iterator(); 
        while(l.hasNext()) 
        { 
            String col = (String)l.next(); 
            String cellValue = (String)value.get(col); 
            cell = row.createCell(Integer.parseInt(col)-1); 
            cell.setCellValue(cellValue); 
        } 
  
        hSSFWorkbook.write(fis); 
        fis.close(); 
    } 
      
    /*public static void main(String[] args) throws Exception, IOException { 
        //读取excel 
        HSSFWorkbook work = new HSSFWorkbook(new FileInputStream(new File("c:\\2.xls"))); 
        HSSFSheet sheet = work.getSheetAt(0); 
        System.out.println(getExcelCellData(sheet, 1, 1)); 
        POI_Excel p= new POI_Excel();
        p.getExcelDataBySheet(sheet);
          
//        List<Map<Integer,String>> l = getExcelDataBySheet(sheet); 
//        int size = l.size(); 
//        for(int i = 0; i < size; i++) 
//        { 
//            System.out.println("第"+(i+1)+"行"); 
//            Map<Integer,String> map = (Map<Integer,String>)l.get(i); 
//            Set<Integer> set = map.keySet(); 
//            for(int j : set) 
//            { 
//                System.out.println("第"+(j+1)+"列:"+map.get(j)); 
//            } 
//        } 
//         
//        //把数据写入excel，注意是只写了第一行 
//        Map map = new HashMap(); 
//        map.put("1","wang"); //表示第一行第一列是wang，第一行第二列是gang 
//        map.put("2", "ga"); 
//        writeToExcel("wang","d:\\wang",map,"222.xls"); 
    } */
} 