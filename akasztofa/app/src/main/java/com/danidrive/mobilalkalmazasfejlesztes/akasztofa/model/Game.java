package com.danidrive.mobilalkalmazasfejlesztes.akasztofa.model;

import com.danidrive.mobilalkalmazasfejlesztes.akasztofa.exceptions.AlreadyGuessedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game {

    private String[] words;
    private char[] solution;
    private char[] guess;
    private List<Character> guessedCharacters;
    private int wrongGuesses;

    public Game(String[] words) {
        this.words = words;
        startNewGame();
    }

    public void startNewGame(){
        Random random = new Random();
        this.solution = words[random.nextInt(words.length)].toCharArray();

        this.guess = new char[solution.length];
        for (int i = 0; i < this.solution.length; ++i)
            this.guess[i] = '_';
        this.guessedCharacters = new ArrayList<>();
        this.wrongGuesses = 0;
    }

    public String getGuess() {
        return new String(guess);
    }

    public String getSolution() {
        return new String(solution);
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public boolean isPlayerWin(){
        for (char c : this.guess) {
            if (c == '_')
                return false;
        }
        return true;
    }

    public boolean isPlayerLose(){
        return wrongGuesses == 10;
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
        if (!success)
            ++wrongGuesses;
        return success;
    }

    public String getGuessedChars(){

        StringBuilder stringBuilder = new StringBuilder();

        Iterator<Character> iterator = guessedCharacters.iterator();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
            if (iterator.hasNext())
                stringBuilder.append(", ");
        }
        return stringBuilder.toString().trim();
    }
}
