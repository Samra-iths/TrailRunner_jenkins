package se.iths;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.time.LocalDate;

public class TestUser {
    User user;

   @BeforeEach
   public void setUpUser(){
        user= new User(46, 60);
         }

   @Test
   public void testUserIsCreated(){
    assertEquals(user,user);
   }


  @Test
  public void testUserSetHeightSuccessfully(){
    user=new User(50,60);
    assertEquals(50, user.height_m);
  }

   @Test
  public void testUserSetWeightSuccessfully(){
    user = new User(50,60);
    assertEquals(60, user.weight_kg);
  }


// •	Users should be able to calculate their BMI 
// (Body Mass Index) using an automatic method according 
// to the formula: 𝐵𝑀𝐼 = 𝑤𝑒𝑖𝑔ℎ𝑡 (kg)/ 2 ℎ𝑒𝑖𝑔ℎ𝑡 (m)


@Test

public void calculateBMI (){

 user.calculateBMI();

 double expectedBMI = user.weight_kg/Math.pow(user.height_m,2);
 

assertEquals(expectedBMI,user.BMI);


}



//     Users should be able to save a running session with the following attributes:
// Distance (km)
// Time (hours, minutes, seconds)
// Date (Year-Month-Day)




@Test
public void testSavedSessionWithDate(){

  //user.session1 = date (2024_Jan_05), Time:14:00 , Distnace: km

  Session sessionOne = new Session("2024-01-05",1800,1);
  LocalDate expecteddate= LocalDate.of(2024,01,05);


assertEquals(expecteddate, sessionOne.date);



}

@Test
public void testSavedSessionWithTIme(){

  Session sessionOne = new Session("2024-01-05",1800,1);
  
 assertEquals(1800, sessionOne.time);
}

@Test
public void testSavedSessionWithDistance(){

  Session sessionOne = new Session("2024-01-05",1800,1);
  
 assertEquals(1, sessionOne.distance);

 }

 @Test
public void testUserSaveSessionWithUniqueID(){


  Session sessionOne = new Session("2024-01-05",1800,1);
 user.saveSession();
  
 assertEquals(1, sessionOne.distance);

 }





    
}
