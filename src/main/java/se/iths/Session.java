package se.iths;

import java.time.LocalDate;
import java.time.ZoneId;

import java.time.Duration;

public class Session {
 
    LocalDate dateLocal=LocalDate.now(ZoneId.of("GMT+02:30"));
    //String date;
   public LocalDate date;
   public double time_seconds;
   public double distance_km;
   public double averageSpeed;
   public double kilometerTime;
   public String id;

//createRecord(String id, double distance_km, double time_seconds, LocalDate date)
    public Session(String id, double distance_km, double time_seconds, LocalDate date) {
    
        this.date =date;
        this.id = id;
        this.time_seconds = time_seconds;
        this.distance_km = distance_km;
        calculateAverageSpeed(time_seconds, distance_km);
        calculateKillometerTime(time_seconds,distance_km);
    }


    
    public Session(String id, double distance_km, double time_seconds){
        //this.date = LocalDate.parse(date);
        this.id =id;
        this.time_seconds = time_seconds;
        this.distance_km= distance_km;
        this.date = LocalDate.now();
        calculateAverageSpeed(time_seconds, distance_km);
        calculateKillometerTime(time_seconds,distance_km);
    }



    public void calculateAverageSpeed(double time_seconds, double distance_km){
        double time_hour= time_seconds/3600;
        
        averageSpeed=distance_km/time_hour;

    }
    
public void calculateKillometerTime(double time_seconds, double distance_km){
        double time_minutes= time_seconds/60;
        
        kilometerTime=time_minutes/distance_km;

    }



    public String toString(){
        return "ID:"+id+" " +"distance_km:"+distance_km + " "+"time_seconds:" + time_seconds + " "+"date:" + date ;
           
    }



    
    }




    

