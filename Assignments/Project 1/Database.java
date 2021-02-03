/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 1 */
/* Database class: Stores course objects and contains methods */
/* to interact with stored objects. */
/**************************************************************/

class Database 
{
   protected int last;
   protected final int COURSE_NUM = 5; //base size for course object array
   protected Course[] courses; //declares an array of course object
   
   public Database()
   {
      courses = new Course[COURSE_NUM]; //creates an array of course objects of the base size
      last = -1; //defines last, which will be incremented within the add method
   }
   
   public void add (int index, Course c)
   {
      if ((index < 0) || (index > last + 1)) //checks to make sure index is not out of bounds
      {
         throw new IndexOutOfBoundsException(); //throws exception
      }
      if (last == courses.length - 1)
      {
         Course[] bigger = new Course[courses.length * 2];
         for (int pos = 0; pos < courses.length; pos++)
         {
            bigger[pos] = courses[pos]; //copies over contents of courses
         }
         courses = bigger; //makes courses the same as bigger
      }
      for (int copy = last; copy >= index; copy--)
      {
         courses[copy + 1] = courses[copy]; //shifts all of the array contents over one
         
      }
      courses[index] = c; //adds the specified course object to courses
      last++; //increments last
   }
   
/**************************************************************/
/* Method: searchByNum */
/* Purpose: Search all the courses in the database */
/* to find a particular course using the course code (i.e. CS-102) */
/* Parameters: */
/* String courseNum: The course code to search for */
/* Result: Prints all of the courses matching the given course code */
/**************************************************************/

   public void searchByNum(String courseNum)
   {
      for (Course i: courses) //for each loop navigating course objects
         {
            if (i != null) //if the current index is not empty
            {
               String cCode = i.getCourseCode(); //assigns to the return of course code
               if (courseNum.equals(cCode)) // if the searchterm matches the current object's course code
               {
                  i.print(); //print current course object
               }
            }
         }
   }
   
/**************************************************************/
/* Method: searchByTitle */
/* Purpose: Search all the course in the database */
/* to find a particular course using the course title */
/* Parameters: */
/* String searchTitle: Title of course to find */
/* Result: Prints all of the courses matching the given course title*/
/**************************************************************/

   public void searchByTitle(String searchTitle)
   {
      for (Course i: courses) //for each loop navigating course objects
      {
         if (i != null) //if current index is not empty
         {
            String title = i.getCourseTitle(); //assigns to current object's courseTitle
            if (searchTitle.equals(title)) //if the search title matches 
            {                              //the current object's course title 
               i.print(); //print current course object
            }
         }
      } 
   }
   
/**************************************************************/
/* Method: print */
/* Purpose: Prints all of the courses in the database */
/* Parameters: None */
/* Result: Prints all of the stored courses*/
/**************************************************************/

   public void print() 
   {
      for (Course i: courses) //for each loop navigating course objects
      {
         if (i != null) //if current index is not empty
         {
            i.print(); //print current course object
            System.out.println(""); //empty space for formatting
         }
      }
   }
   
/**************************************************************/
/* Method: getGPA */
/* Purpose: Calculate the GPA based on the grades */
/* of all stored course objects. */
/* Parameters: None */
/* Returns: Double: Numerical GPA (i.e. 4.0, 3.7, etc) */
/**************************************************************/

   public double getGPA()
   {
      double sum = 0; //sets an empty variable which is where we add up our sum
      int totalCredits = 0; //sets an empty variable which is our denominator
      
      for (Course i: courses) //for each loop navigating course objects
      {
         if (i != null) //if current index is not empty
         {
            if (!(i.getFlag().equals("Y"))) //if flag is not Y (exclusion)
               {
                  sum += (i.getGrade() * i.getCredits()); //adds grade * credits to our sum
                  totalCredits += i.getCredits(); //adds current credits to total
               }
         }
      }
      sum = (sum / totalCredits); //divides sum by totalCredits
      return sum; //returns sum
   }
}