import javax.swing.*;
import java.awt.event.*; 
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class systemClock
{
    public static void main(String[] args)
        throws InterruptedException 
        {
            
            DemoMethods demo = new DemoMethods();
            Scanner keyboard = new Scanner(System.in);
            demo.startClock();
            //Thread.sleep(10000);
            System.out.println(System.currentTimeMillis());
            System.out.println(demo.getElapsedTime());
            System.out.println(demo.getElapsedTime()/1000); 
            System.out.println(demo.timeOfDay());
            System.out.print("Enter quantum number for sample process: ");
            int quantumNum = keyboard.nextInt();
            demo.processTimer(quantumNum);
            System.exit(0);
    }
}
class DemoMethods
{
	//Grabs time since the epoch and sets up a counter to use with the clock
    private long bootTime = System.currentTimeMillis();
    private long milliCounter = 0;
	
	//Sets up a timer to keep track of ms since initial time grab
	public void startClock()
	{
		Timer timeFromBoot = new Timer(1,new MyActionListener()); //start time
		timeFromBoot.start();
		
	}
	
	//Calculates current time in ms
	public double getElapsedTime()
	{
		double elapsedTime = milliCounter + bootTime;
		return elapsedTime;
	}
	
	//Will return the current time based on our millisecond measurement.  Uses
	// a built-in class to convert to readable time
	public String timeOfDay()
	{
		SimpleDateFormat readableDate = new SimpleDateFormat("h:mm a");
		String time = readableDate.format(getElapsedTime());
		return time;
	}
	
	//Simulates how the clock prevents processes from running too long
	public void processTimer(int quantumNum)
	{
		System.out.println("New process running for " + quantumNum + " ticks");
		double processStartTime = getElapsedTime();
		double runningTime;
		while (quantumNum >= 0)
		{
			System.out.print(""); 
			if((getElapsedTime()-processStartTime) >= quantumNum)
				quantumNum = -1;
		}
		System.out.println("Process time limit reached");
		
	}
	
	
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			milliCounter ++;	
		}
	}
}
