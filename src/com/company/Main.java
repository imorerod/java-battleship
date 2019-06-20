package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int GAME_WIDTH = 5;
    private static final int GAME_HEIGHT = 5;
    private static final int TARGETS = 5;

    private static char[][] grid = new char[GAME_WIDTH][GAME_HEIGHT];
    private static char[][] targets = new char[GAME_WIDTH][GAME_HEIGHT];

    private static int hitsLeft = TARGETS;
    private static int guesses = 0;

    public static void main(String[] args) {
        // write your code here
        for (int i = 0; i < GAME_WIDTH; i++) {
            for (int j = 0; j < GAME_HEIGHT; j++) {
                grid[i][j] = 'O';
            }
        }

        // PUT THE TARGETS ON THE GAME BOARD (NOT THE PLAYER BOARD!)
        assignHits(TARGETS);

        while(hitsLeft > 0) {
            promptUser();
            printMap();
        }

        System.out.println("YOU WIN!");
        System.out.println("TOTAL GUESSES: " + guesses);
    }

    public static void assignHits(int num_of_targets) {
        // This wil set up where the enemy ships go!

        Random random = new Random();

        for (int i = 0; i < num_of_targets; i++) {
            int y = random.nextInt(GAME_HEIGHT);
            int x = random.nextInt(GAME_WIDTH);
            targets[x][y] = 'Y';
        }
    }

    public static void printMap() {
        // This shows the players board as they are playing!
        for (int i = 0; i < GAME_WIDTH; i++) {
            for (int j = 0; j < GAME_HEIGHT; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static String getInput(String message) {
        // Collect user input and return what they enter as a string!
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void promptUser() {
        // This will operate as our main game loop!

        int xGuess;
        int yGuess;

        // ENTER USER GUESSES
        try {
            xGuess = Integer.parseInt(getInput("Enter an X value: "));
        } catch (Exception error) {
            xGuess = -1;
        }

       try {
            yGuess = Integer.parseInt(getInput("Enter an Y value: "));
        } catch (Exception error) {
            yGuess = -1;
        }

       // CHECK FOR HIT, MISS OR ALREADY GUESSED
        try {
            // Scenario #1, They hit!
            // Scenario #2, They already guessed that space!
            // Scenario #3, They miss!

            if (targets[yGuess][xGuess] == 'Y') {

                //Update boards
                grid[yGuess][xGuess] = 'H';
                targets[yGuess][xGuess] = 'O';

                //Let the player know they hit
                System.out.println("HIT!");

                //Update stats
                --hitsLeft;
                ++guesses;

            } else if (grid[yGuess][xGuess] == 'M') {
                System.out.println("You already guessed that space! Guess again!");
            } else {
                grid[yGuess][xGuess] = 'M';
                System.out.println("Miss");
                ++guesses;
            }
        } catch (Exception error) {
            System.out.println("You entered a position that does not exist! Try again!");
        }
    }
}
