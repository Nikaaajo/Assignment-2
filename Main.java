/* CURRENT PROGRESS: Interactive System is ran via a While Loop in method Main. Have decided not to have a 3rd class. All methods will be listed under main method. The most basic functions of the program are complete, but only work if operated perfectly. Still need to add the ability to survive exceptions
FUNCTIONS COMPLETED: #5, #1, #3, #4, #2, #6ISH, #7
NEXT STEP: Work on extra cred
ASK TEACHER: AI suggests having all functions within the if statements, I want to keep them out for clarity. 
Do users need to be able to input BOTH a int and string to request a function?
When it comes to making the program survive a null pointer exception, is it best practice to check if null or to do a catch exception thing? 
Can we adjust the ppt/book pages we print out for tenta? tx. make them smaller and put many on one page
Ask about noSuchElementException, which started occuring this morning after adding the final else statement- AI suggests it has something to do with closing Scanner, but the scanner it points to is never closed. 
EXTRA CRED: have system report current status of manifest before user inputs during function 3. Have function 2(remove passenger) double check y or no if user wants to remove. Create basic passenger populations for testing (full plane, half plane, etc). Revise texts. Try /n & /t for F6. 
*/


import java.util.Scanner;

class Main {

  //--------------------------------------------------------------
  
  //The main method which runs the interactive system.
  public static void main(String[] args) {
   
    //Generate empty array of 12 Passenger objects
  Passenger[] currentFlight = new Passenger[12];
    
    //this While loop keeps the system asking user for more input until User enters 7, aka the quit function
  boolean programActive = true;
  while (programActive == true){
    
    int fC= userRequest();
    
    //Figure out which function has been called by the user
      if (fC == 1) {
        System.out.println(" ");
        System.out.println("Function #1 Insert Passenger has been requested. Please enter the passenger's name: ");
        Scanner scanner1 = new Scanner(System.in);
        String passName = scanner1.nextLine();
        System.out.println("Please enter "+ passName + "'s age: ");
        int passAge = scanner1.nextInt();
        insertPassenger(currentFlight, passName, passAge);
        System.out.println(" ");
        System.out.println("Thank you, this is all for now");
        System.out.println(" ");
        
        } 
        
        else if (fC == 2) {
          System.out.println(" ");
          System.out.println("Function #2 Remove Passenger has been requested. Please enter the passenger's seat number: ");
          Scanner scanner2 = new Scanner(System.in);
          int passSeat = scanner2.nextInt();
          removePassenger(currentFlight, passSeat);
          System.out.println(" ");
          System.out.println("Thank you, this is all for now");
          System.out.println(" ");
         
        } 
          
        else if (fC == 3) {
          System.out.println(" ");
          System.out.println("Function #3 Switch Passenger Seats has been requested. Please enter the passenger seat number you would like to switch: ");
          Scanner scanner3 = new Scanner(System.in);
          int seat1 = scanner3.nextInt();
          System.out.println("Please enter the seat number you would like seat #" + seat1+ " to switch to:");
          int seat2 = scanner3.nextInt();
          switchSeats(currentFlight, seat1, seat2);
          System.out.println(" ");
          System.out.println("Thank you, this is all for now");
          System.out.println(" ");
          
        } 
          
        else if (fC == 4) {
          System.out.println(" ");
        System.out.println("Function #4 Rename Passenger has been requested. Please enter the passenger's seat number: ");
          Scanner scanner4 = new Scanner(System.in);
          int seatNum = Integer.parseInt(scanner4.nextLine());

          //Fixes OBS by checking that there is a passenger in requested seat
          if (currentFlight[seatNum-1] != null){
            System.out.println(currentFlight[seatNum-1].getName() + " is currently sitting at seat #" + seatNum + ". Please enter the new name you'd like them to have: ");
            String newPassName = scanner4.nextLine();
            renamePassenger(currentFlight, seatNum, newPassName);
          } else {
            System.out.println("The seat you have requested is unoccupied and therefore cannot be renamed. Please request the function again with a seat that is occupied.");
          }
          System.out.println(" ");
        System.out.println("Thank you, this is all for now");
          System.out.println(" ");
          
        }
          
        else if (fC == 5) {
          System.out.println(" ");
        System.out.println("Function #5 Count Passengers has been requested.");
          int f5 = countPassengers(currentFlight);
          System.out.println("There are currently " + f5 + " passengers on board.");
          System.out.println(" ");
        System.out.println("Thank you, this is all for now");
          System.out.println(" ");
        } 
          
        else if (fC == 6) {
          System.out.println(" ");
        System.out.println("Function #6 Print Passenger Manifest has been requested.");
        printPassManifest(currentFlight);
          System.out.println(" ");
        System.out.println("Thank you, this is all for now");
          System.out.println(" ");
        } 
        
        else if (fC == 7) {
        System.out.println(" ");
        System.out.println("Function #7 Exit Program has been requested.");
          System.out.println(" ");
        System.out.println("Thank you, this is all for now");
          System.out.println(" ");
        programActive= false;
        }
    
    }
    //The While loop has been exited by the user. 
    System.out.println(" ");
    System.out.println("The program will now end");
  }

