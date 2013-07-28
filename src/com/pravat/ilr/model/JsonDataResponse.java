/**
 * 
 */
package com.pravat.ilr.model;


/**
 * @author Pravat
 * 
 */
public class JsonDataResponse {

	private Object response;
	private int page;
	private int total;// total pages
	private int records;// total rows

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "JsonResponse [response=" + response + ", page=" + page
				+ ", total=" + total + ", records=" + records + "]";
	}

}
