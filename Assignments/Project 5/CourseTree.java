/**************************************************************/
/* CJ Karinen                                                 */
/* Login ID: kari6535                                         */
/* CS-102, Winter 2021                                        */
/* Programming Assignment 5                                   */
/* CourseTree class: Defines a binary search tree of          */
/* CourseNode objects                                         */
/**************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

class CourseTree
{

   private CourseNode rootNode; //the root node of the CourseTree structure
   
   public CourseTree() 
   {
      rootNode = null; //intialize default value for node
   }
     
/**************************************************************/
/* Method: addToCourseTree                                    */
/* Purpose: removes specified Course from the CourseList      */
/* using the CourseCode as a reference                        */
/* Parameters:                                                */
/* Course: course: The course to be added                     */
/* JTextArea outputArea: The element to output text to.       */
/* Result: The specified course is added                      */
/**************************************************************/

   public void addToCourseTree(Course course, JTextArea outputArea)
   {
      CourseNode current = rootNode; //the current node
      CourseNode previous = null; //the previous node in the order
      String courseCode = course.getCourseCode(); //the CourseCode of the parameter
      
      if (current == null) //if tree is empty
      {
         CourseNode cNode = new CourseNode(); //temporary node
         cNode.setCourseData(course); //sets cNode to reference parameter object
         cNode.setLeft(null); //sets left to null
         cNode.setRight(null); //sets right to null
         rootNode = cNode; //temporary node becomes the root
      }
      while (current != null) //while current is not empty
      {
      
         Course tempCourse = current.getCourseData(); //the current course object 
         String tempCode = tempCourse.getCourseCode(); //the course code of current
         previous = current; //previous set to current as we prepare to move forward
         if (tempCode.compareTo(courseCode) < 0) //if the course goes before
         {
            current = current.getRight(); //move to the right node
         }
         else if (tempCode.equals(courseCode)) //if course already exists
         {
            outputArea.append("\nCourse already exists..."); //error message
            return; //breaks the loop
         }
         else //else course goes later
         { 
            current = current.getLeft(); //move to the left node
         }
         
      }
      
      CourseNode courseNode = new CourseNode(); //the node to be added 
      courseNode.setCourseData(course); //sets our new node to reference parameter object
      courseNode.setLeft(null); //sets left to null
      courseNode.setRight(null); //sets right to null
      
      if (previous == null) //if the new node goes first
      {
         rootNode = courseNode; //the new node becomes the root
      }    
      else 
      {
         Course prevCourse = previous.getCourseData(); //previous course object
         String prevCode = prevCourse.getCourseCode(); //course code of previous
         
         if (prevCode.compareTo(courseCode) < 0) //if previous code is less than courseCode
         {
            previous.setRight(courseNode); //the new node becomes the right node of previous
         }        
         else //if previous code is greater than
         {
            previous.setLeft(courseNode); //the new node becomes the left node of previous
         }
      }
      outputArea.append("Course added..."); //confirmation message
   }
   
/**************************************************************/
/* Method: removeFromCourseTree                               */
/* Purpose: makes a call to a private recursive remove method */
/* using the CourseCode as a reference                        */
/* Parameters:                                                */
/* String: courseNum: The course code in the format (EE-###)  */
/* JTextArea outputArea: The element to output text to.       */
/* Result: The specified course is removed                    */
/**************************************************************/

   public void removeFromCourseTree(String courseNum, JTextArea outputArea)
   {
      rootNode = remove (courseNum, rootNode, outputArea); //makes a call to private recursive remove method
   }
   