  //--------------------------------------------------------------
  
  //Method which figures out which function the user wants to perform by asking for an int
  //
  public static int userRequest(){
    Scanner myScanner = new Scanner(System.in);
    System.out.println(" ");
    System.out.println("Welcome to SkyBox Industries's Passenger Manifest System! Here is the list of the system's possible functions: ");
    //Is there a better way to visually print a list?
    System.out.println(" ");
    System.out.println("#1 Insert Passenger");
    System.out.println("#2 Remove Passenger");
    System.out.println("#3 Switch Passenger Seats");
    System.out.println("#4 Rename Passenger");
    System.out.println("#5 Count Passengers");
    System.out.println("#6 Print Passenger Manifest");
    System.out.println("#7 Exit Program");
    System.out.println(" ");
    System.out.println("Please enter a number to perform your desired function: ");
    
    int functionCall = myScanner.nextInt();
    return functionCall;
  } 

  //--------------------------------------------------------------
  
  /*Function #1: Insert Passenger
  Currently WORKS
  OBS [FIXED]! Only works if there is an empty element in currentFlight. 
  Creates and inserts a new passenger based on user input above at the first available seat.*/
  public static void insertPassenger(Passenger[] currentFlight, String passName, int passAge){
    int passSeat= 13;
    for (int i=0; i<currentFlight.length; i++){
      //Passenger will be inserted at the first available seat on currentFlight
      if (currentFlight[i] == null){
        currentFlight[i] = new Passenger(passName, passAge);
        passSeat = i;
        //Check if passenger is created correctly and confirms which seat in the array they are at
        System.out.println("The new passenger " + currentFlight[passSeat].getName() + " is now seated at seat #" + (passSeat+1));
        break;
        }
    }
    
    //if we completed the entire loop and passSeat never updated, that means that there is no empty seat on currentFlight. Fixes OBS 
      if (passSeat == 13) {
      System.out.println("Our apologies, the plane is full and there are no available seats on this flight. Please try again later.");
      }
    
    }  

  //--------------------------------------------------------------
  
  /* Function #2: Remove Passgener
  Currently WORKS
  I looked into how you could remove a value from an array, and the solutions suggesed either making a smaller array or copying the values over to a new array, etc. However, I don't want to mess with the length or other values in the array. I figured instead that since our array starts off with a set of null values, "deleting" a passenger should be just as easy as declaring it's index as containing now null. 
  OBS [FIXED]! Only works if there is a passenger in the given seat number. 
  */
  public static void removePassenger(Passenger[] currentFlight, int passSeat){
    //Fixes OBS by making sure passenger exists before "deleting"
    if (currentFlight[passSeat-1] != null){
      System.out.println(currentFlight[passSeat-1].getName()+ "is currently sitting in seat #" + passSeat + ". They will now be removed from the flight.");
      currentFlight[passSeat-1] = null;
      //Check that null assignment worked
      if (currentFlight[passSeat-1] == null){
        System.out.println("The passenger at seat #" + (passSeat) + " has been removed.");
      }
    } else {
      System.out.println("There is no passenger at seat #" + passSeat +". Please choose another function.");
    }
    
   
  }

  //--------------------------------------------------------------
  
