import java.io.*;
import java.util.Scanner;

class UserInterface{
  boolean showMenu=true;
  Scanner userInput;
  String fileName;

  public void UserInterface () throws IOException {
    
    System.out.println("----SPELLCHECKER----");
    System.out.println("_____________________");
    System.out.println();
    
    while(showMenu){
      
      System.out.println("1. Load text file");
      System.out.println("2. Run spell check on file");
      System.out.println("3. Quit SPELLCHECKER");

      userInput = new Scanner(System.in);
      int choice = userInput.nextInt();
      
      switch(choice){

        case 1:
        catchFileName();
        break;
        
        case 2:
        SpellChecker correctFile = new SpellChecker(fileName);
        break;
        
        case 3:
        //showMenu=false;
        System.exit(0);
        break;


      }
    }

  }
  
  public void catchFileName(){
    System.out.println("Enter text file name: " );
    userInput = new Scanner(System.in);
    fileName = userInput.next().trim();
  }
  //public void checkFileExist() throws IOException {
    //File fileToLoad = new File

  //}

}
