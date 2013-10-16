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
            Thread.sleep(1000);
            System.out.println(demo.getElapsedTime()/1000); 
            System.out.println(demo.timeOfDay());
            System.out.print("Enter how often to generate interrupts (in milliseconds): "); 
            int numInterrupts = keyboard.nextInt();
            System.out.print("Enter quantum number for sample process: ");
            int quantumNum = keyboard.nextInt();
            
            System.exit(0);
    }
}
class DemoMethods
{
    private long bootTime = System.currentTimeMillis();
    private long milliCounter = 0;
	public void startClock()
	{
		Timer timeFromBoot = new Timer(1,new MyActionListener()); //start time
		timeFromBoot.start();
		
	}
	
	public double getElapsedTime()
	{
		double elapsedTime = milliCounter + bootTime;
		return elapsedTime;
	}
	
	public String timeOfDay()
	{
		SimpleDateFormat readableDate = new SimpleDateFormat("h:mm a");
		String time = readableDate.format(getElapsedTime());
		return time;
	}
	public void processTime(int numInterrupts, int quantumNum)
	{
		double time = getElapsedTime();
		if (time % numInterrupts == 0)
		{
			quantumNum --;
			System.out.println(quantumNum);
		}
	}
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			milliCounter ++;	
		}
	}
}
