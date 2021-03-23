/**************************************************************/
/* CJ Karinen                                                 */
/* Login ID: kari6535                                         */
/* CS-102, Winter 2021                                        */
/* Programming Assignment 5                                   */
/* Database class: Stores a TermList called transcript        */
/* and calls methods to interact with stored objects          */
/**************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import java.io.*;


class Database
{
   TermList transcript; //TermList object used by database
   
   public Database()
   {
      transcript = new TermList(); //initializes TermList
   }
   
/**************************************************************/
/* Method: addTerm                                            */
/* Purpose: Calls the TermList addTerm method to              */
/* the initial TermList                                       */
/* Parameters:                                                */
/* int term: The term number of the term to add               */
/* Result: Adds a new TermNode to the TermList                */
/**************************************************************/

   public void addTerm(int term)
   {
      transcript.addTerm(term); //call to the TermList add method
   }
   
/**************************************************************/
/* Method: removeCourse                                       */
/* Purpose: Removed a course of a specified term              */
/* Parameters:                                                */
/* int term: The term that contains the course                */
/* String courseCode: Indentifier of course to remove         */
/* JTextArea outputArea: The element to output text to.       */
/* Result: 'Removes' CourseNode from the CourseList           */
/**************************************************************/

   public void removeCourse(int term, String courseCode, JTextArea outputArea)
   {
      outputArea.setText(""); //clears the output panel
      transcript.removeCourse(courseCode, term, outputArea); //call to the TermList remove method
   }
   
/**************************************************************/
/* Method: addCourse                                          */
/* Purpose: Calls the TermList method for add                 */
/* Parameters:                                                */
/* Course course: The course to add                           */
/* JTextArea outputArea: The element to output text to.       */
/* Result: Add the course to the term's CourseList            */
/**************************************************************/

   public void addCourse(Course course, JTextArea outputArea)
   {
      outputArea.setText(""); //clears the output panel
      transcript.addCourse(course, outputArea); //call to the TermList add method
   }
   
/**************************************************************/
/* Method: searchByNum                                        */
/* Purpose: Search all the courses in the database            */
/* to find a particular course using the course code          */
/* (i.e. CS-102)                                              */
/* Parameters:                                                */
/* String courseNum: The course code to search for            */
/* JTextArea outputArea: The element to output text to.       */
/* Result: Prints all of the courses matching the course code */
/* Return: boolean: Whether a matching course was found       */
/**************************************************************/

   public boolean searchByNum(String courseNum, JTextArea outputArea)
   {
      outputArea.setText(""); //clears the output panel
      return transcript.searchByNum(courseNum, outputArea); //call to the TermList search method
   }
   
/**************************************************************/
/* Method: searchByTitle                                      */
/* Purpose: Search all the courses in the database            */
/* to find a particular course using the course title         */
/* Parameters:                                                */
/* String searchTitle: Title of course to find                */
/* JTextArea outputArea: The element to output text to.       */
/* Result: Prints all of the courses matching the course title*/
/**************************************************************/

   public void searchByTitle(String searchTitle, JTextArea outputArea)
   {
      outputArea.setText(""); //clears the output panel
      transcript.searchByTitle(searchTitle, outputArea); //call to the TermList search title method
   }
   
/**************************************************************/
/* Method: print                                              */
/* Purpose: Prints all of the courses in the database         */
/* Parameters:                                                */
/* JTextArea outputArea: The element to output text to.       */
/* Result: Prints all of the stored courses                   */
/**************************************************************/

   public void print(JTextArea outputArea)
   {
      outputArea.setText(""); //clears the output panel
      transcript.printAll(outputArea); //call to the TermList print method
   }
   
/**************************************************************/
/* Method: writeToDisc                                        */
/* Purpose: Writes all of the courses to a file               */
/* Parameters:                                                */
/* String: fileName: The file to be written to                */
/* JTextArea outputArea: The element to output text to.       */
/* Result: Writes all of the courses to a single file         */
/**************************************************************/

   public void writeToDisc(String fileName, JTextArea outputArea)
   {
      outputArea.setText(""); //clears the output panel
      transcript.writeToDisc(fileName, outputArea); //call to the TermList write method
   }
   
/**************************************************************/
/* Method: getGPA                                             */
/* Purpose: Calculate the GPA based on the grades             */
/* of all stored course objects.                              */
/* Parameters: None                                           */
/* Returns: Double: Numerical GPA (i.e. 4.0, 3.7, etc)        */
/**************************************************************/

   public double getGPA()
   {
      return transcript.getGPA(); //call to the TermList GPA method
   }

}