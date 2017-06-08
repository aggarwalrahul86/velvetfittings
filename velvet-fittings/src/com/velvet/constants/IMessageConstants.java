package com.velvet.constants;

import com.velvet.vo.MessageBean;

public interface IMessageConstants {

	MessageBean MSG_PARTS_UPDATED_SUCCESSFULLY = new MessageBean(MessageBean.CONFIRMATION,"Parts Related Information is updated Successfully.");
	
	MessageBean MSG_PARTS_UPDATION_FAILED = new MessageBean(MessageBean.ERROR,"Parts Related Information updation Failed.");
	
	MessageBean MSG_ALERT_THRESHOLD_BREACHED = new MessageBean(MessageBean.ERROR,"Parts Related Information is updated Successfully.\r\n Alert:Threshold has been breached.Please add the stock in the System.");
	
	MessageBean MSG_UPDATION_FAILED = new MessageBean(MessageBean.ERROR,"Value Cannot be updated due to insuffieient quantity.");
	
	MessageBean MSG_CHECK_AVAILABILITY_FAILED = new MessageBean(MessageBean.ERROR,"Error occured while checking availability.");
	
	MessageBean MSG_PLACE_OREDER_FAILED = new MessageBean(MessageBean.ERROR,"Order could not be placed. Stocks might have been updated.");

	MessageBean MSG_BILL_GENERATED_SUCCESSFULLY = new MessageBean(MessageBean.CONFIRMATION,"Bill Generated Successfully.The Bill number is {0}");
	
	MessageBean MSG_CANCEL_ORDER_FAILED = new MessageBean(MessageBean.ERROR,"Error occured while cancelling order.");
	
	MessageBean MSG_NO_ORDER_FOUND = new MessageBean(MessageBean.ERROR,"No Order found for the selected criteria.");
}
