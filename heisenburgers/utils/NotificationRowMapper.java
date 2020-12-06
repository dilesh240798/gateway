package com.scb.heisenberg.heisenburgers.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.scb.heisenberg.heisenburgers.models.Client;
import com.scb.heisenberg.heisenburgers.models.Notification;
import com.scb.heisenberg.heisenburgers.models.RM;


public class NotificationRowMapper implements RowMapper<Notification>{

	@Override
	public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Client client = new Client();
		RM rm= new RM();
		
		Notification notification = new Notification();
		notification.setNotificationId(rs.getInt("notification_id"));
		client.setId(rs.getInt("cif"));
		notification.setClient(client);
		rm.setId(rs.getInt("rm_id"));
		notification.setRm(rm);
		
		notification.setNotificationType(rs.getString("notification_type"));
		notification.setStartDate(rs.getDate("start_date"));
		notification.setDueDate(rs.getDate("due_date"));
		notification.setReviewDate(rs.getDate("review_date"));
		notification.setReviewStatus(rs.getString("review_status"));
		notification.setReviewCompleted(rs.getString("review_completed"));
		
		notification.setDepositID(rs.getInt("deposit_id"));
		notification.setDepositCurrency(rs.getString("deposit_currency"));
		notification.setDepositAmount(rs.getDouble("deposit_amount"));
		notification.setDepositInterestRate(rs.getDouble("deposit_interest_rate"));
		notification.setDepositTenure(rs.getDouble("deposit_tenure"));
		
		notification.setLoanId(rs.getInt("loan_id"));
		notification.setLoanInstallmentAmount(rs.getDouble("loan_installment_amount"));  //name is different
		notification.setLoanAmount(rs.getLong("loan_amount"));
		notification.setLoanCurrency(rs.getString("loan_currency"));
		notification.setLoanInterestRate(rs.getDouble("loan_interest_rate"));
		notification.setLoanTenure(rs.getDouble("loan_tenure"));
		notification.setLoanType(rs.getString("loan_type"));
		notification.setKycDocumentType(rs.getString("kyc_document_type"));
		notification.setKycDoucumentTypeID(rs.getInt("kyc_document_type_id"));
		// TODO Auto-generated method stub
		return notification;
	}

}
