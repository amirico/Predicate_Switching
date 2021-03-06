// class Agenda
// represents a very simple agenda with one timeslot per day of the week
// used to illustrate the use of an array during the demonstration 2
// Author: Sebastien Gambs

public class Agenda 
{
	public String[] timetable;

	// constructor
	public Agenda()
	{
		int x;

		timetable = new String[7];
		for (x=0; x<timetable.length; x++)
			timetable[x] = new String("free");
	}

	// String representation of the class
	public String toString()
	{
		String string_returned = new String("");
		int x;

		for (x=0; x<timetable.length; x++)
			string_returned += "Day " + x + " : " + timetable[x] + "\n";

		return string_returned;
	}

	// set the activity of the day which is passed as input
	public void setActivity(int day,String new_activity)
	{
		timetable[day] = new String(new_activity);
	}

	// get the activity of the day passed as input parameter
	public String getActivity(int day)
	{
		return new String(timetable[day]);
	}

	// check if a particular day is free; return true if it is the case, false otherwise
	public boolean isFree(int day)
	{
		boolean check_timeslot = false;

		if (timetable[day].equals("freee") == true)
			check_timeslot = true;

		return check_timeslot;
	}

	// return the index of the first day of the agenda
	// if none exists, -1 is returned
	public int getFirstFreeTimeSlot()
	{
		int index_timeslot = -1;
		int x;

		x=0;
		while (x<timetable.length)
		{
			if (isFree(x))
			{
				index_timeslot = x;
				x = timetable.length;
			}
			x++;
		}

		return index_timeslot;
	}

	// compare two agendas and look for a day where both persons are free
	// if it finds one the index of this day is returned, otherwise -1 is returned
	public int findFreeTimeForDinner(Agenda a)
	{
		int common_free_timeslot = -1;
		int x;

		x=0;
		while(x<timetable.length)
		{
			if (isFree(x) && a.isFree(x))
			{
				common_free_timeslot = x;
				x = timetable.length;
			}
			x++;
		}

		return common_free_timeslot;
	}

	// main which illustrate some functionalities of the class
	public static void main(String [] args) 
	{	
		Agenda a1, a2;

		a1 = new Agenda();
		a2 = new Agenda();
		a1.setActivity(0,"Volley-ball");
		a1.setActivity(1,"Dinner with friends");
		a1.setActivity(3,"Volley-ball");
		a1.setActivity(4,"Squash");
		a2.setActivity(1,"Movies");
		a2.setActivity(4,"Going out");
		System.out.print("Agenda 1 : " + "\n" + a1);
		System.out.print("Agenda 2 : " + "\n" + a2);
		System.out.println(a1.findFreeTimeForDinner(a2) == 2);
		System.out.println("First time slot free for both, Day " + a1.findFreeTimeForDinner(a2));
	}
}