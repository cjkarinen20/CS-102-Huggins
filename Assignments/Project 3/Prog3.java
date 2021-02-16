/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 3 */
/* Prog3 class: Contains the main method and methods relating*/
/* to variable assignment and user interaction (UI). */
/**************************************************************/

import java.util.*;
import java.io.*;

public class Prog3
{
   
   static int courseCredits;
   static String courseYear; //the course year in the format "YYYYTT"
   static String courseCode; //the course code in the format "EEE-###" (i.e. CS-102)
   static String letterGrade; //the letter grade for a course (A, B-, C+, etc)
   static String courseTitle; //the full course name (Ex: Microcomputers I)
   static String flag; //a string ("Y" or "N") that determines whether the course is excluded from GPA calculations
   
/**************************************************************/
/* Method: processData */
/* Purpose: Reads input from a file and seperates */
/* scanned information into stored variables.*/
/* Parameters: */
/* String line: The line of String input to be processed. */
/* Result: Scanned input is ready to be turned into a */
/* new course object. */
/**************************************************************/
   public static void main(String[] args)
   {

      Scanner console = new Scanner(System.in); //reads input from the keyboard
      Scanner file = null; 
      
      int index = 0; //initializes an index value to be used in calling database.add
      
      Database myDatabase = new Database(); //creates a new database object
 
      try {
            file = new Scanner(new File(args[0]));
          }
      catch (ArrayIndexOutOfBoundsException exc) 
         {
           System.out.println("No arguments given to program.");
           System.exit(1);
         }
      catch (FileNotFoundException exc) 
         {
           System.out.println("File could not be opened.");
           System.exit(2);
         } 
      while (file.hasNext()) //loops as long as the file contains more input
         {  
            String dataLine = file.nextLine(); //creates a string variable out of the next line in the file
            processData(dataLine); //calls processData (assigns variables from input file) using the dataLine string
            Course course = new Course(courseYear, courseCode, courseCredits, letterGrade, courseTitle, flag); //creates a new course object
            int termNum = Integer.parseInt(courseYear); //the term to be added
            myDatabase.addTerm(termNum); //calls database method to add the term
            myDatabase.addCourse(course); //calls database method to add the course
            index++; //increments index
         }
         userInterface(console, myDatabase); //calls the userInterface method using created scanner and database object
   }
   
/**************************************************************/
/* Method: processData */
/* Purpose: Reads input from a file and seperates */
/* scanned information into stored variables.*/
/* Parameters: *
/* String line: The line of String input to be processed. */
/* Result: Scanned input is ready to be turned into a */
/* new course object. */
/**************************************************************/

   public static void processData(String line)
   {
      Scanner scn = new Scanner(line).useDelimiter("/"); //seperates the string into pieces by specifying a delimiter
      
      while (scn.hasNext()) //loops as long as the scanner has input
      {
         courseYear = scn.next(); //assigns the first string segment to courseYear   
         courseCode = scn.next(); //assigns the next segment to courseCode
         courseCredits = Integer.parseInt(scn.next()); //parses the next String to int and assigns to courseCredits      
         courseTitle = scn.next(); //assigns next segment to courseTitle
         letterGrade = scn.next(); //assigns next piece to letterGrade
         flag = scn.next(); //assigns the final piece to flag
      }
   }
   
/**************************************************************/
/* Method: userInterface */
/* Purpose: Prints the available input options to the user */
/* and reacts according to the options selected. */
/* Parameters: */
/* Scanner scn: Used to read user input from the keyboard. */
/* Database database: The given database object through */
/* which the search, getGPA, and print methods will be called. */
/* Result: Prints an 'interactive' user interface. */
/**************************************************************/

