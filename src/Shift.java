import java.util.Comparator;

public class Shift{
	User employee;
	int hours; // 0 = 9am-9pm, 1=9am-1pm, 2=1pm-9pm
	int dayOfTheWeek; //1 monday, 2 tuesday, 3 wednesday, 4 thursday, 5 friday
	
	public Shift(User employee, int hours, int dayOfTheWeek) {
		this.employee = employee;
		this.hours = hours;
		this.dayOfTheWeek = dayOfTheWeek;
	}
	
	public User whoIsWorking() {
		return this.employee;
	}
	
	public boolean changeHours(int newHours) {
		if (this.hours == newHours) {
			return false;
		}else {
			this.hours = newHours;
			return true;
		}
	}
	
	public boolean changeWorkDay(int newDay) {
		if (this.dayOfTheWeek == newDay) {
			return false;
		}else {
			this.dayOfTheWeek = newDay;
			return true;
		}
	}
	
	
	
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