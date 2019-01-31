package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Stateless;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Auction;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class AuctionServices
 */
@Stateless
public class AuctionServices extends GenericDAO<Auction> implements AuctionServicesRemote, AuctionServicesLocal {

	/**
	 * Default constructor.
	 */
	public AuctionServices() {
		// TODO Auto-generated constructor stub
		super(Auction.class);

	}

	private Thread thrd;

	@Override
	public String GetServerDate() {
		Date d1 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		String formattedDate = df.format(d1);
		return (formattedDate);
	}

	@Override
	public Date GetServerDatetypeDate() {
		// TODO Auto-generated method stub
		Date d1 = new Date();
		return d1;
	}

	@Override
	public void StartCountdown(Integer currSecond) {

		thrd = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {

						int currSeconds = currSecond;
						// here c down
						if (currSeconds >= 0) {
							SetOutPutServer(currSeconds);
						}
						Thread.sleep(1000);

						if (currSeconds < 0) {
							thrd.stop();
						}
						currSeconds -= 1;
					}
				} catch (Exception e) {

				}
				// TODO Auto-generated method stub

			}
		});
		thrd.start();
	}

	LinkedList<Integer> SetOutPutServer(Integer currSec) {
		LinkedList<Integer> currHms = secondsTohms(currSec);
		return currHms;

	};

	@Override
	public LinkedList<Integer> secondsTohms(Integer currSecond) {
		Integer Hours = currSecond / 3600;
		currSecond = currSecond % 3600;
		Integer Minuites = currSecond / 60;
		currSecond = currSecond % 60;
		Integer Seconds = currSecond;
		LinkedList<Integer> answer = new LinkedList<>();
		answer.add(Hours + 0);
		answer.add(Minuites + 0);
		answer.add(Seconds + 0);
		return answer;

	}

	@Override
	public Integer hmsToSecons(Integer h, Integer m, Integer s) {
		Integer hToSeconds = h * 3600;
		Integer mToSeconds = m * 60;
		Integer total = hToSeconds + mToSeconds + s;
		return total;

	}

	@Override
	public void infoBox(String infoMessage, String titleBar) {

		infoBox(infoMessage, titleBar, null);
	}

	@Override
	public void infoBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

	@Override
	public void ErrorBox(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	@Override
	public void WarningBox(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	}

