package project1;

import ver06.PhoneBookManager;
import ver06.MenuSelectException;

public class PhoneBookVer06 {
	public static void main(String[] args) throws MenuSelectException {
		PhoneBookManager handler = new PhoneBookManager();
		handler.printMenu();
	}// end of main
}// end of class