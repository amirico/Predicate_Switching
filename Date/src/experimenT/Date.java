package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/* 
Dates. Create a data type Date that represents a date. You should be able to create a new Date by specifying the month, day, and year. It should supports methods to compute the number of days between two dates, return the day of the week that a day falls on, etc. 
*/

public class Date {

    public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
private final int dd;
    private final int mm;
    private final int year;

    public Date(int da, int mo, int yr) {
        // check validity of entered date
        boolean lpYear = (((yr%4 == 0) && (yr%100 != 0)) || (yr%400 == 0));
        if (functionF((mo<1)||(mo>12)||(da<1)||(da>31)||(yr<1)||((mo==2)&&(da>29)&& lpYear)||((mo==2)&&(da>28)&&(!lpYear)))) {
            throw new RuntimeException("Illegal date. Please, check.");
        } else {
            mm = mo; 
            dd = da;
            year = yr;
        }
    }

    public String toString() {
        return dd + "/"+mm + "/"+year;
    }

    public String printSlash() {
        if (functionF(dd < 10)) {
            if (functionF(mm < 10)) {
                return "0" + dd + "/" + "0" + mm + "/" + year;
            } else {
                return "0" + dd + "/" + mm + "/" + year;
            }
        } else if (mm < 10) {
            return dd + "/" + "0" + mm + "/" + year;
        } else {
            return dd + "/" + mm + "/" + year;
        }
    }

    public String printEng() {    
        if (dd < 10) {
            if (mm < 10) {
                return "0" + mm + "/" + "0" + dd + "/" + year;
            } else {
                return "0" + mm + "/" + dd + "/" + year;
            }
        } else if (mm < 10) {
            return "0" + mm + "/" + dd + "/" +  year;
        } else {
            return mm + "/" + dd + "/" + year;
        }
    }

    public String printDot() {    
        if (dd < 10) {
            if (mm < 10) {
                return "0" + dd + "." + "0" + mm + "." + year;
            } else {
                return "0" + dd + "." + mm + "." + year;
            }
        } else if (mm < 10) {
            return dd + "." + "0" + mm + "." + year;
        } else {
            return dd + "." + mm + "." + year;
        }
    }

