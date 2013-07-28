package com.pravat.ilr.service;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractTestExcel extends AbstractView {

	private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SXSSFWorkbook workbook;

		workbook = new SXSSFWorkbook(20);
		workbook.setCompressTempFiles(true);
		buildExcelDocument(workbook, request, response);

		response.setContentType(CONTENT_TYPE);
		response.setHeader("Content-Disposition", "filename=excel.xlsx");
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.close();
		System.out.println(workbook.dispose());
	}

	protected abstract void buildExcelDocument(SXSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
