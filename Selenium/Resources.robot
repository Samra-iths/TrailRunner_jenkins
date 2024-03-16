*** Settings ***
Documentation   Testing card rental functionality of the webpage
Library    SeleniumLibrary
Library    Collections
Library    BuiltIn
Library    DateTime
Library    String



*** Variables ***

${URL}  https://rental17.infotiv.net/
${cardNumber}    1234123412341234
${expected}    2 Vols Model S 2024-02-17 2024-02-17 9 BDE463\nCancel booking
${newEmail}    hgdsuh@yahoo.com
${newPassword}    123sam
${phoneNumber}  0798465327
${lastName}    Assa
${firstName}    sam
${email}    sam123@gmail.com
${password}    123sam


*** Keywords ***

The user is at home page

    [Documentation]    Navigating the create user page
    [Tags]    create user, VG_test

    Open Browser    browser=chrome
    Go To    ${URL}
    Wait Until Element Is Visible    //h1[@id='questionText']

The user enters her information

     [Documentation]  the user sign up
     [Tags]      VG_test  create user
     [Arguments]     ${Frst name}    ${Last name}     ${Phone number}    ${Email}    ${password}
     
     Click Button    //button[@id='createUser']
     Wait Until Element Is Visible    //h1[@id="questionText"]
     Input Text    //input[@id='name']     ${Frst name}
     Input Text    //input[@id='last']     ${Last name}
     Input Text    //input[@id='phone']     ${Phone number}
     Input Text    //input[@id='emailCreate']     ${Email}
     Input Text    //input[@id='confirmEmail']     ${Email}
     Input Password     //input[@id='passwordCreate']    ${password}
     Input Password    //input[@id='confirmPassword']    ${password}
    

The user is created and signed in

     [Documentation]  Verify the user is created
     [Tags]      VG_test  create  user

     Click Button    //button[@id='create']
     Wait Until Element Is Visible    //label[@id='welcomePhrase']
     Close Browser

The user sign in with her credential

    [Documentation]  Entering users credential
    [Tags]      VG_test  Log in
    [Arguments]     ${userName}    ${password}
    
    Input Text    //input[@id='email']    ${userName}
    Input Password    //input[@id='password']    ${password}
    Click Button    //button[@id='login']


The user is signed in

    [Documentation]  Verify the user is signed in
    [Tags]       VG_test Log in

    Wait Until Element Is Visible    //label[@id='welcomePhrase']
    Close Browser


The user selects the dates and the car

    [Documentation]  Date Selection and Car Selection
    [Tags]      VG_test  book car

    ${getDate}    Get Time
    ${startDate}    Get Substring  ${getDate}        4     10
    ${endDate}    Get Substring  ${getDate}        4     10

    ${startDateConfirm}    Get Substring  ${getDate}        0     10
    ${endDateConfirm}    Get Substring  ${getDate}        0     10
    
    Input Text    (//input[@id='start'])[1]    ${startDate}
    Input Text    (//input[@id='end'])[1]   ${endDate}
    Click Button    //button[@id='continue']
    Wait Until Element Is Visible   //label[contains(text(),'Selected trip dates: ${startDate} – ${endDateConfirm}')]
    Click Button    //tbody/tr[4]/td[5]/form[1]/input[4]
    Wait Until Element Is Visible    //h1[@id='questionText']

The user pays

    [Documentation]  Confirm Booking
    [Tags]      VG_test  book car
    [Arguments]    ${cardNumber}     ${name}      ${month}     ${year}     ${CVC}

    Input Text    //input[@id='cardNum']    ${cardNumber}
    Input Text   //input[@id="fullName"]   ${name}
    Select From List By Index          //select[@title="Month"]    ${month}
    Select From List By Index    //select[@title='Year']    ${year}
    Input Text    //input[@id='cvc']    ${CVC}


The user is directed booking confirmation page

    [Documentation]  Successful Booking
    [Tags]      VG_test  book car

    Click Button   //button[@id='confirm']
    Wait Until Element Is Visible    //h1[@id='questionTextSmall']
    Close Browser

The user filters the car

    [Documentation]  Car Selection
    [Tags]      VG_test  filter
    [Arguments]     ${Car brand}    ${No of people}

    Click Element    //h1[@id='title']
    Click Button    //button[@id='continue']
    Click Button   //div[@id='ms-list-1']//button[@type='button']
    Select Checkbox    ${Car brand}
    Click Button    //div[@id='ms-list-2']//button[@type='button']
    Select Checkbox    ${No of people}

The cars are shown accordingly

    [Documentation]  Car Selection
    [Tags]      VG_test  filter

    Wait Until Element Is Visible    //td[normalize-space()='Tesla']
    Wait Until Element Is Visible    //img[@src='/webpage/img/passengerIcon.png']
    Close Browser

The user goes on my page

    [Documentation]  Navigating to homepage
    [Tags]      VG_test  homepage

    Click Button    //button[@id="mypage"]
    Wait Until Element Is Visible    //h1[@id="historyText"]

Only the booked info is shown

    [Documentation]  verify only booked cars are in the booking tabel
    [Tags]      VG_test  Negative test

    
    @{actual}=  Create List
    ${table}    Get WebElement    css:.orderTable
    ${bookingTabel}    Get WebElements    css:.orderTDg

    FOR    ${booking}    IN    @{bookingTabel}
        Append To List      ${actual}      ${booking.text}
    END

    ${result}=  Run Keyword And Return Status      List Should Not Contain Value         ${actual}    ${expected}

    Run Keyword If    ${result}==True     Log     Verified: Unbooked car is not on the list of confirmed bookings
    Run Keyword If     ${result}==False    Log     Bug: unbooked car is on list of confirmed booking
    
    Close Browser

The user tries to choose a date that is in the past

    [Documentation]  verify only allowed dates accepted
    [Tags]      VG_test  Negative test

    Input Text    (//input[@id='start'])[1]    2024-02-18
    Input Text    (//input[@id='end'])[1]      2024-02-19


The user get alert info about the earliest allowed date that she can choose

    [Documentation]  verify only allowed dates accepted
    [Tags]      VG_test  Negative test

    Click Button    //button[@id='continue']
    Wait Until Element Is Visible    //h1[@id="questionText"]
    Close Browser
