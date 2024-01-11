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


    public Session printDetailForSessionUsingID(String key){
        

      if(api.readRecord(key).equals(null)){
        throw new IllegalArgumentException("Key does not exist");
       }
      else{
       
        return api.readRecord(key);
        }

    }



//     public Session NewprintDetailForSessionUsingID(String key)  {
//     try  {
//         return api.readRecord(key);
//     } catch (Exception e) {
//         System.out.println("Error: ID not recognized - " );
       
//         throw e;
//     }
// }
    

    public void deleteSessionUsingID(String key){
       if(!savingSession.containsKey(key)){
        throw new IllegalArgumentException("Key does not exist");
       }
      else{
        savingSession.remove(key);
      }

    }

 









    
}
