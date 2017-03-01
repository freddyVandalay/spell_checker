import java.io.*;
class sb{
  public static void main(String... args) {

    String temp = "this.";



    if(!Character.isLetter(temp.charAt(temp.length()-1))){
      temp=temp.replaceAll("\\p{Punct}+", "");

      temp = temp + "bajs";

      System.out.println(temp);

    }
  }
}
