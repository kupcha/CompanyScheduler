import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author Patrick Kupcha
 *
 */
public class CreateScheduleController{
	EmployeeDatabase db = new EmployeeDatabase();
	public TextField budgetHours;
	public DatePicker startingDate;

	public void createSchedule(ActionEvent event) throws ClassNotFoundException, SQLException {
			
			Staff staffList;
			ArrayList<Request> added = db.timeoffCheck();
			try {
			staffList = db.creating();
			staffList.setAvail();
			staffList.printStaffMembers();
			System.out.println("Now creating schedule...");
			Schedule sched = createScheduleHelper(staffList, 200);
			System.out.println("Finished schedule!");
			//JOptionPane.showMessageDialog(null, "Schedule Created!");
			db.setSchedule(sched);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSchedule.fxml"));
			Parent root = loader.load();
			Scene viewFullScheduleScene = new Scene(root);
			EmployerViewScheduleController controller = loader.getController();
			controller.showSchedule();
			Stage viewScheduleStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			viewScheduleStage.setScene(viewFullScheduleScene);
			viewScheduleStage.setTitle("Full Staff Schedule");
			viewScheduleStage.show();
			
			}catch(Exception e) {
				System.err.println("EXCEPTION!");
				System.err.println(e.getMessage());
			}
			
			db.setBack(added, 0);
			
		}

			
	Schedule createScheduleHelper(Staff staff, int budgetHours) {
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
	
	public void handleReturn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployerGUI.fxml"));
		Parent root = loader.load();
		Scene employerHomeScene = new Scene(root);
		EmployerHomeController controller = loader.getController();
		Stage employerHomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		employerHomeStage.setScene(employerHomeScene);
		employerHomeStage.setTitle("Administrator Home Page");
		employerHomeStage.show();
	}

	
	
}