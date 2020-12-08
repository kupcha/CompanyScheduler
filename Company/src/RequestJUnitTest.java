import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class RequestJUnitTest{
	String week1 = "2020-29-11";
	String start1 = "2020-30-11";
	String end1 = "2020-30-12";
	
	DateFormat df = new SimpleDateFormat("yyy-MM-dd");
	
	Date week = new Date();
	Date start = new Date();
	Date end = new Date();
	
			
			//Date weekOf, Date start, Date end, int prevAvail
	@Test
	public void testGetDay() throws ParseException
	{
	week = df.parse(week1);
	start = df.parse(start1);
	end = df.parse(end1);
	
	
	Request requestTest1 = new Request("Kyle",week, start, end ,-1);
		requestTest1.setDiff(1);
		
		assertEquals(1, requestTest1.getDay());
		//fail("Not yet implemented");
	}

	@Test
	public void testGetPrev() throws ParseException {
		week = df.parse(week1);
		start = df.parse(start1);
		end = df.parse(end1);
		
		
		Request requestTest1 = new Request("Kyle",week, start, end ,-1);
		assertEquals(-1, requestTest1.getPrev());
		//fail("Not yet implemented");
	}

	@Test
	public void testGetWeekOf() throws ParseException {
		week = df.parse(week1);
		start = df.parse(start1);
		end = df.parse(end1);
		
		
		Request requestTest1 = new Request("Kyle",week, start, end ,-1);
		assertEquals(week, requestTest1.getWeekOf());
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetStart() throws ParseException {
		week = df.parse(week1);
		start = df.parse(start1);
		end = df.parse(end1);
		
		
		Request requestTest1 = new Request("Kyle",week, start, end ,-1);
		assertEquals(start, requestTest1.getStart());
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetEnd() throws ParseException {
		week = df.parse(week1);
		start = df.parse(start1);
		end = df.parse(end1);
		
		
		Request requestTest1 = new Request("Kyle",week, start, end ,-1);
		assertEquals(end, requestTest1.getEnd());
		
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetUser() throws ParseException {
		week = df.parse(week1);
		start = df.parse(start1);
		end = df.parse(end1);
		
		
		Request requestTest1 = new Request("Kyle",week, start, end ,-1);
		assertEquals("Kyle", requestTest1.getUser());
		
		//fail("Not yet implemented");
	}

}
