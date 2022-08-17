import java.util.Scanner;
import java.util.PriorityQueue;

class Runner {

	static Scanner input = new Scanner(System.in); // IS NECCESSARY?

	public static void runThis(String[] args) {

		PriorityQueue<PatientNode> patQueue = new PriorityQueue<PatientNode>();
		PriorityQueue<PhysicianNode> docQueue = new PriorityQueue<PhysicianNode>(); // The office cannot accommodate
																					// more than five practitioners

		PhysicianNode Zed = new PhysicianNode();
		genZed(Zed);
		docQueue.offer(Zed);

		PatientNode Scooter = new PatientNode();
		genScooter(Scooter);
		// Scooter.setVisits("Zed");

		PatientNode Mitch = new PatientNode();
		genMitch(Mitch);
		// this.patient.setVisits(Jasper Kaufman);

		PatientNode Seth = new PatientNode();
		genSeth(Seth);
		// this.patient.setVisits(Zakaria Colon);

		patQueue.offer(Scooter);
		patQueue.offer(Seth);
		patQueue.offer(Mitch);

		// printQueue(pQueue);
		printMenu();
		int choice = input.nextInt();
		while (choice != 0) {
			dispatch(choice, patQueue, docQueue);
			printMenu();
			choice = input.nextInt();
		}

	}

	public static void printMenu() {
		System.out.println("\n   Menu   ");
		System.out.println("   ====");
		System.out.println("1: Add a Patient"); // Add ALL patient info (Name & Issue)
		System.out.println("2: Add a Physician"); // Name and Specialty
		System.out.println("3: Schedule an appointment for an existing patient");
		System.out.println("4: Remove an appointment");
		System.out.println("5: Print patient appointments based on appointment order");
		System.out.println("6: Print physician appointments based on appointment order");
		System.out.println("0: Quit");
		System.out.print("\nEnter your choice: ");
	}

	public static PriorityQueue dispatch(int choice, PriorityQueue<PatientNode> patQueue,
			PriorityQueue<PhysicianNode> phyQueue) {
		switch (choice) {
		case 0:
			System.out.println("Bye!");
			break;
		case 1: // add Patient
			PatientNode newPatient = new PatientNode();
			newPatient = addPatient();
			patQueue.offer(newPatient);
			return patQueue;

		case 2: // add Physician
			PhysicianNode newPhysician = new PhysicianNode();
			newPhysician = addPhysician();
			phyQueue.offer(newPhysician);
			return phyQueue;

		case 3: // Schedule an appointment for an existing patient
			runCase5(patQueue, phyQueue);

			break;
		case 4: // Cancel an appointment
			cancelApp(patQueue, phyQueue);
			// remove that patient from appointment queue

			break;

		case 5: // Print patient appointments
			printPatientNames2(patQueue);
			printPatApp(patQueue);
			break;

		case 6: // Print Physician Appointments based on priority
			printPhysicianNames(phyQueue);
			printPhyApp(phyQueue);
			// stored queue inside of the PhysicianNode for Appointments
			break;

		default:
			System.out.println("Sorry, invalid choice");
		}
		return phyQueue;

	}

	private static void cancelApp(PriorityQueue<PatientNode> patQueue, PriorityQueue<PhysicianNode> phyQueue) {
		// declare variables and objects
		String param;
		String param2;
		boolean ticker = false;
		// for queue traversal
		PatientNode patInfo = new PatientNode();
		PhysicianNode phyInfo = new PhysicianNode();

		String phyName;

		// search inputs
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);

		// nodes for comparing their names to the param
		PatientNode patComp = new PatientNode();
		PhysicianNode phyComp = new PhysicianNode();

		System.out.println("The list of patients with appointments are:");
		// display patients with appointments
		printPatientNames2(patQueue);
		//printPatApp(patQueue);
		System.out.println("What is the name of the Patient whose appointment you would like to cancel?");
		param = input.nextLine();
		// ask for patient to cancel
		///////System.out.println("Please type the physician name to confirm your cancellation.");
		//////param2 = input.nextLine();
		PriorityQueue<PatientNode> patDump = new PriorityQueue<PatientNode>();
		PriorityQueue<PhysicianNode> phyDump = new PriorityQueue<PhysicianNode>();

