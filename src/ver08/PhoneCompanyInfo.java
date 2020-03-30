package ver08;

import ver08.PhoneInfo;

public class PhoneCompanyInfo extends PhoneInfo {
	String Company_Name;
	
	public PhoneCompanyInfo(String name,String phone,String Company_Name) {
		super(name,phone);
		this.Company_Name = Company_Name;
	}
	
	public void showPhoneInfo() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		System.out.println("회사:"+ Company_Name);
	}
	
	@Override
	public String toString() {
		return "data 이름=" + name + ", 번호=" + phoneNumber
				+ ", 회사=" + Company_Name;
	}
}
