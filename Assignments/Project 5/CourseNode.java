/**************************************************************/
/* CJ Karinen                                                 */
/* Login ID: kari6535                                         */
/* CS-102, Winter 2021                                        */
/* Programming Assignment 5                                   */
/* CourseNode class: Store course objects that are            */
/* "held" in a CourseTree                                     */
/**************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import java.io.*;

class CourseNode
{
   private Course courseData; //the course object for the node
   protected CourseNode courseLeft; //the left course node
   protected CourseNode courseRight; //the right course node
   
   public CourseNode()
   {
      courseData = null; //default value for courseData
      courseLeft = null; //default value for left node
      courseRight = null; //default value for right node
   }
   
/**************************************************************/
/* Method: setCourseData                                      */
/* Purpose: Assigns given course object to the current node   */
/* Parameters:                                                */
/* Course: courseData: The course to store in the node        */
/* Result: The given course is now stored in the node         */
/**************************************************************/

   public void setCourseData(Course courseData)
   {
      this.courseData = courseData; //sets the course object
   }
   
/**************************************************************/
/* Method: getCourseData                                      */
/* Purpose: Return the course object stored in the node       */
/* Parameters: None                                           */
/* Returns: Course: The stored course object                  */
/**************************************************************/

   public Course getCourseData()
   {
      return courseData; //returns the course object
   }
   
/**************************************************************/
/* Method: getLeft, getRight                                  */
/* Purpose: Returns the next node in the search tree          */
/* Parameters: None                                           */
/* Returns: CourseNode: The next course                       */
/**************************************************************/

   public CourseNode getLeft()
   {
      return courseLeft; //returns the left node
   }
   
   public CourseNode getRight()
   {
      return courseRight; //returns the right node
   }
   
/**************************************************************/
/* Method: setLeft, setRight                                  */
/* Purpose: Sets the left or right course in the tree         */
/* Parameters:                                                */
/* CourseNode: the Coursenode to be assigned                  */
/* Results: Sets the next coursenode to the parameter         */
/**************************************************************/

   public void setLeft(CourseNode courseLeft)
   {
      this.courseLeft = courseLeft; //sets left node
   }
   
   public void setRight(CourseNode courseRight)
   {
      this.courseRight = courseRight; //sets right node
   }
   
}