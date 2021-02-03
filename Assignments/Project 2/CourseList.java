/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 2 */
/* CourseList class: Defines a list of CourseNode objects */
/**************************************************************/


class CourseList
{
   private CourseNode courseHead; //the head of the CourseList

   
   public CourseList()
   {
      courseHead = null; //intializes CourseList object
   }
   
/**************************************************************/
/* Method: getTotalCredits */
/* Purpose: Return the totalCredits for each term */
/* Parameters: None */
/* Returns: int: Number of credits */
/**************************************************************/

   public int getTotalCredits()
   {
      int totalCredits = 0; //initializes return variable
      CourseNode current = courseHead; //walker variable
      
      while (current != null) //while life is not empty
      {
         Course temp = current.getCourseData(); //current course object
         
         if (!(temp.getFlag().equals("Y"))) //if the course is not to be excluded
         {
            totalCredits += temp.getCredits(); //adds current course credits to total
         }
         current = current.getNextCourse(); //moves forward in the list
      }
      return totalCredits; //return total
   }
   
/**************************************************************/
/* Method: getSum */
/* Purpose: Return sum for use in TermList class */
/* Parameters: None */
/* Returns: Double: The sum of term grades * credits */
/**************************************************************/

   public double getSum()
   {
      double sum = 0; //initializes return variable
      CourseNode current = courseHead; //walker variable
      
      while (current != null) //while list is not empty
      {
         Course temp = current.getCourseData(); //the current course object
         if (!(temp.getFlag().equals("Y"))) //if the course is not to be excluded
         {
            sum += (temp.getGrade() * temp.getCredits()); //adds grade * credits to sum
         }
         current = current.getNextCourse(); //moves forward in the list
      }
      return sum; //return sum
   }
   
/**************************************************************/
/* Method: printAll */
/* Purpose: Calculate the GPA based on the grades */
/* Parameters: None */
/* Returns: Double: Numerical GPA (i.e. 4.0, 3.7, etc) */
/**************************************************************/

   public void printAll()
   {
      CourseNode current = courseHead; //walker variable
      while (current != null) //while the list is not empty
      {
         Course temp = current.getCourseData(); //the current course object
         temp.print(); //calls Course print method
         current = current.getNextCourse(); //moves forward in list
      }
   }
   
/**************************************************************/
/* Method: getHead */
/* Purpose: Sets the node head */
/* Parameters: None */
/* Result: Sets courseHead to point toward the specified node */
/**************************************************************/ 

   public void setHead(CourseNode courseHead)
   {
      this.courseHead = courseHead; //points the head toward 
   }                                //the given CourseNode
   
/**************************************************************/
/* Method: getHead */
/* Purpose: Returns the node head */
/* Parameters: None */
/* Returns: CourseNode: returns the CourseNode head */
/**************************************************************/ 

   public CourseNode getHead()
   {
      return courseHead; //returns the head node
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
      CourseNode current = courseHead; //head of the list
      while (current != null) //while the list is not empty
      {
         Course temp = current.getCourseData(); //temporary course object 
         String tempCode = temp.getCourseCode(); //temporary course code for comparison
         if (courseNum.compareTo(tempCode) == 0) //if parameter string equals current string
         {
            temp.print(); //prints matching course
         }
         current = current.getNextCourse(); //moves on to the next node
      }
   }
   
/**************************************************************/
/* Method: searchByTitle */
/* Purpose: Searches for a given keyword and prints the matches */
/* Parameters: */
/* String: searchTerm: The given keyword to search for
/* Result: The specified course is printed*/
/**************************************************************/

   public void searchByTitle(String searchTerm)
   {
      CourseNode current = courseHead; //head of the list
      while (current != null) //while the list is not empty
      {
         Course temp = current.getCourseData(); //temporary course object 
         String tempTitle = temp.getCourseTitle(); //temporary course code for comparison
         if (tempTitle.contains(searchTerm)) //if current title contains 
         {                                   //parameterized searchTerm
            temp.print(); //prints matching course
         }
         current = current.getNextCourse(); //moves on to the next node
      }
   }
   
/**************************************************************/
/* Method: addToCourseList */
/* Purpose: removes specified Course from the CourseList */
/* using the CourseCode as a reference */
/* Parameters: */
/* Course: course: The course to be added
/* Result: The specified course is added*/
/**************************************************************/

   public void addToCourseList(Course course)
   {
      CourseNode current = courseHead; //walker variable
      CourseNode previous = null; //previous walker
      int count = 0; //used in place of index
      String courseCode = course.getCourseYear(); //CourseCode of the parameter object
      //...used for comparison in sorting


         if (courseHead == null) //if the list is empty
         {
            CourseNode splice = new CourseNode(); //create new node
            splice.setCourseData(course); //assign course to the node
            courseHead = splice; //splice becomes the headnode
            return; 
         }
         while (current != null) //while list is not empty
         { 
            Course courseTemp = current.getCourseData(); //current node's course object
            String tempCode = courseTemp.getCourseCode(); //current node's course code for comparison
            
            if (courseCode.equals(tempCode)) //checks if course already exists
            {
               System.out.print("Course entry already exists..."); //prints message
               break; //ends the loop
            }
            
            if (courseCode.compareTo(tempCode) > 0) //if the course comes later
            {
               previous = current; //moves current back in the list
               current = current.getNextCourse(); //assigns nextcourse to current
               count++; //moves forward in list
            }
            else if (courseCode.compareTo(tempCode) < 0) //if the course comes sooner
            {
               CourseNode splice = new CourseNode();//creates new CourseNode
               splice.setCourseData(course); //assigns course to new CourseNode
               splice.setNextCourse(current); 
                
               if(previous == null) //if previous is empty
               {
                  setHead(splice); //splice becomes the headnode
               }
               else
               {
                  previous.setNextCourse(splice); //sets splice before current
               }
               break; //breaks the loop
            }
         }
   }
   
/**************************************************************/
/* Method: removeFromCourseList */
/* Purpose: removes specified Course from the CourseList */
/* using the CourseCode as a reference */
/* Parameters: */
/* String: courseCode: The course code in the format (EE-###)
/* Result: The specified course is removed*/
/**************************************************************/

   public void removeFromCourseList(String courseCode)
   {
      CourseNode current = courseHead; //walker variable
      CourseNode previous =  null; //previous walker
      int count = 0; //used in place of index
      
         while (current != null)
         { 
            Course courseTemp = current.getCourseData(); //current node's course object
            String tempCode = courseTemp.getCourseCode(); //current node's course code for comparison
            
            if (!(courseCode.equals(tempCode))) //checks if courseCodes match
            {
               previous = current;               //moves forward 
               current = current.getNextCourse();//in the list
            }
            else
            {
               break; //break the loop
            }
         }
         if (current == null) //if list is empty
         {
            throw new IndexOutOfBoundsException(); //throw exception
         }  
         if (previous != null) //if previous is not empty
         {
            previous.setNextCourse(current.getNextCourse()); //unlink the CourseNode from the list
         }  
         else
         {
            setHead(current.getNextCourse()); //relink the other Node in the list
         }  
         Course temp = current.getCourseData(); //temporary course object
         System.out.println("Removed Course: "); //header statement
         temp.print(); //print the removed course list
   }
}