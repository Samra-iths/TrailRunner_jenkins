*** Settings ***
Resource    Resources.robot


*** Test Cases ***

#Creat user

   # Given The user is at home page
   # When The user enters her information    ${firstName}   ${lastName}  ${phoneNumber}   ${newEmail}    ${newPassword}
   # Then The User Is Created And Signed In

Sign in
    
    Given The user is at home page
    When The user sign in with her credential    ${email}    ${password}
    Then The User Is Signed In


Choose and book car

    Given The user is at home page
    And The user sign in with her credential    ${email}    ${password}
    When The user selects the dates and the car
    And The User Pays    ${cardNumber}     sam    4    5    234
    Then The User Is Directed Booking Confirmation Page


Filters the car
    
    Given The user is at home page
    And The user sign in with her credential    ${email}    ${password}
    When The User Filters The Car    Tesla   7
    Then The Cars Are Shown Accordingly
    
No unbooked car in the booking tabel

     Given The user is at home page
     And The user sign in with her credential    ${email}    ${password}
     When The User Goes On My Page
     Then Only The Booked Info Is Shown


Invalid date

     Given The user is at home page
     And The user sign in with her credential    ${email}    ${password}
     When The User Tries To Choose A Date That Is In The Past
     Then The User Get Alert Info About The Earliest Allowed Date That She Can Choose



