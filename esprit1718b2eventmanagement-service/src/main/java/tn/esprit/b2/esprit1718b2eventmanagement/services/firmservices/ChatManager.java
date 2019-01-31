package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Message;

/**
 * Session Bean implementation class ChatManager
 */
@Singleton
@Startup

public class ChatManager implements ChatManagerLocal {

	 private final List<Message> messages =
	            Collections.synchronizedList(new LinkedList());;
	 
	    @Override
	    public void sendMessage(Message msg) {
	        messages.add(msg);
	        msg.setDateSent(new Date());
	    }
	 
	    @Override
	    public Message getFirstAfter(Date after) {
	        if(messages.isEmpty())
	            return null;
	        if(after == null)
	            return messages.get(0);
	        for(Message m : messages) {
	            if(m.getDateSent().after(after))
	                return m;
	        }
	        return null;
	    }	

}
