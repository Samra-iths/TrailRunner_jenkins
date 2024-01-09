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


@Test

public void calculateBMI (){

 user.calculateBMI();

 double expectedBMI = user.weight_kg/Math.pow(user.height_m,2);
 
assertEquals(expectedBMI,user.BMI);

}


 @Test
public void testUserSaveSessionWithUniqueID(){


Session sessionOne = new Session("2024-01-05",1800,1);
Session sessionTwo = new Session("2024-01-09",2000,2);

 user.saveSession("SD123",sessionOne);
 user.saveSession("SD2024",sessionTwo);
  
 assertEquals(sessionOne, user.savingSession.get("SD123"));

 assertEquals(sessionTwo, user.savingSession.get("SD2024"));

 }


  @Test
public void testUserSaveSessionWithExistingKeyIsFailed(){


Session sessionOne = new Session("2024-01-05",1800,1);
Session sessionTwo = new Session("2024-01-09",2000,2);

 assertTrue(user.saveSession("SD123",sessionOne));
 assertFalse(user.saveSession("SD123",sessionTwo));
 
  
}


@Test
public void testTotalDistanceReturnsCorrectValue(){
Session sessionOne = new Session("2024-01-05",1800,1);
Session sessionTwo = new Session("2024-01-09",2000,2);

 user.saveSession("SD123",sessionOne);
 user.saveSession("SD2024",sessionTwo);

 user.calculateTotalDistance(sessionOne);
 user.calculateTotalDistance(sessionTwo);

assertEquals(3, user.totalDistance);

}



@Test
public void calculateAverageDistanceReturnsCorrectValue(){
Session sessionOne = new Session("2024-01-05",1800,2);
Session sessionTwo = new Session("2024-01-09",2000,2);

 user.saveSession("SD123",sessionOne);
 user.saveSession("SD2024",sessionTwo);

 user.calculateTotalDistance(sessionOne);
 user.calculateTotalDistance(sessionTwo);

 user.calculateAverageDistance(user.totalDistance,2);
 

assertEquals(2, user.averageDistance);

}
}
