package com.scb.heisenberg.heisenburgers.services.interfaces;

import java.util.List;
import java.util.Map;

import com.scb.heisenberg.heisenburgers.models.Notification;
import com.scb.heisenberg.heisenburgers.viewmodel.NotificationCreateViewModel;

public interface INotificationService {
	
	void uploadNotification(List<NotificationCreateViewModel> notification);
	List<Notification> getClientNotification(long clientId, int month, int year) throws Exception;
	void updateNotification(long id) throws Exception;
	List<Notification> upcommingNotification(long rmid) throws Exception;
	List<Notification> getNotificationByMonth(long rmid) throws Exception;
	List<Notification> getSummary(long rmid) throws Exception;
	void updateNotificationStatus() throws Exception;
	Map<String, Integer> rmTrackingFunctions(long rmid) throws Exception;
}
