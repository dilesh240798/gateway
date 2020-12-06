package com.scb.heisenberg.heisenburgers.repositories.implementations;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scb.heisenberg.heisenburgers.models.Client;
import com.scb.heisenberg.heisenburgers.models.Notification;
import com.scb.heisenberg.heisenburgers.repositories.interfaces.IClientRepository;
import com.scb.heisenberg.heisenburgers.utils.ClientRowMapper;
import com.scb.heisenberg.heisenburgers.utils.NotificationRowMapper;


@Repository
@Transactional
public class ClientRepository implements IClientRepository {

	private final JdbcTemplate jdbcTemplate;

	public ClientRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Client> getClients(long rm) {
		String sql = "SELECT id, name, dob, email, address, contact, country, "
				+ "nationality, networth, rating, clienttype, industry, rm FROM clients where rm=?";
		RowMapper<Client> rowMapper = new ClientRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, rm);
	}
	
	@Override
	public Client getClientById(long id) {
		String sql = "SELECT id, name, dob, email, address, contact, country, "
				+ "nationality, networth, rating, clienttype, industry, rm FROM clients WHERE id=?";
		RowMapper<Client> rowMapper = new ClientRowMapper();
		return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
	}
	
	@Override
	public Client createClient(Client client) {
		String sql = "INSERT INTO clients ( id, name, dob, email, address, contact, "
				+ "country, nationality, networth, rating, clienttype, industry, rm) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
		long id = this.jdbcTemplate.queryForObject(sql, new Object[] {client.getId(), client.getName(), 
				client.getDob(), client.getEmail(), client.getAddress(), client.getContact(), 
				client.getCountry(), client.getNationality(), client.getNetworth(),
				client.getRating(), client.getClientType(), client.getIndustry(),
				client.getRm().getId()}, Integer.class);
		return this.getClientById(id);
	}
	
	@Override
	public Client updateClient(long id, Client client) {
		String sql = "UPDATE clients SET name=?, dob=?, email=?, address=?, contact=?, country=?, "
				+ "nationality=?, networth=?, rating=?, clienttype=?, industry=?, rm=? WHERE id=?";  
		this.jdbcTemplate.update(sql, client.getName(), client.getDob(), client.getEmail(), 
				client.getAddress(), client.getContact(), client.getCountry(), client.getNationality(),
				client.getNetworth(), client.getRating(), client.getClientType(), client.getIndustry(),
				client.getRm().getId(), id);
		return client;
	}
	
	@Override
	public List<Client> upcomingBirthday(long rmid){
		String sql = "select * from clients	where rm=? AND date(date_part('year', current_date)||'-'||date_part('month', dob)||'-'||date_part('day', dob)) between current_date and current_date + interval '7 days'"; 
		RowMapper<Client> rowMapper = new ClientRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, rmid);
	}
}
