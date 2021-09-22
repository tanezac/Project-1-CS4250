package edu.umsl;


import java.io.File;
import java.util.Scanner;


public class Project_1_CS4250 {
    //Global declaration
    //Variable
    int charClass;
    char[] lexeme = new char[100];
    char nextChar;
    int lexLen;
    int token;
    int nextToken;

    //Character classes
    final int LETTER    = 0;
    final int DIGIT     = 1;
    final int UNKNOWN   = 99;

    //Token Codes
    final int INT_LIT   = 10;
    final int IDENT     = 11;
    final int ASSIGN_OP = 20;
    final int ADD_OP    = 21;
    final int SUB_OP    = 22;
    final int MULTI_OP   = 23;
    final int DIV_OP    = 24;
    final int LEFT_PAREN= 25;
    final int RIGHT_PAREN= 26;
    final int SEMI_COLON = 29;
    final int ASSI_OP    = 30;

    // Main
    public void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("The Lexical Analyzer!");
        System.out.println("Enter expression: ");
        String expression = input.nextLine();
        getChar(expression);

        for(int i=0; i < expression.length(); i++){
            char c = expression.charAt(i);
            System.out.print(c);
        }




    }
    int lookup(char ch){
        switch (ch){
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = ADD_OP;
                break;
            case '-':
                addChar();
                nextToken = SUB_OP;
                break;;
            case '*':
                addChar();
                nextToken = MULTI_OP;
                break;
            case '/':
                addChar();
                nextToken = DIV_OP;
                break;
            case ';':
                nextToken = SEMI_COLON;
                addChar();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + ch);
        }
        return nextToken;
    }


    void addChar(){
        //lexLen = lexeme.length;
        if(lexLen <= 98){
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        }
        else{
            System.out.println("Error - Lexeme is too long");
        }
    }
    void getChar(String s){
        String string = s + "EOF";
        for( int i = 0; i < string.length(); i++){
            char c = string.charAt(i);
            if(Character.isAlphabetic(c)){
                charClass = LETTER;
            }
            else if(Character.isDigit(c)){
                charClass = DIGIT;
            }
            else
                charClass = UNKNOWN;
        }
    }
    void getNonBlank(){
        while (Character.isSpaceChar(nextChar)){
            getChar();
        }

    }
    int lex(){
        return 0;

    }


}
