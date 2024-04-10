// Skillmea JAVA academy - webinar #3 - 2.4.2024
// Programming the game of hangman (guessing words)

import java.util.Random;
import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        final Random random = new Random();

        // final keyword - program does not allow to change content of the parameter
        final String[] words = {"hAngmAn", "flOweR", "prOgraM", "tomCAt", "pictUrE", "ApPles", "bANanas", "CuRrency", "coMputeR"};

        // call method to randomly select word for the game
        String wordToGuess = selectWord(words, random);

        // call method to hide selected word letters
        String hiddenWord = generateHiddenWord(wordToGuess);

        // definition of the limit of incorrect guess cases
        // constants are usually formatted with _ separating words
        final int max_incorrect_cases = 6;

        // parameter used to count incorrect cases
        int incorrectCases = 0;

        // used to scan user inputs (characters)
        Scanner scanner = new Scanner(System.in);


        // starting the game
        System.out.println("                        WELCOME TO THE GAME");
        System.out.println("                           H A N G M A N");
        System.out.println("=================================================================");

        System.out.println("Program will show you hidden word in format where sign '_' represent a hidden letter.");
        System.out.println("Your task is to guess the word letter by letter.");
        System.out.println("You need to enter alphabetic single letter when asked by the program. Lets start.");
        System.out.println();
        System.out.println(" > > > Guess the word > > >  " + hiddenWord);


        while (incorrectCases < max_incorrect_cases && hiddenWord.contains("_")) {

            System.out.print("Enter a letter = ");
            char guess = scanLetter(scanner);

            // condition to check if the word include the entered char
            // condition check converting all letters to lower case
            if (wordToGuess.toLowerCase().contains(String.valueOf(guess).toLowerCase())) {
                hiddenWord = revealLetters(wordToGuess, hiddenWord, guess);
                System.out.println(" > > > Correct guess = " + hiddenWord + " < < <");
            } else {
                incorrectCases++;
                System.out.println("! Incorrect guess. Your remaining guesses are = " + (max_incorrect_cases - incorrectCases) + ".");
                System.out.println("Hidden word: " + hiddenWord);
            }
        }

        if (!hiddenWord.contains("_")) {
            System.out.println();
            System.out.println("< < < Congratulations, you have guessed the hidden word. > > >");
        } else {
            System.out.println("You have unfortunately not guessed the hidden word this time.");
            System.out.println("The hidden word was " + wordToGuess + ".");
            System.out.println("Program ends.");
        }


    }

//   -------------------------------------------------------------------------------------------------
// METHODS USED IN THE PROGRAM:
    public static String selectWord (String[] words, Random random) {
        // the method shall return string with characters replaced by '-' for user to guess
        return words[random.nextInt(words.length)];

//        the line above does the same as if written like this:
//        final String wordToGuess = words[random.nextInt(words.length)];
//        return wordToGuess;
//        no need for parameter definition if program does not work with it
    }
    //   -------------------------------------------------------------------------------------------------
    public static String generateHiddenWord (String word) {
        return "_".repeat(word.length()); // replaces all string positions with "_"
    }
    //   -------------------------------------------------------------------------------------------------
    public static char scanLetter (Scanner scanner) {
        char guess;

        while (true) {
        try {
            String line = scanner.nextLine();
            System.out.println("User input entered.");

            if (line.length() != 1) {
                throw new Exception ("Invalid input. Please enter single character.");
            }

            guess = line.charAt(0);
            if (!Character.isLetter(guess)) {
                throw new Exception ("Invalid input. Please enter single alphabetic character. Numbers and special chars (&, %, #) are not accepted.");
            }

            break; // to break try-catch exception

        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
        }

        return guess;
    }
    //   -------------------------------------------------------------------------------------------------
    public static String revealLetters (String wordToGuess, String hiddenWord, char letter) {
        char[] hiddenWordChars = hiddenWord.toCharArray();
        String wordToGuessToLower = wordToGuess.toLowerCase(); // will convert the guessed word to lower case

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuessToLower.charAt(i) == Character.toLowerCase(letter)) {
                // letterStringToLower.charAt(0) = 0 used as this will always have only first index
                hiddenWordChars[i] = Character.toLowerCase(letter); // here I do not see any reason to work with converted strings, can use original word and letter directly

            }
        }
        return String.valueOf(hiddenWordChars);
    }
    //   -------------------------------------------------------------------------------------------------
}