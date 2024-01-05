package se.iths;


public class User {
    double height_m;
    double weight_kg;
    double BMI;

    public User(double height_m, double weight){
        this.height_m=height_m;
        this.weight_kg =weight_kg;

    }


    public void calculateBMI(){
        BMI = weight_kg/Math.pow(height_m,2);
        
    }






    
}
