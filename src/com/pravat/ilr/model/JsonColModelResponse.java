/**
 * 
 */
package com.pravat.ilr.model;

import java.util.Arrays;

/**
 * @author Pravat
 * 
 */
public class JsonColModelResponse {

	private String[] colNames;
	private Object colModel;

	public String[] getColNames() {
		return colNames;
	}

	public void setColNames(String[] colNames) {
		this.colNames = colNames;
	}

	public Object getColModel() {
		return colModel;
	}

	public void setColModel(Object colModel) {
		this.colModel = colModel;
	}

	@Override
	public String toString() {
		return "JsonColModelResponse [colNames=" + Arrays.toString(colNames)
				+ ", colModel=" + colModel + "]";
	}

}
