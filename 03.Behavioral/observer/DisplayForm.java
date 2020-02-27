package com.jp.behavioral.observer;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class DisplayForm extends JFrame {
    InputFormObserver input = new InputFormObserver();
    InputForm inputForm ;
    Observable obsInput;
    JTextField display;
    //...
    public DisplayForm() {        
        addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                  System.exit(0);
        }});
        inputForm = new InputForm();
        obsInput = inputForm.getInputInfo();
        obsInput.addObserver(input);
    
        display = new JTextField(10);
            display.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                    
               }
           });
        getContentPane().add(display);
        setTitle("Observer form");
        setSize(200,100);
        setLocation(200,100);
        setVisible(true);
     
    }
    
    private class InputFormObserver implements Observer {
         public void update(Observable ob, Object o) {
             doSomeUpdate();
             if (obsInput.countObservers()>0) 
                    obsInput.deleteObservers();
             obsInput = inputForm.getInputInfo();
             obsInput.addObserver(input);
         }
         
     }
     public void doSomeUpdate() {
            display.setText(inputForm.getText());
                    JOptionPane.showMessageDialog(DisplayForm.this,
                            "This form has been updated");
    }
    public static void main(String args[]) {
        DisplayForm df = new DisplayForm();
    }
}
class InputForm extends JFrame {
    public InformDisplay inform = new InformDisplay();
    //...
    JTextField input= new JTextField(10);
        
    public InputForm() {
        JPanel panel= new JPanel();
        input.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                    inform.notifyObservers();
               }
           });
        panel.add(new JLabel("Enter: "));
        panel.add(input);
        addWindowListener(new WindowAdapter() {
                  public void windowClosing(WindowEvent e) {
                      System.exit(0);
        }});
        getContentPane().add(panel);
        setTitle("Observable form");
        setSize(200,100);
        setVisible(true);
    }
    public Observable getInputInfo() {
        return inform;
    }
    
    public String getText() {
        return input.getText();
    }
    
    private class InformDisplay extends Observable {
        public void notifyObservers() {
            setChanged();
            super.notifyObservers();
        }
        public String getChange() {
            return input.getText();
        }
    }
    //...
}
