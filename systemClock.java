import javax.swing.*;
import java.awt.event.*; 
public class systemClock
{
    public static void main(String[] args)
        throws InterruptedException 
        {
            
            DemoMethods demo = new DemoMethods();
            demo.startClock();
            Thread.sleep(1000);
            System.out.println(demo.getElapsedTime()/1000); 
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
		double elapsedTime = System.currentTimeMillis() - bootTime;
        return elapsedTime;
	}
	
	public double getElapsedTimeTimer()
	{
		double elapsedTime2 = milliCounter - bootTime;
		return elapsedTime2;
	}
	
	public void Interupt()
	{
		double time = getElapsedTime();
	}
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			milliCounter ++;	
		}
	}
}
