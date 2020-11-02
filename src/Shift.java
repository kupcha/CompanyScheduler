public class Shift{
	User employee;
	int hours; // 0 = 9am-9pm, 1=9am-1pm, 2=1pm-9pm
	int dayOfTheWeek; //1 monday, 2 tuesday, 3 wednesday, 4 thursday, 5 friday
	
	public Shift(User employee, int hours, int dayOfTheWeek) {
		this.employee = employee;
		this.hours = hours;
		this.dayOfTheWeek = dayOfTheWeek;
	}
	
}