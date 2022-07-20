package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    File file = new File("music.wav");
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();

    public Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.open(audioStream);
        System.out.println("Clip lenght is " + clip.getMicrosecondLength() / 1000000 + " s");
    }
    public void start() {
        clip.start();
    }
    public void pause() {
        clip.stop();
    }
    public void reset() {
        clip.setMicrosecondPosition(0);
    }
    public void forward() {
        if((clip.getMicrosecondLength() - clip.getMicrosecondPosition()) > 5000000) {
            clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 5000000);
        }
    }
    public void back() {
        if (clip.getMicrosecondLength() > 5000000) {
            clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 5000000);
        }
    }
}
