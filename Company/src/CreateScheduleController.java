import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Patrick Kupcha
 *	Controls the page for an administrator creating a Schedule
 */
public class CreateScheduleController{
	EmployeeDatabase db = new EmployeeDatabase();
	public TextField budgetHours;
	public DatePicker startingDate;
	
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
	
	@FXML
	/**
	 * Launch the Add Employee page
	 * @param event
	 */
	void AddEmployee(ActionEvent event) {

		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("AddEmployeeUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Add Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Launch the Change Employee page
	 * @param event
	 */
	@FXML
	void ChangeEmployee(ActionEvent event) {
		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("ViewEmployeesUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("View Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the Check Requests page
	 * @param event
	 */
	@FXML
	void CheckRequest(ActionEvent event) {
		
		
		
		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("CheckRequestsUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
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
	public void goToCreateSchedule(ActionEvent event) throws IOException {
		
}
	
	/**
	 * User will logout of program
	 * @param event
	 */
	@FXML
	void Logout(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "You are now logging out.");
		try {

			AnchorPane second =  FXMLLoader.load(getClass().getResource("LogInUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Login");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return;

	}

	/**
	 * Launch the Remove Employee page
	 * @param event
	 */
	@FXML
	void RemoveEmployee(ActionEvent event) {
		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("RemoveEmployeeUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("View Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a schedule for all employees. Database will be updated for all users to see, and will launch a page for the administrator to view the schedule
	 * that includes all employees.
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void createSchedule(ActionEvent event) throws ClassNotFoundException, SQLException {
		if (budgetHours.getText() == "") {
			JOptionPane.showMessageDialog(null, "Must input how many total hours to be scheduled.");
			return;
		}
		int hoursToSchedule = Integer.parseInt(budgetHours.getText());
		if (hoursToSchedule <= 0) {
			JOptionPane.showMessageDialog(null, "Must input a positive number of hours to be scheduled.");
			return;
		}
		Staff staffList;
		ArrayList<Request> added = db.timeoffCheck();
		try {
			//get StaffList from database
			staffList = db.creating();
			staffList.setAvail();
			//staffList.printStaffMembers();
			//System.out.println("Now creating schedule...");
			Schedule sched = createScheduleHelper(staffList, hoursToSchedule);
			//System.out.println("Finished schedule!");
			//JOptionPane.showMessageDialog(null, "Schedule Created!");
			//update each employees schedule table in database
			db.setSchedule(sched);
			//launch employer view of the new schedule
			

				AnchorPane second = FXMLLoader.load(getClass().getResource("ViewScheduleUpdate.fxml"));
				Scene scene2 = new Scene(second, 930, 565);
				Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				secondStage.setTitle("View Schedule");
				secondStage.setScene(scene2);
				secondStage.show();
			
			}catch(Exception e) {
				System.err.println("EXCEPTION!");
				System.err.println(e.getMessage());
			}
			
			db.setBack(added, 0);
			
		}

	/**
	 * Helper function for createSchedule() -- creates entity objects and returns the actual Schedule object that will be used to update database.
	 * @param staff
	 * @param budgetHours
	 * @return
	 */
	public Schedule createScheduleHelper(Staff staff, int budgetHours) {
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
		//System.out.println("TEST:" + currentStaff.get(i).toString());
		//sort shifts so open availabilities will appear first
		Collections.sort(sched.monday, Shift.shiftHours);
		Collections.sort(sched.tuesday, Shift.shiftHours);
		Collections.sort(sched.wednesday, Shift.shiftHours);
		Collections.sort(sched.thursday, Shift.shiftHours);
		Collections.sort(sched.friday, Shift.shiftHours);
		// now go back and schedule open availability according to needs
		//update open availability to whatever shift is most needed
		// do this for each day of the week
		//System.out.println("Slotted all full timers shifts.");
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
		//System.out.println(budgetHours);
		//System.out.println(partTimeIndex);
		//sched.printSchedule();
		//at this point -- full time employees have a shift each day of the week
		//start of partTimeIndex
		int curr = partTimeIndex;
		int day = 0;
		int amShifts;
		int pmShifts;
		int exhaustedOptions = currentStaff.size() - curr;
		int count = 0;
		while (budgetHours > 7 && curr < currentStaff.size() && day < 5) {
			if (count == exhaustedOptions) {
				day++;
				continue;
			}
			int schedFactor = day % 5;
			//System.out.println(schedFactor);
			switch (schedFactor) {
			case 0:
				amShifts = sched.howManyScheduled(1, 1);
				pmShifts = sched.howManyScheduled(1, 2);
				if (amShifts >= pmShifts) {
					if ((currentStaff.get(curr).mondayAvailability == 1 || currentStaff.get(curr).mondayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 1) ) {
						Shift temp = new Shift(currentStaff.get(curr), 1, 1);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}else {
					if ((currentStaff.get(curr).mondayAvailability == 2 || currentStaff.get(curr).mondayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 1) ) {
						Shift temp = new Shift(currentStaff.get(curr), 1, 2);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}
				break;
			case 1:
				amShifts = sched.howManyScheduled(2, 1);
				pmShifts = sched.howManyScheduled(2, 2);
				if (amShifts >= pmShifts) {
					if ((currentStaff.get(curr).tuesdayAvailability == 1 || currentStaff.get(curr).tuesdayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 2) ) {
						Shift temp = new Shift(currentStaff.get(curr), 2, 1);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}else {
					if ((currentStaff.get(curr).tuesdayAvailability == 2 || currentStaff.get(curr).tuesdayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 2) ) {
						Shift temp = new Shift(currentStaff.get(curr), 2, 2);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					count++;
				}
				break;
			case 2:
				amShifts = sched.howManyScheduled(3, 1);
				pmShifts = sched.howManyScheduled(3, 2);
				if (amShifts >= pmShifts) {
					if ((currentStaff.get(curr).wednesdayAvailability == 1 || currentStaff.get(curr).wednesdayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 3) ) {
						Shift temp = new Shift(currentStaff.get(curr), 3, 1);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}else {
					if ((currentStaff.get(curr).wednesdayAvailability == 2 || currentStaff.get(curr).wednesdayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 3) ) {
						Shift temp = new Shift(currentStaff.get(curr), 3, 2);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}
				break;
			case 3:
				amShifts = sched.howManyScheduled(4, 1);
				pmShifts = sched.howManyScheduled(4, 2);
				if (amShifts >= pmShifts) {
					if ((currentStaff.get(curr).thursdayAvailability == 1 || currentStaff.get(curr).thursdayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 4) ) {
						Shift temp = new Shift(currentStaff.get(curr), 4, 1);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}else {
					if ((currentStaff.get(curr).thursdayAvailability == 2 || currentStaff.get(curr).thursdayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 4) ) {
						Shift temp = new Shift(currentStaff.get(curr), 4, 2);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}
				break;
			case 4:
				amShifts = sched.howManyScheduled(5, 1);
				pmShifts = sched.howManyScheduled(5, 2);
				if (amShifts >= pmShifts) {
					if ((currentStaff.get(curr).fridayAvailability == 1 || currentStaff.get(curr).fridayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 5) ) {
						Shift temp = new Shift(currentStaff.get(curr), 5, 1);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}else {
					if ((currentStaff.get(curr).fridayAvailability == 2 || currentStaff.get(curr).fridayAvailability == 0) && !sched.alreadyScheduled(currentStaff.get(curr).username, 5) ) {
						Shift temp = new Shift(currentStaff.get(curr), 5, 2);
						sched.addShift(temp);
						budgetHours -= 8;
						day++;
						count = 0;
					}
					else {
						count++;
					}
				}
				break;
			default:
				break;
			}
			curr++;
			if (curr >= currentStaff.size()) {
				curr = partTimeIndex;
			}
		}
		//System.out.println(budgetHours);
		return sched;
	}
	
	
	
}