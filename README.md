# Java Calender
Java Calender is a simple commandline calender tool. 

# Methods!
To start first create an instance of the class
```java
Calender cal = new Calander();
```

 # showMonth(int year, int month) 
  It displays the calender of the given month of the given year
  `returnType: void`
 ```java
    cal.showMonth(2020,11);
    //Output
    November, 2020
       Sun   Mon   Tue   Wed   Thu   Fri   Sat   
         1     2     3     4     5     6     7
         8     9    10    11    12    13    14
        15    16    17    18    19    20    21
        22    23    24    25    26    27    28
        29    30
```
 # showYear(int year)
 It displays the calender of the given year. 
 `returnType: void`
  ```java
    cal.showYear(2020);
  ```
# dayOfMonth(int year, int month, int date)
It returns the name of the day on the given date. 
`returnType: String`
```java
    cal.dayOfMonth(2020,11,1);
    // Output
    Sunday
```

# getNoOfDays(int year, int month)
It returns the number of days in the given month.
`@param: year` It is required to check if the year is a leap year or not.
`returnType: int`
```java
    cal.getNoOfDays(2020,11);
    // Output
    30
```
# isLeapYear(int year)
It returns true if the year is a leap year else false. 
`returnType: bool`
```java
    cal.isLeapYear(2020);
    // Output
    true
```
# getCurrentYear()
It returns the integer value of the current year. 
`returnType: int`
```java
    cal.getCurrentYear();
    // Output
    2020
```
# getCurrentMonth()
It returns the name of current month. 
`returnType: String`
```java
    cal.getCurrentMonth();
    // Output
    November
```
# getCurrentMonthIndex()
It returns the integer value of current month. 
`returnType: int`
`1 - January ... 12 - December`
```java
    cal.getCurrentMonthIndex();
    // Output
    11
```

# showClock()
It will display a digital clock. It uses Swing.
```java
    cal.showClock();
```
License
----

MIT


 
