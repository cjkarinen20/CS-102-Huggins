/**************************************************************/
/* CJ Karinen                                                 */
/* Login ID: kari6535                                         */
/* CS-102, Winter 2021                                        */
/* Programming Assignment 5                                   */
/* Prog5 class: Contains the main method and methods relating */
/* to variable assignment and GUI interaction.                */
/**************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import java.io.*;

public class Prog5 extends JFrame
{
   static int input = 0; //int value to represent each button in the UI
   static int courseCredits; //the amount of credits a course is worth
   
   static Container contents; //the container that GUI components are added to
   
   static JButton[] uiButtons; //the array of buttons used for the UI
   
   static JPanel uiPanel; //the Jpanel which displays the buttons and textboxes
   static JPanel outputPanel = new JPanel(); //the Jpanel which displays the output
   
   static JLabel greetText; //the text at the top of the output panel
   
   Database database; //the database object 
   
   static JTextArea outputArea = new JTextArea(); //the area that output will be written to 
   
   JTextField fileNameTF = new HintTextField("[FileName]"); //the textbox for filename input
   JTextField courseYearTF = new HintTextField("[Term Number]"); //the textbox for term number input
   JTextField courseCodeTF = new HintTextField("[Course Code]"); //the textbox for course code input
   JTextField courseTitleTF = new HintTextField("[Course Title]"); //the textbox for course title input
   JTextField creditsTF = new HintTextField("[Course Credits]"); //the text box for course credit input
   JTextField letterGradeTF = new HintTextField("[Letter Grade]"); //the textbox for letter grade input
   JTextField flagTF = new HintTextField("[Flag]"); //the textbox for course flag input
   
   
   static String fileName; //the name of the file to be written to
   static String courseYear; //the course year in the format "YYYYTT"
   static String courseCode; //the course code in the format "EEE-###" (i.e. CS-102)
   static String letterGrade; //the letter grade for a course (A, B-, C+, etc)
   static String courseTitle; //the full course name (Ex: Microcomputers I)
   static String flag; //a string ("Y" or "N") that determines whether the course is excluded from GPA calculations
   static String userInput; //tser input string for search box
   static String cyInput; //user input for course year 
   
   static String[] uiOptions =      //the name of the                             
   {                                //menu buttons
      "Add a new course",
      "Delete an existing course",
      "Edit an existing course",
      "Search by course code",
      "Search by course title",
      "Write database to disc",
      "Re-initialize database",
      "Print all records",
      "Compute GPAs",
      "Exit" 
   };
   static String[] uiToolTips =                          //the text   
   {                                                     //for the 
      "Enter the TERM, CODE, TITLE, GRADE, and FLAG. ",  //button
      "Enter a course CODE of the course to delete.",    //tooltips
      "Enter a course CODE of the course to edit.",
      "Enter the course CODE to find.",
      "Enter the course TITLE",
      "Enter a FILENAME to be written to.",
      "Enter a FILENAME to be used to re-initialize.",
      "Prints all of the courses in the current database.",
      "Computes the GPA of all courses in the current database.",
      "Exits the program.",
   };
      
      
   public Prog5(Database argument)
   {
      super("Database Menu"); //call JFrame constructor
      database = argument; //the database object used for method calls
      
      contents = getContentPane(); //returns the content pane for the window
      contents.setLayout(new GridLayout(1, 2)); //arranges layout of GUI components     
      
      greetText = new JLabel("Welcome to the CS-102 Transcript Program!"); //greeting text for the user
      ButtonHandler buttonHandler = new ButtonHandler(); //creates a buttonHandler for button input
     

      uiPanel = new JPanel(); //intializes the panel for buttons and textfields
      uiPanel.setLayout(new GridLayout(17, 1)); //sets the layout of uiPanel
      uiPanel.add(fileNameTF); //adds the filename text box
      uiPanel.add(courseYearTF); //adds the term number text box
      uiPanel.add(courseCodeTF); //adds the course code text box
      uiPanel.add(courseTitleTF); //adds the course title text box
      uiPanel.add(creditsTF); //adds the course credit text box
      uiPanel.add(letterGradeTF); //adds the letter grade text box
      uiPanel.add(flagTF); //adds the course flag text box
      uiPanel.setBackground(new Color(230, 230, 255)); //changes background color
      
      outputPanel.add(greetText); //adds the greeting message
      outputPanel.add(outputArea); //adds the JTextField area to the panel
      outputPanel.setLayout(new GridLayout(2,3)); //sets the layout of uiPanel
      outputPanel.setBackground(uiPanel.getBackground()); //changes background color
      
      
          
      uiButtons = new JButton[uiOptions.length];
      //intializes button array to be length of the array of labels
      for ( int i = 0; i < uiOptions.length; i ++ ) 
      {
         uiButtons[i] = new JButton(uiOptions[i]); //creates a button using the title at current index
         uiButtons[i].setToolTipText(uiToolTips[i]); //assigns the button tooltip with the string at current index
         uiButtons[i].addActionListener(buttonHandler);
         uiPanel.add(uiButtons[i]); //adds current button to the uiPanel
      }
      contents.add(uiPanel); //adds the uiPanel to the container
      contents.add(outputPanel); //adds the outputPanel to the container

      setSize(1200, 600); //sets the size of the GUI window
      setVisible(true); //enables the window to be displayed on screen
   }
      
      
   
/**************************************************************/
/* Method: processData                                        */
/* Purpose: Reads input from a file and seperates             */
/* scanned information into stored variables.                 */
/* Parameters:                                                */
/* String line: The line of String input to be processed.     */
/* Result: Scanned input is ready to be turned into a         */
/* new course object.                                         */
/**************************************************************/
   public static void main(String[] args)
   {

      Scanner console = new Scanner(System.in); //reads input from the keyboard
      Scanner file = null; //default value for file scanner
      
      Database myDatabase = new Database(); //creates a new database object
 
      try {
            file = new Scanner(new File(args[0])); //Try catch for scanner
          }
          
      catch (ArrayIndexOutOfBoundsException exc) //if the specified index was not correct
         {
           System.out.println("No arguments given to program.");
           System.exit(1);
         }
         
      catch (FileNotFoundException exc) //if the file does not exist
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
            myDatabase.addCourse(course, outputArea); //calls database method to add the course
         }
         
      Prog5 myApplication = new Prog5(myDatabase); //Creates the GUI Application shell
      myApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      //specifies that the application should end when the window is closed
      outputArea.setText(""); //clears the output
      
      
   }
   