		// Search for the patient (if not patient then have a message to display)
		while (patQueue.isEmpty() == false) {
			patComp = patQueue.peek();
			// once found; Look at the Patient's appointment
			if (param.equals(patComp.getPatientName())) {
				patInfo = patQueue.poll();
				phyName = patInfo.getAppointment();
				while (phyQueue.isEmpty() == false) {
					// Then look through that Doctor's appointment
					// queue for the patient
					phyComp = phyQueue.peek();
					if (phyName.equals(phyComp.getPhysicianName())) {
						// once the patient is found, remove the patient
						phyInfo = phyQueue.poll();
						phyInfo.deleteApp(patInfo);
						patInfo.delApp();
						phyQueue.offer(phyInfo);
						patQueue.offer(patInfo);
						System.out.println("The appointment with Dr. " + phyInfo.getPhysicianName() + " and with "
								+ patInfo.getPatientName() + " has been cancelled.");
						ticker = true;
						break;
					} else {
						phyDump.offer(phyQueue.poll());
					}
				} // end of phyQueue while

				// once the patient is removed from the Doctor's Appointment Queue
				// remove the appointment from the patient's appointment queue
				patInfo.delApp();
			} else {
				patDump.offer(patQueue.poll());
			} // end of nested if

			if (ticker == true) {
				break;
			}
		} // end of patQueue While
		while (patDump.isEmpty() == false) {
			patQueue.offer(patDump.poll());
		}
		while (phyDump.isEmpty() == false) {
			phyQueue.offer(phyDump.poll());
		}
	}

	private static void runCase5(PriorityQueue<PatientNode> patQueue, PriorityQueue<PhysicianNode> phyQueue) {
		boolean ticker = false;
		String param;
		String param2;

		// for queue traversal
		PatientNode patInfo = new PatientNode();
		PhysicianNode phyInfo = new PhysicianNode();

		// search inputs
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);

		// nodes for comparing their names to the param
		PatientNode patComp = new PatientNode();
		PhysicianNode phyComp = new PhysicianNode();

		System.out.println("The list of available patients are:");
		printPatientNames(patQueue);
		System.out.println("What is the name of the patient?");
		param = input.nextLine();

		// generates an empty queue to dump the uneeded entries in the original gueue
		PriorityQueue<PatientNode> patDump = new PriorityQueue<PatientNode>();
		PriorityQueue<PhysicianNode> phyDump = new PriorityQueue<PhysicianNode>();

		while (patQueue.isEmpty() == false) {
			patComp = patQueue.peek();

			// in case they ask for a physician that doesn't exist

			if (param.equals(patComp.getPatientName())) {
				patInfo = patQueue.poll();

				// print out all available physicians
				System.out.println(" ");
				System.out.println("The list of available physicians are:");
				printPhysicianNames(phyQueue);
				// Asks for the doctor and then searches for the doctor
				System.out.println("What is the name of the physician you " + "would like to assign "
						+ "the patient to? (please omit the prefix Dr.)");
				param2 = input.nextLine();
				while (phyQueue.isEmpty() == false) {
					// traverses and looks for the doctor

					// Use a SECOND Queue to remove the nodes
					// as they are displayed.
					// BUT be sure to copy the second queue's info
					// back into the original queue!

					phyComp = phyQueue.peek();
					if (param2.equals(phyComp.getPhysicianName())) {
						phyInfo = phyQueue.poll();
						phyInfo.setAppointment(patInfo);
						patInfo.setAppointment(phyInfo);
						phyQueue.offer(phyInfo);
						ticker = true;
						System.out.println(
								"Your appointment with Dr. " + phyInfo.getPhysicianName() + " has been scheduled.");
						break;
					} else {
						phyDump.offer(phyQueue.poll());

					}
				}
				if (phyQueue.isEmpty() == true) {
					System.out.println("The physician that you are looking for is not in our system.");
					break;
				} // in case they ask for a physician that doesn't exist
			} else {
				patDump.offer(patQueue.poll());
			}

			// re add the new patient (with appointment) back into the queue
			if (ticker == true) {
				patQueue.offer(patInfo);
				break;
			}
		} // end of first while statement

		if (patQueue.isEmpty() == true) {
			System.out.println("The patient that" + " you are looking for is not" + " in our system.");
			System.out.println();
		}
		// Now that everything has been set
		// add the content from the second
		// queue dumps back into the originals
		while (patDump.isEmpty() == false) {
			patQueue.offer(patDump.poll());
		}
		while (phyDump.isEmpty() == false) {
			phyQueue.offer(phyDump.poll());
		}
	}

	private static void genSeth(PatientNode Seth) {
		Seth.setPatientName("Seth Dougherty");
		Seth.setIssue("Needs X-Ray taken of leg.");
		Seth.setPriority(10);
	}

	private static void genMitch(PatientNode Mitch) {
		Mitch.setPatientName("Basic Mitch");
		Mitch.setIssue("Needs a kidney transplant!");
		Mitch.setPriority(1);
	}

	private static void genScooter(PatientNode Scooter) {
		Scooter.setPatientName("Scooter");
		Scooter.setIssue("Caught a ride!");
		Scooter.setPriority(1);
	}

	private static void genZed(PhysicianNode Zed) {
		Zed.setPhysicianName("Zed");
		Zed.setSpecialty("Nothing (he's a real doctor, he swears)");

	}

	private static PatientNode addPatient() {

		Scanner input = new Scanner(System.in);

		PatientNode patient = new PatientNode();
		String name;
		String issue;
		int priority;

		System.out.println("What is the name of the patient you would like to add?");
		name = input.nextLine();
		patient.setPatientName(name);

		System.out.println("What is issue?");
		issue = input.nextLine();
		patient.setIssue(issue);

		System.out.println("Set a priority for the patient between 1 and 10. (Lower is more severe)");
		priority = input.nextInt();
		patient.setPriority(priority);

		return patient;
	}

	private static PhysicianNode addPhysician() {

		Scanner input = new Scanner(System.in);

		PhysicianNode physician = new PhysicianNode();
		String name;
		String specialty;

		System.out.println("What is the name of the Phsyician you would like to add?");
		name = input.nextLine();
		physician.setPhysicianName(name);

		System.out.println("What is the physician's specialty?");
		specialty = input.nextLine();
		physician.setSpecialty(specialty);
		return physician;
	}

	private static void printPatApp(PriorityQueue<PatientNode> pQueue) {
		PriorityQueue<PatientNode> temp = new PriorityQueue<PatientNode>(pQueue);
		PatientNode printNode = new PatientNode();
		String param;
		Scanner pat = new Scanner(System.in);
		System.out.println("What is the name of the patient whose appointments you would like to check?");

		param = pat.nextLine();
		while (temp.isEmpty() == false) {

			printNode = temp.poll();
			if (param.equals(printNode.getPatientName())) {
				System.out.println(
						"The patient, " + printNode.getPatientName() + "'s appointments are as follows:" + "\r\n");
				printNode.displayAppointments();

				break;
			}
		}
	}

	private static void printPatientNames(PriorityQueue<PatientNode> pQueue) {
		PriorityQueue<PatientNode> patCopy = new PriorityQueue<PatientNode>(pQueue);
		PatientNode pat = new PatientNode();

		while (patCopy.isEmpty() == false) {
			pat = patCopy.poll();

			if (pat.hasAppointment() == false) {
				System.out.println(pat.getPatientName());
			}
		}
		System.out.println();
	}

	private static void printPatientNames2(PriorityQueue<PatientNode> pQueue) {
		PriorityQueue<PatientNode> patCopy = new PriorityQueue<PatientNode>(pQueue);
		PatientNode pat = new PatientNode();

		while (patCopy.isEmpty() == false) {
			pat = patCopy.poll();

			if (pat.hasAppointment() == true) {
				System.out.println(pat.getPatientName());
			}
		}
		System.out.println();
	}

	private static void printPhysicianNames(PriorityQueue<PhysicianNode> pQueue) {
		PriorityQueue<PhysicianNode> phyCopy = new PriorityQueue<PhysicianNode>(pQueue);
		PhysicianNode pat = new PhysicianNode();

		while (phyCopy.isEmpty() == false) {
			pat = phyCopy.poll();

			if (pat.hasSpace() == true)
				System.out.println("Dr. " + pat.getPhysicianName() + " --> " + "Specialty: " + pat.getSpecialty());
		}
		System.out.println();
	}

	private static void printPhyApp(PriorityQueue<PhysicianNode> pQueue) {
		PriorityQueue<PhysicianNode> temp = new PriorityQueue<PhysicianNode>(pQueue);
		PhysicianNode printNode = new PhysicianNode();
		String param;
		Scanner doc = new Scanner(System.in);
		System.out.println(
				"What is the name of the physician whose appointments you would like to check? (Please omit the prefix Dr.)");

		param = doc.nextLine();
		while (temp.isEmpty() == false) {

			printNode = temp.poll();
			if (param.equals(printNode.getPhysicianName())) {
				System.out.println("Dr. " + printNode.getPhysicianName() + "'s appointments are as follows:" + "\r\n");
				printNode.displayAppointments();

				break;
			}
		}
	}

}