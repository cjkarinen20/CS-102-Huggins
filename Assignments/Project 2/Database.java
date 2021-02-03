/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 2 */
/* Database class: Stores a TermList called transcript */
/* and calls methods to interact with stored objects */
/**************************************************************/

import java.util.*;


class Database
{
   TermList transcript; //TermList object used by database
   
   public Database()
   {
      transcript = new TermList(); //initializes TermList
   }
/**************************************************************/
/* Method: addTerm */
/* Purpose: Calls the TermList addTerm method to */
/* the initial TermList */
/* Parameters: */
/* int term: The term number of the term to add */
/* Result: Adds a new TermNode to the TermList */
/**************************************************************/
   public void addTerm(int term)
   {
      transcript.addTerm(term); //call to the TermList add method
   }
   
/**************************************************************/
/* Method: removeCourse */
/* Purpose: Removed a course of a specified term */
/* Parameters: */
/* int term: The term that contains the course
/* String courseCode: Indentifier of course to remove */
/* Result: 'Removes' CourseNode from the CourseList*/
/**************************************************************/

   public void removeCourse(int term, String courseCode)
   {
      transcript.removeCourse(courseCode, term); //call to the TermList remove method
   }
   
/**************************************************************/
/* Method: addCourse */
/* Purpose: Calls the TermList method for add */
/* Parameters: */
/* Course course: The course to add*/
/* Result: Add the course to the term's CourseList*/
/**************************************************************/

   public void addCourse(Course course)
   {
      transcript.addCourse(course); //call to the TermList add method
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
      transcript.searchByNum(courseNum); //call to the TermList search method
   }
   
/**************************************************************/
/* Method: searchByTitle */
/* Purpose: Search all the courses in the database */
/* to find a particular course using the course title */
/* Parameters: */
/* String searchTitle: Title of course to find */
/* Result: Prints all of the courses matching the course title*/
/**************************************************************/

   public void searchByTitle(String searchTitle)
   {
      transcript.searchByTitle(searchTitle); //call to the TermList search title method
   }
/**************************************************************/
/* Method: print */
/* Purpose: Prints all of the courses in the database */
/* Parameters: None */
/* Result: Prints all of the stored courses*/
/**************************************************************/

   public void print()
   {
      transcript.printAll(); //call to the TermList print method
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
      return transcript.getGPA(); //call to the TermList GPA method
   }
   
}