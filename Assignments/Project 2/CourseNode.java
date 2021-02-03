/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 2 */
/* CourseNode class: Store course objects and are 
/* "held" in a CourseList */
/**************************************************************/

import java.util.*;

class CourseNode
{
   private Course courseData; //the course object for the node
   private CourseNode nextCourse; //the next node in the list
   
   public CourseNode()
   {
      courseData = null; //sets default value for courseData 
      nextCourse = null; //and nextCourse to be null
   }
   
/**************************************************************/
/* Method: setCourseData */
/* Purpose: Assigns given course object to the current node */
/* Parameters:  */
/* Course: courseData: The course to store in the node */
/* Result: The given course is now stored in the node*/
/**************************************************************/

   public void setCourseData(Course courseData)
   {
      this.courseData = courseData;
   }
   
/**************************************************************/
/* Method: getCourseData */
/* Purpose: Return the course object stored in the node */
/* Parameters: None */
/* Returns: Course: The stored course object */
/**************************************************************/

   public Course getCourseData()
   {
      return courseData; 
   }
   
/**************************************************************/
/* Method: setNextCourse */
/* Purpose: Sets the next course */
/* Parameters:  */
/* Course: nextCourse: The course to be assigned */
/* Results: Sets the next course to the parameter */
/**************************************************************/

   public void setNextCourse(CourseNode nextCourse)
   {
      this.nextCourse = nextCourse;
   }
   
/**************************************************************/
/* Method: getNextCourse */
/* Purpose: Returns the nextCourse for the node */
/* Parameters: None */
/* Returns: CourseNode: The next course */
/**************************************************************/

   public CourseNode getNextCourse()
   {
      return nextCourse;
   }   
   
}