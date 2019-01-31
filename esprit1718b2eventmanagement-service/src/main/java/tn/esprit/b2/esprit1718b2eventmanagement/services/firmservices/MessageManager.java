package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;


import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;


import tn.esprit.b2.esprit1718b2eventmanagement.entities.Message;



/**
 * Session Bean implementation class MessageManager
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)

public class MessageManager implements MessageManagerRemote, MessageManagerLocal {

    /**
     * Default constructor. 
     */
	private static final boolean TRACE_MODE = false;
	
	   String resourcesPath = getResourcesPath();
	 Bot bot = new Bot("super", resourcesPath);
  Chat chatSession = new Chat(bot);
	
	 private final List <Message> messages =
	            Collections.synchronizedList(new LinkedList());
	 private final List <String> postrep =
	            Collections.synchronizedList(new LinkedList());
	 
	    @Override
	    public void sendMessage(Message msg) {
	        messages.add(msg);
	        msg.setDateSent(new Date());
	        postrep.add(msg.getMessage());
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

		@Override
		public String GetReponse(String textLine) {
			 String resourcesPath = getResourcesPath();
	            System.out.println(resourcesPath);
	           MagicBooleans.trace_mode = TRACE_MODE;
	         //   Bot bot = new Bot("super", resourcesPath);
	           // Chat chatSession = new Chat(bot);
	            bot.brain.nodeStats();
	            
	                if ((textLine == null) || (textLine.length() < 1))
	                    textLine = MagicStrings.null_input;
	                if (textLine.equals("q")) {
	                    System.exit(0);
	                    return "";
	                } else if (textLine.equals("wq")) {
	                    bot.writeQuit();
	                    System.exit(0);
	                    return "";
	                } else {
	                    String request = textLine;
	                    if (MagicBooleans.trace_mode)
	                        System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
	                    String response = chatSession.multisentenceRespond(request);
	                    while (response.contains("&lt;"))
	                        response = response.replace("&lt;", "<");
	                    while (response.contains("&gt;"))
	                        response = response.replace("&gt;", ">");
	                    return response;
	                }
	            
	        
	        
	            
			
			
		}

		@Override
		public String getResourcesPath() {
			 File currDir = new File(".");
		        String path = currDir.getAbsolutePath();
		        path = path.substring(0, path.length() - 2);
		        System.out.println(path);
		        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		        return resourcesPath;
		}

		@Override
		public String getFirstAfter2(Date after) {
			 if(postrep.isEmpty())
		            return null;
		        if(after == null)
		            return postrep.get(0);
		        for(String m : postrep) {
		          
		                return m;
		        }
		        return null;
		}
	    
}
