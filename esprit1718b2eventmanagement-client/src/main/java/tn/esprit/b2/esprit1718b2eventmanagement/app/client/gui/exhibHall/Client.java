package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import java.io.Serializable;
import java.util.function.Consumer;

public class Client extends NetworkConnection {
    private String ip;
    private int port;
	public Client(String ip,int port ,Consumer<Serializable> onReceiveCallback) {
		super(onReceiveCallback);
		this.ip=ip;
		this.port=port;
	}
	@Override
	protected Boolean isServer() {
		return false;
	}

	@Override
	protected String getIP() {
		return ip;
	}

	@Override
	protected int getPort() {
		return port;
	}

}
