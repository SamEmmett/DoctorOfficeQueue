import java.util.PriorityQueue;

//****************************************************************
// PatientNode.java
// A node for use of storing Patient's name, address, gender,
// medical issue, and appointment information.
//****************************************************************

public class PatientNode implements Comparable<PatientNode> {
	@SuppressWarnings("unused")
	private PatientNode next;
	
	private String patientName;
	private String issue;
	private int priority; // Scale of 1 to 10 based on the severity of their issue
							// where 1 needs immediate attention
	private PriorityQueue<PhysicianNode> appointments = new PriorityQueue<PhysicianNode>(1);
	// -------------------------------------------
	// Creates an empty node.
	// -------------------------------------------
	public PatientNode() {
		next = null;
		patientName = null;
	}

	// ---------------------------------------------
	// Creates a node storing a specified name.
	// ---------------------------------------------
	public PatientNode(String name) {
		next = null;
		this.patientName = name;
	}

	// Getters and setters for Next//

	// Getters and setters for PatientName//
	public String getPatientName() {

		return patientName;

	}
public PatientNode returnPat() {
	return this;
}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayAppointments() {
		PriorityQueue<PhysicianNode> temp = new PriorityQueue<PhysicianNode>(this.appointments);
		while (temp.isEmpty() == false) {
			System.out.println(temp.remove());
		}
	}

	public void setAppointment(PhysicianNode physician){
		this.appointments.offer(physician);
	}
	public String getAppointment(){
		PriorityQueue<PhysicianNode> temp = new PriorityQueue<PhysicianNode>(this.appointments);
		PhysicianNode phyName = new PhysicianNode();
		
		phyName = temp.poll();
		return phyName.getPhysicianName();
	}
	public void delApp() {
		if(appointments.isEmpty() == false) {
			appointments.remove();}
		//else {System.out.println("The patient does not have any appointments.");}
	}
	public boolean hasAppointment(){
		if (appointments.isEmpty() == true) {
			return false;
		}
		else
			return true;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getPriority() { // gets priority of the patient
		return priority;
	}

	public void setPriority(int priority) {// sets the patients priority
		this.priority = priority;
	}


	// Getters and Setter for Medical Issue
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
/*
	public void setNext(PatientNode newNode) {
		this.next = newNode;

	}

	public PatientNode getNext() {

		if (next == null) {
			return null;
		} else {
			return next;
		}
	}
*/
	public int compareTo(PatientNode patient2) {

		if (this.equals(patient2) == true) {
			return 0;
		}

		else if (getPriority() > patient2.getPriority()) {
			return 1;
		} else {
			return -1;
		}
	}
	

	public boolean equals(PatientNode patient2) {

		return (this.getPriority() == patient2.getPriority());
	}

	public boolean equalsName(String name) {

		return (this.getPatientName() == name);
	}

	public String toString() {

		return ("Patient Name: " + getPatientName() + "\r\n" + "Issue: " + getIssue() + "\r\n" + "Priority: "
				+ getPriority() + "\r\n");
	}

}