/**************************************************************/
/* Method: remove                                             */
/* Purpose: removes specified Course from the CourseList      */
/* using the CourseCode as a reference                        */
/* Parameters:                                                */
/* String: courseNum: The course code in the format (EE-###)  */
/* CourseNode: current: The root of the current tree          */
/* JTextArea outputArea: The element to output text to.       */
/* Result: The specified course is removed                    */
/**************************************************************/

   private CourseNode remove(String courseNum, CourseNode current, JTextArea outputArea)
   {
      if (current == null) //if the list is empty
      {
         outputArea.append("\nCourse not found..."); //error message
         return null; //empty return
      }
      
      Course tempCourse = current.getCourseData(); //the current course object
      String tempCode = tempCourse.getCourseCode(); //the course code of current
      
      if (tempCode.compareTo(courseNum) < 0) //if parameter goes later
      {
         current.setRight(remove(courseNum, current.getRight(), outputArea)); //recursive call starting from right node
         return current; //returns the result of remove
      }
      
      if (tempCode.compareTo(courseNum) > 0) //if parameter comes sooner
      {
         current.setLeft(remove(courseNum, current.getLeft(), outputArea)); //recursive call starting from left node
         return current; //returns the result of remove
      }
     
      if (current.getLeft() == null)  //if left is null
      {
         return current.getRight(); //return right
      }

      if (current.getRight() == null) // return left 
      {
         return current.getLeft(); //return left
      }
      
      CourseNode heir = current.getLeft(); //the left node
      while (heir.getRight() != null) //if heir doesn't have a right node
      {
         heir = heir.getRight(); //sets heir's right node to heir
      }
      current.setCourseData(heir.getCourseData()); //sets current to heir
      Course heirCourse = heir.getCourseData(); //the course of heir
      current.setLeft(remove(heirCourse.getCourseCode(), current.getLeft(), outputArea)); //unlinks heir from the tree
      outputArea.append("\nCourse Removed..."); //prints confirmation
      return current; //returns current
   }
   
/**************************************************************/
/* Method: searchByTitle                                      */
/* Purpose: Searches for a given keyword and prints the       */
/* matches.                                                   */
/* Parameters:                                                */
/* String courseTitle: The given keyword to search for       */
/* JTextArea outputArea: The element to output text to.       */
/* Returns: boolean: Whether a matching course has been found */
/**************************************************************/ 

   public boolean searchByTitle(String courseTitle, JTextArea outputArea)
   {
      CourseNode current = rootNode; //the root of the course tree
      CourseNode previous = null; //the previous coursenode
      boolean courseMatch = false; //whether a matching course has been found
      
      while (current != null && courseMatch == false)
      { //while the list is not empty and a match has not been found
         Course tempCourse = current.getCourseData(); //the current course object
         String tempTitle = tempCourse.getCourseTitle(); //the current course title
         previous = current; //previous set to current as we prepare to move forward
         if (tempTitle.contains(courseTitle)) //if current title contains parameter
         {
            tempCourse.print(outputArea); //print current course
            courseMatch = true; //a matching course has been found 
         }
         if (tempTitle.compareTo(courseTitle) < 0) //if parameter goes later
         {
            current = current.getRight(); //move to right node
         }
         else //if parameter comes sooner 
         {
            current = current.getLeft(); // move to left node 
         }
      }
      return courseMatch; //return coursematch (determines whether to print "course not found" or not)
   }
   
/**************************************************************/
/* Method: searchByNum                                        */
/* Purpose: Searches for a given CourseCode                   */
/* and prints the result                                      */
/* Parameters:                                                */
/* String courseNum: The course code in the format (EE-###)  */
/* JTextArea outputArea: The element to output text to.       */
/* Returns: boolean: Whether a matching course has been found */
/**************************************************************/  

   public boolean searchByNum(String courseNum, JTextArea outputArea)
   {
      CourseNode current = rootNode; //the root of the coursetree
      CourseNode previous = null; //the previous coursenode
      boolean courseMatch = false; //whether a matching course has been found
      
      while (current != null && courseMatch == false)
      { //while the list is not empty and a match has not been found
      
         Course tempCourse = current.getCourseData(); //the current course object
         String tempCode = tempCourse.getCourseCode(); //the current course code
         previous = current; //previous set to current as we prepare to move forward
         if (tempCode.compareTo(courseNum) < 0) //if parameter comes later 
         {
            current = current.getRight(); //move to right course node
         }
         else if (tempCode.compareTo(courseNum) == 0) //the two course codes are the same
         {
            tempCourse.print(outputArea); //print current course
            courseMatch = true; //a match has been found 
         }
         else //if parameter comes sooner
         {
            current = current.getLeft(); //move to left node
         }
      }
      return courseMatch; //return coursematch (determines whether to print "course not found" or not)
   }
   
/**************************************************************/
/* Method: printAll                                           */
/* Purpose: Print all the courses in the database             */
/* Parameters: None                                           */
/* Result: Calls the private print method                     */
/**************************************************************/ 

   public void printAll(JTextArea outputArea)
   {
      print(rootNode, outputArea); //call to private method     
   }
   
