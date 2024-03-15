package se.iths;

import java.time.LocalDate;
import java.time.ZoneId;

import java.time.Duration;

public class Session {
 
    LocalDate dateLocal=LocalDate.now(ZoneId.of("GMT+02:30"));
    //String date;
   public LocalDate date;
   public double time_seconds;
   public double distance;
   public double averageSpeed;
   public double kilometerTime;

    public Session(double time_seconds, double distance) {
        date = LocalDate.now();
        this.time_seconds = time_seconds;
        this.distance = distance;
        calculateAverageSpeed(time_seconds, distance);
        calculateKillometerTime(time_seconds,distance);
    }
    
    public Session(String date ,long time_seconds , double distance ){
        this.date = LocalDate.parse(date);
        this.time_seconds = time_seconds;
        this.distance= distance;

    }
    public void calculateAverageSpeed(double time_seconds, double distance){
        double time_hour= time_seconds/3600;
        
        averageSpeed=distance/time_hour;

    }
    
public void calculateKillometerTime(double time_seconds, double distance){
        double time_minutes= time_seconds/60;
        
        kilometerTime=time_minutes/distance;

    }

    
}
