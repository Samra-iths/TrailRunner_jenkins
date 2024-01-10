package se.iths;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.*;
import static org.mockito.Mockito.*;

import java.beans.Transient;
import java.time.LocalDate;

public class TestUser {
    User user;

    @Mock
    DatabaseAPI api;

   @BeforeEach
   public void setUpUser(){
    api = mock(DatabaseAPI.class);    
    
    user= new User(46, 60, api);
         }


   @Test
   public void testUserIsCreated(){
    assertEquals(user,user);
   }


  @Test
  public void testUserSetHeightSuccessfully(){
    user=new User(50,60,api);
    assertEquals(50, user.height_m);
  }


   @Test
  public void testUserSetWeightSuccessfully(){
    user = new User(50,60,api);
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
user.saveSession("SD123",sessionOne);
user.saveSession("SD123",sessionTwo);

 
 assertEquals(sessionOne, user.savingSession.get("SD123"));
 assertNotEquals(sessionTwo, user.savingSession.get("SD123"));

 
 
 
  
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

@Test
public void TestPrintDetailForSessionUsingID(){

Session sessionOne = new Session("2024-01-05",1800,2);
Session sessionTwo = new Session("2024-01-09",2000,2);

user.saveSession("SD123",sessionOne);
user.saveSession("SD2024",sessionTwo);


//assertEquals(sessionOne.distance,user.printDetailForSessionUsingID("SD123").distance );


}


@Test
public void TestPrintDetailForSessionUsingIDReturnsExpectedValues(){

Session sessionOne = new Session("2024-01-05",1800,2);

user.saveSession("SD123",sessionOne);
assertEquals("date:2024-01-05 time_seconds:1800.0 distance:2.0",sessionOne.toString());


}



@Test
public void TestExectionIsThrownIfIncorrectIdIsGiven(){
Session sessionOne = new Session("2024-01-05",1800,2);
user.saveSession("SD123",sessionOne);
IllegalArgumentException exception= assertThrows(IllegalArgumentException.class, ()->{user.printDetailForSessionUsingID("rtde");});;

assertEquals("Key does not exist", exception.getMessage());


}


@Test
public void TestDeleteSessionUsingID(){
  Session sessionOne = new Session("2024-01-05",1800,2);
  Session session = new Session("2024-01-05",1800,3);
  user.saveSession("SD123",sessionOne);
  user.saveSession("SD124",session);
   user.deleteSessionUsingID("SD123");
   assertFalse(user.savingSession.containsKey("SD123"));
   

}

@Test
public void testUserSaveSessionWithUniqueID_DatabaseVersion(){

  when(api.createRecord("SD123", 1, 1800, LocalDate.parse("2024-01-05"))).thenReturn(true);
  
  when(api.createRecord("SD",2 , 2000, LocalDate.parse("2024-01-09"))).thenReturn(true);
Session sessionOne = new Session("2024-01-05",1800,1);
Session sessionTwo = new Session("2024-01-09",2000,2);

  assertTrue(user.saveSession("SD", sessionTwo));

 }


}
