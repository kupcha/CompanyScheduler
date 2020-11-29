import java.sql.SQLException;
import java.util.Date;

public class Request {

	
	String user;
	Date weekOf;
	Date start;
	Date end;
	int prevAvail;
	int dayNumber = -1;
	
	public Request(String user, Date weekOf, Date start, Date end, int prevAvail)
	{
		
		this.user = user;
		this.weekOf = weekOf;
		this.start = start;
		this.end = end;
		this.prevAvail = prevAvail;
		
		
	}
	public int getDay()
	{
		return dayNumber;
	}
	public int getPrev()
	{
		
		return prevAvail;
		
	}
	public void revertAvail()
	{
		
	
		
		
	}
	public void setDiff(int diff)
	{
		
		dayNumber = diff;
		
	}
	public void setPrevAvail() throws ClassNotFoundException, SQLException
	{
		
		EmployeeDatabase db = new EmployeeDatabase();
		
		prevAvail = db.setPrev(user, dayNumber);
		
		
	}

	public Date getWeekOf()
	{
		
		return weekOf;
		
	}
	public Date getStart()
	{
		
		return start;
		
	}
	public Date getEnd()
	{
		return end;
	}
	public String getUser()
	{
		return user;
	}
	
}
