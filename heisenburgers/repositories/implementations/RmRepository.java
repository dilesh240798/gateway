package com.scb.heisenberg.heisenburgers.repositories.implementations;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scb.heisenberg.heisenburgers.models.RM;
import com.scb.heisenberg.heisenburgers.repositories.interfaces.IRmRepository;
import com.scb.heisenberg.heisenburgers.utils.RmRowMapper;
import com.scb.heisenberg.heisenburgers.viewmodel.RegisterViewModel;

@Repository
@Transactional
public class RmRepository implements IRmRepository {
	private final JdbcTemplate jdbcTemplate;

	public RmRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override // To get rms of rmtl through his(rmtl's) id.
	public List<RM> getAllMyRm(long rmtlid) {
		String sql = "SELECT rm_id, rmtl_id, rm_name, rmtl_name, email, contact, password, role, token FROM rm WHERE rmtl_id=?"
				+ "AND rm_id != rmtl_id";
		RowMapper<RM> rowMapper = new RmRowMapper();
		List<RM> rm = this.jdbcTemplate.query(sql, rowMapper, rmtlid);
		return rm;
	}

	@Override // To get the profile of the rm when RMTL clicks on view profile/
	public RM getMyData(long rmid) {
		String sql = "SELECT rm_id, rmtl_id, rm_name, rmtl_name, email, contact, password, role, token FROM rm WHERE rm_id=?";
		RowMapper<RM> rowMapper = new RmRowMapper();
		RM rm = this.jdbcTemplate.queryForObject(sql, rowMapper, rmid);
		return rm;
	}

	@Override
	public RM updateRmProfile(long rmid, RM rm) {
		String sql = "UPDATE rm SET contact=? WHERE rm_id=?";
		this.jdbcTemplate.update(sql, rm.getContact(), rm.getId());
		return rm;
	}

	@Override

	public void createNewRm(RM rm) {
		String sql = "INSERT INTO rm (rm_id, rmtl_id, rm_name, rmtl_name, email, contact, password, \r\n"
				+ "       role) VALUES (?,?,?,?,?,?,?,?)";
		this.jdbcTemplate.update(sql, rm.getId(), rm.getRmtlid(), rm.getRmname(), rm.getRmtlname(), rm.getEmail(),
				rm.getContact(), rm.getPassword(), rm.getRole());
	}

	public RM login(long id, String token) {
		String sql = "UPDATE rm SET token = ? WHERE rm_id=?";
		this.jdbcTemplate.update(sql, token, id);
		return this.getMyData(id);
	}

	public void logout(long id) {
		String sql = "UPDATE rm SET token = '' WHERE rm_id=?";
		this.jdbcTemplate.update(sql, id);
	}

}
