package com.scb.heisenberg.heisenburgers.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.scb.heisenberg.heisenburgers.models.Client;
import com.scb.heisenberg.heisenburgers.models.RM;

public class ClientRowMapper implements RowMapper<Client> {

	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

		Client client = new Client();
		RM rm = new RM();

		client.setId(rs.getLong("id"));
		client.setName(rs.getString("name"));
		client.setDob(rs.getDate("dob"));
		client.setEmail(rs.getString("email"));
		client.setAddress(rs.getString("address"));
		client.setContact(rs.getLong("contact"));
		client.setCountry(rs.getString("country"));
		client.setNationality(rs.getString("nationality"));
		client.setNetworth(rs.getLong("networth"));
		client.setRating(rs.getInt("rating"));
		client.setClientType(rs.getString("clienttype"));
		client.setIndustry(rs.getString("industry"));
		rm.setId(rs.getLong("rm"));
		client.setRm(rm);

		return client;
	}
}
