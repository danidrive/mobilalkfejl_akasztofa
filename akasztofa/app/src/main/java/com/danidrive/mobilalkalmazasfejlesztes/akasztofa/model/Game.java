package com.danidrive.mobilalkalmazasfejlesztes.akasztofa.model;

import com.danidrive.mobilalkalmazasfejlesztes.akasztofa.exceptions.AlreadyGuessedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private String[] words;
    private char[] solution;
    private char[] guess;
    private List<Character> guessedCharacters;

    public Game(String[] words) {
        this.words = words;
        Random random = new Random();
        this.solution = words[random.nextInt(words.length)].toCharArray();

        this.guess = new char[solution.length];
        for (int i = 0; i < this.solution.length; ++i)
            this.guess[i] = '_';
        this.guessedCharacters = new ArrayList<>();
    }

    public String getGuess() {
        return new String(guess);
    }

    public int getTotalLength(){
        return this.solution.length;
    }

    public int getRemaingCharacters(){
        int counter = 0;
        for (char c : this.guess) {
            if (c == '_')
                ++counter;
        }
        return counter;
    }

    public boolean guessCharacter(char c) throws AlreadyGuessedException{

        if (guessedCharacters.contains(c))
            throw new AlreadyGuessedException(c);

        guessedCharacters.add(c);
        boolean success = false;
        for (int i = 0; i < solution.length; ++i) {
            if (solution[i] == c) {
                guess[i] = c;
                success = true;
            }
        }
        return success;
    }
}
