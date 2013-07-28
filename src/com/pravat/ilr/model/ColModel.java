/**
 * 
 */
package com.pravat.ilr.model;

/**
 * @author Pravat
 * 
 */
public class ColModel {

	private String index;
	private String name;
	private int width;
	private String align;
	private String searchtype;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public ColModel(String index, String name, int width, String align,
			String searchtype) {
		super();
		this.index = index;
		this.name = name;
		this.width = width;
		this.align = align;
		this.searchtype = searchtype;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getSearchType() {
		return searchtype;
	}

	public void setSearchType(String searchtype) {
		this.searchtype = searchtype;
	}

}
