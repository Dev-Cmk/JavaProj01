import java.util.Scanner;

import ver03.PhoneBookManager;
import java.util.logging.Handler;

public class PhoneBookVer09 {
	public static void printMenu() {
		System.out.println("선택하세요...");
		System.out.println("1.데이터 입력");
		System.out.println("2.데이터 검색");
		System.out.println("3.데이터 삭제");
		System.out.println("4.주소록 출력");
		System.out.println("5.프로그램 종료");
	}
	public static void main(String[] args) {
		PhoneBookManager handler = 
				new PhoneBookManager();
		
		while(true) {
			printMenu();
			
			System.out.print("선택:");
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:
				handler.dataInput();
				break;
			case 2:
				handler.dataSearch();
				break;
			case 3:
				handler.dataDelete();
				break;
			case 4:
				handler.dataAllShow();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
		
	}
}//end of class
