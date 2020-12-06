package com.scb.heisenberg.heisenburgers.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.heisenberg.heisenburgers.models.RM;
import com.scb.heisenberg.heisenburgers.services.implementations.RmService;
import com.scb.heisenberg.heisenburgers.viewmodel.LoginViewModel;
import com.scb.heisenberg.heisenburgers.viewmodel.RegisterViewModel;
import com.scb.heisenberg.heisenburgers.viewmodel.TokenViewModel;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/v1/rm")
public class RmController {
	private final RmService rmservice;
	
	public RmController(RmService rmservice) {
		super();
		this.rmservice=rmservice;
	}

	@GetMapping("/myrm/{rmtlid}")
	public ResponseEntity<List<RM>> getAllMyRm(@PathVariable long rmtlid){
		try {
			return new ResponseEntity(this.rmservice.getAllMyRm(rmtlid), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{rmid}")
	public ResponseEntity<RM> getMyData(@PathVariable long rmid) {
		try{
			return new ResponseEntity(this.rmservice.getMyData(rmid), HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{rmid}")
	public ResponseEntity<RM> updateRmProfile(@PathVariable long rmid, @RequestBody RM rm) {
		try{
			return new ResponseEntity(this.rmservice.updateRmProfile(rmid,rm), HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	

	@PostMapping("/register")
	public ResponseEntity createNewRm(@RequestBody RegisterViewModel registerViewModel) {
		try {
			this.rmservice.createNewRm(registerViewModel);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenViewModel> login(@RequestBody LoginViewModel loginViewModel){
		return new ResponseEntity(this.rmservice.login(loginViewModel), HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity logout(@RequestHeader("X-SCB-ID") long id,
			@RequestHeader("X-SCB-Token") String token) {
		TokenViewModel tokenViewModel = new TokenViewModel();
		tokenViewModel.setId(id);
		tokenViewModel.setToken(token);
		this.rmservice.logout(tokenViewModel);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
//	@ExceptionHandler(RuntimeException.class)
//    public ResponseEntity handleGeneralExceptions() {
//        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//    }
	
	
	
	
}
