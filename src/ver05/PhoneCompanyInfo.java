package ver05;

import ver05.PhoneInfo;

public class PhoneCompanyInfo extends PhoneInfo {
	String Company_Name;
	
	public PhoneCompanyInfo(String name,String phone,String Copany_Name) {
		super(name,phone);
		this.Company_Name = Company_Name;
	}
	
	public void showPhoneInfo() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		System.out.println("회사:"+ Company_Name);
	}
}