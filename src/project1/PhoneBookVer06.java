package project1;
import java.util.InputMismatchException;
import java.util.Scanner;

import ver06.MenuItem;
import ver06.PhoneBookManager;
import ver06.MenuSelectException;

public class PhoneBookVer06 implements MenuItem {
	public static void printMenu() {
		System.out.println("선택하세요...");
		System.out.println("1.데이터 입력");
		System.out.println("2.데이터 검색");
		System.out.println("3.데이터 삭제");
		System.out.println("4.주소록 출력");
		System.out.println("5.프로그램 종료");
	}
	public static void main(String[] args) throws MenuSelectException {
		PhoneBookManager handler = 
				new PhoneBookManager();
		
		while(true) {
			printMenu();
			
			System.out.print("선택:");
			
			try {
				Scanner scan = new Scanner(System.in);
				int choice = scan.nextInt();
				
				try {
					if(choice<1||choice>5) {
						MenuSelectException err = new MenuSelectException();
						throw err;
					}
				}
				catch(MenuSelectException e) {
					System.out.println(e.getMessage());
				}
				
				switch(choice) {
				case INPUT:
					handler.dataInput();
					break;
				case SEARCH:
					handler.dataSearch();
					break;
				case DELETE:
					handler.dataDelete();
					break;
				case EXIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				}
			}
			catch(InputMismatchException e) {
				System.out.println("에러,정수를입력하세요");
				continue;
			}
			catch(NullPointerException e) {
				System.out.println("에러,검색결과가 없습니다.");
				continue;
			}
		}//end of while
	}//end of main
}//end of class