package com.scb.heisenberg.heisenburgers.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.heisenberg.heisenburgers.models.Client;
import com.scb.heisenberg.heisenburgers.services.interfaces.IClientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/clients/")
public class ClientController {

	private final IClientService clientService;

	public ClientController(IClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/myclients/{rm}")
	public ResponseEntity<List<Client>> getClients(@PathVariable long rm) {
		try {
			return new ResponseEntity(this.clientService.getClients(rm), HttpStatus.OK);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable long id) {
		try {
			return new ResponseEntity(this.clientService.getClientById(id),HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("birthdays/{rmid}")
	public ResponseEntity<List<Client>> upcomingBirthdays(@PathVariable long rmid){
		try {
			return new ResponseEntity(this.clientService.upcomingBirthdays(rmid), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			return new ResponseEntity(this.clientService.createClient(client), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client client) {
		try {
			return new ResponseEntity(this.clientService.updateClient(id, client),HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}

