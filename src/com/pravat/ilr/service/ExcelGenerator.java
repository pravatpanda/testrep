/**
 * 
 */
package com.pravat.ilr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.pravat.ilr.dao.DaoImpl;

/**
 * @author Pravat
 * 
 */
public class ExcelGenerator extends AbstractTestExcel {

	private DaoImpl dao;

	@Override
	protected void buildExcelDocument(SXSSFWorkbook workbook,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		String[] columns = { "EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME" };

		Sheet sheet = workbook.createSheet("Sheet 1");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("EMPLOYEE_ID");
		header.createCell(1).setCellValue("FIRST_NAME");
		header.createCell(2).setCellValue("LAST_NAME");

		int count = 100;
		int buffer = 20;
		int counter = 1;
		List<Map<String, String>> result = null;
		String query = null;

		while (count > counter) {
			System.out.println("counter = " + counter);
			query = "SELECT * FROM ( SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME , ROW_NUMBER() OVER (ORDER BY EMPLOYEE_ID ASC) RN FROM EMPLOYEES) WHERE RN BETWEEN "
					+ counter + " AND " + (counter + buffer) + " order by RN";
			result = dao.getData(columns, query);
			System.out.println("result's size = " + result.size());
			for (int i = counter, j = 0; i < counter + result.size(); i++, j++) {
				Map<String, String> aRow = result.get(j);
				Row row = sheet.createRow(i);
				row.createCell(0).setCellValue(aRow.get("EMPLOYEE_ID"));
				row.createCell(1).setCellValue(aRow.get("FIRST_NAME"));
				row.createCell(2).setCellValue(aRow.get("LAST_NAME"));
			}
			counter += buffer;
		}
	}

	public DaoImpl getDao() {
		return dao;
	}

	public void setDao(DaoImpl dao) {
		this.dao = dao;
	}

}
