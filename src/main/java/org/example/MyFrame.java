package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {

    JTextField textField = new JTextField();
    JButton playButton;
    JButton resetButton;
    JButton pauseButton;
    JButton forwardButton;
    JButton backButton;
    JButton loadFileButton;
    Audio audio;


    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actualPosition = String.valueOf(audio.clip.getMicrosecondPosition() / 10000);
            String clipLength = String.valueOf(audio.clip.getMicrosecondLength() / 10000);
            textField.setText(actualPosition + " / " + clipLength);
        }
    });

    MyFrame() {
        playButton = new JButton("PLAY");
        playButton.setBounds(20, 120, 80, 30);
        playButton.addActionListener(this);
        playButton.setFocusable(false);
        playButton.setEnabled(false);

        resetButton = new JButton("RESET");
        resetButton.setBounds(100, 120, 80, 30);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);
        resetButton.setEnabled(false);

        pauseButton = new JButton("PAUSE");
        pauseButton.setBounds(180, 120, 80, 30);
        pauseButton.addActionListener(this);
        pauseButton.setFocusable(false);
        pauseButton.setEnabled(false);

        forwardButton = new JButton(">>");
        forwardButton.setBounds(260, 120, 80, 30);
        forwardButton.addActionListener(this);
        forwardButton.setFocusable(false);
        forwardButton.setEnabled(false);

        backButton = new JButton("<<");
        backButton.setBounds(340, 120, 80, 30);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setEnabled(false);

        loadFileButton = new JButton("Load");
        loadFileButton.setBounds(180, 20, 80, 30);
        loadFileButton.addActionListener(this);
        loadFileButton.setFocusable(false);

        //  textField.setText();
        textField.setBounds(20, 20, 160, 30);
        textField.setOpaque(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lame Player");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(450, 200);
        this.setVisible(true);
        this.add(textField);
        this.add(playButton);
        this.add(resetButton);
        this.add(pauseButton);
        this.add(forwardButton);
        this.add(backButton);
        this.add(loadFileButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            audio.start();
            timer.start();
        }
        if (e.getSource() == resetButton) {
            audio.reset();
        }
        if (e.getSource() == pauseButton) {
            audio.pause();
        }
        if (e.getSource() == forwardButton) {
            audio.forward();
        }
        if (e.getSource() == backButton) {
            audio.back();
        }
        if (e.getSource() == loadFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(".")); //sets default path for project location
            int response = fileChooser.showOpenDialog(null); //select file to open
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    audio = new Audio(file);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                playButton.setEnabled(true);
                resetButton.setEnabled(true);
                pauseButton.setEnabled(true);
                forwardButton.setEnabled(true);
                backButton.setEnabled(true);
            }
        }
    }
}