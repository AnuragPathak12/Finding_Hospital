***
# Overview – Finding Hospital #

This project aims to automate the Practo web-based application using Selenium WebDriver. Once the application is launched, we have to search doctors for any city of particular speciality and get the list of 5 doctors based on the filters. Then go to Surgeries section and list all the surgeries present. Navigate to For Corporate section and fill Health and Wellness form with different values for contact number and email address.
***

## Plugins and Dependencies ##

```
- Maven Compiler Plugin: 3.12.1
- Maven Surefire Plugin: 3.2.5
- Selenium Java: 4.17
- Apache POI & Apache POI OOXML: 5.2.5
- TestNG: 7.9.0
- Extent Report: 5.1.1
- Selenium WebDriver: 5.6.3
- Cucumber Junit, Cucumber TestNG Cucumber Java: 7.15.0
- Extent Reports Cucumber7 Adapter: 1.14.0

```

## Automation Flow ##

```
1. Navigate to Practo application, enter city name and doctor’s speciality in the input boxes.
2. Apply filters such as Number of Patient Stories, Experience, Fees, Availability and Sort the result based on the preference.
3. List first 5 doctors based on the result.
4. Then go to Surgeries section and list all the surgeries mentioned there.
5. Navigate to Health and Wellness Plans under For Corporate dropdown.
6. Fill the Corporate Wellness form with valid and invalid contact numbers and email address.
7. Validate whether Schedule A Demo button is getting enabled or not.

```

## Challenges ##
```
Overcame issues related to dynamic content or page reloading by implementing proper Explicit Wait commands after click action is performed.
```
## Benefits ##
```
Increased efficiency by automating form with multiple sets of data. Reduced manual efforts and minimized the chance of human errors while testing the form.
```
