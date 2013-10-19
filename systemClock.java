import javax.swing.*;
import java.awt.event.*; 
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.StringTokenizer;
public class systemClock
{
    public static void main(String[] args) 
        {
            
            boolean runAgain = true;
            DemoMethods demo = new DemoMethods();
            Scanner keyboard = new Scanner(System.in);
            demo.startClock();
            int select;
            do{
            	System.out.print("Select a function:\n1. Show time of day.\n2. Demo process timing\n3. Demo CPU accounting\n4. Demo alarm system call\n5. Demo watchdog timer\n6. Demo profiling\n7. Exit\n");
            	select = keyboard.nextInt();
            	switch(select)
            	{
            		case 1: System.out.println(demo.timeOfDay());
            				break;
            		case 2: System.out.print("Enter a quantum number: ");
            				int num = keyboard.nextInt();
            				demo.processTimer(num);
            				break;
            		case 3: demo.cpuTimer();
            				break;
            		case 4: System.out.println("Enter time intervals to generate signals: ");
            				String input = keyboard.next();
            				demo.alarmCall(input);
            				break;
            		case 7: runAgain = false;
            				break;
            		default: System.out.println("Invalid option");
            	}
            	
            }while(runAgain == true);
            System.exit(0);
    }
}
class DemoMethods
{
	//Grabs time since the epoch and sets up a counter to use with the clock
    private long bootTime = System.currentTimeMillis();
    private long milliCounter = 0;
    Scanner scn = new Scanner(System.in);
	
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
	
	public void cpuTimer()
	{
		Timer cpuTimer = new Timer(1,new MyActionListener());
		cpuTimer.start();
		System.out.println("New process started.");
		System.out.println("Press enter to stop process and show total running time.");
		scn.nextLine();
		cpuTimer.stop();
		System.out.println("The process ran for " + milliCounter + " ticks"); 
	}
	
	public void alarmCall(String input)
	{
		System.out.println("The clock driver will send signals at the following intervals: " + input);
		StringTokenizer str = new StringTokenizer(input,",");
		double signalTimes[] = new double[str.countTokens()];
		int j = 0;
		while(str.hasMoreTokens())
		{
			String temp = str.nextToken();
			signalTimes[j] = Double.parseDouble(temp);
			System.out.println(j);
			j++;
		}
		double currentTime = getElapsedTime();
		for(int i=0; i<signalTimes.length; i++)
		{
			boolean signalNotSent = true;
			while(signalNotSent == true)
			{
				System.out.print("");
				if ((currentTime + signalTimes[i] == getElapsedTime()) || (currentTime + signalTimes[i] <= getElapsedTime()) ){
					System.out.println("Signal sent at: " + (currentTime + signalTimes[i]));
					currentTime += signalTimes[i];
					signalNotSent = false;
					}
			}
		}
		
	}
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			milliCounter ++;	
		}
	}
}
