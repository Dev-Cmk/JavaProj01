package project1;

import ver07.MenuItem;
import ver07.PhoneBookManager;
import ver07.MenuSelectException;

public class PhoneBookVer07 implements MenuItem {
	public static void main(String[] args) throws MenuSelectException {
		PhoneBookManager handler = new PhoneBookManager();
		handler.printMenu();
	}// end of main
}// end of class