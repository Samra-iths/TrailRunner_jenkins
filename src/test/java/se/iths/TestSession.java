package se.iths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;





public class TestSession {
@Test
public void testSavedSessionWithOutDate(){

  Session sessionOne = new Session("SD123", 2, 1800);
  LocalDate expecteddate= LocalDate.of(2024,01,12);

assertEquals(expecteddate, sessionOne.date);

}
@Test
public void testSavedSessionWithDate(){

  Session sessionOne = new Session("SD123", 2, 1800,LocalDate.parse("2024-01-05"));
  LocalDate expecteddate= LocalDate.of(2024,01,05);

assertEquals(expecteddate, sessionOne.date);

}


@Test
public void testSavedSessionWithTIme(){

  Session sessionOne = new Session("SD123",1.0,1800);
  
 assertEquals(1800, sessionOne.time_seconds);
}



@Test
public void testSavedSessionWithDistance(){

  Session sessionOne = new Session("SD123", 1, 1800);
  
 assertEquals(1, sessionOne.distance_km);

 }
 

 //  medelhastighet enligt formeln ℎ𝑎𝑠𝑡𝑖𝑔ℎ𝑒𝑡 = 𝑑𝑖𝑠𝑡𝑎𝑛𝑠 (𝑘𝑚)/ 𝑡𝑖𝑑 (ℎ𝑟𝑠)
// kilometer-tid enligt formeln 𝑘𝑖𝑙𝑜𝑚𝑒𝑡𝑒𝑟𝑡𝑖𝑑 =𝑡𝑖𝑑 (𝑚𝑖𝑛)/𝑑𝑖𝑠𝑡𝑎𝑛𝑠 (𝑘𝑚)

 @Test
 public void TestaverageSpeed(){
  Session session= new Session("SD123", 5, 1800);
  
  assertEquals(10.0,session.averageSpeed);

 }
 @Test
 public void TestKillometerPerTime(){
  Session session=new Session("SD123", 5, 1800);
  assertEquals(6, session.kilometerTime);

 }



 /////test
 

}


 

