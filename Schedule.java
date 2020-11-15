import java.util.ArrayList;

/*
 * @author Patrick Kupcha
 * Describes the Schedule class. A Schedule will be created each week and distributed to employees.
 */
public class Schedule {
	int totalHours;
	Staff currentEmployees;
	ArrayList<Shift> monday;
	ArrayList<Shift> tuesday;
	ArrayList<Shift> wednesday;
	ArrayList<Shift> thursday;
	ArrayList<Shift> friday;

	public Schedule(Staff currentStaff, int hoursToSchedule) {
		totalHours = hoursToSchedule;
		currentEmployees = currentStaff;
		monday = new ArrayList<Shift>();
		tuesday = new ArrayList<Shift>();
		wednesday = new ArrayList<Shift>();
		thursday = new ArrayList<Shift>();
		friday = new ArrayList<Shift>();
	}

	boolean addShift(Shift shiftToAdd) {
		int day = shiftToAdd.dayOfTheWeek;
		switch (day) {
		case 1:
			return monday.add(shiftToAdd);
		case 2:
			return tuesday.add(shiftToAdd);
		case 3:
			return wednesday.add(shiftToAdd);
		case 4:
			return thursday.add(shiftToAdd);
		case 5:
			return friday.add(shiftToAdd);
		default:
			return false;
		}
	}

	boolean removeShift(Shift shiftToRemove) {
		int day = shiftToRemove.dayOfTheWeek;
		switch (day) {
		case 1:
			return monday.remove(shiftToRemove);
		case 2:
			return tuesday.remove(shiftToRemove);
		case 3:
			return wednesday.remove(shiftToRemove);
		case 4:
			return thursday.remove(shiftToRemove);
		case 5:
			return friday.remove(shiftToRemove);
		default:
			return false;
		}
	}

	// place holder for future build schedule algo
	void buildSchedule() {

	}
}