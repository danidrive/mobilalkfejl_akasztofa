package com.danidrive.mobilalkalmazasfejlesztes.akasztofa.exceptions;

public final class AlreadyGuessedException extends Exception {

    private char c;

    public AlreadyGuessedException(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }
}
