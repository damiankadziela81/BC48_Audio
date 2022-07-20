package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {

    JTextField textField = new JTextField();
    JButton playButton;
    JButton resetButton;
    JButton pauseButton;
    JButton forwardButton;
    JButton backButton;
    Audio audio = new Audio();
    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actualPosition = String.valueOf(audio.clip.getMicrosecondPosition() / 10000);
            String clipLenght = String.valueOf(audio.clip.getMicrosecondLength() / 10000);
            textField.setText(actualPosition + " / " + clipLenght);
        }
    });

    MyFrame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playButton = new JButton("PLAY");
        playButton.setBounds(20,120,80,30);
        playButton.addActionListener(this);
        playButton.setFocusable(false);

        resetButton = new JButton("RESET");
        resetButton.setBounds(100,120,80,30);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        pauseButton = new JButton("PAUSE");
        pauseButton.setBounds(180,120,80,30);
        pauseButton.addActionListener(this);
        pauseButton.setFocusable(false);

        forwardButton = new JButton(">>");
        forwardButton.setBounds(260,120,80,30);
        forwardButton.addActionListener(this);
        forwardButton.setFocusable(false);

        backButton = new JButton("<<");
        backButton.setBounds(340,120,80,30);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

      //  textField.setText();
        textField.setBounds(20,20,160,30);
        textField.setOpaque(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lame Player");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(450,200);
        this.setVisible(true);
        this.add(textField);
        this.add(playButton);
        this.add(resetButton);
        this.add(pauseButton);
        this.add(forwardButton);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            audio.start();
            timer.start();
        }
        if(e.getSource() == resetButton) {
            audio.reset();
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
