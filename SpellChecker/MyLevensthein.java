

class MyLevensthein{

  int change=1;
  char [] wordChar1;
  char [] wordChar2;
  int [][] distanceMatrix;

  MyLevensthein(String word1, String word2){

    //converting both strings to capitals
    //which will allow for comparing characters
    wordChar1 = word1.toUpperCase().toCharArray();
    wordChar2 = word2.toUpperCase().toCharArray();

    distanceMatrix = new int[wordChar1.length+1][wordChar2.length+1];
    //System.out.println(distanceMatrix[0][0]);
  }

  public int distance(){

    //This is the base case. If any of the strings would be empty
    if ( wordChar1.length == 0) {

      return wordChar2.length;

    } else if (wordChar2.length == 0) {

      return wordChar1.length;

    }


    for (int i = 0; i < wordChar1.length+1; i++){

      distanceMatrix[i][0] = i;

    }

    for (int j = 0; j < wordChar2.length+1; j++)
    {

      distanceMatrix[0][j] = j;

    }

    //iterate through the distanceMatrix
    for (int i=1;i<wordChar1.length+1;i++){

      for(int j=1;j<wordChar2.length+1;j++){

        if(wordChar1[i-1]==wordChar2[j-1]){

          distanceMatrix[i][j]=distanceMatrix[i-1][j-1];

        }
        else{

          distanceMatrix[i][j]=minimum(distanceMatrix[i-1][j-1],distanceMatrix[i][j-1],distanceMatrix[i-1][j]) + change;

        }

      }
    }
    return distanceMatrix[wordChar1.length][wordChar2.length];
  }

  //return the minimum value of three integers
  public int minimum(int one, int two, int three){

    int mini=one;

    if(two<mini){

      mini=two;

    }
    if(three<mini){

      mini=three;

    }

    return mini;

  }

}
