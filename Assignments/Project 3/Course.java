/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 3 */
/* Course class: Contains methods relating to stored */
/* course variables  */
/**************************************************************/

class Course
{
   protected String courseYear; //the course year in the format "YYYYTT"
   protected String courseCode; //the course code in the format "EE-###" (i.e. CS-102)
   protected String letterGrade; //the letter grade for a course (A, B-, C+, etc)
   protected String courseTitle; //the full course name (Ex: Microcomputers I)
   protected String flag; //a string ("Y" or "N") that determines whether the course is excluded from GPA calculations
   protected int courseCredits; //the amount of credits a course is worth
   protected double numberGrade; //the letter grade translated onto the 4-point scale

   public Course(String courseYear, String courseCode, int courseCredits, String letterGrade, String courseTitle, String flag)
   { 
      this.courseYear = courseYear;       //takes in the parameters 
      this.courseCode = courseCode;       //and sets the class 
      this.courseCredits = courseCredits; //variables to be equivalant 
      this.letterGrade = letterGrade;
      this.courseTitle = courseTitle;
      this.flag = flag;
      
      numberGrade = convertGrade(); //calls convertGrade and stores the result
   }
   
/**************************************************************/
/* Method: convertGrade */
/* Purpose: Return a grade in the 4-point scale based on */
/* the stored letter grade (i.e. A, B-, etc). */
/* Returns: Double: Number grade in the 4-point scale. */
/**************************************************************/

   private double convertGrade() 
   {
      if (letterGrade.equals("A"))       //runs a series 
      {                                  //of logic expressions
         return 4.0;                     //to equate the 
      }                                  //letter grade onto 
      else if (letterGrade.equals("A-")) //the 4-point grading scale
      {
         return 3.7;
      }
      else if (letterGrade.equals("B+"))
      {
         return 3.3;
      }
      else if (letterGrade.equals("B"))
      {
         return 3.0;
      }
      else if (letterGrade.equals("B-"))
      {
         return 2.7;
      }
      else if (letterGrade.equals("C+"))
      {
         return 2.3;
      }
      else if (letterGrade.equals("C"))
      {
         return 2.0;
      }
      else if (letterGrade.equals("C-"))
      {
         return 1.7;
      }
      else if (letterGrade.equals("D+"))
      {
         return 1.3;
      }
      else if (letterGrade.equals("D"))
      {
         return 1.0;
      }
      else
      {
         return 0; //default return
      }
   }
   
/**************************************************************/
/* Method: getGrade */
/* Purpose: Returns the stored number grade variable. */
/* Returns: double: Number grade in the 4-point scale. */
/**************************************************************/

   public double getGrade() //accessor method for numberGrade variable
   {
      return numberGrade;  //returns numberGrade
   }
   
/**************************************************************/
/* Method: getCredits */
/* Purpose: Returns the stored course credits variable. */
/* Returns: int: The amount of course credits. */
/**************************************************************/

   public int getCredits() //accessor method for courseCredits variable
   {
      return courseCredits; //returns courseCredits
   }
   
/**************************************************************/
/* Method: getFlag, getCourseTitle, getCourseCode, getCourseYear */
/* Purpose: Returns the Strings representing the flag,  */
/* course title and course code. */
/* Returns: String: course flag, title, code and year. */
/**************************************************************/

   public String getFlag() //accessor method for flag variable
   {
      return flag; //returns flag
   }
   
   public String getCourseTitle() //accessor method for courseTitle variable
   {
      return courseTitle; //returns courseTitle
   }
   
   public String getCourseCode() //accessor method for courseCode variable
   {
      return courseCode; //returns courseCode
   }
   
   public String getCourseYear() //accessor method for courseYear variable
   {
      return courseYear; //returns courseYear
   }
    
/**************************************************************/
/* Method: setFlag, setCourseTitle, setCourseCode */
/* setCourseYear, setGrade, setCredits
/* Purpose: Mutator methods for class variables */
/* Parameters: */
/* String flag, courseTitle, courseCode, courseYear */
/* letterGrade: The String value to set
/* int courseCredits: The int value to set
/* Result: Sets the class variable to the specified parameter */
/**************************************************************/

   public void setFlag(String flag) //mutator method for flag variable
   {
      this.flag = flag; //sets flag
   }
   
   public void setCourseTitle(String courseTitle) //mutator method for courseTitle variable
   {
      this.courseTitle = courseTitle; //sets courseTitle
   }
   
   public void setCourseCode(String courseCode) //mutator method for courseCode variable
   {
      this.courseCode = courseCode; //sets courseCode
   }
   
   public void setCourseYear(String courseYear) //mutator method for courseYear variable
   {
      this.courseYear = courseYear; //sets courseYear
   }

   public void setGrade(String letterGrade) //mutator method for numberGrade variable
   {
      this.letterGrade = letterGrade;  //returns numberGrade
      numberGrade = convertGrade(); //calls convertGrade and stores the result
   }
   
   public void setCredits(int courseCredits) //mutator method for courseCredits variable
   {
      this.courseCredits = courseCredits; //set courseCredits
   }
   
/**************************************************************/
/* Method: convertYear */
/* Purpose: Converts the stored course year number as */
/* a string in the form "Term YYYY".
/* Returns: String: Returns the course year in written form. */
/**************************************************************/

   private String convertYear() 
   {
      String cYear = ""; //creates empty return string
      if (courseYear.substring(4).equals("01")) //if the original string ends with "01" 
      {                                         //then it is Winter term
         cYear += "Winter "; //adds the term to the return string
         cYear += courseYear.substring(0, 4); //adds the year to the end of the return string
         return cYear; //returns "Winter YYYY"
      }
      else if (courseYear.substring(4).equals("02")) //if the original string ends with "02"
      {                                              //then it is Spring term
         cYear += "Spring ";
         cYear += courseYear.substring(0, 4);
         return cYear; //returns "Spring YYYY"
      }
      else if (courseYear.substring(4).equals("03")) //if the original string ends with "03"
      {                                              //then it is Summer term
         cYear += "Summer ";
         cYear += courseYear.substring(0, 4);
         return cYear; //returns "Summer YYYY"
      }
      else if (courseYear.substring(4).equals("04")) //if the original string ends with "04"
      {                                              //then it is Fall term
         cYear += "Fall ";
         cYear += courseYear.substring(0, 4);
         return cYear; //returns "Fall YYYY"
      }
      else 
      {
         return null; //default return
      }
   }
   
/**************************************************************/
/* Method: print */
/* Purpose: Prints all of the stored course variables in */
/* a written format. */
/* Result: Prints variables in the following format: */
/* "title (credits). Term YYYY (lettergrade)."
/**************************************************************/

   public void print()
   {
      System.out.print(convertYear() + " ");
      System.out.print("(" + courseCode + ") ");
      System.out.print(courseTitle + " ");            //prints class variables on a single line
      System.out.print("(" + courseCredits + ").");  //for example: "Microcomputers I (4). Summer 2010 (B+)."
      System.out.print("(" + letterGrade + "). \n");
   }
}