package ver07;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ver07.MenuSelectException;
import ver07.PhoneBookManager;

public class PhoneBookManager implements MenuItem,SubMenuItem{

	HashSet<PhoneInfo> set = new HashSet<PhoneInfo>();
	
	public void printMenu() throws MenuSelectException {
		while(true) {
			System.out.println("선택하세요...");
			System.out.println("1.데이터 입력");
			System.out.println("2.데이터 검색");
			System.out.println("3.데이터 삭제");
			System.out.println("4.주소록 출력");
			System.out.println("5.프로그램 종료");
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
					dataInput();
					break;
				case SEARCH:
					dataSearch();
					break;
				case DELETE:
					dataDelete();
					break;
				case AllDATA:
					dataAllShow();
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
	}
	
	public void dataInput() throws MenuSelectException {
		try {
			System.out.println("데이터 입력을 시작합니다..");
			System.out.println("1.일반|| 2.동창|| 3.회사");
			System.out.print("선택>>");
			//사용자로부터 친구정보를 입력받기위한 준비
			Scanner scan = new Scanner(System.in);
							
			String iName,iPhone,imajor,iCompany_Name;
			int ilevel;
			int res = scan.nextInt();
			
			if(res<1 || res>3) {
				MenuSelectException e = new MenuSelectException();
				throw e;
			}
			
			if(res==GENERAL) {
			//공통사항 입력받기
			System.out.print("이름:"); iName = scan.next();
			System.out.print("전화번호:"); iPhone = scan.next();
			
			PhoneInfo fri = 
					new PhoneInfo(iName, iPhone);
			
			int opt = 0;
			Iterator<PhoneInfo> itr = set.iterator();
			while(itr.hasNext()) {
				PhoneInfo data = (PhoneInfo) itr.next();
				if(iName.equals(data.name)) {
					System.out.println("동일한이름입력됨,덮어쓰기하시겠습니까?(예:0,아니오:1)");
					opt=scan.nextInt();
				}
				if(opt==0) {
					set.remove(data);
					set.add(fri);
				}
				else if(opt==1){
					printMenu();
				}
			}
				set.add(fri);
				System.out.print("데이터 입력이 완료되었습니다.");	
			}
			
			else if(res==SCHOOL) {
				System.out.print("이름:"); iName = scan.next();
				System.out.print("전화번호:"); iPhone = scan.next();
				System.out.print("전공:"); imajor = scan.next();
				System.out.print("학년:"); ilevel = scan.nextInt();
				PhoneInfo fri = 
						new PhoneSchoolInfo(iName, iPhone,imajor, ilevel);
				
				int opt=1;
				Iterator<PhoneInfo> itr = set.iterator();
				while(itr.hasNext()) {
					PhoneInfo data = (PhoneInfo) itr.next();
					if(iName.equals(data.name)) {
						System.out.println("동일한이름입력됨,덮어쓰기하시겠습니까?(예:0,아니오:1)");
						opt=scan.nextInt();
						if(opt==0) {
							set.remove(data);
							set.add(fri);
						}
						else if(opt==1){
							printMenu();
						}
					}
				}
					set.add(fri);
					System.out.print("데이터 입력이 완료되었습니다.");
			}
			
			else if(res==COMPANY) {
				System.out.print("이름:"); iName = scan.next();
				System.out.print("전화번호:"); iPhone = scan.next();
				System.out.print("회사:"); iCompany_Name = scan.next();
				PhoneInfo fri = 
						new PhoneCompanyInfo(iName, iPhone,iCompany_Name);
				
				int opt=1;
				Iterator<PhoneInfo> itr = set.iterator();
				while(itr.hasNext()) {
					PhoneInfo data = (PhoneInfo) itr.next();
					if(iName.equals(data.name)) {
						System.out.println("동일한이름입력됨,덮어쓰기하시겠습니까?(예:0,아니오:1)");
						opt=scan.nextInt();
						if(opt==0) {
							set.remove(data);
							set.add(fri);
						}
						else if(opt==1){
							printMenu();
						}
					}
				}
					set.add(fri);
					System.out.print("데이터 입력이 완료되었습니다.");
			}
		}
		catch(InputMismatchException e) {
			System.out.println("에러,정수를입력하세요");
			dataInput();
		}
	}
	
	public void dataSearch() {
			System.out.println("데이터 검색을 시작합니다..");
			
			//이름으로 검색
			Scanner sc = new Scanner(System.in);
			System.out.print("검색할이름:");
			String searchName = sc.nextLine();
			boolean searchFlag = false; //검색결과 유무 확인
			
			Iterator<PhoneInfo> itr = set.iterator();
			while(itr.hasNext()) {
				PhoneInfo data = (PhoneInfo) itr.next();
				if(searchName.equals(data.name)) {
					//검색결과가 있다면 플래그를 변경
					searchFlag = true;
					//toString()메소드를 오버라이딩 했으므로 객체를 즉시 출력가능.
					System.out.println(data);
				}
			}
					
			if(searchFlag == true) {
				System.out.println("요청하신 정보를 찾았습니다.");
			}
			else {
				System.out.println("요청하신 정보는 없습니다.");
			}
	}
	
	public void dataDelete() {
		System.out.println("데이터 삭제를 시작합니다..");
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = scan.nextLine();
		
		boolean searchFlag2 = false; //검색결과 유무 확인
		
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) {
			PhoneInfo data = (PhoneInfo) itr.next();
			if(deleteName.equals(data.name)) {
				set.remove(data);
				//검색결과가 있다면 플래그를 변경
				searchFlag2 = true;
				//toString()메소드를 오버라이딩 했으므로 객체를 즉시 출력가능.
				System.out.println(data);
			}
		}
				
		if(searchFlag2 == true) {
			System.out.println("요청하신 정보를 삭제했습니다.");
		}
		else {
			System.out.println("요청하신 정보는 없습니다.");
		}
	}
	
	public void dataAllShow() {
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) {
			PhoneInfo object = (PhoneInfo) itr.next();
				object.showPhoneInfo();
			}
		System.out.println("==전체정보가 출력되었습니다==");
	}
	
}
	
