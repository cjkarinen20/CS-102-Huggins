/**************************************************************/
/* CJ Karinen                                                 */
/* Login ID: kari6535                                         */
/* CS-102, Winter 2021                                        */
/* Programming Assignment 5                                   */
/* TermNode class: Stores an indentifying integer and BSTs    */
/* of CourseNode objects                                      */
/**************************************************************/

class TermNode 
{
   private int term; //the term number stored in the node
   private TermNode nextTerm; //the next term in the list
   private CourseTree courseTree; //the BST of courses for this term
   
   public TermNode()
   {
      term = 000000; //default value for term
      courseTree = new CourseTree(); //initialize coursetree
      nextTerm = null; //default value for nextTerm
   }
   
/**************************************************************/
/* Method: getTerm                                            */
/* Purpose: Return the term value                             */
/* Parameters: None                                           */
/* Returns: int: The value of the TermNode                    */
/**************************************************************/

   public int getTerm() 
   {
      return term;
   }
   
/**************************************************************/
/* Method: getNextTerm                                        */
/* Purpose: return the next Term                              */
/* Parameters: None                                           */
/* Returns: TermNode: The next TermNode                       */
/**************************************************************/

   public TermNode getNextTerm() 
   {
      return nextTerm;
   }
   
/**************************************************************/
/* Method: setTerm                                            */
/* Purpose: Set the term number for the current term          */
/* Parameters:                                                */
/* int: term: The given term number                           */
/* Result: Sets term equal to given term number               */
/**************************************************************/

   public void setTerm(int term)
   {
      this.term = term;
   }
      
/**************************************************************/
/* Method: setNextTerm                                        */
/* Purpose: Set the term number for the next term             */
/* Parameters:                                                */
/* int: term: The given term number                           */
/* Result: Sets nextTerm equal to given term number           */
/**************************************************************/
   
   public void setNextTerm (TermNode nextTerm)
   {
      this.nextTerm = nextTerm;
   }
      
/**************************************************************/
/* Method: getCourseTree                                      */
/* Purpose: Return the BST of courses for the term            */
/* Parameters: None                                           */
/* Returns: CourseTree: Returns the CourseTree of the TermNode*/
/**************************************************************/

   public CourseTree getCourseTree()
   {
      return courseTree;
   }
}