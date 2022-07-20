package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {
    JButton playButton;
    JButton stopButton;
    JButton pauseButton;
    JButton forwardButton;
    JButton backButton;

    Audio audio = new Audio();

    MyFrame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playButton = new JButton("PLAY");
        playButton.setBounds(20,20,80,30);
        playButton.addActionListener(this);
        playButton.setFocusable(false);

        stopButton = new JButton("STOP");
        stopButton.setBounds(100,20,80,30);
        stopButton.addActionListener(this);
        stopButton.setFocusable(false);

        pauseButton = new JButton("PAUSE");
        pauseButton.setBounds(180,20,80,30);
        pauseButton.addActionListener(this);
        pauseButton.setFocusable(false);

        forwardButton = new JButton(">>");
        forwardButton.setBounds(260,20,80,30);
        forwardButton.addActionListener(this);
        forwardButton.setFocusable(false);

        backButton = new JButton("<<");
        backButton.setBounds(340,20,80,30);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,300);
        this.setVisible(true);
        this.add(playButton);
        this.add(stopButton);
        this.add(pauseButton);
        this.add(forwardButton);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            audio.start();
        }
        if(e.getSource() == stopButton) {
            audio.stop();
        }
        if(e.getSource() == pauseButton) {
            audio.pause();
        }
        if(e.getSource() == forwardButton) {
            audio.forward();
        }
        if(e.getSource() == backButton) {
            audio.back();
        }
    }
}
