package se.iths;
import java.util.*;

import javax.xml.crypto.Data;

public class User {
    double height_m;
    double weight_kg;
    double BMI;
    Session session;
    Map <String, Session> savingSession = new HashMap<>();
    double totalDistance=0;
    double averageDistance=0;
   
    DatabaseAPI api;

    

    public User(double height_m, double weight_kg, DatabaseAPI api){
        this.height_m=height_m;
        this.weight_kg =weight_kg;
        this.api = api;
        //savingSession = new HashMap<>();

    }


    public void calculateBMI(){
        BMI = weight_kg/Math.pow(height_m,2);
        
    }


    public boolean saveSession(String key,Session session){

        return api.createRecord(key, session.distance, session.time_seconds, session.date);

    }


    public void calculateTotalDistance(Session session){

     totalDistance += session.distance;

    }


    public void calculateAverageDistance(double totalDistance, int numberOfSession){

    averageDistance = totalDistance/numberOfSession;

    }


    public String printDetailForSessionUsingID(String key){
        

      if(!savingSession.containsKey(key)){
        throw new IllegalArgumentException("Key does not exist");
       }
      else{
        Session session=savingSession.get(key);
        return session.toString();
        }

    }
    

    public void deleteSessionUsingID(String key){
       if(!savingSession.containsKey(key)){
        throw new IllegalArgumentException("Key does not exist");
       }
      else{
        savingSession.remove(key);
      }

    }

 









    
}
