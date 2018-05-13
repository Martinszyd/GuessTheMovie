

public class Main {

    //Give information about game to user
    public static void openingMessage(){
        System.out.println("________Guess the Movie________");
        System.out.println("Guess only one character in each turn!!");
        System.out.println("Guess only alphabetic or digit letters!!, other characters(Spaces, punctuations etc.) will be given..");
        System.out.println("10 wrong guesses mean you lost");
        System.out.println("We are starting!!!");
        threeSecDots();
    }

    //this is only waiting dots..
    public static void threeSecDots(){
        for(int i=0;i<3;i++){
            System.out.print('.');
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void main(String [] args){

        //opening message
        openingMessage();

        char wantToPlay = 'y';
        //throughout the player want to play
        while(wantToPlay == 'y'){

            //new game initilize
            Game game = new Game();

            //throughout rightGuess counter is not zero or wrongGuess counter is not ten
            while(game.rightGuess >0 && game.wrongGuess<10){

                //Print hidden word
                game.printHiddenWord();

                //Print total wrong guesses
                System.out.println("You have guessed (" + game.wrongGuess + ") wrong letters");

                //get user guess, end check it
                game.check();
            }

            //replay or finish
            wantToPlay = '_';
            while(wantToPlay != 'y' && wantToPlay != 'n'){ //Force user for select 'y' or 'n'
                System.out.println("Do you wanna play again?('y' or 'n')");
                wantToPlay = game.getChar(false); // get user choice(it is not a guess so call with false)
            }

        }
        //exit message
        System.out.println("bye");
        threeSecDots();
    }
}