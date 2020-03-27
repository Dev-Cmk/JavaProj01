package ver07;


public class PhoneInfo {
	//멤버변수
	String name;
	String phoneNumber;
	
	//생성자
	//생년월일은 필수사항 아니므로 인자가 없는경우 null로 초기화
	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	//정보출력용메소드
	public void showPhoneInfo() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		
	}
	
	@Override
	public String toString() {
		return "data 이름=" + name + ", 번호=" + phoneNumber;
	}
	
	@Override
	public int hashCode() {
		//객체가 가진 고유한값(참조값)을 int형으로 반환하는 메소드
		int hc1 = name.hashCode();
		int result = hc1;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//형변환
		PhoneInfo inf = (PhoneInfo)obj;
		if(inf.name.equals(this.name)) 
		{
			return true;
		}
		else {
			return false;
		}
	}
	
}
