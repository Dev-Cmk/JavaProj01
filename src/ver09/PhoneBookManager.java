package ver09;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ver09.MenuSelectException;

public class PhoneBookManager extends IConnectImpl {
		
	public PhoneBookManager() {
		super("study","1234");
		makeTable();
		makeSequence();
	}
	
	public void printMenu() {
		while(true) {
			System.out.println("선택하세요...");
			System.out.println("1.데이터 입력");
			System.out.println("2.데이터 검색");
			System.out.println("3.데이터 삭제");
			System.out.println("4.주소록출력");
			System.out.println("5.프로그램 종료");
			System.out.print("선택:");
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			
			try {
				try {
					if(choice<1 || choice>5) {
						MenuSelectException menuEx = new MenuSelectException();
						throw menuEx;
					}
				}
				catch(MenuSelectException e) {
					System.out.println("예외메세지:"+ e.getMessage());
				}
				
				switch(choice) {
				case 1:
					dataInput();
					break;
				case 2:
					dataSearch();
					break;
				case 3:
					dataDelete();
					break;
				case 4:
					dataAllShow();
					break;
				case 5:
					close();
					exit();
				}//end of switch
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
	}//end of printMenu
	
	public void makeTable() {
		try {
		System.out.println("테이블 생성");
		String query = "create table phonebook_tb( "
				+ " iName varchar2(100) primary key, "
				+ " iPhone varchar2(100), "
				+ " iBirth varchar2(100)"
				+ ")";
		
		psmt = con.prepareStatement(query);
		psmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
		System.out.println("테이블 생성완료");
	}
	
	public void makeSequence() {
		try {
			System.out.println("시퀀스 생성");
			String query = "create sequence seq_phonebook "
					+ " increment by 1 "
					+ " start with 1 "
					+ " nomaxvalue "
					+ " minvalue 1 "
					+ " nocycle "
					+ " nocache ";
			
			psmt = con.prepareStatement(query);
			psmt.executeUpdate();
			}
			catch(SQLException e) {
				System.out.println("쿼리오류발생");
				e.printStackTrace();
			}
			System.out.println("시퀀스 생성완료");
		}
	
	public void dataInput() {
		try {
			System.out.println("데이터 입력을 시작합니다..");
			
			//1.쿼리문준비 : 값의 세팅이 필요한 부분을 ?로 대체한다. 
			String query = "INSERT INTO phonebook_tb VALUES (?, ?, ?)";
			
			//2.prepared객체 생성 : 생성시 준비한 쿼리문을 인자로 전달한다. 
			psmt = con.prepareStatement(query);
			
			//3.DB에 입력할 값을 사용자로부터 입력받음.
			Scanner scan = new Scanner(System.in);
			String iName,iPhone,iBirth;
			//공통사항 입력받기
			System.out.print("이름:"); iName = scan.next();
			System.out.print("전화번호:"); iPhone = scan.next();
			System.out.print("생년월일:"); iBirth = scan.next();
			
			//4.인파라미터 설정하기 : ?의 순서대로 설정하고 DB이므로 인덱스는 1부터 시작.
			psmt.setString(1, iName);
			psmt.setString(2, iPhone);

			psmt.setString(3, iBirth);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 입력되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dataSearch() {
		System.out.println("데이터 검색을 시작합니다..");
		
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("검색할 이름을 입력하세요:");
			String searchName = scan.next();
			
			System.out.println("name      phone     birth");
			String query = "SELECT iName,iPhone,iBirth FROM phonebook_tb WHERE iName LIKE ' "+ '%'+searchName + '%' +" ";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String name = rs.getString("iName");
				String phone = rs.getString("iPhone");
				String birth = rs.getString("iBirth");	
				/*
				오라클의 date타입을 getDate()로 추출하면
					2020-03-25 형태로 출력된다. 이경우 date형 자료가 되기
					때문에 java.sql.Date클래스의 참조변수로 저장해야한다.
				 */

				System.out.printf("%-10s %-10s %-13s \n",
						name, phone, birth);
			System.out.println("데이터 검색이 완료되었습니다.");
		  }
		}
		catch(SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
			}
	}
	
	public void dataDelete() {
		try {
			System.out.println("데이터 삭제를 시작합니다..");
			Scanner scan = new Scanner(System.in);
			System.out.print("삭제할 이름을 입력하세요:");
			String deleteName = scan.nextLine();
			
			String query = "DELETE FROM phonebook_tb WHERE iName='" + deleteName + "'";
			//3.prepared객체 생성
			psmt = con.prepareStatement(query);
			//5.쿼리실행후 결과값 반환
			System.out.println(psmt.executeUpdate() 
					+"행이 삭제되었습니다");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dataAllShow() {
		System.out.println("주소록출력..");
		System.out.println("name      phone     birth");
		try {
			String query = "SELECT iName,iPhone,iBirth FROM phonebook_tb ";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String name = rs.getString("iName");
				String phone = rs.getString("iPhone");
				String birth = rs.getString("iBirth");			
				/*
				오라클의 date타입을 getDate()로 추출하면
					2020-03-25 형태로 출력된다. 이경우 date형 자료가 되기
					때문에 java.sql.Date클래스의 참조변수로 저장해야한다.
				 */
				System.out.printf("%-10s %-10s %-13s \n",
						name, phone, birth);
			System.out.println("주소록 출력이 완료되었습니다.");
		  }
		}
		catch(SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
			}
	}
	
	public void close() {
		try {
			if(con!=null) con.close(); 
			if(psmt!=null) psmt.close();
			if(stmt!=null) stmt.close();
			if(rs!=null) rs.close();
			System.out.println("자원 반납 완료");
		}
		catch(Exception e) {
			System.out.println("자원 반납시 오류 발생");
			e.printStackTrace();
		}
	}
	
	public void exit() {
		System.out.println("프로그램 종료..!!");
		System.exit(0);
	}
	
	}//end of class
	