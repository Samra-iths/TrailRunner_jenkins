package se.iths;

import java.time.LocalDate;
import java.time.ZoneId;

public class Session {
 
    LocalDate dateLocal=LocalDate.now(ZoneId.of("GMT+02:30"));
    //String date;
    LocalDate date;
    int time;
    double distance;

    public Session(int time, double distance) {
        date = LocalDate.now();
        this.time = time;
        this.distance = distance;
    }
    
    public Session(String date ,int time , double distance ){
        this.date = LocalDate.parse(date);
        this.time = time;
        this.distance= distance;

       // this.date=LocalDate.now(ZoneId.of("GMT+02:30"));

    }




    
}
