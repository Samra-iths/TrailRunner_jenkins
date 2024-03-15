package se.iths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;





public class TestSession {
@Test
public void testSavedSessionWithDate(){

  Session sessionOne = new Session("2024-01-05",1800,1);
  LocalDate expecteddate= LocalDate.of(2024,01,05);

assertEquals(expecteddate, sessionOne.date);

}


@Test
public void testSavedSessionWithTIme(){

  Session sessionOne = new Session("2024-01-05",1800,1);
  
 assertEquals(1800, sessionOne.time_seconds);
}



@Test
public void testSavedSessionWithDistance(){

  Session sessionOne = new Session("2024-01-05",1800,1);
  
 assertEquals(1, sessionOne.distance);

 }
 



 @Test
 public void TestaverageSpeed(){
  Session session= new Session(1800,5);
  
  assertEquals(10,session.averageSpeed);

 }
 @Test
 public void TestKillometerPerTime(){
  Session session=new Session(3600, 10);
  assertEquals(6, session.kilometerTime);

 }
 
}


 