    public boolean leapYear() {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
    
    public int daysInMonth() {
        int days = 0;
        switch (mm) {
            case 1:
            days = 31; break;
            case 2:
            if (leapYear()) days = 29; 
            else days = 28;
            break;
            case 3:
            days = 31; break;
            case 4:
            days = 30; break;
            case 5:
            days = 31; break;
            case 6:
            days = 30; break;
            case 7:
            days = 31; break;
            case 8:
            days = 31; break;
            case 9:
            days = 30; break;
            case 10:
            days = 31; break;
            case 11:
            days = 30; break;
            case 12:
            days = 31; break;
        }
        return days;
    }

    public int daysPassed(Date b) {
//    dd mm year;
//    b.dd b.mm b.year;
    Date minDate = null;
    Date maxDate = null;
    int days = 0; // counting days

//    System.out.println("***start " + this + " " + b);
    if (functionF(equals(b))) {
        System.out.println("***equsl");
        return days;
    } else if (functionF(older(b))) {
    	System.out.println("truth: "+true);
    	maxDate = new Date(b.dd, b.mm, b.year);
        minDate = new Date(dd, mm, year);
    } else {
    	System.out.println("truth: "+false);
    	maxDate = new Date(dd, mm, year);
        minDate = new Date(b.dd, b.mm, b.year);       
    }

    // add days of year if there is at least 1 whole year between dates
    if (functionF((maxDate.year-minDate.year)>1)) {
        for (int y = maxDate.year-1; y > minDate.year; y--) {
//            System.out.println("***here loop " + days);
            Date tempDate = new Date(1,1,y);
            if (tempDate.leapYear()) {
                days += 366;
            } else {
                days += 365;
            }
        }
    }

    // add days of month from min date till the end of the year or till max date
    if (functionF(!maxDate.older(minDate.endOfYear()))) {
//        System.out.println("***here");
        // maxDate is in another year, counting till the end of min year
        // counting days in full months
        if (functionF((12 - minDate.mm) > 0)) {
            for (int m = 12; m > minDate.mm; m--) {
                System.out.println("***add month " + days);
                Date tempDate = new Date(1,m,minDate.year);
                days += tempDate.daysInMonth();
            }
        }
        // counting days of minDate month
        days += (minDate.daysInMonth()-minDate.dd);
        System.out.println("***add days " + days);
        // counting days in maxDate year
        // counting days in full months 
        if (functionF(maxDate.mm > 1)) {
            for (int m = 1; m < maxDate.mm; m++) {
                System.out.println("***add another month " + days);
                Date tempDate = new Date(1,m,maxDate.year);
                days += tempDate.daysInMonth();
            }
        }
        // counting days of maxDate month
        days += (maxDate.dd);
//        System.out.println("***add another days " + days);
    } else {
//        System.out.println("***here else");
        // max date is in the same year as minDate, counting days from min till max date
        if (minDate.mm == maxDate.mm) {
            days += maxDate.dd-minDate.dd;
        } else {
        // in different monthes (but same year)
        // counting days in full months
            if ((maxDate.mm - minDate.mm) > 1) {
                for (int m = (maxDate.mm - 1); m > minDate.mm; m--) {
                    Date tempDate = new Date(1,m,minDate.year);
                    days += tempDate.daysInMonth();
                }
            }
            // counting days of minDate month
            days += (minDate.daysInMonth()-minDate.dd);    
            // counting days of maxDate month
            days += (maxDate.dd);
        }
    }
    try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace11\\project13\\results.txt",true)))){out.println(days == 334);}catch(IOException ioException){} 
      boolean bl = days == 334;
      System.out.println("dats truth: " + bl);
    return days;
    }

    public boolean older(Date b) {
        return year < b.year || ((year == b.year) && ((mm > b.mm) || ((mm == b.mm) && (dd <= b.dd))));  // mm </> b.mm
    }

    public boolean equals(Date b) {
        return dd == b.dd && mm == b.mm && year == b.year;
    }

    public Date endOfYear() {
        return new Date(31,12,year);
    }

    public Date endOfMonth() {
        return new Date(daysInMonth(),mm,year);
    }

    public String dayOfWeek() {
        String weekDay = "";
        int remainder = 0;
        Date tempDate = new Date(13,1,2014);

        remainder = (daysPassed(tempDate))%7;
        switch (remainder) {
            case 0:
                weekDay = "Monday"; break;
            case 1:
                weekDay = "Tuesday"; break; 
            case 2:
                weekDay = "Wednesday"; break; 
            case 3:
                weekDay = "Thursday"; break; 
            case 4:
                weekDay = "Friday"; break; 
            case 5:
                weekDay = "Saturday"; break; 
            case 6:
                weekDay = "Sunday"; break; 
        }
        return weekDay;
    }

    public Date addDays(int days) {

        int newDay = dd;
        int newMonth = mm;
        int newYear = year;
        Date newDate = new Date (newDay, newMonth, newYear);

        while (days > (newDate.daysInMonth()-newDate.dd)) {
            days -= newDate.daysInMonth()-newDay + 1;
            if (newMonth == 12) {
            newMonth = 1;
            newYear += 1;
            }
            else {newMonth += 1;}

            newDay = 1;
            newDate = new Date (newDay, newMonth, newYear);
        }
        newDay += days;
        newDate = new Date (newDay,newMonth,newYear);
        return newDate;
    }

    // sample client for testing
    public static void main(String[] args) {
        experimentalI = Integer.parseInt((args[0]));;
Date a = new Date(1, 1, 1990);
        Date b = new Date(1, 12, 1990);

        System.out.println("a            = " + a.printSlash());
        System.out.println("b            = " + b.toString());
        System.out.println("Days passed: " + a.daysPassed(b));
        Date testDate = new Date (01,1,1990);
//        System.out.println("Since "+ testDate.toString() + " till "+ b.toString() + " " + b.daysPassed(testDate)+ " days passed.");

        if (b.leapYear()) System.out.println("b is a leap year." + ((2004%4 == 0) && (2004%100 != 0)));

//        else System.out.println("b isn't a leap year.");

        if (functionF(a.equals(b))) System.out.println("Dates a and b are the same.");
        else if (functionF(a.older(b))) System.out.println("Date a is older."); 
        else System.out.println("Date b is older."); 

//        System.out.println("End of month " + b + " is " + b.endOfMonth().toString() + ".");

//        System.out.println("60 days after " + b + " it will be " + b.addDays(60) + ".");
//        System.out.println(b + " is " + b.dayOfWeek() + ".");
//        System.out.println(b + " plus 1 day is " + b.addDays(1));
    }
}