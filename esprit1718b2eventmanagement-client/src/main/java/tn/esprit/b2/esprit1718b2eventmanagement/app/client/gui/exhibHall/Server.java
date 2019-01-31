package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import java.io.Serializable;
import java.util.function.Consumer;

public class Server extends NetworkConnection{
  private int port;
	public Server(int port,Consumer<Serializable> onReceiveCallback) {
		super(onReceiveCallback);
		this.port=port;
	}

	@Override
	protected Boolean isServer() {
		
		return true;
	}

	@Override
	protected String getIP() {
		
		return null;
	}

	@Override
	protected int getPort() {

		return port;
	}

}
