import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class SpellChecker{

  char [] wordChar1;
  String fileName;
  String word;
  String newStr = "";
  String currentWord;
  String replaceWord;
  ArrayList<String> suggestions;
  File dictionary = new File("dictionary.txt");
  File localDictionary = new File("localDictionary.txt");
  File fileToLoad;
  Boolean foundWord = false;
  UserInterface display = new UserInterface();

  SpellChecker(String fileName) throws IOException {
    this.fileName=fileName;
    loadUserFile();
    readFile();
    printToFile();
  }

  public void loadUserFile() throws IOException {
    fileToLoad = new File(fileName);
  }

  public void readFile() throws IOException {

    Scanner read = new Scanner(fileToLoad);
    String temp = "";
    //true from the beginning to make first word of file capitalized
    Boolean capitalize=true;

    while(read.hasNext()){

      word = read.next();
      temp = word.toLowerCase();

      //if a letter ends with a punctation the next word will be capitalized
      if(temp.charAt(temp.length()-1)=='.'){

        //trims off any special characters
        temp=word.replaceAll("\\p{Punct}+", "");

        //inserts back the punctiations
        word = findInDictionary(temp) +".";

        //capitalize first letter of word if true
        if(capitalize){
          word = temp.substring(0,1).toUpperCase() + temp.substring(1);
          capitalize=false;
        }
        capitalize=true;
      }
      else{

        temp = temp.replaceAll("\\p{Punct}+", "");

        word = findInDictionary(temp);

        //capitalize first letter of word if true
        if(capitalize){

          word = temp.substring(0,1).toUpperCase() + temp.substring(1);

          capitalize=false;

        }
      }
      //creates a new string with edited and/or none edited words
      newStr= newStr + word + " ";

    }
    System.out.println("New text: "+ newStr);
  }

  //the search function using levenssthein
  public String findInDictionary(String currentWord) throws IOException {
    foundWord=false;
    suggestions = new ArrayList<String>();
    //variables decides the Levensthein distance from 1-3.
    //loop iterats until ArrayList size is 10
    int range=1;

    int adds=0;
    while(range<=3 && suggestions.size()<10){

      Scanner read = new Scanner(dictionary);

      while(read.hasNext() && !foundWord){

        String dictionaryWord = read.next();

        if(currentWord.equals(dictionaryWord)){

          foundWord=true;

          System.out.println("Word found " + word);

    return word;
        }
        else{
          //Using levenssthein class created in previous assignment.
          MyLevensthein suggest = new MyLevensthein(word,dictionaryWord);

          if(suggest.distance()<=range && suggestions.size()<10){

            if(!suggestions.contains(dictionaryWord)){

              suggestions.add(dictionaryWord);

              adds++;

            }

          }

        }

      }

      range++;

    }


    if(!foundWord){
      String [] suggest = new String[suggestions.size()];
      int index=0;
      Boolean replace=false;


      System.out.println("Word not found: " + word);
      System.out.println("Suggestions: " + suggestions);
      System.out.println("Replace with a word [1-10]: ");
      System.out.println("Enter 0 to keep word as it is.");
      Scanner userInput = new Scanner(System.in);
      int insertWord=userInput.nextInt();
      if(insertWord>0){
        System.out.println(suggestions.get(insertWord-1));
        System.out.println("Replaced: " + word + " with: " + suggestions.get(insertWord-1));
        currentWord=suggestions.get(insertWord-1);
      }
    }
    System.out.println(currentWord);
    return currentWord;

  }

  public void printToFile()throws IOException {

    FileWriter fw = new FileWriter(fileName);
    fw.write(newStr);
    fw.close();

  }
}
