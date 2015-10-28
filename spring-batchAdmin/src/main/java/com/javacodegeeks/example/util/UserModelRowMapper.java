package com.javacodegeeks.example.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserModelRowMapper implements RowMapper<UserModel>{

	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {		
		UserModel user = new UserModel();
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setCity(rs.getString("city"));
		user.setId(rs.getString("id"));
		return user;
	}

}
