package se.iths;
import java.util.*;

public class User {
    double height_m;
    double weight_kg;
    double BMI;
    Session session;
    Map <String, Session> savingSession = new HashMap<>();
    double totalDistance=0;
    double averageDistance=0;


    

    public User(double height_m, double weight_kg){
        this.height_m=height_m;
        this.weight_kg =weight_kg;
        //savingSession = new HashMap<>();

    }


    public void calculateBMI(){
        BMI = weight_kg/Math.pow(height_m,2);
        
    }




   

    public boolean saveSession(String key,Session session){
        
        boolean saveSession = false;

        if(!savingSession.containsKey(key)){
        savingSession.put(key, session);

        saveSession=true;
        return saveSession;
        }

        else
        return saveSession;
    
    }


public void calculateTotalDistance(Session session){

totalDistance += session.distance;

}




public void calculateAverageDistance(double totalDistance, int numberOfSession){

averageDistance = totalDistance/numberOfSession;

}












    
}
