package ver05;

import ver05.PhoneInfo;

public class PhoneSchoolInfo extends PhoneInfo {
	String major;
	int level;
	
	public PhoneSchoolInfo(String name, String phone,
			String major,int ilevel) {
		super(name,phone);
		this.major = major;
		this.level = ilevel;
	}

	public void showPhoneInfo() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		System.out.println("전공:"+ major);
		System.out.println("학년:"+ level);
	}
}