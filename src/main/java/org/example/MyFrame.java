package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener, MouseListener {

    JTextField textFieldActualPosition = new JTextField();
    JTextField textFieldClipLength = new JTextField();

    JButton playButton;
    JButton stopButton;
    JButton pauseButton;
    JButton forwardButton;
    JButton backButton;
    JButton loadFileButton;
    Audio audio;
    JPanel sliderPanel;
    JSlider slider;
    String actualPosition;
    String clipLength;
    boolean clipIsEmpty = true;


    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            actualPosition = String.valueOf(audio.clip.getMicrosecondPosition() / 10000);
            textFieldActualPosition.setText(actualPosition);
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
        slider.setEnabled(false);

        sliderPanel = new JPanel();
        sliderPanel.setBounds(10,50,420,80);
        //sliderPanel.setBackground(Color.red);
        sliderPanel.add(slider);

        //  textField.setText();
        textFieldActualPosition.setBounds(20,20,80,30);
        textFieldActualPosition.setOpaque(true);
        textFieldActualPosition.setEditable(false);
        textFieldActualPosition.setBackground(Color.BLACK);
        textFieldActualPosition.setForeground(Color.GREEN);
        textFieldActualPosition.setFont(new Font("Consolas",Font.BOLD,25));
        textFieldActualPosition.setText("0");

        textFieldClipLength.setBounds(100,20,80,30);
        textFieldClipLength.setOpaque(true);
        textFieldClipLength.setEditable(false);
        textFieldClipLength.setBackground(Color.BLACK);
        textFieldClipLength.setForeground(Color.GREEN);
        textFieldClipLength.setFont(new Font("Consolas",Font.BOLD,25));
        textFieldClipLength.setText("0");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lame Wave Player");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.add(textFieldActualPosition);
        this.add(textFieldClipLength);
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
            playButton.setBackground(Color.GREEN);
            pauseButton.setBackground(null);
           // loadFileButton.setEnabled(false);
        }
        if (e.getSource() == stopButton) {
            audio.pause();
            audio.reset();
            playButton.setBackground(null);
            pauseButton.setBackground(null);
        }
        if (e.getSource() == pauseButton) {
            audio.pause();
            pauseButton.setBackground(Color.GRAY);
            playButton.setBackground(null);
        }
        if (e.getSource() == forwardButton) {
            audio.forward();
        }
        if (e.getSource() == backButton) {
            audio.back();
        }
        if (e.getSource() == loadFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\Damian\\Downloads")); //sets default path for project location
            int response = fileChooser.showOpenDialog(null); //select file to open
            if (response == JFileChooser.APPROVE_OPTION) {
                if (!clipIsEmpty) audio.clip.close();
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    audio = new Audio(file);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                clipIsEmpty = false;
                playButton.setEnabled(true);
                playButton.setBackground(null);
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);
                pauseButton.setBackground(null);
                forwardButton.setEnabled(true);
                backButton.setEnabled(true);
                clipLength = String.valueOf(audio.clip.getMicrosecondLength() / 10000);
                slider.setMaximum(Integer.parseInt(clipLength));
                slider.setMinimum(0);
                slider.setEnabled(true);
                textFieldClipLength.setText(clipLength);
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
        timer.start();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}