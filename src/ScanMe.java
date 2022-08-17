
public class ScanMe {
	String string1;
	String string2;
	int val1;
	int val2;

	ScanMe() {

	}

	ScanMe(PhysicianNode phy1, PhysicianNode phy2) {
		string1 = phy1.getPhysicianName();

		string2 = phy2.getPhysicianName();

	}

	public void GetVal() {
// Character input
		
		char a = string1.charAt(0);
		char b = string2.charAt(0);
		// Print the read value
		while (a == b) {
			int count = 1;
			a = string1.charAt(count);
			b = string2.charAt(count);
		}
		
		this.val1 = (int) a;
		this.val2= (int) b;
	
	}

	public int getVal1() {
		return val1;
	}

	public void setVal1(int val1) {
		this.val1 = val1;
	}

	public int getVal2() {
		return val2;
	}

	public void setVal2(int val2) {
		this.val2 = val2;
	}
}