import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    private String movieName;       //randomly selected from text file
    private int totalLetter;        //length of movieName
    private char[] hiddenWord;      // char array for showing hidden word
    int rightGuess;                 //counter for right guesses, it will be count from moviname's total number of digits and alphabetics,
    // it will be decrease one after each right guess
    int wrongGuess;                 //counter for wrong guesses
    private String guessStore;      //Store user guesses in lowercase status


    //Constructor
    Game(){

        movieName = getRandomMovie();           // get random movie
        totalLetter = movieName.length();       //find it length
        hiddenWord = new char[totalLetter];     //set char array size belong moviname length
        guessStore = " ";                       // initilize quessStore
        wrongGuess = 0;
        rightGuess = 0;

        //Count Digits and Alphabetics in moviname and find user need to be known guesses (rightGuess)
        //initilize hiddenWord char array with '_', don't hide characters except digit and alphebetic letters
        for(int i = 0; i<totalLetter; i++){
            if(Character.isAlphabetic(movieName.charAt(i))  || Character.isDigit(movieName.charAt(i))) {
                rightGuess++;
                hiddenWord[i] = '_';
            }else{
                hiddenWord[i] = movieName.charAt(i);
            }
        }
    }

    //Methods..
    //Get user guess, check this single guess, and check for game end..
    void check(){
        char singleGuess = this.getChar(true); //Get user guess
        if(singleGuess != '_'){                      //if it is a regular guess
            int searchIndex = 0;                     //search index for searching guess in moviname, start with 0 index
            boolean wrong = false;                   //flag for wronGuess counter, after check process it must be change for rigth guesses, if it will not change so it this is a wrongGuess
            boolean changeCase = false;              //flag for changing case(upper/lower) of guess, guess will be search in moviname with original case, and will be search with changing case.
            do {                                                                //do it while searchIndex is not equal to -1
                searchIndex = this.movieName.indexOf(singleGuess, searchIndex); //search single guess in moviNAme and assign result-index to searchIndex
                if (searchIndex == -1) {
                    if((!changeCase) && Character.isAlphabetic(singleGuess)){       //if guess is alphabetic and untried with changing case
                        changeCase = true;                                          //changeCase flag is true for prevent repeated changingCase
                        searchIndex = 0;                                            //update searchIndex 0 for start from first letter of movieName
                        if(Character.isLowerCase(singleGuess)){                     //change Case uppercase->lowercase or lowercase ->uppercase
                            singleGuess = Character.toUpperCase(singleGuess);
                        }else{
                            singleGuess = Character.toLowerCase(singleGuess);
                        }
                    }
                } else {                                                              //searching guess is successful
                    this.hiddenWord[searchIndex] = this.movieName.charAt(searchIndex);//update hiddenWord char array
                    wrong = true;                                                      //this is not a wrong Guess(don't increase wrongGuess counter each time for upper or lower cases)
                    this.rightGuess--;                                                  //this is a right guess, decrease rightGuess counter for each successful search
                    searchIndex++;                                                      //update searchIndex for new next starting index point
                }
            } while (searchIndex != -1);

            if(!wrong){              //if wrong flag is not change during search process, it is a wrong Guess
                this.wrongGuess++;   //increase wrongGuess counter
            }

            //check for win / lost
            if(this.rightGuess == 0){
                System.out.println("CONGRULATIONS!!!");
                System.out.println(this.movieName);

            }else if(this.wrongGuess == 10){
                System.out.println("you lost!!!");
                System.out.println(this.movieName);
            }
        }
    }

    //Print hiddenWord method
    void printHiddenWord(){

        for(int i = 0; i<this.totalLetter; i++){
            System.out.print(this.hiddenWord[i]);
        }
        System.out.println();
    }

    /* ------------Thank you Cristian "cristian.nelutescu"
    Method to Add element at the end of a String Array
    If you have a String array with a certain length and want to add a new element, this is not possible because the size of an array is fixed when created.
    Only possible way is to re-create a new String Array with larger capacity to be able to put the new value at the end.
    @param stringArray to add a new element
    @param newValue the new string element to be added to array
    @return string array with element added
    */
    private String[] addElement(String[] stringArray, String newValue)
    {
        String[] tempArray = new String[stringArray.length + 1];                   // new string array with one more element
        System.arraycopy(stringArray, 0, tempArray, 0, stringArray.length); // copy all array elements
        tempArray[stringArray.length] = newValue;                                  // add the new element at the end
        return tempArray;
    }

    //selecting random line from text and return movieName
    private String getRandomMovie(){

        int totalLine = 0;                                              // total lines in text file
        String[] lineArray = {" "};                                     //String array for storing lines
        try {
            File file = new File("movies.txt");                 //open file
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {                                 //while text file has new line
                lineArray = addElement(lineArray, scanner.nextLine());  // add new line to stringArray *Special thanks to Cristian from forum for addElement function*
                // before this function, open text file two times, one for counting total line and one for stoping rondom number and get moviename
                totalLine++;
            }
            scanner.close();

        } catch (FileNotFoundException e){                                          //exception for if file not found
            e.getMessage();
        }

        int randomNumber = (int) (Math.random() * totalLine) ;      //create random number between [0 ant totalLine)
        randomNumber++;                                             //lineArray[0] = " ", so we added one to our index
        return lineArray[randomNumber].trim();                      //Cutting line string from starting and ending spaces
    }

    //get user input and return char, call with true for guesses, otherwise call with false
    char getChar(boolean guess){

        char c  = '_';                              //returning character, '_' for unsuccessfull inputs

        Scanner sc = new Scanner(System.in);
        String input = sc.next();                   // get user input in string
        c = input.charAt(0);                        //our character is first one

        if(input.length()>1){                                                //if user enter more than one character...
            System.out.println("!! Enter only one character..");            //warn user
            c = '_';                                                        //'_' for unsuccessfull inputs
        }else if(!Character.isAlphabetic(c) && !Character.isDigit(c)){      //if user enter neither Alphabetic nor Digit character
            System.out.println("!! Only Alphabetic or digit characters");   //warn user
            c = '_';                                                        //'_' for unsuccessfull inputs
        }

        //check for repeated guesses, if method call for guess(true)
        if(guess && c!= '_'){
            if(this.guessStore.indexOf(Character.toLowerCase(c)) != -1 ){    // if guessed character is in guessStore
                System.out.println("You already tried this!");              //warn user for repeated guess
                c= '_';                                                     //'_' for unsuccessfull inputs
            }else{
                this.guessStore+= Character.toLowerCase(c);                 //successful input to guessStore
            }
        }
        return c;
    }
}