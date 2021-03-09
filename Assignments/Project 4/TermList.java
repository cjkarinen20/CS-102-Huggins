/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 4 */
/* TermList class: Defines a list of TermNode objects */
/**************************************************************/

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

class TermList
{
   private TermNode termHead; //the head of the TermList
   
   public TermList()
   {
      termHead = null; //initializes TermList object
   }
   
/**************************************************************/
/* Method: printAll */
/* Purpose: Prints all of the courses by term */
/* Parameters: None */
/* Result: Calls CourseList.printAll for every TermNode */
/**************************************************************/

   public void printAll()
   {
      TermNode current = termHead; //head of TermList
      while (current != null) //while list is not empty
      {
         CourseTree courseTree = current.getCourseTree(); //CourseList of current TermNode
         courseTree.printAll(); //calls CourseList's print method
         current = current.getNextTerm(); //gets next TermNode
      }
   }
    
/**************************************************************/
/* Method: writeToDisc */
/* Purpose: Writes all of the courses to a file */
/* Parameters: */
/* String: fileName: The file to be written to */
/* Result: Writes all of the courses to a single file */
/**************************************************************/

   public void writeToDisc(String fileName)
   {
   
      FileWriter fileWriter = null; //the filewriter object to be used
            
      try
      {
         fileWriter = new FileWriter(fileName); //initializes fileWriter using given file name
      }
      
      catch (IOException ioe)
      {
         System.out.println("Unable to create file...");
         return;
      }
      
      TermNode current = termHead; //head of TermList
      
      while (current != null) //while list is not empty
      {
         CourseTree courseTree = current.getCourseTree(); //CourseList of current TermNode
         courseTree.writeToDisc(fileWriter); //calls CourseList's print method
         current = current.getNextTerm(); //gets next TermNode
      }
      try
      {
         fileWriter.close(); //stop writing to file
      }
      catch (IOException ioe) //if writer doesn't exist
      {
         System.out.println("A problem occurred...");
      }
   }
   
/**************************************************************/
/* Method: searchByNum */
/* Purpose: Search all the courses in the database */
/* to find a particular course using the course code (i.e. CS-102) */
/* Parameters: */
/* String courseNum: The course code to search for */
/* Result: Calls CourseList.searchByNum */
/**************************************************************/

