package project1;
import ver08.MenuSelectException;
import ver08.PhoneBookManager;

public class PhoneBookVer08 {
	public static void main(String[] args) throws MenuSelectException {
		PhoneBookManager handler = 
				new PhoneBookManager();
		handler.PullData();
		handler.printMenu();
		handler.SaveData();
	}//end of main
}//end of class