/**************************************************************/
/* Method: processData                                        */
/* Purpose: Reads input from a file and seperates             */
/* scanned information into stored variables.                 */
/* Parameters:                                                */
/* String line: The line of String input to be processed.     */
/* Result: Scanned input is ready to be turned into a         */
/* new course object.                                         */
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
/* Method: processInput                                       */
/* Purpose: Takes integer value and takes                     */
/* corresponding action.                                      */
/* Parameters:                                                */
/* int input: The line of String input to be processed.       */
/* Result: Different methods are called depending on input    */
/**************************************************************/

   public void processInput(int input)
   {
         if (input == 0) //add course
         {
            if (!(courseYear.equals("")))
            {  //prevents the throwing of an ugly exception if courseYear is not entered
               Course tempCourse = new Course(courseYear, courseCode, courseCredits, letterGrade, courseTitle, flag);
               //temporary course object using given input
               database.addCourse(tempCourse, outputArea); //call to database method
            }
            else
            {
               outputArea.setText(""); //clears output area
               outputArea.append("\nMissing input..."); //user error message
            }
         }
         else if (input == 1) //delete course
         {
            if (!(courseYear.equals("")))
            {  //prevents the throwing of an ugly exception if courseYear is not entered
               int termNum = Integer.parseInt(courseYear); //input courseYear parsed to int
               database.removeCourse(termNum, courseCode, outputArea); //call to database method
            }
            else
            {
               outputArea.setText(""); //clears output area
               outputArea.append("\nMissing input..."); //user error message
            }
         }
         else if (input == 2) //edit course
         {
            if (database.searchByNum(courseCode, outputArea) == true) //if the course exists
            {
               editFrame editWindow = new editFrame(database); //Creates the GUI Application shell
               editWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
               //specifies that the application should end when the window is closed
            }
         }
         else if (input == 3) //search for course code
         {
            database.searchByNum(courseCode, outputArea);   //call to database method
         }
         else if (input == 4) //search for course title
         {
            database.searchByTitle(courseTitle, outputArea); //call to database method
         }
         else if (input == 5) //write database to disc
         {
            writeToDisc(fileName); //calls write to disc method
         }
         else if (input == 6) //re-initialize database
         {
            reinitializeDB(fileName); //calls reinitialize method
         }
         else if (input == 7) //print all records
         {
            database.print(outputArea); //call to database method
         }
         else if (input == 8) //compute GPA
         {
            outputArea.setText(""); //clears the output panel
            String gpa = Double.toString(database.getGPA()); //the gpa to be output
            outputArea.setText(""); //clears output area
            outputArea.append("\nCurrent GPA: " + gpa); //outputs the GPA to GUI
         }
         else if (input == 9) //exit the program
         {
            System.exit(0); //exits the program
         }
   }
   