   public boolean searchByNum(String courseNum)
   {
      TermNode current = termHead; //head of TermList
      boolean match = false; //if a matching course has been found
      while (current != null) //while list is not empty
      {
         CourseTree courseTree = current.getCourseTree(); //CourseList of current TermNode
         
         if (courseTree.searchByNum(courseNum)) //calls a CourseList method to search
         {
            match = true; //if the method returns true a match was found
         }
         current = current.getNextTerm(); //get next TermNode
      }
      
      if (match != true) //if no match was found
      {
         System.out.println("No results found...");
      }
      return match; //return match
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
      TermNode current = termHead; //head of TermList
      boolean matchFound = false;
      while (current != null) //while list is not empty
      {
         CourseTree courseTree = current.getCourseTree(); //CourseList of current TermNode
         
         if (courseTree.searchByTitle(courseTitle)) //calls a CourseList method to search
         {
            matchFound = true; //matcch was found
         }
         current = current.getNextTerm(); //get next TermNode
      }
      
      if (matchFound != true) //match was not found
      {
         System.out.println("No results found...");
      }
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
      TermNode current = termHead; //walker variable
      TermNode previousTerm = null; //previous walker
      int courseTerm = Integer.parseInt(course.getCourseYear()); //term number of parameter Course
      int count = 0; //increment variable
      boolean termMatch = false; //if a matching term number has been found
      
      while ((current != null) && (termMatch == false)) //while the list is not empty
      {                                             //and doesn't contain a match
         int compareTerm = current.getTerm();//term number of current TermNode
         
         if (courseTerm != compareTerm) //if term is not a match
         {
            previousTerm = current; 
            current = current.getNextTerm();
            count++; 
            //move forward in list
         }
         else if (courseTerm == compareTerm) //if term is a match
         {
            CourseTree tempTree = current.getCourseTree(); //current TermNode's CourseList
            tempTree.addToCourseTree(course); //call to CourseList add
            termMatch = true; //match is true, breaking the loop
         }
      }
      
      if (termMatch != true) //if a match was not found
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
/* Result: 'Removes' CourseNode from the CourseList*/
/**************************************************************/

   public void removeCourse(String courseCode, int term)
   {
      TermNode current = termHead; //walker variable
      TermNode previousTerm = null; //previous walker
      int count = 0; //increment variable
      boolean match = false; //if a matching term number has been found
      
      while ((current != null) && (match == false)) //while the list is not empty
      {                                             //and doesn't contain a match
         int compareTerm = current.getTerm(); //term number of current TermNode
         
         if (term != compareTerm) //if term is not a match
         {
            previousTerm = current;
            current = current.getNextTerm();
            count++; 
            //move forward in list
         }
         else if (term == compareTerm) //if term is a match
         {
            CourseTree temp = current.getCourseTree(); //temporary course object
            temp.removeFromCourseTree(courseCode); //call to CourseList remove method
            match = true; //match is true, breaking loop
         }
      }
      if (match = false) //if term doesn't exist
      {
         System.out.println("No matching terms found..."); //print error message
      }
   }
   
/**************************************************************/
/* Method: addTerm */
/* Purpose: Adds a TermNode to TermList given the term number */
/* Parameters: */
/* int term: The term number of the term to add */
/* Result: Adds a new TermNode to the TermList */
/**************************************************************/

   public void addTerm (int term)
   {
      TermNode current = termHead; //walker variable
      TermNode previousTerm = null; //previous walker 
      int count = 0; //increment variable
      boolean present = false; //checks if node is already present
      
      if ((current == null) && (count == 0))
      {
         TermNode splice = new TermNode(); //create new TermNode 
         splice.setTerm(term);             //and start TermList
         termHead = splice;
        
      }
      while ((present == false) && (current != null)) //if list is not empty
      {
         int compareTerm = current.getTerm(); //current's term number for comparison
         
         if (term == compareTerm) //if term already exists
         {
            System.out.println("Term already exists..."); //print error message
            break; //break the loop
         }
         if (term > compareTerm) //if term goes later
         {
            previousTerm = current;           //move 
            current = current.getNextTerm(); //forward 
            count++;                        //in list
            
         }
         else if (term < compareTerm) //if term comes before current
         {
            TermNode splice = new TermNode(); //create new TermNode
            splice.setTerm(term); //set the term number
            splice.setNextTerm(current); //sets splice to current
            
            if (previousTerm == null)
            {
               termHead = splice; //sets the TermHead to spice
            }
            else 
            {
               previousTerm.setNextTerm(splice); //sets PreviousTerm to splice
            }
            //create and add node
            present = true;
            //object is added
         }
      }
      if (current == null && count > 0) //if term is first
      {
         TermNode splice = new TermNode(); //create new TermNode
         splice.setTerm(term); //set the term number
         previousTerm.setNextTerm(splice); //sets PreviousTerm to splice
      }
   }
   
/**************************************************************/
/* Method: getGPA */
/* Purpose: Calculate the GPA based on the grades */
/* of all terms */
/* Parameters: None */
/* Returns: Double: Numerical GPA (i.e. 4.0, 3.7, etc) */
/**************************************************************/

   public double getGPA()
   {
      double sum = 0; //sets an empty variable which is where we add up our sum
      int totalCredits = 0; //sets an empty variable which is our denominator
      TermNode current = termHead; //head of TermList
      while (current != null) //while list is not empty
      {
         CourseTree courseTree = current.getCourseTree(); //CourseList of current TermNode
         totalCredits += courseTree.getTotalCredits(); //calls CourseList's totalCredit method
         sum += courseTree.getSum(); //adds the current course's sum to the total sum
         current = current.getNextTerm(); //gets next TermNode
      }
      sum = (sum / totalCredits); //divides sum by totalCredits
      return sum; //returns sum
   } 
}