import java.util.Scanner;

public class WordGuesser {

    public static void main(String[] args) {

        // initialize variables and constants


        int MAXGUESSES = 6;
        int numGuesses = 0;

        // initialize input and setting the secret word
        Scanner input = new Scanner(System.in);
        String secretWord = "";
        boolean inputtingSecretWord = true;
        while (inputtingSecretWord) {
            System.out.println("Enter a 6-letter secret word (or type 'HELP' for instructions):");
            secretWord = input.nextLine().toUpperCase();

            if (secretWord.equals("HELP")) {
                System.out.println("Welcome to WORD GUESSER!\n" +
                        "- Set a 6-letter secret word.\n" +
                        "- Try to guess the word within 6 attempts.\n" +
                        "- Type 'EXIT' while guessing at any time to quit the game.\n");
                continue;
            }

            //check if the secret word only contains alphabetical characters
            if (!checkAllAlpha(secretWord)) {
                System.out.println("Invalid input. The word must contain only letters.");
                continue;
            }

            //check if the secret word is of appropriate length
            if (!checkWordLength(secretWord, 6)){
                System.out.println("Invalid word length. The word must be exactly 6 letters long.");
                continue;
            }

            break;
        }

        while (numGuesses < MAXGUESSES)   {
            System.out.println("Type a six-letter word");
            String guess = input.nextLine().toUpperCase();


            // check keyword
            if (guess.equals("EXIT")){
                System.out.println("You ended the game :(");
                input.close();
                break;
            }

            //check input

            //check if the guess only contains alphabetical characters
            if (!checkAllAlpha(guess)) {
                System.out.println("Invalid input. Only alphabetical letters allowed");
                continue;
            }

            //check if the guess is of appropriate length
            if (!checkWordLength(guess, secretWord.length())) {
                System.out.println("Invalid number of characters: " + guess + " Word must be 6 letters");
                continue;
            }

            int correctPos = 0;
            int correctLetters = 0;

            // process input

            //guessed list keeps track of the correct letters in their proper position
            boolean[] guessed = new boolean[secretWord.length()];

            //First Loop: Check input word for position
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess.charAt(i)) {
                    correctPos++;
                    guessed[i] = true;
                    System.out.println("Correct letter: " + guess.charAt(i) + " (and position: " + (i + 1) + ")");
                }
            }

            //Second Loop: Check for correct letters in wrong positions
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.contains(String.valueOf(guess.charAt(i))) && secretWord.charAt(i) != guess.charAt(i)) {
                    for (int j = 0; j < secretWord.length(); j++) {
                        if (!guessed[j] && secretWord.charAt(j) == guess.charAt(i)) {
                            correctLetters++;
                            guessed[j] = true;
                            System.out.println("Correct letter: " + guess.charAt(i) + " Wrong position");
                            break;
                        }
                    }

                }

            }

            //Third loop: Check for letters not in the word
            for (int i = 0; i < secretWord.length(); i++) {
                if (!secretWord.contains(String.valueOf(guess.charAt(i)))) {
                    System.out.println("Letter not in word: " + guess.charAt(i));

                }
            }

            // output results

            numGuesses++;

            int guessesLeft = MAXGUESSES - numGuesses;
            System.out.println("\nGuesses left: " + guessesLeft);

            //Check win condition
            if (guess.equals(secretWord)) {
                System.out.println("---------------------\nYou guessed the word!\n---------------------");
                input.close();
                break;
            }

            //If max guesses is reached
            if (numGuesses == MAXGUESSES) {
                System.out.println("No more guesses left :(");
                input.close();
                break;

            }
        }
    }

    /**
     *
     * Check whether all input is an alphabetic character
     * Return false if this is not the case
     * @param toCheck The string that we are testing to see if it is all alphabetic
     * @return false If toCheck contains a non-alphabetic character; true if all characters are alphabetic
     */
    public static boolean checkAllAlpha(String toCheck) {
        return toCheck.matches("[A-Z]+");
    }

    public static boolean checkWordLength(String toCheck, int wordLength)
    {
        return toCheck.length() == wordLength;
    }

    // do I need this? Take it out if I don't, I guess
    public static boolean checkIsWord(String toCheck)
    {
        return false;
    }
}