/**
 * 
 */
package com.pravat.ilr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * @author Pravat
 * 
 */
public class DaoImpl {

	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, String>> getData(String[] columns, String query) throws SQLException {

		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Connection conn = dataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map<String, String> aRow = new LinkedHashMap<String, String>();
			for (int i = 0; i < columns.length; i++) {
				aRow.put(columns[i], rs.getString(i+1));
			}
			result.add(aRow);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return result;
	}

	public int getDataCount() throws SQLException {

		int count = 0;

		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("SELECT COUNT(*) FROM EMPLOYEES");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}
}
