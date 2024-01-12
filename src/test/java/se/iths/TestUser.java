package se.iths;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.*;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.SQLException;

public class TestUser {
    User user;

    @Mock
    DatabaseAPI api;

    Session sessionOne ;
    Session sessionTwo;




   @BeforeEach
   public void setUpUser(){
    api = mock(DatabaseAPI.class);    
    
    user= new User(46, 60, api);
   
   sessionOne = new Session("SD123", 2, 1800,LocalDate.parse("2024-01-05"));

    sessionTwo = new Session("SD", 2, 2000,LocalDate.parse("2024-01-09"));
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
public void testUserSaveSessionWithUniqueID_DatabaseVersion() throws SQLException{

  when(api.createRecord("SD123", 1, 1800, LocalDate.parse("2024-01-05"))).thenReturn(true);
  
  when(api.createRecord("SD",2 , 2000, LocalDate.parse("2024-01-09"))).thenReturn(true);

  assertTrue(user.saveSession("SD",2 , 2000, LocalDate.parse("2024-01-09")));

 }



 @Test
public void testingSaveSessionWithDuplicateIDRetursSameErrorMessage() throws SQLException{

when(api.createRecord("SD123", 1, 1800, LocalDate.parse("2024-01-05"))).thenReturn(false);
SQLException exception= assertThrows(SQLException.class, ()->{user.saveSession("SD123", 1, 1800, LocalDate.parse("2024-01-05"));});

assertEquals("Error: Duplicate ID", exception.getMessage());

}

 @Test
public void testReadingSession() throws SQLException{

when(api.readRecord("SD123")).thenReturn(sessionOne);

assertEquals(sessionOne,user.printDetailForSessionUsingID("SD123"));

}


 
 @Test
public void testingReadingSessionWhichDoesNotExistRetursSameErrorMessage(){

when(api.readRecord("SD123")).thenReturn(null);
SQLException exception= assertThrows(SQLException.class, ()->{user.printDetailForSessionUsingID("rtde");});

assertEquals("Error: ID not recognized", exception.getMessage());

}




 @Test
public void testingReadingSessionWhichDoesNotExist(){

when(api.readRecord("SD123")).thenReturn(null);

assertThrows(SQLException.class,() ->{user.printDetailForSessionUsingID("SD123");});

}



@Test
public void TestDeleteSessionUsingID() throws SQLException{
 
when(api.readRecord("SD123")).thenReturn(sessionOne);

when(api.deleteRecord("SD123")).thenReturn(true);
  
assertTrue(user.deleteSessionUsingID("SD123"));

}


 @Test
public void DeleteSessionUsingIDThrowsExpectedException() throws SQLException{
when(api.readRecord("SD123")).thenReturn(null);

assertThrows(SQLException.class,() ->{user.deleteSessionUsingID("SD123");});
}

 


 @Test
public void testUserSaveSessionWithUniqueID() throws SQLException{

 when(api.readRecord("SD123")).thenReturn(sessionOne);
 when(api.createRecord("SD123", 1, 1800,LocalDate.parse("2024-01-05"))).thenReturn(true);

 assertTrue(user.saveSession("SD123", 1, 1800,LocalDate.parse("2024-01-05")));

 }




@Test
public void testTotalDistanceReturnsCorrectValue(){

when(api.readRecord("SD123")).thenReturn(sessionOne);
  
when(api.readRecord("SD")).thenReturn(sessionTwo);

 user.calculateTotalDistance("SD123");
 user.calculateTotalDistance("SD");

assertEquals(4, user.totalDistance);

}



@Test
public void calculateAverageDistanceReturnsCorrectValue(){

 when(api.readRecord("SD123")).thenReturn(sessionOne);
  
 when(api.readRecord("SD")).thenReturn(sessionTwo);
 user.calculateTotalDistance("SD123");
 user.calculateTotalDistance("SD");

 
 user.calculateAverageDistance(user.totalDistance,2);
 
assertEquals(2, user.averageDistance);

}


//String id, double distance_km, double time_seconds, LocalDate date)
//"SD123", 2, 1800,LocalDate.parse("2024-01-05")

@Test
public void TestPrintDetailForSessionUsingIDReturnsExpectedValues(){

when(api.readRecord("SD123")).thenReturn(sessionOne);

assertEquals( "ID:SD123 distance_km:2.0 time_seconds:1800.0 date:2024-01-05",sessionOne.toString());

}



@Test
public void test(){
ArrayList<String> IDs = new ArrayList<String>();

IDs.add("SD");
IDs.add("SD123");

when(api.getRecordIDs()).thenReturn(IDs);

assertEquals(IDs,user.readIDFromDatabase());

}

@Test
public void testFilteringSessionWithDistance(){
  ArrayList<String> IDs = new ArrayList<String>();

IDs.add("SD");
IDs.add("SD123");

when(api.getRecordIDs()).thenReturn(IDs);
when(api.readRecord("SD123")).thenReturn(sessionOne);
when(api.readRecord("SD")).thenReturn(sessionTwo);

ArrayList<Session> expected = new ArrayList<Session>();
expected.add(sessionTwo);
expected.add(sessionOne);


assertEquals(expected, user.filterSessionWithDistance(5,"less"));
assertNotEquals(expected, user.filterSessionWithDistance(5,"Greater"));
assertNotEquals(expected, user.filterSessionWithDistance(5,"equal"));


}





 


}
