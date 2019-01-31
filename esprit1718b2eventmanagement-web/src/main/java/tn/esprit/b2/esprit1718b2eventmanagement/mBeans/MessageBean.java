package tn.esprit.b2.esprit1718b2eventmanagement.mBeans;
import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.primefaces.context.RequestContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Message;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.MessageManagerLocal;

@ManagedBean
@SessionScoped
public class MessageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	@ManagedProperty (value = "#{identity}") // this references the @ManagedBean named user
    private Identity identity;
    @EJB
    MessageManagerLocal mm;
 
    private final List<Message> messages;
    private Date lastUpdate;
    private Message message;
    private Message message3;
    private String msg;
    private String reponse;
 
    /**
     * Creates a new instance of MessageBean
     */
    public MessageBean() {
        messages = Collections.synchronizedList(new LinkedList());
        lastUpdate = new Date(0);
        message = new Message();
        String msg;
    }
 
    public Date getLastUpdate() {
        return lastUpdate;
    }
 
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
 
    public Message getMessage() {
        return message;
    }
 
    public void setMessage(Message message) {
        this.message = message;
    }
 
    public void sendMessage(ActionEvent evt) {
     //   reponse=mm.GetReponse(msg);
     //   message.setUser("monta");
      //  message.setMessage(msg);
        mm.sendMessage(message);
    }
  

    public void firstUnreadMessage(ActionEvent evt) {
       RequestContext ctx = RequestContext.getCurrentInstance();
 
       Message m = mm.getFirstAfter(lastUpdate);
 
       ctx.addCallbackParam("ok", m!=null);
       if(m==null)
           return;
       String message2= mm.GetReponse(m.getMessage());  
       lastUpdate = m.getDateSent();
 
       ctx.addCallbackParam("user", identity.getUser().getName());
       ctx.addCallbackParam("dateSent", m.getDateSent().toString());
       ctx.addCallbackParam("text", m.getMessage());
       ctx.addCallbackParam("text2", message2);
    }
    public String Reponse(String msg)
    {
    	String reponse=mm.GetReponse(msg);
		return reponse;
    	
    }

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Message getMessage3() {
		return message3;
	}

	public void setMessage3(Message message3) {
		this.message3 = message3;
	}

	public List getMessages() {
		return messages;
	}
	
}