/**************************************************************/
/* Method: writeToDisc                                        */
/* Purpose: Writes all of the courses to a file               */
/* Parameters:                                                */
/* String: fileName: The file to be written to                */
/* Result: Writes all of the courses to a single file         */
/**************************************************************/

   public void writeToDisc(String fileName)
   {
      File newInput = new File(fileName); //the new input file
      Scanner newFile = null; //default value
      try 
      {
         newFile = new Scanner(newInput); //scanner using new file
         outputArea.setText(""); //clears output area
         outputArea.append("\nWriting to disc..."); //error message
         database.writeToDisc(fileName, outputArea);
         //calls the database write method
      }
      catch (FileNotFoundException exc) //if file does not exist
      {
         outputArea.setText(""); //clears output area
         outputArea.append("\nFile not found..."); //error message
      }
   }
   
/**************************************************************/
/* Method: reinitializeDB                                     */
/* Purpose: Specifies new input file for the database         */
/* Parameters:                                                */
/* String: fileName: The file to be written to                */
/* Result: Reinitializes database using new input file        */
/**************************************************************/  

   public void reinitializeDB(String fileName)
   {  
      File newInput = new File(fileName); //the new input file
      Scanner newFile = null; //default value
      try 
      {
         newFile = new Scanner(newInput); //scanner using new file
         database = new Database(); //re-intialize database object
         while (newFile.hasNext()) //loops as long as the file contains more input
         {  
            String dataLine = newFile.nextLine(); //creates a string variable out of the next line in the file
            processData(dataLine); //calls processData (assigns variables from input file) using the dataLine string
            Course course = new Course(courseYear, courseCode, courseCredits, letterGrade, courseTitle, flag); //creates a new course object
            int termNum = Integer.parseInt(courseYear); //the term to be added
            database.addTerm(termNum); //calls database method to add the term
            database.addCourse(course, outputArea); //calls database method to add the course
         }
         outputArea.setText("Database reinitialized...");
      }
      catch (FileNotFoundException exc) //if file does not exist
      {
        outputArea.setText(""); //clears output area
        outputArea.append("\nFile not found, database is unchanged..."); //error message
      }
   }
   
/**************************************************************/
/* Class: HintTextField                                       */
/* Purpose: Handles TextField hint text that dissapears with  */
/* after the user interacts with the TextField.               */
/**************************************************************/

   class HintTextField extends JTextField implements FocusListener 
   {

      private String hint; //the hint to be displayed
      private boolean showingHint; //if the hint should be displayed

      public HintTextField(String hint) 
      {
         super(hint); //initializes textfield using hint
         this.hint = hint; //intializes the hint for the textfield
         this.showingHint = true; //shows the hint
         super.addFocusListener(this); //adds a focus listener to the textfield
      }
      
/**************************************************************/
/* Method: focusGained                                        */
/* Purpose: Determines whether or not to show hint text.      */
/* Result: Sets showingHint accordingly.                      */
/**************************************************************/

      public void focusGained(FocusEvent e) 
      {
         if(this.getText().isEmpty()) 
         {
            super.setText(""); //set text to empty
            showingHint = false; //hint is not showing
         }
      }
      
/**************************************************************/
/* Method: focusLost                                          */
/* Purpose: Determines whether or not to show hint text.      */
/* Result: Sets showingHint accordingly.                      */
/**************************************************************/

      public void focusLost(FocusEvent e) 
      {
         if(this.getText().isEmpty()) 
         {
            super.setText(hint); //sets the text to hint
            showingHint = true; //hint is now showing
         }
      }
      
/**************************************************************/
/* Method: getText                                            */
/* Purpose: Determines whether or not to show hint text.      */
/* Result: Sets showingHint accordingly.                      */
/**************************************************************/  

      public String getText() 
      {
         return showingHint? "": super.getText(); //return the text
      }
   }
   
/**************************************************************/
/* Class: ButtonHandler                                       */
/* Purpose: Handles button input                              */
/**************************************************************/

   private class ButtonHandler implements ActionListener
   {
   
/**************************************************************/
/* Class: ActionPerformed                                     */
/* Purpose: Dictates what to do when the button is pushed     */
/**************************************************************/
      
      public void actionPerformed(ActionEvent event)
      {
         fileName = "";       //default
         courseYear = "";     //values
         courseCode = "";     //for 
         courseTitle = "";    //class
         courseCredits = 0;   //variables
         letterGrade = "";
         flag = "";
         
         fileName = fileNameTF.getText();       //sets class 
         courseYear = courseYearTF.getText();   //variables to
         courseCode = courseCodeTF.getText();   //TextField
         courseTitle = courseTitleTF.getText(); //input
         letterGrade = letterGradeTF.getText();
         flag = flagTF.getText();
         try 
         {
            courseCredits = Integer.parseInt(creditsTF.getText());  //try catch to set input credits as an integer 
         }
         catch(NumberFormatException exc) {}
         
         for (int bNum = 0; bNum < uiButtons.length; bNum++) 
         {
           //if specific button was pressed
           if (event.getSource() == uiButtons[bNum])
           {
              input = bNum; //input becomes button number
           }
         }
         processInput(input); //call to input logic method
      }  
   }
}