   public static void userInterface(Scanner scn, Database database)
   {
     while (true) //loops infinitely
     {
      System.out.println("Welcome to the CS-102 Transcript Program"); //welcome message
      System.out.println("Current available commands: ");      //display
      System.out.println("1 --> Search by course number");  //input
      System.out.println("2 --> Search by course title");     //options
      System.out.println("3 --> Add a new course");        
      System.out.println("4 --> Delete a course");
      System.out.println("5 --> Edit an existing course");
      System.out.println("6 --> Print all records");
      System.out.println("7 --> Compute GPAs");
      System.out.println("8 --> Exit");
      
      int input = scn.nextInt(); //assigns to the user input
      scn.nextLine();
      
      if (input == 1) 
      {  
         System.out.println("Please enter the number of the course you would like to search for: "); //prompt for search term
         String searchNum = scn.nextLine(); //user inputted course code
         database.searchByNum(searchNum); //calls the database search method 
      }
      if (input == 2)
      {
         System.out.println("Please enter the title of the course you would like to search for: ");   //prompt for search term
         String searchTerm = scn.nextLine(); //user inputted search term
         database.searchByTitle(searchTerm); //calls the database search by title method 
      }
      if (input == 3)
      {
         System.out.println("Please enter the course year (YYYYTT): ");    //Prompt user
         courseYear = scn.nextLine();                                      //for required 
         System.out.println("Please enter the course code (EEE-###): ");   //course data 
         courseCode = scn.nextLine();                                      //variables
         System.out.println("Please enter the number of credits for this course: ");
         courseCredits = Integer.parseInt(scn.nextLine());
         System.out.println("Please enter the letter grade: ");
         letterGrade = scn.nextLine();
         System.out.println("Please enter full course title (Ex: Microcomputers I): ");
         courseTitle = scn.nextLine();
         System.out.println("Should the course be excluded from GPA? (Y or N): ");
         flag = scn.nextLine();
         Course courseEntry = new Course(courseYear, courseCode, courseCredits, letterGrade, courseTitle, flag); //initializes new course 
         database.addCourse(courseEntry); //calls database method to add the new course 
      }
      if (input == 4) 
      {
         System.out.println("Please enter the Term: ");       //takes in user 
         int academicTerm = Integer.parseInt(scn.nextLine()); //input for the term 
         System.out.println("Please enter the course code: ");//and course code
         String searchCode = scn.nextLine();
         System.out.println("Delete this course? (Y or N): "); //ask user to confirm decision
         if (scn.nextLine().equals("Y")) //if answer is yes
         {
           database.removeCourse(academicTerm, searchCode); //call to database remove method
         }
         else //if user says no, return to neutral state
         {
            //do nothing
         }
      }
      if (input == 5)
      {
         System.out.println("Please enter the Term: ");       //takes in user 
         int academicTerm = Integer.parseInt(scn.nextLine()); //input for the term
         System.out.println("Please enter the course code: ");//and course code
         String searchCode = scn.nextLine();
         System.out.println("Edit this course? (Y or N): ");
         if (scn.nextLine().equals("Y")) //if answer is yes
         {
           database.removeCourse(academicTerm, searchCode); //call to database remove method
           System.out.println("Please enter the course year (YYYYTT): ");    //Prompt user
           courseYear = scn.nextLine();                                      //for required 
           System.out.println("Please enter the course code (EEE-###): ");   //course data 
           courseCode = scn.nextLine();                                      //variables
           System.out.println("Please enter the number of credits for this course: ");
           courseCredits = Integer.parseInt(scn.nextLine());
           System.out.println("Please enter the letter grade: ");
           letterGrade = scn.nextLine();
           System.out.println("Please enter full course title (Ex: Microcomputers I): ");
           courseTitle = scn.nextLine();
           System.out.println("Should the course be excluded from GPA? (Y or N): ");
           flag = scn.nextLine();
           Course courseEntry = new Course(courseYear, courseCode, courseCredits, letterGrade, courseTitle, flag); //initializes new course 
           database.addCourse(courseEntry); //calls database method to add the new course 
         }
         else //if user says no, return to neutral state
         {
            //do nothing
         }
      }
      if (input == 6)
      {
         System.out.println("Printing all records... ");
         database.print(); //calls the database print method
      }
      if (input == 7)
      {
         System.out.println("Computing GPA..");
         System.out.println(database.getGPA()); //calls the database GPA method and prints result
      }
      if (input == 8)
      {
         System.out.println("Have a nice day!"); //user goodbye message
         return; //breaks the loop
      }
     }
   }
}