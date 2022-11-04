import javax.swing.*; // To use input dialogs
import java.util.Date; // The class Date represents a specific instant in time. (1)
import java.text.ParseException; // Signals errors have been reached unexpectedly while parsing. Helpful for parsing unusal objects(2) e.g String to Date.
import java.text.SimpleDateFormat; // Helps in formatting and unformatting time
/**
 * Write a description of class LongDistanceCall here.
 * 
 * This program computes long distance calls, with different rates in differenet times.
 * The program would calculate calls from 08:00 AM to 16:00 PM to be at 0.40€ per minutes in weekdays
 * 0.25€ per minute for other times in weekdays, this program can also calculate mixed rates and times.
 * Calls on weekends (Saturday, Sunday) will have a constant rate of 0.15€ per minute not matter what time.
 *
 * Naif Alsulaiman (21230176)
 * Assignment1 (03/10/2022)
 */
public class LongDistanceCall //class name

{
    public static void main(String[] args) throws ParseException{

        String callStart = "00:00", callEnd = "00:00", callDay, PM = "18:00", AM = "08:00"; // Time strings
        int minutes1, minutes2; // Minutes in integer 
        double normalCost, changingCost, totalCost; // normal cost = constant rates, changing is when there are two or more rates at the same time. 

        // Input dialogs to store call start, end and day of call.
        callStart = JOptionPane.showInputDialog(null,"Enter call start time: \n\n e.g 12:00" , "Call Start Time" , -1);  
        callEnd = JOptionPane.showInputDialog(null,"Enter call end time: \n\n e.g 12:00" , "Call End Time" , -1);
        callDay = JOptionPane.showInputDialog(null, "Enter first two letters of the day of call: \n\n e.g Monday is Mo", "Day of the Call", -1);

        //SimpleDateFormat is a concrete class for formatting and parsing dates in a locale-sensitive manner. It allows for formatting (date → text), parsing (text → date), and normalization.
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        // Date is a type of string that can understand times, this is created by importing thee class date
        // This will help in inserting a string with specific time e.g 18:00
        Date callStartTime = format.parse(callStart); // Parses the input start time into a new Date format
        Date callEndTime = format.parse(callEnd); // Parses the end input time into a new Date format
        Date eveningTime = format.parse(PM); // Parses 18:00 PM to eveningTime 
        Date morningTime = format.parse(AM); // Parses the string 08:00 AM to morningtime in HH:MM format

        long difference = (callEndTime.getTime() - callStartTime.getTime()); // Stores seconds from call start and end time
        long mixedRateMinutes; // Variable for storing counting seconds (bonus grade minutes)

        // If call starts after 18:00 and ends before 08:00 and call day is not Saturday or Sunday.
        if(callStartTime.after(eveningTime) && callEndTime.after(morningTime) && (!callDay.startsWith("S")))
        {

            minutes1 = (int) (difference / 60000 ); // Converts call seconds to minutes
            if(minutes1 > 180){ // Makes sure length of call is not above the limit given
                JOptionPane.showInputDialog("You cannot excceed more than 3 hours per day"); // Illustration message for exceeding 180 mins limit
                System.exit(0); // Stop

            }
            normalCost = minutes1 * 0.25; // Multiplies total minutes by cheap rate.
            totalCost = normalCost; // Sends cheap rate total cost to the totalCost variable

            JOptionPane.showConfirmDialog(null, "Time in minutes: " + minutes1 + "\n\n Total cost: €" + totalCost, "Final output", -1); // final output message

        }
        // If call starts before 08:00 and ends before 18:00 and the day of call is not Saturday or Sunday
        if(callStartTime.after(morningTime) && callEndTime.before(eveningTime) && (!callDay.startsWith("S")))
        {

            minutes1 = (int) (difference / 60000 ); // converts seconds to minutes
            if(minutes1 > 180){ // Makes sure length of call is not above the limit given
                JOptionPane.showInputDialog("You cannot excceed more than 3 hours per day"); // Illustration message for exceeding 180 mins limit
                System.exit(0); // Stop

            }
            normalCost = minutes1 * 0.40; // Multiplies total minutes by expensive rate.
            totalCost = normalCost; // Calculating minutes * expensive rate

            JOptionPane.showConfirmDialog(null, "Time in minutes: " + minutes1 + "\n\n Total cost: €" + totalCost, "Final output", -1); // final output message

        }
        // If call starts before 08:00 and ends after 08:00 and day is not Saturday nor Sunday. (BONUS GRADE MIXED RATE) 
        if (callStartTime.before(morningTime) && callEndTime.after(morningTime) && (!callDay.startsWith("S")) 
            // Or Call starts before 08:00 and ends before 08:00 and day is not Saturday or Sunday
        || (callStartTime.before(morningTime) && callEndTime.before(morningTime) && (!callDay.startsWith("S"))))
        {

            mixedRateMinutes = (morningTime.getTime() - callStartTime.getTime());  // Calculates minutes before 08:00 and after 16:00
            difference = (callEndTime.getTime() - morningTime.getTime()); // Calculates minutes within 08:00 and 16:00
            minutes1 = (int) (difference / 60000 ); // converts seconds to minutes
            minutes2 = (int) (mixedRateMinutes / 60000 ); // converts seconds to minutes
            if(minutes1 + minutes2 > 180){ // Makes sure length of call is not above the limit given
                JOptionPane.showInputDialog("You cannot excceed more than 3 hours per day"); // Illustration message for exceeding 180 mins limit
                System.exit(0); // Stop

            }
            changingCost = minutes2 * 0.25; // Multiplies minutes from 16:00 to 08:00 by 0.25
            normalCost = minutes1 * 0.40; // Multiplies minutes within 08:00 to 16:00 by 0.40
            totalCost = changingCost + normalCost; // calculates the total cost
            int totalMinutes = minutes1 + minutes2; // calculates total minutes in the whole call
            JOptionPane.showConfirmDialog(null, " \n Time in high cost minutes: " + minutes1 + " \n Time in cheap minutes:" + minutes2 + " \n total minutes: " + totalMinutes + "\n\n Total cost: €" + totalCost, "Final output", -1);  // final output message

        }
        if(callStartTime.before(eveningTime) && callEndTime.after(eveningTime) && (!callDay.startsWith("S")))
        {
            difference = (eveningTime.getTime() - callStartTime.getTime()); // Calculates minutes before 08:00 and after 16:00
            mixedRateMinutes = (callEndTime.getTime() - eveningTime.getTime()); // Calculates minutes within 08:00 and 16:00
            minutes1 = (int) (difference / 60000 ); // converts seconds to minutes
            minutes2 = (int) (mixedRateMinutes / 60000 ); // converts seconds to minutes
            if(minutes1 + minutes2 > 180){ // Makes sure length of call is not above the limit given
                JOptionPane.showInputDialog("You cannot excceed more than 3 hours per day"); // Illustration message for exceeding 180 mins limit
                System.exit(0); // Stop

            }
            changingCost = minutes2 * 0.25; // Multiplies minutes from 16:00 to 08:00 by 0.25
            normalCost = minutes1 * 0.40; // Multiplies minutes within 08:00 to 16:00 by 0.40
            int totalMinutes = minutes1 + minutes2; // calculates the total cost
            totalCost = changingCost + normalCost; // calculates total minutes in the whole call
            JOptionPane.showConfirmDialog(null, "Time in minutes: " + totalMinutes + "\n\n Total cost: €" + totalCost, "Final output", -1); // final output message

        }
        // If call of the day is Saturday or Sunday
        if(callDay.startsWith("S"))
        {
            minutes1 = (int) (difference / 60000 ); // converts seconds to minutes

            if(minutes1 > 180){ // Makes sure length of call is not above the limit given
                JOptionPane.showInputDialog("You cannot excceed more than 3 hours per day"); // Illustration message for exceeding 180 mins limit
                System.exit(0); // Stop

            }

            normalCost = minutes1 * 0.15; // // Multiplies minutes by Weekend rate (0.15)
            totalCost = normalCost; // total cost being valid

            JOptionPane.showConfirmDialog(null, "Time in minutes: " + minutes1 + "\n\n Total cost: €" + totalCost, "Final output", -1); // final output message
        }
    }
}

