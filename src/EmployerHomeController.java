import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javax.swing.JOptionPane;

public class EmployerHomeController {

	EmployeeDatabase db = new EmployeeDatabase();
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Add;

	@FXML
	private Button ChangeE;

	@FXML
	private Button CheckR;

	@FXML
	private Button RemoveE;

	@FXML
	private Button CreateS;

	@FXML
	private Button Logout;

	/*
	 * Author: Kyle VanWageninge goes to the Add Employee GUI
	 */
	@FXML
	void AddEmployee(ActionEvent event) {

		try {

			BorderPane second = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
			Scene scene2 = new Scene(second, 600, 600);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Add Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void ChangeEmployee(ActionEvent event) {

	}

	@FXML
	void CheckRequest(ActionEvent event) {
		try {

			VBox second = (VBox) FXMLLoader.load(getClass().getResource("CheckRequests.fxml"));
			Scene scene2 = new Scene(second, 600, 600);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Check Requests");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void CreateSchedule(ActionEvent event) {
		
			
			Staff staffList;
			try {
			staffList = db.creating();
			staffList.setAvail();
			staffList.printStaffMembers();
			System.out.println("Now creating schedule...");
			Schedule sched = createSchedule(staffList, 200);
			System.out.println("Finished schedule!");
			db.setSchedule(sched);
			}catch(Exception e) {
				System.err.println("EXCEPTION!");
				System.err.println(e.getMessage());
			}
			
			
		}

	@FXML
	void Logout(ActionEvent event) {
	JOptionPane.showMessageDialog(null, "You are now logging out.");
	Stage stage = (Stage) Logout.getScene().getWindow();
	    stage.close();
	    return;
	}

	@FXML
	void RemoveEmployee(ActionEvent event) {
		try {

			VBox second = (VBox) FXMLLoader.load(getClass().getResource("RemoveEmployee.fxml"));
			Scene scene2 = new Scene(second, 600, 600);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Remove Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {

	}
	
	Schedule createSchedule(Staff staff, int budgetHours) {
		ArrayList<Employee> currentStaff = new ArrayList<Employee>();
		for (int i = 0; i < staff.currentStaff.size(); i++) {
			if (staff.currentStaff.get(i).access == 1) {
				currentStaff.add((Employee) staff.currentStaff.get(i));
			}
		}
		//sort by type so full time employees appear first and will be scheduled first
		Collections.sort(currentStaff, Employee.employeeType);
		Schedule sched = new Schedule(staff.currentStaff, budgetHours);
		int i;
		//for each full time employee: schedule them their availabiltiy. Given open availability will set to 0 and we handle later
		for (i = 0; i < currentStaff.size() && budgetHours >= 0 && currentStaff.get(i).type == 0; i++) {
			for (int j = 1; j < 6; j++) {
				Shift temp;
				switch (j) {
				case 1: temp = new Shift(currentStaff.get(i), currentStaff.get(i).mondayAvailability, j);
						sched.monday.add(temp);
						budgetHours -= 8;
						continue;
				case 2:	temp = new Shift(currentStaff.get(i), currentStaff.get(i).tuesdayAvailability, j);
						sched.tuesday.add(temp);
						budgetHours -= 8;
						continue;
				case 3: temp = new Shift(currentStaff.get(i), currentStaff.get(i).wednesdayAvailability, j);
						sched.wednesday.add(temp);
						budgetHours -= 8;
						continue;
				case 4: temp = new Shift(currentStaff.get(i), currentStaff.get(i).thursdayAvailability, j);
						sched.thursday.add(temp);
						budgetHours -= 8;
						continue;
				case 5: temp = new Shift(currentStaff.get(i), currentStaff.get(i).fridayAvailability, j);
						sched.friday.add(temp);
						budgetHours -= 8;
						continue;
				default: continue;
				}
				
			}
		}
		int partTimeIndex = i;
		//sort shifts so open availabilities will appear first
		Collections.sort(sched.monday, Shift.shiftHours);
		Collections.sort(sched.tuesday, Shift.shiftHours);
		Collections.sort(sched.wednesday, Shift.shiftHours);
		Collections.sort(sched.thursday, Shift.shiftHours);
		Collections.sort(sched.friday, Shift.shiftHours);
		// now go back and schedule open availability according to needs
		//update open availability to whatever shift is most needed
		// do this for each day of the week
		System.out.println("Slotted all full timers shifts.");
		sched.printSchedule();
		for (i = 0; i < sched.monday.size(); i++) {
			//if we reach shifts already scheduled day/night we can move on
			if (sched.monday.get(i).hours != 0) {
				break;
			}else {
				//schedule them to whichever shift has less help
				int monAM = sched.howManyScheduled(1 , 1);
				int monPM = sched.howManyScheduled(1 , 2);
				if (monAM > monPM) {
					sched.monday.get(i).hours = 2;
				}else {
					sched.monday.get(i).hours = 1;
				}
			}
		}
		for (i = 0; i < sched.tuesday.size(); i++) {
			if (sched.tuesday.get(i).hours != 0) {
				break;
			}else {
				int tuesAM = sched.howManyScheduled(2 , 1);
				int tuesPM = sched.howManyScheduled(2 , 2);
				if (tuesAM > tuesPM) {
					sched.tuesday.get(i).hours = 2;
				}else {
					sched.tuesday.get(i).hours = 1;
				}
			}
		}
		for (i = 0; i < sched.wednesday.size(); i++) {
			if (sched.wednesday.get(i).hours != 0) {
				break;
			}else {
				int wedAM = sched.howManyScheduled(3 , 1);
				int wedPM = sched.howManyScheduled(3 , 2);
				if (wedAM > wedPM) {
					sched.wednesday.get(i).hours = 2;
				}else {
					sched.wednesday.get(i).hours = 1;
				}
			}
		}
		for (i = 0; i < sched.thursday.size(); i++) {
			if (sched.thursday.get(i).hours != 0) {
				break;
			}else {
				int thursAM = sched.howManyScheduled(4 , 1);
				int thursPM = sched.howManyScheduled(4 , 2);
				if (thursAM > thursPM) {
					sched.thursday.get(i).hours = 2;
				}else {
					sched.thursday.get(i).hours = 1;
				}
			}
		}
		for (i = 0; i < sched.friday.size(); i++) {
			if (sched.friday.get(i).hours != 0) {
				break;
			}else {
				int friAM = sched.howManyScheduled(5 , 1);
				int friPM = sched.howManyScheduled(5 , 2);
				if (friAM > friPM) {
					sched.friday.get(i).hours = 2;
				}else {
					sched.friday.get(i).hours = 1;
				}
			}
		}
		sched.printSchedule();
		//at this point -- full time employees have a shift each day of the week
		//start of partTimeIndex
		int curr = partTimeIndex;
		int day = 0;
		while (budgetHours > 0 && curr < currentStaff.size()) {
			int schedFactor = day % 5;
			System.out.println(schedFactor);
			switch (schedFactor) {
			case 0:
				if (currentStaff.get(curr).mondayAvailability == 0) {
					Shift temp = new Shift(currentStaff.get(curr), 1, 1);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else if (currentStaff.get(curr).mondayAvailability == 1 || currentStaff.get(curr).mondayAvailability == 2) {
					Shift temp = new Shift(currentStaff.get(curr), currentStaff.get(curr).mondayAvailability, 1);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else {
					curr++;
				}
				if (curr >= currentStaff.size()) {
					curr = partTimeIndex;
				}
				day++;
				continue;
			case 1:
				if (currentStaff.get(curr).tuesdayAvailability == 0) {
					Shift temp = new Shift(currentStaff.get(curr), 1, 2);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else if (currentStaff.get(curr).tuesdayAvailability == 1 || currentStaff.get(curr).tuesdayAvailability == 2) {
					Shift temp = new Shift(currentStaff.get(curr), currentStaff.get(curr).tuesdayAvailability, 2);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else {
					curr++;
				}
				if (curr >= currentStaff.size()) {
					curr = partTimeIndex;
				}
				day++;
				continue;
			case 2:
				if (currentStaff.get(curr).wednesdayAvailability == 0) {
					Shift temp = new Shift(currentStaff.get(curr), 1, 3);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else if (currentStaff.get(curr).wednesdayAvailability == 1 || currentStaff.get(curr).wednesdayAvailability == 2) {
					Shift temp = new Shift(currentStaff.get(curr), currentStaff.get(curr).wednesdayAvailability, 3);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else {
					curr++;
				}
				if (curr >= currentStaff.size()) {
					curr = partTimeIndex;
				}
				day++;
				continue;
			case 3:
				if (currentStaff.get(curr).thursdayAvailability == 0) {
					Shift temp = new Shift(currentStaff.get(curr), 1, 4);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else if (currentStaff.get(curr).thursdayAvailability == 1 || currentStaff.get(curr).thursdayAvailability == 2) {
					Shift temp = new Shift(currentStaff.get(curr), currentStaff.get(curr).thursdayAvailability, 4);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else {
					curr++;
				}
				if (curr >= currentStaff.size()) {
					curr = partTimeIndex;
				}
				day++;
				continue;
			case 4:
				if (currentStaff.get(curr).fridayAvailability == 0) {
					Shift temp = new Shift(currentStaff.get(curr), 1, 5);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else if (currentStaff.get(curr).thursdayAvailability == 1 || currentStaff.get(curr).fridayAvailability == 2) {
					Shift temp = new Shift(currentStaff.get(curr), currentStaff.get(curr).fridayAvailability, 5);
					sched.addShift(temp);
					budgetHours -= 8;
					curr++;
				}else {
					curr++;
				}
				if (curr >= currentStaff.size()) {
					curr = partTimeIndex;
				}
				day++;
				continue;
			default:
				if (curr >= currentStaff.size()) {
					curr = partTimeIndex;
				}
				day++;
			}
		}
		sched.printSchedule();
		
		
		
		
		
		return sched;
	}
	
}