  /*Function #3: Switch Passenger Seats
  Currently WORKS
  OBS [FIXED]! Only works if the requested seats are both occupied by a Passenger. Will throw error if seat returns null. */ 
  public static void switchSeats(Passenger[] currentFlight, int seat1, int seat2){
    //Temporary variable is initialized
    Passenger tempPassenger1 = new Passenger(" ", 0);
    Passenger tempPassenger2 = new Passenger(" ", 0);

    //Fixes OBS by accounting for seats not already being occupied. 
    if (currentFlight[seat1-1] != null && currentFlight[seat2-1] != null) {
      //IF BOTH ARE CURRENTLY OCCUPIED BY PASSENGERS----------------------
      
      //TP1 takes on properties of currentFlight[s1]. TP2 takes on properties of currentFlight[s2]. We do s1/s2 - 1 to account for element numbering
      tempPassenger1 = currentFlight[seat1-1]; 
      tempPassenger2 = currentFlight[seat2-1];

      //Check what values were before swap
      System.out.println("Seat #" + seat1 + " was occupied by " + tempPassenger1.getName() + " and seat #" + seat2 + " was occupied by "+ tempPassenger2.getName());

      //Changing the value of the Passenger at s1/s2 to the values of their opposite tempPassengers.
      currentFlight[seat1-1] = tempPassenger2;
      currentFlight[seat2-1] = tempPassenger1;

      System.out.println("The seats have successfully been switched. " + currentFlight[seat1-1].getName()+ " now sits at seat #" + seat1 + " and" + currentFlight[seat2-1].getName() + " now sits at seat #" + seat2);
      
    } else if (currentFlight[seat1-1] != null && currentFlight[seat2-1] == null){
      //IF SEAT 1 IS OCCUPIED BUT SEAT 2 ISN'T-------------------
      
      tempPassenger1 = currentFlight[seat1-1]; 
      System.out.println("Seat #" + seat1 + " was occupied by " + tempPassenger1.getName() + ". However, seat #" + seat2 + " was unoccupied.");
      //Reassign s2 to pass1, clear pass1's previous seat
      currentFlight[seat2-1] = tempPassenger1;
      currentFlight[seat1-1] = null;
      System.out.println("The seats have successfully been switched. " + currentFlight[seat2-1].getName()+ " now sits at seat #" + seat2 + " and seat #" + seat1+ " is now empty.");
      
    } else if (currentFlight[seat1-1] == null && currentFlight[seat2-1] != null) {
      //USER REQUESTS TO SWITCH AN UNOCCUPIED SEAT.--------------------
      System.out.println("You have requested to switch from seat #" + seat1 + " to seat #" + seat2 + ". However, seat #" + seat1 + " is unoccupied and therefore cannot be switched. If you wish to switch the passenger in seat #" + seat2 + " please request the function again and enter seat #" + seat2 +" first.");
      
    } else {
      //NEITHER REQUESTED SEATS WERE OCCCUPIED (s1 && s2 == null))
      System.out.println("Neither of the requested seats were occupied and therefore no switch can be made. Please request the function again with two currently occupied seats");
    }
    

    }

  //--------------------------------------------------------------
  
  /*Function #4: Rename Passenger
  Takes user-inputed info to replace the name of a passenger at a given seat number
  OBS! Only works if the requested seat is occupied by a passenger. If the seat is null, error will throw
  */
  public static void renamePassenger(Passenger[] currentFlight, int seatNum, String newPassName){
      currentFlight[seatNum-1].setName(newPassName);
      System.out.println("The passenger at seat #" + seatNum + " has been renamed to " + newPassName);
    
    }
    
  
  

  //--------------------------------------------------------------
  
  /*Function #5: Count Passengers
  Currently WORKS
  Counts # of passengers by iterating through currentFlight and marking any seats that are occupied.*/
  public static int countPassengers(Passenger[] currentFlight) {
    int numOfPassengers = 0;

    for (int i = 0; i < currentFlight.length ; i++ ) {

      if (currentFlight[i] != null) {
        numOfPassengers++;
      }

    }
    
    return numOfPassengers;
    
  }

  //--------------------------------------------------------------

  /*Function #6: Print Passenger Manifest
  Currently PRETTY MUCH WORKS
  NEED TO: figure out how to make sure spacing matches between name and age even with different name lengths. Could use if statements but that would be messy. 
  Looked online to see if there is a function to automate things in the requested formatting other than a loop. Looks like Arrays.asList() could be an option, but doesn't seem to neatly fit our requirements. I will go forward with printing lines instead. 
  */
  public static void printPassManifest(Passenger[] currentFlight){
   System.out.println("##### PASSENGER MANIFEST ######");
  System.out.println("SkyBox Ltd.");
  System.out.println("Seat" + "\t" + "Name"+ "\t" + "Age");

  for (int i=0; i<currentFlight.length; i++){
    
    if (currentFlight[i] != null){
      System.out.println((i+1) +  "\t\t"   + currentFlight[i].getName() + "\t\t"+ currentFlight[i].getAge());
    } else {
      System.out.println(i+1);
      }
    
  }
  
  System.out.println("##### #### #### #### #### #####");
    
  }
  
  
}
  