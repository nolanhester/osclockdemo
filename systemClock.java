import javax.swing.*;
import java.awt.event.*; 
import java.text.SimpleDateFormat;
import java.util.Scanner;
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
		
	}
	
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			milliCounter ++;	
		}
	}
}
