package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener, MouseListener {

    JTextField textField = new JTextField();
    JButton playButton;
    JButton stopButton;
    JButton pauseButton;
    JButton forwardButton;
    JButton backButton;
    JButton loadFileButton;
    Audio audio;
    JPanel sliderPanel;
    JSlider slider;


    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actualPosition = String.valueOf(audio.clip.getMicrosecondPosition() / 10000);
            String clipLength = String.valueOf(audio.clip.getMicrosecondLength() / 10000);
            textField.setText(actualPosition + " / " + clipLength + " / " + slider.getValue());
            slider.setMaximum(Integer.parseInt(clipLength));
            slider.setMinimum(0);
            slider.setValue(Integer.parseInt(actualPosition));
        }
    });

    MyFrame() {
        playButton = new JButton("PLAY");
        playButton.setBounds(20, 120, 80, 30);
        playButton.addActionListener(this);
        playButton.setFocusable(false);
        playButton.setEnabled(false);

        stopButton = new JButton("STOP");
        stopButton.setBounds(100, 120, 80, 30);
        stopButton.addActionListener(this);
        stopButton.setFocusable(false);
        stopButton.setEnabled(false);

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

        loadFileButton = new JButton("LOAD WAVE FILE");
        loadFileButton.setBounds(260, 20, 160, 30);
        loadFileButton.addActionListener(this);
        loadFileButton.setFocusable(false);

        slider = new JSlider(0,6000,0);
        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.setPreferredSize(new Dimension(420,60));
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(6000);
        slider.setMinorTickSpacing(1000);
        slider.setPaintLabels(true);
        slider.addMouseListener(this);

        sliderPanel = new JPanel();
        sliderPanel.setBounds(10,50,420,80);
        //sliderPanel.setBackground(Color.red);
        sliderPanel.add(slider);

        //  textField.setText();
        textField.setBounds(20, 20, 160, 30);
        textField.setOpaque(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lame Wave Player");
        this.setLayout(null);
        this.setResizable(true);

        this.add(textField);
        this.add(playButton);
        this.add(stopButton);
        this.add(pauseButton);
        this.add(forwardButton);
        this.add(backButton);
        this.add(loadFileButton);
        this.add(sliderPanel);
        this.setSize(455, 200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            audio.start();
            timer.start();
        }
        if (e.getSource() == stopButton) {
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
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);
                forwardButton.setEnabled(true);
                backButton.setEnabled(true);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        timer.stop();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        audio.set(slider.getValue());
        System.out.println(slider.getValue());
        timer.start();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}