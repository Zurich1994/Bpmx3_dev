
package com.hotent.excelTempData_lc.dao.excelTempData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.fr.third.org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.fr.third.org.apache.poi.hssf.usermodel.HSSFCell;
import com.hotent.core.db.BaseDao;
import com.hotent.excelTempData_lc.model.excelTempData.ExcelsjlsbLc;
import com.hotent.platform.model.system.SysFile;

@Repository
public class ExcelsjlsbLcDao extends BaseDao<ExcelsjlsbLc>
{
	private SysFile sysFile;
	protected static Long i ;
	@Override
	public Class<?> getEntityClass()
	{
		return ExcelsjlsbLc.class;
	}
	 public static String getPostfix(String path) {
	        if (path == null || "".equals(path.trim())) {
	            return null;
	        }
	        if (path.contains(".")) {
	            return path.substring(path.lastIndexOf(".") + 1, path.length());
	        }
	        return null;
	    }
	 
	 public List<ExcelsjlsbLc> readExcel() throws IOException {
			String path = "C:\\data\\web\\admin\\2015\\5\\10000013610000.xlsx";
			if(path == null){
				return null;
			}else{
				
				String postfix = getPostfix(path);
	            if (!"".equals(postfix)) {
	                if ("xls".equals(postfix)) {
	                   
							return readXls(path);
						
	                } else if ("xlsx".equals(postfix)) {
	                    return readXlsx(path);
	                }
	            } else {
	                System.out.println(path +  ": Not the Excel file!");
	            }
			}
			
			return null;
					
		}
	 
	public List<ExcelsjlsbLc> readExcel(SysFile sysFile) throws IOException {
		String path = sysFile.getFilePath();
//		String path = "C:\\data\\web\\admin\\2015\\5\\10000013590000.xlsx";
		if(path == null){
			return null;
		}else{
			
			String postfix = getPostfix(path);
            if (!"".equals(postfix)) {
                if ("xls".equals(postfix)) {
                   
						return readXls(path);
					
                } else if ("xlsx".equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path +  ": Not the Excel file!");
            }
		}
		
		return null;
		
	
		
	}
	private List<ExcelsjlsbLc> readXlsx(String path) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("processing..." + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        ExcelsjlsbLc excelsjlsbLc = null;
        List<ExcelsjlsbLc> list = new ArrayList<ExcelsjlsbLc>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	excelsjlsbLc = new ExcelsjlsbLc();
                    XSSFCell sj = xssfRow.getCell(0);
                    XSSFCell fsl = xssfRow.getCell(1);
                    excelsjlsbLc.setSj(getValue(sj));
                    excelsjlsbLc.setFsl(getValue(fsl));
                    list.add(excelsjlsbLc);
                }
            }
        }
        return list;
	}
	private List<ExcelsjlsbLc> readXls(String path) throws IOException {
		System.out.println("xls processing..." + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        ExcelsjlsbLc excelsjlsbLc = null;
        List<ExcelsjlsbLc> list = new ArrayList<ExcelsjlsbLc>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            com.fr.third.org.apache.poi.hssf.usermodel.HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                com.fr.third.org.apache.poi.hssf.usermodel.HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                	excelsjlsbLc = new ExcelsjlsbLc();
                    com.fr.third.org.apache.poi.hssf.usermodel.HSSFCell sj = hssfRow.getCell(0);
                    com.fr.third.org.apache.poi.hssf.usermodel.HSSFCell fcl = hssfRow.getCell(1);
                    excelsjlsbLc.setId(i++);
                    excelsjlsbLc.setSj(getValue(sj));
                    excelsjlsbLc.setFsl(getValue(fcl));
                   
                    list.add(excelsjlsbLc);
                }
            }
        }
        if (list != null) {
            for (ExcelsjlsbLc e:list) {
                System.out.println("No. : " + excelsjlsbLc.getSj() + ", name : " + e.getFsl()) ;     }
        }
        return list;
	}
	
	private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf((long)xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
	
	 private String getValue(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }

   

}
