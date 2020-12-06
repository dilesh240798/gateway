package com.scb.heisenberg.heisenburgers.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.scb.heisenberg.heisenburgers.models.RM;

public class RmRowMapper implements RowMapper<RM> {

	@Override
	public RM mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RM rm = new RM();
		rm.setId(rs.getLong("rm_id"));
		rm.setRmName(rs.getString("rm_name"));
		rm.setRmtlname(rs.getString("rmtl_name"));
		rm.setRmtlId(rs.getLong("rmtl_id"));
		rm.setEmail(rs.getString("email"));
		rm.setContact(rs.getLong("contact"));
		rm.setPassword(rs.getString("password"));
		rm.setToken(rs.getString("token"));
		rm.setRole(rs.getString("role"));
		return rm;
	}

}
