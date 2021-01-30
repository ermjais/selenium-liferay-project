package testpro.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testpro.beans.MyFormData;
import testpro.util.MyUtilities;

public class ExcelParserPoi {

	public static void main(String[] args) {

		getExcel();
	}

	
	public static MyFormData[] getExcel() {

		FileInputStream fileIs;
		File file = new File("C:\\java_workspace\\testpro_excel.xlsx");
		
		List<MyFormData> formDataList = new ArrayList<MyFormData>();
		try {
			fileIs = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fileIs);

			Sheet sheet = workbook.getSheetAt(0);
			for (int i=sheet.getFirstRowNum(); i<=sheet.getLastRowNum(); i++) {
			//for (Row row : sheet) {
				Row row = sheet.getRow(i);
				
				if(row.getCell(0) == null) {
					continue;
				}
				MyFormData MyFormData = new MyFormData();
				//name
				MyFormData.setName(row.getCell(0).getStringCellValue());
				
				//date
				Date theDate = row.getCell(1).getDateCellValue();
				MyFormData.setDate(MyUtilities.convertToLocalDateViaInstant(theDate));

				//chosen option
				MyFormData.setChosenOption(row.getCell(2).getStringCellValue());
				
				//multiple choice
				int selectedOption = Double.valueOf(row.getCell(5).getNumericCellValue()).intValue();
				if(selectedOption == 1) {
					MyFormData.setOption1(true);
				}else {
					MyFormData.setOption1(false);
				}
				if(selectedOption == 2) {
					MyFormData.setOption2(true);
				}else {
					MyFormData.setOption2(false);
				}
				if(selectedOption == 3) {
					MyFormData.setOption3(true);
				}else {
					MyFormData.setOption3(false);
				}
				
				//file path
				Hyperlink hl = row.getCell(6).getHyperlink();
				String absolutePath = MyUtilities.getAbsolutePath(hl.getAddress(), file.getParentFile());
				
				try {
					absolutePath = java.net.URLDecoder.decode(absolutePath, StandardCharsets.UTF_8.name());
				} catch (UnsupportedEncodingException e) {
				    // not going to happen - value came from JDK's own StandardCharsets
				}
				
				
				MyFormData.setFilePath(absolutePath);
				
				System.out.println(MyFormData.toString());
				System.out.println("---------------------------------------");
				formDataList.add(MyFormData);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		MyFormData allFormData[] = formDataList.stream().toArray(MyFormData[]::new);
		
		
		return allFormData;
	}

}
