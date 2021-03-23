/**************************************************************/
/* CJ Karinen                                                 */
/* Login ID: kari6535                                         */
/* CS-102, Winter 2021                                        */
/* Programming Assignment 5                                   */
/* editFrame class: A seperate input window                   */
/* to facilitate the editing of course objects.                */
/**************************************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import java.io.*;

public class editFrame extends JFrame
{
   static int input = 0; //int value to represent each button in the UI
   
   static int courseCredits; //the amount of credits a course is worth
   
   static Container contents; //the container that GUI components are added to
   
   static JButton editButton; //the array of buttons used for the UI
   
   static JPanel uiPanel; //the Jpanel which displays the buttons and textboxes
   
   Database database; //the database object 
   
   static JTextArea outputArea = new JTextArea("Welcome to the CS-102 Transcript program!"); //the area that output will be written to 
   
   JTextField courseYearTF = new HintTextField("[Term Number]"); //the textbox for term number input
   JTextField courseCodeTF = new HintTextField("[Course Code]"); //the textbox for course code input
   JTextField courseTitleTF = new HintTextField("[Course Title]"); //the textbox for course title input
   JTextField creditsTF = new HintTextField("[Course Credits]"); //the text box for course credit input
   JTextField letterGradeTF = new HintTextField("[Letter Grade]"); //the textbox for letter grade input
   JTextField flagTF = new HintTextField("[Flag]"); //the textbox for course flag input
   
   static String courseYear; //the course year in the format "YYYYTT"
   static String courseCode; //the course code in the format "EEE-###" (i.e. CS-102)
   static String letterGrade; //the letter grade for a course (A, B-, C+, etc)
   static String courseTitle; //the full course name (Ex: Microcomputers I)
   static String flag; //a string ("Y" or "N") that determines whether the course is excluded from GPA calculations
   static String userInput; //tser input string for search box
   static String cyInput; //user input for course year 

   public editFrame(Database argument)
   {
      super("Edit Menu"); //call JFrame constructor
      database = argument; //the database object used for method calls
      
      contents = getContentPane(); //returns the content pane for the window
      contents.setLayout(new GridLayout(1, 2)); //arranges layout of GUI components     
      
      
      ButtonHandler buttonHandler = new ButtonHandler(this); //creates a buttonHandler for button input
      
      editButton = new JButton("Edit Course and Commit Changes"); //creates the button
      editButton.setToolTipText("Enter the TERM, CODE, TITLE, GRADE, and FLAG. "); //adds tooltip to the button
      editButton.addActionListener(buttonHandler); //adds button handler to add functionality
      
      uiPanel = new JPanel(); //intializes the panel for buttons and textfields
      uiPanel.setLayout(new GridLayout(7, 1)); //sets the layout of uiPanel
      uiPanel.add(courseYearTF); //adds the term number text box
      uiPanel.add(courseCodeTF); //adds the course code text box
      uiPanel.add(courseTitleTF); //adds the course title text box
      uiPanel.add(creditsTF); //adds the course credit text box
      uiPanel.add(letterGradeTF); //adds the letter grade text box
      uiPanel.add(flagTF); //adds the course flag text box
      uiPanel.add(editButton); //adds the edit button
      uiPanel.setBackground(new Color(230, 230, 255)); //changes background color
      
      
          
      
      contents.add(uiPanel); //adds the uiPanel to the container

      setSize(400,600); //sets the size of the GUI window
      setVisible(true); //enables the window to be displayed on screen
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
         return showingHint? "": super.getText(); //returns text
      }
   }
   
   private class ButtonHandler implements ActionListener
   {
      JFrame parent; //the JFrame used to call dispose method
      
      public ButtonHandler(JFrame parent)
      {
         this.parent = parent; //sets parent to parameter
      }
      public void actionPerformed(ActionEvent event)
      {
         flag = "";           //default
         courseYear = "";     //values
         courseCode = "";     //for 
         courseTitle = "";    //class
         courseCredits = 0;   //variables
         letterGrade = "";
         
         flag = flagTF.getText();               //sets class 
         courseYear = courseYearTF.getText();   //variables to
         courseCode = courseCodeTF.getText();   //TextField
         courseTitle = courseTitleTF.getText(); //input
         letterGrade = letterGradeTF.getText();

         try 
         {
            courseCredits = Integer.parseInt(creditsTF.getText());  //try catch to set input credits as an integer 
         }
         catch(NumberFormatException exc) {}
         
         if (event.getSource() == editButton) //if button is pushed
         {
            if (!(courseYear.equals("")))
            {  //prevents the throwing of an ugly exception if courseYear is not entered
               Course tempCourse = new Course(courseYear, courseCode, courseCredits, letterGrade, courseTitle, flag);
               //temporary course object using given input
               database.addCourse(tempCourse, outputArea); //call to database method
               outputArea.append("\nCourse changes saved..."); //update message
               parent.dispose(); //closes the window
            }
            else
            {
               outputArea.append("\nMissing input..."); //user error message
            }
         }
      }  
   }
}
