import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.BorderLayout;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Dimension;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource; 


class GUI { 
    JFrame window = new JFrame("WakeUp v2.0");
    JButton startButton = new JButton("Start Process");
    JButton endButton = new JButton("End Process");
    JTextArea textArea = new JTextArea("This program is meant to intermittently move the mouse to keep the computer awake. The default time interval between mouse movements is 15 minutes. To change this, enter the desired time interval into the box and press 'confirm'. Press the 'Start' button to begin automatic mouse movements and the 'End' button to stop it. Closing this window will kill the process.");
    JPanel buttons = new JPanel();
    JPanel input = new JPanel();
    JLabel description = new JLabel("Enter delay time (in minutes): ");
    JButton confirmButton = new JButton("Confirm");
    JFormattedTextField amount = new JFormattedTextField();
    static int timeDelay = 900000; //default 15 minutes
    
    static ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            try {
                System.out.println(timeDelay);
                Robot mouse = new Robot();
                mouse.mouseMove(10000, 10000);
                for (int shift = 0; shift < 10000; shift++) {
                    mouse.mouseMove(shift, 0);
                }
                for (int shift = 10000; shift > 0; shift--) {
                    mouse.mouseMove(shift, 0);
                }
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    };

    ActionListener start = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            timer.start();
        }
    };

    ActionListener end = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            timer.stop();
        }
    };

    ActionListener confirm = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                amount.commitEdit();
                timeDelay = ((Number) amount.getValue()).intValue() * 1000;
                System.out.println(timeDelay);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    };
    
    static Timer timer = new Timer(timeDelay, taskPerformer);

    public GUI() {
        startButton.addActionListener(start);
        endButton.addActionListener(end);
        confirmButton.addActionListener(confirm);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new InsetsUIResource(10, 30, 1, 30));
        textArea.setBackground(new ColorUIResource(238, 238, 238));
        
        buttons.add(startButton);
        buttons.add(endButton);
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        confirmButton.setPreferredSize(new Dimension(90, 18));
       
        amount.setValue((Integer) 15);
        amount.setColumns(10);

        window.setSize(500, 220);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        
        input.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        input.add(description, JPanel.LEFT_ALIGNMENT);
        input.add(amount, JPanel.CENTER_ALIGNMENT);
        input.add(confirmButton, JPanel.RIGHT_ALIGNMENT);

        window.add(textArea, BorderLayout.PAGE_START);
        window.add(input, BorderLayout.CENTER);
        window.add(buttons, BorderLayout.PAGE_END);
    
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

public class Main {
    public static void main(String[] args) {
        GUI main = new GUI();
    }
}