package ver06;

import java.util.InputMismatchException;
import java.util.Scanner;
import ver06.PhoneBookManager;
import ver06.MenuSelectException;

public class PhoneBookManager implements SubMenuItem, MenuItem {

	private PhoneInfo[] myFriends;
	private int numOfFriends;

	public PhoneBookManager() {
		myFriends = new PhoneInfo[100];
		numOfFriends = 0;
	}

	public void printMenu() {
		while (true) {
			System.out.println("선택하세요...");
			System.out.println("1.데이터 입력");
			System.out.println("2.데이터 검색");
			System.out.println("3.데이터 삭제");
			System.out.println("4.주소록 출력");
			System.out.println("5.프로그램 종료");
			try {
				System.out.print("선택:");
				Scanner scan = new Scanner(System.in);
				int choice = scan.nextInt();

				if (choice < 1) {
					MenuSelectException err = new MenuSelectException();
					throw err;
				} else if (choice > 5) {
					MenuSelectException err = new MenuSelectException();
					throw err;
				}

				switch (choice) {
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
			} catch (InputMismatchException e) {
				System.out.println("에러,정수를입력하세요");
				continue;
			} catch (NullPointerException e) {
				System.out.println("에러,검색결과가 없습니다.");
				continue;
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
		} // end of while
	}

	public void dataInput() throws MenuSelectException {
		try {
			System.out.println("데이터 입력을 시작합니다..");
			System.out.println("1.일반|| 2.동창|| 3.회사");
			System.out.print("선택>>");
			// 사용자로부터 친구정보를 입력받기위한 준비
			Scanner scan = new Scanner(System.in);

			String iName, iPhone, imajor, iCompany_Name;
			int ilevel;
			int res = scan.nextInt();

			if (res < 1 || res > 3) {
				MenuSelectException e = new MenuSelectException();
				throw e;
			}

			if (res == GENERAL) {
				// 공통사항 입력받기
				System.out.print("이름:");
				iName = scan.next();
				System.out.print("전화번호:");
				iPhone = scan.next();
				/*
				 * 1.친구정보를 입력받은후... 2.객체배열 0번방에 객체를 저장하고... 3.numOfFriends 변수를 1 증가시킨다.(후위증가)
				 */
				PhoneInfo fri = new PhoneInfo(iName, iPhone);
				myFriends[numOfFriends++] = fri;
				System.out.println("데이터 입력이 완료되었습니다.");
			} else if (res == SCHOOL) {
				System.out.print("이름:");
				iName = scan.next();
				System.out.print("전화번호:");
				iPhone = scan.next();
				System.out.print("전공:");
				imajor = scan.next();
				System.out.print("학년:");
				ilevel = scan.nextInt();
				PhoneInfo fri = new PhoneSchoolInfo(iName, iPhone, imajor, ilevel);
				myFriends[numOfFriends++] = fri;
				System.out.print("데이터 입력이 완료되었습니다.");
			} else if (res == COMPANY) {
				System.out.print("이름:");
				iName = scan.next();
				System.out.print("전화번호:");
				iPhone = scan.next();
				System.out.print("회사:");
				iCompany_Name = scan.next();
				PhoneInfo fri = new PhoneCompanyInfo(iName, iPhone, iCompany_Name);
				myFriends[numOfFriends++] = fri;
				System.out.println("데이터 입력이 완료되었습니다.");
			}
		} catch (InputMismatchException e) {
			System.out.println("에러,정수를입력하세요");
			dataInput();
		}
	}

	public void dataSearch() {
		System.out.println("데이터 검색을 시작합니다..");
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 이름을 입력하세요:");
		String searchName = scan.nextLine();

		for (int i = 0; i < numOfFriends; i++) {

			System.out.println("검색중인이름:" + myFriends[i].name);

			// 검색할 이름과 객체의 이름이 일치하는 경우 모든정보를 출력함
			if (searchName.compareTo(myFriends[i].name) == 0) {
				myFriends[i].showPhoneInfo();

				System.out.println("데이터 검색이 완료되었습니다.");
			} else {
				System.out.println("검색된 결과가 없습니다.");
				NullPointerException e;
			}
		}

	}

	public void dataDelete() {
		System.out.println("데이터 삭제를 시작합니다..");
		Scanner scan = new Scanner(System.in);
		System.out.println("삭제할 이름을 입력하세요:");
		String deleteName = scan.nextLine();

		/*
		 * 배열의 요소중 삭제된 요소의 인덱스값을 저장할 용도의 변수 요소를 삭제한후 빈자리를 채울때 사용할 예정임.
		 */
		int deleteIndex = -1;

		for (int i = 0; i < numOfFriends; i++) {
			if (deleteName.compareTo(myFriends[i].name) == 0) {
				// 요소를 삭제하기 위해 참조값을 null로 변경
				myFriends[i] = null;
				// 삭제된 요소의 인덱스값 저장
				deleteIndex = i;
				// 전체카운트 변수 -1 차감
				numOfFriends--;
			}
		}

		if (deleteIndex == -1) {
			// 검색된 데이터가 없는경우
			System.out.println("==검색된 데이터가 없습니다==");
			System.out.println("==삭제된 데이터가 없습니다==");
			NullPointerException e;
		} else {
			/*
			 * 객체배열에서 검색된 요소를 삭제한후 입력된 위치의 바로뒤 요소를 앞으로 하나씩 교환한다.
			 */
			for (int i = deleteIndex; i < numOfFriends; i++) {
				myFriends[i] = myFriends[i + 1];
			}
			System.out.println("==데이터(" + deleteIndex + "번)가 삭제되었습니다==");
		}
	}

	public void dataAllShow() {
		for (int i = 0; i < numOfFriends; i++) {
			myFriends[i].showPhoneInfo();
		}
		System.out.println("==전체정보가 출력되었습니다==");
	}

}
