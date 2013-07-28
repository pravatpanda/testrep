/**
 * 
 */
package com.pravat.ilr.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.pravat.ilr.dao.DaoImpl;
import com.pravat.ilr.model.ColModel;
import com.pravat.ilr.model.JsonColModelResponse;
import com.pravat.ilr.model.JsonDataResponse;

/**
 * @author Pravat
 * 
 */
@Controller
public class BaseController {

	@Autowired
	private DaoImpl dao;

	private static String[] columns = { "EMPLOYEE_ID", "FIRST_NAME",
			"LAST_NAME", "PHONE_NUMBER", "HIRE_DATE", "JOB_ID", "SALARY",
			"COMMISSION_PCT", "MANAGER_ID", "DEPARTMENT_ID" };
	private static List<ColModel> colModels = new ArrayList<ColModel>();

	{
		colModels.add(new ColModel("EMPLOYEE_ID", "EMPLOYEE_ID", 100, "center", "integer"));
		colModels.add(new ColModel("FIRST_NAME", "FIRST_NAME", 100, "left", "test"));
		colModels.add(new ColModel("LAST_NAME", "LAST_NAME", 100, "left", "test"));
		colModels
				.add(new ColModel("PHONE_NUMBER", "PHONE_NUMBER", 150, "left", "number"));
		colModels.add(new ColModel("HIRE_DATE", "HIRE_DATE", 150, "left", "data"));
		colModels.add(new ColModel("JOB_ID", "JOB_ID", 100, "left", "number"));
		colModels.add(new ColModel("SALARY", "SALARY", 100, "left", "number"));
		colModels.add(new ColModel("COMMISSION_PCT", "COMMISSION_PCT", 100,
				"left", "number"));
		colModels.add(new ColModel("MANAGER_ID", "MANAGER_ID", 100, "center", "integer"));
		colModels.add(new ColModel("DEPARTMENT_ID", "DEPARTMENT_ID", 100,
				"left", "integer"));
	}

	@RequestMapping("/")
	public String showHomePage() {
		return "index";
	}

	@RequestMapping(value = "/getColData", method = RequestMethod.POST)
	public @ResponseBody
	JsonColModelResponse getcolModel() {

		JsonColModelResponse response = new JsonColModelResponse();
		response.setColModel(colModels);
		response.setColNames(columns);
		return response;
	}

	@RequestMapping("/excel")
	public String generateExcel(Model model) {
		System.out.println("Export to excel...");
		return "excel";
	}
	
	@RequestMapping(value = "/getEntities", method = RequestMethod.POST)
	public @ResponseBody
	JsonDataResponse getcolData(WebRequest req,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer rows,
			@RequestParam(required = false) final String sidx,
			@RequestParam(required = false) final String sord,
			@RequestParam(required = false) final String _search)
			throws SQLException {

		System.out.println("==================================================================");
		
		for(Map.Entry<String, String[]> e : req.getParameterMap().entrySet()){
			System.out.println("Key : " + e.getKey() + "\t\t Value : " + Arrays.asList(e.getValue()));
		}
		

		/*System.out.println("Ajax : " + "rows = " + rows + ", sidx = " + sidx
				+ ", page = " + page + ", sord = " + sord + ", _search = " + _search);*/

		int count = dao.getDataCount();

		int totalPages = 0;
		if (count > 0) {
			totalPages = (int) Math.ceil(count / (double) rows);
		}

		if (page > totalPages) {
			page = totalPages;
		}

		int start = rows * page - rows;

		if (rows > count)
			rows = count;

		int to = rows + start;
		if (to > count) {
			to = count;
		}

		String query = "SELECT * FROM ( SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID, ROW_NUMBER() OVER (ORDER BY "
				+ sidx
				+ " "
				+ sord
				+ " ) RN FROM EMPLOYEES) WHERE RN BETWEEN "
				+ start + " AND " + to + " order by RN";
		
		List<Map<String, String>> result = dao.getData(columns, query);

		JsonDataResponse response = new JsonDataResponse();

		response.setPage(page);
		response.setTotal(totalPages);
		response.setRecords(count);
		response.setResponse(result);

		return response;
	}

}
