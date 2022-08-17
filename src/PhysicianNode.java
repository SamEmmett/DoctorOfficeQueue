import java.util.PriorityQueue;

public class PhysicianNode implements Comparable<PhysicianNode> {

//****************************************************************
	// PatientNode.java
	// A node for use of storing Patient's name, address, gender,
	// medical issue, and appointment information.
	// ****************************************************************

	@SuppressWarnings("unused")
	private PhysicianNode next;
	private String physicianName;
	private String specialty;
	private int priority; // Scale of 1 to 10 based on the severity of their issue
							// where 1 needs immediate attention

	private PriorityQueue<PatientNode> appointments = new PriorityQueue<PatientNode>(5);

	// -------------------------------------------
	// Creates an empty node.
	// -------------------------------------------
	public PhysicianNode() {
		next = null;
		physicianName = null;
	}

	// ---------------------------------------------
	// Creates a node storing a specified name.
	// ---------------------------------------------
	public PhysicianNode(String name) {
		next = null;
		this.physicianName = name;
	}

	// Getters and setters for Next//

	// Getters and setters for PatientName//
	public String getPhysicianName() {

		return physicianName;

	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

//////////////////////////////////////////////////////////////////	
	public boolean hasSpace() {
		if (appointments.size() == 5) {
			return false;
		} else
			return true;

	}

	public void displayAppointments() {
		PriorityQueue<PatientNode> temp = new PriorityQueue<PatientNode>(this.appointments);
		while (temp.isEmpty() == false) {
			System.out.println(temp.remove());
		}
	}

	public void setAppointment(PatientNode patient) {
		this.appointments.offer(patient);
	}

	public void deleteApp(PatientNode deleteMe) {
		PriorityQueue<PatientNode> temp = new PriorityQueue<PatientNode>();
		PatientNode patDel = new PatientNode();

		while (this.appointments.isEmpty() == false) {
			patDel = appointments.peek();
			if (patDel.equalsName(deleteMe.getPatientName())) {
				appointments.remove();
			} else {
				temp.offer(appointments.poll());
			}
		}
		
		while (temp.isEmpty()==false){
			appointments.offer(temp.poll());
			
		}
	}

///////////////////////////////////////////////////////////////
	// Getters and Setter for Specialty
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int compareTo(PhysicianNode physician2) {

		// this should suffice for finding a doctor with a specific specialty
		// when looking for a doctor who can help

		ScanMe workPls = new ScanMe(this, physician2);

		workPls.GetVal();
		int val1 = workPls.getVal1();
		int val2 = workPls.getVal2();
		if (this.equals(physician2) == true) {
			return 0;
		}

		else if (val1 > val2) {
			return 1;
		} else {
			return -1;
		}
	}

	public boolean equals(PhysicianNode patient2) {

		return (this.getSpecialty() == patient2.getSpecialty());
	}

	public String toString() {

		return ("Physician Name: Dr. " + getPhysicianName() + "\r\n" + "Specialty: " + getSpecialty() + "\r\n");
	}

	public void scanSpec(PhysicianNode physician2) {

		// Declare the object and initialize with
		// predefined standard input object

	}
}
