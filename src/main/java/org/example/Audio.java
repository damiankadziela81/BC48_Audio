package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    AudioInputStream audioStream;
    Clip clip;
    public Audio(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
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
    public void set(long position) {
        clip.setMicrosecondPosition(position*10000);
    }
}
