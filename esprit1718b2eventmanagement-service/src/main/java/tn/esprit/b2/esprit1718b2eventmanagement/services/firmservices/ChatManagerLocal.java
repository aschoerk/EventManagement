package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.Date;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Message;

@Local
public interface ChatManagerLocal {

	void sendMessage(Message msg);

	Message getFirstAfter(Date after);

}
