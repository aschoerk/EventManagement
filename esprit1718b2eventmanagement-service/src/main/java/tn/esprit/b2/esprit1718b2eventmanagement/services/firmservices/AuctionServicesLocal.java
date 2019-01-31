package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Auction;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface AuctionServicesLocal extends IGenericDAO<Auction>{
String GetServerDate();
Date GetServerDatetypeDate();
LinkedList<Integer> secondsTohms (Integer currSecond);
void StartCountdown(Integer currSecond);
Integer hmsToSecons(Integer h,Integer m,Integer s);
void infoBox(String infoMessage, String titleBar);
void infoBox(String infoMessage, String titleBar, String headerMessage);
void ErrorBox(String msg);
void WarningBox(String msg);
}
