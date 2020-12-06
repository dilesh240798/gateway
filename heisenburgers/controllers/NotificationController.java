package com.scb.heisenberg.heisenburgers.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
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

import com.scb.heisenberg.heisenburgers.models.Notification;
import com.scb.heisenberg.heisenburgers.services.implementations.NotificationService;
import com.scb.heisenberg.heisenburgers.viewmodel.NotificationCreateViewModel;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/notifications")
public class NotificationController {
	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@GetMapping("/{clientId}/{month}/{year}")
	public ResponseEntity<List<Notification>> getClientNotification(@PathVariable long clientId,@PathVariable int month,@PathVariable int year ) { 
		try {
		return new ResponseEntity(this.notificationService.getClientNotification(clientId,month,year),HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} 
    } 
	
	@GetMapping("/summary/{rmid}")
	public ResponseEntity<List<Notification>> getSummary(@PathVariable long rmid){
		try {
			return new ResponseEntity(this.notificationService.getSummary(rmid),HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	} 
		
	@PostMapping
	public void uploadNotification(@RequestBody List<NotificationCreateViewModel> notification) {
        this.notificationService.uploadNotification(notification);
    }
	
	@GetMapping("/{rmid}")
	public ResponseEntity<List<Notification>> getNotificationByMonth(@PathVariable long rmid){
		try {
			return new ResponseEntity(this.notificationService.getNotificationByMonth(rmid),HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity updateNotification(@PathVariable long id ) {
		try {
			this.notificationService.updateNotification(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	} 
	
	@PutMapping("/updateStatus")
	public ResponseEntity updateNotificationStatus( ) {
		try {
			this.notificationService.updateNotificationStatus();
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	} 
	

	@GetMapping("/week/{rmid}")
	public ResponseEntity<List<Notification>> upcommingNotification(@PathVariable long rmid){
		try {
			return new ResponseEntity(this.notificationService.upcommingNotification(rmid),HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/status/{rmid}")
		public ResponseEntity<Map<String,Integer>> rmTrackingFunctions(@PathVariable long rmid){
			try {
				return new ResponseEntity(this.notificationService.rmTrackingFunctions(rmid),HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
