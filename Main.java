import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.AWTException;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource; 

class Processes {
    static void refresh() {
        try {
            Robot mouse = new Robot();
            mouse.mouseMove(10000, 10000);
            for (int shift = 0; shift < 1000; shift++) {
                mouse.mouseMove(shift, 0);
            }
            for (int shift = 1000; shift > 0; shift--) {
                mouse.mouseMove(shift, 0);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    static int delay = 900000; //15 minutes = 900000 milliseconds
    static ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            refresh();
        }
    };
    static Timer timer = new Timer(delay, taskPerformer);
   
    public static void start() {
       timer.start();
    }

    public static void end() {
        timer.stop();
    }
}

class GUI { 
    JFrame window = new JFrame("WakeUp v1.0");
    JButton startButton = new JButton("Start Process");
    JButton endButton = new JButton("End Process");
    JTextArea textArea = new JTextArea("This program is meant to intermittently move the mouse automatically to keep the computer awake. Press the 'Start' button to begin the process and the 'End' button to stop it.");
    JPanel buttons = new JPanel();

    ActionListener start = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Processes.start();
        }
    };

    ActionListener end = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Processes.end();
        }
    };

    public GUI() {
        window.setSize(550, 115);
        window.setLayout(new GridLayout(2, 1));
        window.setVisible(true);
        
        startButton.addActionListener(start);
        endButton.addActionListener(end);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new InsetsUIResource(1, 30, 1, 20));
        textArea.setBackground(new ColorUIResource(238, 238, 238));
        buttons.add(startButton);
        buttons.add(endButton);
       
        window.add(textArea);
        window.add(buttons);
        
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

public class Main {
    public static void main(String[] args) {
        GUI main = new GUI();
    }
}