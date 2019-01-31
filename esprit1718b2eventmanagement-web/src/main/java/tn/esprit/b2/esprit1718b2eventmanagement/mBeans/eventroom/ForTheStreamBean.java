package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ForTheStreamBean {

	
	private String stramlab ="Stream";
	private boolean toggleStream = false;
	
	
	public void doStream() {
		if(toggleStream)
		{
			stramlab ="Stream";
			toggleStream=false;
		}else {
			stramlab ="Stop Stream";
			toggleStream=true;
		}
		
		
	}


	public boolean isToggleStream() {
		return toggleStream;
	}


	public void setToggleStream(boolean toggleStream) {
		this.toggleStream = toggleStream;
	}


	public String getStramlab() {
		return stramlab;
	}


	public void setStramlab(String stramlab) {
		this.stramlab = stramlab;
	}
}
