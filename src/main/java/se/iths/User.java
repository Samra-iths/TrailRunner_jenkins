package se.iths;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.xml.crypto.Data;
import java.time.LocalDate;



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

//createRecord(String id, double distance_km, double time_seconds, LocalDate date)
    public boolean saveSession(String id, double distance_km, double time_seconds, LocalDate date) throws SQLException{
       if(api.createRecord( id,  distance_km,  time_seconds,  date)==false){
        throw new SQLException("Error: Duplicate ID");
       }
        return api.createRecord(id,  distance_km,  time_seconds,  date);

    }


    public void calculateTotalDistance(String key){
    Session currentSession=api.readRecord(key);
     totalDistance += currentSession.distance_km;

    }


    public void calculateAverageDistance(double totalDistance, int numberOfSession){

    averageDistance = totalDistance/numberOfSession;

    }


    public Session printDetailForSessionUsingID(String key) throws SQLException{
        
      if(api.readRecord(key)==null){
        throw new SQLException("Error: ID not recognized");
       }
      else{
       
        return api.readRecord(key);
        }
    }


    public boolean deleteSessionUsingID(String key) throws SQLException{
      
       if(api.deleteRecord(key)==false){
        throw new SQLException("Key does not exist");
        
       }
      else{
        return true;
       
    
    }
    }
    
   public List<String> readIDFromDatabase(){

    return api.getRecordIDs();

   }
   

 public List<Session> filterSessionWithDistance(int distance, String condition ){
  List<Session> filteredSessions = new ArrayList<>();
  
  for(String id:api.getRecordIDs()){

    
    
    switch (condition) {
      case "Greater":
          if(api.readRecord(id).distance_km > distance){
      filteredSessions.add(api.readRecord(id));
    }
          break;
      case "less":
          if(api.readRecord(id).distance_km < distance){
      filteredSessions.add(api.readRecord(id));
    }
          break;
      case "equal":
             if(api.readRecord(id).distance_km == distance){
      filteredSessions.add(api.readRecord(id));
    }
          break;
      

  }
  }
  return filteredSessions;



 }

   
}
