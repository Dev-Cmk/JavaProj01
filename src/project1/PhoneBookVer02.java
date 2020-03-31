package project1;

import java.io.IOException;
import java.util.Scanner;

import ver01.PhoneInfo;

public class PhoneBookVer02 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String name = null;
		String phonenumber = null;
		boolean flag = true;

		while (flag) {
			System.out.print("선택하세요...\n" + "1.데이터 입력\n" + "2.프로그램 종료\n" + "선택:");
			int res = sc.nextInt();
			if (res == 1) {
				System.out.print("이름:");
				name = sc.next();
				System.out.print("전화번호:");
				phonenumber = sc.next();

				byte[] input = new byte[256];
				System.out.print("생년월일:");
				System.in.read(input);
				String birthday = new String(input);
				int check = birthday.charAt(0);

				if (check == 13) {
					PhoneInfo p1 = new PhoneInfo(name, phonenumber);
					System.out.print("\n 입력된 정보 출력...\n");
					p1.showPhoneInfo();
					flag = true;
				} else {
					PhoneInfo p1 = new PhoneInfo(name, phonenumber, birthday);
					System.out.print("\n 입력된 정보 출력...\n");
					p1.showPhoneInfo();
					flag = true;
				}
			} else if (res == 2) {
				System.out.println("프로그램을 종료합니다.");
				flag = false;
			}
			System.out.println();
		} // end of while

	}// end of main

}// end of class
