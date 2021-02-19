/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 3 */
/* Database class: Stores a TermList called transcript */
/* and calls methods to interact with stored objects */
/**************************************************************/

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

class Database
{
   protected LinkedList<Term> terms;
   
   public Database()
   {
      terms = new LinkedList<Term>();
   }
   
/**************************************************************/
/* Method: addTerm */
/* Purpose: Adds a new term to the terms list */
/* Parameters: */
/* int term: The term number of the term to add */
/* Result: Adds a new term object to the list */
/**************************************************************/

   public void addTerm(int term)
   {
      Term tempTerm = new Term(term); //term object to be added
      
      for(int index = 0; index < terms.size(); index++) //for length of term
         {
            if (terms.get(index) != null) //if current is not empty
            {
               Term currentTerm = terms.get(index); //current term object
               int compareNum = currentTerm.getTermNumber(); //current term number
               
               if (term == compareNum) //if term already exists
               {
                  System.out.println("Term already exists...");
                  return;
               }
            
               else if (term < compareNum) //if term goes sooner
               {
                  terms.add(index, tempTerm);
                  return;
                  //adds the element at the current position
               }
            }
         }
         
         if (terms.size() - 1 >= 0)  //if the list is bigger than 0
         {
            Term lastTerm = terms.get(terms.size() - 1); //the last term of the list
            int compareNum = lastTerm.getTermNumber(); //the last term number
         
            if (term > compareNum) //if parameter term goes last
            {
               terms.addLast(tempTerm); //adds tempTerm to the end of the list
            }
         }
         
         if (terms.size() <= 0) //if list is empty
         {
            terms.add(0, tempTerm);
            //adds element to the start of the list
         }
   }
   
/**************************************************************/
/* Method: addCourse */
/* Purpose: Searches for a matching term to add course into */
/* Parameters: */
/* Course course: The course to add*/
/* Result: Add the course to the term's CourseList*/
/**************************************************************/  

   public void addCourse(Course course)
   {
      boolean termMatch = false; //if a matching term has been found
      int courseTerm = Integer.parseInt(course.getCourseYear());
      
      
      for(int index = 0; index < terms.size(); index++)
         {
            Term currentTerm = terms.get(index); //the current term object
            int compareNum = currentTerm.getTermNumber(); //the current term number
            
            if (courseTerm == compareNum) //if the terms match
            {
               currentTerm.addCourse(course);
               termMatch = true;
               return;
               //calls addCourse and declares termMatch to be true
            }
         }
         
      if (termMatch != true)
      {
         addTerm(courseTerm);
         addCourse(course);
         //if the term is not present, then adds it to the list
      }   
   }
   
/**************************************************************/
/* Method: removeCourse */
/* Purpose: Removed a course of a specified term */
/* Parameters: */
/* int term: The term that contains the course
/* String courseCode: Indentifier of course to remove */
/* Result: Removes the course from the database */
/**************************************************************/

   public void removeCourse(int term, String courseCode)
   {
      boolean termMatch = false; //if a matching term has been found
      
      for(int index = 0; index < terms.size(); index++)
         {
            Term currentTerm = terms.get(index); //the current term object
            int compareNum = currentTerm.getTermNumber(); //the current term number
            
            if (term == compareNum)
            {
               currentTerm.removeCourse(courseCode);
               termMatch = true;
               //calls removeCourse and declares termMatch to be true
            }
         }
         
      if (termMatch != true)
      {
         System.out.println("Term does not exist...");
         //if no matching term has been found, report to user
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
      for(int index = 0; index < terms.size(); index++)
         {
            if (terms.get(index) != null) //if current is not null
            {
               Term currentTerm = terms.get(index); //the current term
               currentTerm.printAll(); 
               //prints all courses for current term
            }
         }
   }
   
/**************************************************************/
/* Method: searchByNum */
/* Purpose: Search all the courses in the database */
/* to find a particular course using the course code (i.e. CS-102) */
/* Parameters: */
/* String courseNum: The course code to search for */
/* Result: Prints all of the courses matching the course code */
/**************************************************************/   
   
   public void searchByNum(String courseNum)
   {
        for(int index = 0; index < terms.size(); index++)
         {
            if (terms.get(index) != null) //if current is not null
            {
               Term currentTerm = terms.get(index);//current term object
               currentTerm.searchByNum(courseNum);
               //searches current term for matching course
            }
         }
   }
   
/**************************************************************/
/* Method: searchByTitle */
/* Purpose: Search all the courses in the database */
/* to find a particular course using the course title */
/* Parameters: */
/* String searchTitle: Title of course to find */
/* Result: Prints all of the courses matching the course title*/
/**************************************************************/  

   public void searchByTitle(String courseTitle)
   {
      for(int index = 0; index < terms.size(); index++)
         {
            if (terms.get(index) != null) //if current is not null
            {
               Term currentTerm = terms.get(index); //current term object
               currentTerm.searchByTitle(courseTitle);
               //searches current term for matching course
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
      DecimalFormat df = new DecimalFormat("0.00");
      double sum = 0; //the sum of all grades multiplied by course credits
      int totalCredits = 0; //the total number of credits
      
      for(int index = 0; index < terms.size(); index++)
      {
         if (terms.get(index) != null) //if current is not null
         {
               Term currentTerm = terms.get(index); //the current term object
               sum += currentTerm.getSum(); //adds the sum of current term to sum
               totalCredits += currentTerm.getTotalCredits();
               //adds totalCredits of current term to totalCredits
         }
         
      }
         sum = (sum / totalCredits); //divides sum by totalCredits
         return Double.parseDouble(df.format(sum)); //returns sum
   }
   
}