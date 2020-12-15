import java.sql.SQLException;
import java.util.Date;

public class Request {

	
	String user;
	Date weekOf;
	Date start;
	Date end;
	int prevAvail;
	int dayNumber = -1;
	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Constructor
	 * 
	 * @params: Username of employee
	 */
	public Request(String user, Date weekOf, Date start, Date end, int prevAvail)
	{
		
		this.user = user;
		this.weekOf = weekOf;
		this.start = start;
		this.end = end;
		this.prevAvail = prevAvail;
		
		
	}
	/*
	 * @returns: number the day falls on in regards to the weekOf 
	 */
	public int getDay()
	{
		return dayNumber;
	}
	/*
	 * @returns:Get the prevAvail var
	 */
	public int getPrev()
	{
		
		return prevAvail;
		
	}
	/*
	 * sets the diff of the dayNumber
	 */
	public void setDiff(int diff)
	{
		
		dayNumber = diff;
		
	}
	/*
	 * Sets the prevAvail back in the database
	 */
	public void setPrevAvail() throws ClassNotFoundException, SQLException
	{
		
		EmployeeDatabase db = new EmployeeDatabase();
		
		prevAvail = db.setPrev(user, dayNumber);
		
		
	}
	/*
	 * Gets the weekOf for the Request
	 */
	public Date getWeekOf()
	{
		
		return weekOf;
		
	}
	/*
	 * @returns: start date of the request
	 */
	public Date getStart()
	{
		
		return start;
		
	}
	/*
	 * @returns: The end date of the request
	 */
	public Date getEnd()
	{
		return end;
	}
	/*
	 * @returns: user making the request
	 */
	public String getUser()
	{
		return user;
	}
	
}
