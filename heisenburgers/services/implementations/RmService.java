package com.scb.heisenberg.heisenburgers.services.implementations;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.scb.heisenberg.heisenburgers.models.RM;
import com.scb.heisenberg.heisenburgers.repositories.interfaces.IRmRepository;
import com.scb.heisenberg.heisenburgers.viewmodel.LoginViewModel;
import com.scb.heisenberg.heisenburgers.viewmodel.RegisterViewModel;
import com.scb.heisenberg.heisenburgers.viewmodel.TokenViewModel;

@Service
public class RmService {
	private final IRmRepository repository;

	public RmService(IRmRepository repository) {
		this.repository = repository;
	}

	public List<RM> getAllMyRm(long rmtlid) throws Exception {
		try {
			return this.repository.getAllMyRm(rmtlid);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public RM getMyData(long rmid) throws Exception {
		try {
			return this.repository.getMyData(rmid);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public RM updateRmProfile(long rmid, RM rm) throws Exception {
		try {
			return this.repository.updateRmProfile(rmid, rm);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public void createNewRm(RegisterViewModel registerViewModel) {
		RM rm = new RM();
		String hashedPassword = Hashing.sha512().hashString(registerViewModel.getPassword(), StandardCharsets.UTF_8)
				.toString();

		rm.setId(registerViewModel.getId());
		rm.setRmtlId(registerViewModel.getRmtlid());
		rm.setRmName(registerViewModel.getRmname());
		rm.setRmtlname(registerViewModel.getRmtlname());
		rm.setEmail(registerViewModel.getEmail());
		rm.setContact(registerViewModel.getContact());
		rm.setPassword(hashedPassword);
		rm.setRole(registerViewModel.getRole());

		this.repository.createNewRm(rm);

	}

	public TokenViewModel login(LoginViewModel loginViewModel) {
		RM rmDb = this.repository.getMyData(loginViewModel.getId());
		String hashedPassword = Hashing.sha512().hashString(loginViewModel.getPassword(), StandardCharsets.UTF_8)
				.toString();

		if (rmDb.getPassword().equals(hashedPassword)) {
			RM loggedInRm = this.repository.login(loginViewModel.getId(), UUID.randomUUID().toString());
			TokenViewModel tokenViewModel = new TokenViewModel();
			tokenViewModel.setId(loggedInRm.getId());
			tokenViewModel.setRmtlid(loggedInRm.getRmtlId());
			tokenViewModel.setRmname(loggedInRm.getRmName());
			tokenViewModel.setRmtlname(loggedInRm.getRmtlname());
			tokenViewModel.setEmail(loggedInRm.getEmail());
			tokenViewModel.setContact(loggedInRm.getContact());
			tokenViewModel.setToken(loggedInRm.getToken());
			tokenViewModel.setRole(loggedInRm.getRole());

			return tokenViewModel;
		} else {
			throw new RuntimeException("Cannot proceed!");
		}
	}

	public void logout(TokenViewModel tokenViewModel) {
		this.repository.logout(tokenViewModel.getId());
	}

}
