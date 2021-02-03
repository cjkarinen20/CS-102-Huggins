/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 2 */
/* Database class: Stores and indentifying integer and sorted lists*/
/* of CourseNode objects */
/**************************************************************/

class TermNode 
{
   private int term; //the term number stored in the node
   private TermNode nextTerm;
   private CourseList courses; //the LinkedList of courses 
                               //contained in each term node
   public TermNode()
   {
      term = 000000; //default value for term
      nextTerm = null; //default value for nextTerm
      courses = new CourseList(); //initializes empty list
   }
   
/**************************************************************/
/* Method: getTerm */
/* Purpose: Return the term value */
/* Parameters: None */
/* Returns: int: The value of the TermNode */
/**************************************************************/

   public int getTerm() 
   {
      return term;
   }
   
/**************************************************************/
/* Method: getNextTerm */
/* Purpose: return the next Term */
/* Parameters: None */
/* Returns: TermNode: The next TermNode */
/**************************************************************/

   public TermNode getNextTerm() 
   {
      return nextTerm;
   }
   
/**************************************************************/
/* Method: setTerm */
/* Purpose: Set the term number for the current term */
/* Parameters:  */
/* int: term: The given term number */
/* Result: Sets term equal to given term number */
/**************************************************************/

   public void setTerm(int term)
   {
      this.term = term;
   }
      
/**************************************************************/
/* Method: setNextTerm */
/* Purpose: Set the term number for the next term */
/* Parameters:  */
/* int: term: The given term number */
/* Result: Sets nextTerm equal to given term number */
/**************************************************************/
   
   public void setNextTerm (TermNode nextTerm)
   {
      this.nextTerm = nextTerm;
   }
      
/**************************************************************/
/* Method: getCourseList */
/* Purpose: Return the list of course for the term */
/* Parameters: None */
/* Returns: CourseList: Returns the CourseList of the TermNode */
/**************************************************************/

   public CourseList getCourseList()
   {
      return courses;
   }
}