/**************************************************************/
/* Method: print                                              */
/* Purpose: Print all the courses in the database             */
/* Parameters:                                                */
/* CourseNode: current: The root node of the tree to print    */
/* JTextArea outputArea: The element to output text to.       */
/* Returns: Prints all the courses in the course tree         */
/**************************************************************/   

   private void print(CourseNode current, JTextArea outputArea)
   {
      if (current == null) //if list is empty
      {
         return; //empty return
      }
      print(current.getLeft(), outputArea); //recursive call to left node
      Course tempCourse = current.getCourseData(); //current course object
      tempCourse.print(outputArea); //print current course
      print(current.getRight(), outputArea); //recursive call to right node
   }
   
/**************************************************************/
/* Method: writeToDisc                                        */
/* Purpose: Writes all of the courses to a file               */
/* Parameters:                                                */
/* FileWriter: fileWriter: The file to be written to          */
/* Result: Writes all of the courses to a single file         */
/**************************************************************/

   public void writeToDisc(FileWriter fileWriter)
   { 
      writeToDisc(rootNode, fileWriter); //call to private method
   }
   
/**************************************************************/
/* Method: writeToDisc                                        */
/* Purpose: Writes all of the courses to a file               */
/* Parameters:                                                */
/* FileWriter: fileWriter: The file to be written to          */
/* CourseNode: current: The root node of the course tree      */
/* Result: Writes all of the courses to a single file         */
/**************************************************************/   

   private void writeToDisc(CourseNode current, FileWriter fileWriter)
   {
      if (current == null) //if list is empty
      {
         return; //empty return
      }
      writeToDisc(current.getLeft(), fileWriter); //recursive call starting at left node
      Course tempCourse = current.getCourseData(); //current course object
      tempCourse.writeToDisc(fileWriter); //writes current course to file
      writeToDisc(current.getRight(), fileWriter); //recursive call starting at right node
   }
   
/**************************************************************/
/* Method: getTotalCredits                                    */
/* Purpose: Calls the private method to ge total credits      */
/* Parameters: None                                           */
/* Returns: int: The total credits for the term               */
/**************************************************************/
   
   public int getTotalCredits()
   {
      int credits = 0; //sum variable passed into private method
      return getTotalCredits(credits, rootNode); //return the result of the private method
   }
   
/**************************************************************/
/* Method: getTotalCredits                                    */
/* Purpose: Return the totalCredits for each term             */
/* Parameters:                                                */
/* int credits: Empty passed in from the public method        */
/* CourseNode current: The root of the course tree            */
/* Returns: int: The total credits for the term                */
/**************************************************************/   
   
   private int getTotalCredits(int credits, CourseNode current)
   {
      if (current == null) //if the list is empty
      {
         return credits; //empty return
      }
      
      Course tempCourse = current.getCourseData(); //the current course object
      
      credits = getTotalCredits(credits, current.getLeft()); //recursive call starting at left node
      
      if (!(tempCourse.getFlag().equals("Y"))) //if the course is not to be excluded
      {    
         credits += tempCourse.getCredits(); //adds current credits to total credits 
      }

      credits = getTotalCredits(credits, current.getRight()); //recursive call starting at right node
      
      return credits; //returns credits
   }
   
/**************************************************************/
/* Method: getSum                                             */
/* Purpose: Return sum for use in TermList class              */
/* Parameters: None                                           */
/* Returns: Double: The sum of term grades * credits          */
/**************************************************************/

   public double getSum()
   {
      double sum = 0; //sum variable passed into private method
      return getSum(sum, rootNode); //return the result of the private method
   }
   
/**************************************************************/
/* Method: getSum                                             */
/* Purpose: Return the sum of credits multiplied by the grade */
/* grade for each term                                        */
/* Parameters:                                                */
/* double sum: Empty passed in from the public method         */
/* CourseNode current: The root of the course tree            */
/* Returns: int: The total credits for the term               */
/**************************************************************/    
 
   private double getSum(double sum, CourseNode current)
   {
      if (current == null) //if list is empty
      {
         return sum; //empty return
      }
      
      Course tempCourse = current.getCourseData(); //current course object
      
      sum = getSum(sum, current.getLeft()); //recursive call starting at left node
      
      if (!(tempCourse.getFlag().equals("Y"))) //if the course is not to be excluded
      {
         sum += (tempCourse.getGrade() * tempCourse.getCredits()); //adds current credits * grade to sum,
      }
      
      sum = getSum(sum, current.getRight()); //recursive call starting at right node
      
      return sum; //returns sum
   }
}