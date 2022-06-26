package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);

        File file = new File("music.wav");
        AudioInputStream audioStrem = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStrem);

        System.out.println("Clip lenght is " + clip.getMicrosecondLength() / 1000000 + " s");

        String response = "";

        while (!response.equals("Q")){
            System.out.println("P = play, S = Stop, R = Reset, Q = Quit");
            System.out.print("Enter your choice: ");

            response = scanner.next();
            response = response.toUpperCase();

            switch (response){
                case("P"): clip.start();
                break;
                case("S"): clip.stop();
                break;
                case("R"): clip.setMicrosecondPosition(2700000);
                break;
                case("Q"): clip.close();
                break;
                default: System.out.println("Not a valid choice");
            }
        }
    }
}