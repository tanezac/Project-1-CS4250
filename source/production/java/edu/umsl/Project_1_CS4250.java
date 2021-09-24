package edu.umsl;
import java.io.CharArrayWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Project_1_CS4250 {
    //Global declaration
    //Variable
    static int charClass;
    static char[] lexeme = new char[100];
    static char[] lexeme_letter = new char[100];
    static char[] lexeme_digit = new char[100];
    static char[] lexeme_operators  = new char[100];
    static char[] lexeme_EOF = new char[3];
    static char nextChar;
    static int lexLen;
    static int token;
    static int nextToken;
    static int nextElement;
    static char[] EXP = new char[100];

    //Character classes
    final static int LETTER    = 0;
    final static int DIGIT     = 1;
    final static int UNKNOWN   = 99;
    final static int EOF       = -1;

    //Token Codes
    final static int INT_LIT    = 10;
    final static int IDENT      = 11;
    final static int ASSIGN_OP  = 20;
    final static int ADD_OP     = 21;
    final static int SUB_OP     = 22;
    final static int MULTI_OP   = 23;
    final static int DIV_OP     = 24;
    final static int LEFT_PAREN = 25;
    final static int RIGHT_PAREN= 26;
    final static int SEMI_COLON = 29;
    final static int ASSI_OP    = 30;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("The Lexical Analyzer!");
        System.out.print("Enter expression: ");
        String expression = input.nextLine();
        expression = expression + " E";

        for (int i = 0; i < expression.length(); i++) {
            EXP[i] = expression.charAt(i);
        }

        Arrays.fill(lexeme_letter, ' ');
        Arrays.fill(lexeme_digit, ' ');
        Arrays.fill(lexeme_operators, ' ');
        Arrays.fill(lexeme, ' ');
        nextElement = 0;

        getChar();
        do {
            lex();
        }while (nextToken != EOF);

        Arrays.fill(EXP, ' ');
    }

    public static void lookup(char ch){
        switch (ch){
            case '(':
                addChar(lexeme_operators);
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar(lexeme_operators);
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar(lexeme_operators);
                nextToken = ADD_OP;
                break;
            case '-':
                addChar(lexeme_operators);
                nextToken = SUB_OP;
                break;
            case '*':
                addChar(lexeme_operators);
                nextToken = MULTI_OP;
                break;
            case '/':
                addChar(lexeme_operators);
                nextToken = DIV_OP;
                break;
            case ';':
                nextToken = SEMI_COLON;
                addChar(lexeme_operators);
                break;
            default:
                break;
        }
    }


    public static void addChar(char[] ch){
        if(lexLen <= 98){
            lexeme[lexLen] = nextChar;
            ch[lexLen] = nextChar;
            lexLen += 1;
        }
        else{
            System.out.println("Error - Lexeme is too long");
        }
    }
    public static void getChar(){
        nextChar = EXP[nextElement];
        nextElement += 1;
        if(Character.isAlphabetic(nextChar)){
            if(nextChar == 'E')
            {
                charClass = EOF;
            }
            else
                charClass = LETTER;
        }
        else if(Character.isDigit(nextChar)){
                charClass = DIGIT;
        }
        else {
                charClass = UNKNOWN;
        }
    }
    //function to call getChar until it returns a non-whitespace character
    public static void getNonBlank(){
        while (Character.isSpaceChar(nextChar)){
            getChar();
        }
    }
    /////////////////////////////////////////////////////
    public static int lex(){
        lexLen = 0;
        getNonBlank();
        switch (charClass){
            case LETTER:
                addChar(lexeme_letter);
                getChar();
                while (charClass == LETTER || charClass == DIGIT){
                    addChar(lexeme_letter);
                    getChar();
                }
                nextToken = IDENT;
                print_lexeme(lexeme_letter);
                break;
            case DIGIT:
                addChar(lexeme_digit);
                getChar();
                while (charClass == DIGIT){
                    addChar(lexeme_digit);
                    getChar();
                }
                nextToken = INT_LIT;
                // print lexeme
                print_lexeme(lexeme_digit);
                break;
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                print_lexeme(lexeme_operators);
                break;
            case EOF:
                nextToken = EOF;
                lexeme_EOF[0] = 'E';
                lexeme_EOF[1] = 'O';
                lexeme_EOF[2] = 'F';
                System.out.print("Next token is : " + nextToken + " Next lexeme is: " );
                for (char c : lexeme_EOF) {
                    System.out.print(c);
                }
                System.out.println(" ");
                break;
            default:
                break;
        }
        return nextToken;
    }

    static void print_lexeme(char[] ch){
        System.out.print("Next token is : " + nextToken + " Next lexeme is: " );
        for (char c : ch) {
            if(!Character.isSpaceChar(c)) {
                System.out.print(c);
            }
        }
        System.out.println(" ");
        Arrays.fill(ch, ' ');
    }

}
