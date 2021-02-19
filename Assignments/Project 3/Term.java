/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 3 */
/* Term class: Defines a term object, which stores */
/* a LinkedList of course objects */
/**************************************************************/

import java.util.*;

class Term
{
   protected int term; //the term number (i.e. YYYYTT)
   protected LinkedList<Course> courses; 
   
   
   
   public Term(int term)
   {
      this.term = term;
      courses = new LinkedList<Course>();
   }
   
/**************************************************************/
/* Method: getTermNumber */
/* Purpose: Returns the term number */
/* Parameters: None */
/* Returns: int term: the term number in the format (YYYYTT) */
/**************************************************************/

   public int getTermNumber()
   {
      return term;
   }
   
/**************************************************************/
/* Method: addCourse */
/* Purpose: Searches for a matching term to add course to list */
/* Parameters: */
/* Course course: The course to add*/
/* Result: Add the course to the term's CourseList */
/**************************************************************/    

   public void addCourse(Course course)
   {
      String courseCode = course.getCourseCode(); 
      //the coursecode of the parameter object
      
      for (int index = 0; index < courses.size(); index++) //for the length of courses
      { 
         if (courses.get(index) != null) //if current is not empty
         {
            Course currentCourse = courses.get(index); //current course object
            String compCode = currentCourse.getCourseCode(); //coursecode of current object
         
            if (courseCode.equals(compCode)) //if the course already exists
            {
               System.out.println("Course already exists...");
               break;
            }
         
            else if (courseCode.compareTo(compCode) < 0) //if course comes sooner
            {
               courses.add(index, course);
               break;
               //put course in current index, moving the current object forward
            }
            
         }
      }
      if (courses.size() - 1 >= 0)  //if the list is bigger than 0
      {
         Course lastCourse = courses.get(courses.size() - 1); //the last course of the list
         String compCode = lastCourse.getCourseCode(); //the last course code
         
         if (courseCode.compareTo(compCode) > 0) //if course goes last
         {
            courses.addLast(course); //adds course to the end of the list
         }
      }
      if (courses.size() <= 0) //if list is empty
      {
         courses.add(0, course);
         //adds element to the start of the list
      }
   }
   
/**************************************************************/
/* Method: removeCourse */
/* Purpose: Removed a course of a specified term */
/* Parameters: */
/* int term: The term that contains the course
/* String courseCode: Indentifier of course to remove */
/* Result: 'Removes' CourseNode from the CourseList*/
/**************************************************************/

   public void removeCourse(String courseCode)
   {
   
        boolean courseMatch = false; //if a matching course has been found
        
        for (int index = 0; index < courses.size(); index++)
        {         
            if (courses.get(index) != null) //if current is not empty
            {
               Course currentCourse = courses.get(index); //the current course object
               String compCode = currentCourse.getCourseCode(); //current coursecode
         
               if (courseCode.equals(compCode)) //if coursecodes match
               {
                  currentCourse.print(); //print the course
                  courses.remove(index);
                  courseMatch = true;
                  break;
                  //removes the course and sets courseMatch to true
               }
               
            }
         }
         
         if (courseMatch != true)
         {
            System.out.println("Course does not exist...");
            //if no matching course has been found
         }
   }
   
/**************************************************************/
/* Method: printAll */
/* Purpose: Print all term courses */
/* Parameters: None */
/* Results: Prints all the course for the term */
/**************************************************************/

   public void printAll()
   {
   
      for (int index = 0; index < courses.size(); index++)
        {     
            
            if (courses.get(index) != null) //while current is not null
            {
               Course currentCourse = courses.get(index); //current course object
               currentCourse.print();
               //prints the current course object
            }
            
         }
   }
   
/**************************************************************/
/* Method: searchByNum */
/* Purpose: Searches for a given CourseCode */
/* and prints the result */
/* Parameters: */
/* String: courseNum: The course code in the format (EE-###)
/* Result: The specified course is printed */
/**************************************************************/
   
   public void searchByNum(String courseNum)
   {
   
      boolean courseMatch = false; //if a match has been found
      
      for (int index = 0; index < courses.size(); index++)
        {         
            if (courses.get(index) != null) //if current is not empty
            {
               Course currentCourse = courses.get(index); //the current course object
               String compCode = currentCourse.getCourseCode(); //the current courseCode
         
               if (courseNum.equals(compCode)) //if courseCodes are equal
               {
                  currentCourse.print();
                  courseMatch = true;
                  return;
                  //prints the matching course and sets courseMatch to true
               }
               
            }
         }
         
       if (courseMatch != true)
       {
         System.out.println("No results found...");
         //if no matching courses were found
       }
   }
   
/**************************************************************/
/* Method: searchByTitle */
/* Purpose: Searches for a given keyword and prints the matches */
/* Parameters: */
/* String: searchTerm: The given keyword to search for
/* Result: The specified course is printed*/
/**************************************************************/

   public void searchByTitle(String courseTitle)
   {
   
      boolean courseMatch = false; //if a match has been found 
      
      for (int index = 0; index < courses.size(); index++)
         {         
         
            if(courses.get(index) != null) //if current is not empty
            {
               Course currentCourse = courses.get(index); //the current course object
               String compTitle = currentCourse.getCourseTitle(); //the current course title
               
               if (compTitle.contains(courseTitle)) //if current title contains 
               {                                   //parameterized searchTerm
                  currentCourse.print(); 
                  courseMatch = true;
                  //prints the matching course and sets courseMatch to true
               }
               
            }
         }
         
      if (courseMatch != true)
       {
         System.out.println("No results found...");
         //if no matching courses were found
       }
   }
   
/**************************************************************/
/* Method: getSum */
/* Purpose: Return sum for use in TermList class */
/* Parameters: None */
/* Returns: Double: The sum of term grades * credits */
/**************************************************************/

   public double getSum()
   {
   
      double sum = 0.0; //the return value
      
      for (int index = 0; index < courses.size(); index++)
         {      
               
            if(courses.get(index) != null) //if current is not empty
            {
            
               Course currentCourse = courses.get(index); //current course object
               
               if (!(currentCourse.getFlag().equals("Y"))) //if the course is not to be excluded
               {
                  sum += (currentCourse.getGrade() * currentCourse.getCredits()); //adds grade * credits to sum
               }
               
            }
         }
         return sum; //returns the sum
   }
   
/**************************************************************/
/* Method: getTotalCredits */
/* Purpose: Return the totalCredits for each term */
/* Parameters: None */
/* Returns: int: Number of credits */
/**************************************************************/  

   public int getTotalCredits()
   {
      int totalCredits = 0; //the return value
      for (int index = 0; index < courses.size(); index++)
         {       
           
            if(courses.get(index) != null) //if current is not empty
            {
            
               Course currentCourse = courses.get(index); //the current course object 
               
               if (!(currentCourse.getFlag().equals("Y"))) //if the course is not to be excluded
               {
                  totalCredits += currentCourse.getCredits(); //adds current course credits to total
               }
               
            }
         }
      return totalCredits; //returns totalCredits
   }
}