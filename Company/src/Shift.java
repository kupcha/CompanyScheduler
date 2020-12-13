/**
 * @author Patrick Kupcha
 * 
 * Describes the Shift class, the building block of the Schedule class
 */
import java.util.Comparator;


public class Shift{
	User employee;
	int hours; // 0 = 9am-9pm, 1=9am-1pm, 2=1pm-9pm
	int dayOfTheWeek; //1 monday, 2 tuesday, 3 wednesday, 4 thursday, 5 friday
	
	//constructor
	public Shift(User employee, int hours, int dayOfTheWeek) {
		this.employee = employee;
		this.hours = hours;
		this.dayOfTheWeek = dayOfTheWeek;
	}
	
	
	//Used to sort shifts based on time of day -- this is used in the buildSchedule function
    public static Comparator<Shift> shiftHours = new Comparator<Shift>() {

	public int compare(Shift s1, Shift s2) {

	   int shift1 = s1.hours;
	   int shift2 = s2.hours;

	   /*For ascending order*/
	   return shift1 - shift2;

	   /*For descending order*/
	   //return emp2 - emp1;
   }};
}