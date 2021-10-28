package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame2 extends JFrame implements ActionListener
{

    final JTextField textField;
    final JButton button;
    final JPanel panel4;
    final JTextArea output;

    public Frame2() throws HeadlessException
    {

        JLabel text = new JLabel();
        text.setText("Business Name: ");
        text.setBounds(0,0,50,75);
        text.setHorizontalAlignment(JLabel.RIGHT);

        JLabel text2 = new JLabel();
        text2.setText("Similarities: ");
        text2.setBounds(0,0,50,75);
        text2.setHorizontalAlignment(JLabel.RIGHT);

        /*Textfield for the input*/
        textField = new JTextField();
        textField.setFont(new Font("Courier", Font.PLAIN, 18));

        /*The text field where the output will be displayed, cant type it in*/
        output = new JTextArea();
        output.setFont(new Font("Courier", Font.PLAIN, 14));
        output.setEditable(false);
        output.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        /*business name panel*/
        JPanel panel = new JPanel();
        panel.setBounds(0,0,200,100);
        panel.setLayout(new BorderLayout());

        /*Textfield panel*/
        JPanel panel2 = new JPanel();
        panel2.setBounds(200,25,400,50);
        panel2.setLayout(new BorderLayout());

        /*Text output panel*/
        JPanel panel3 = new JPanel();
        panel3.setBounds(0,100,200,50);
        panel3.setLayout(new BorderLayout());

        /*output for all of the businesses names panel*/
        panel4 = new JPanel();
        panel4.setBackground(Color.WHITE);
        panel4.setBounds(200,100,400,300);
        panel4.setLayout(new BorderLayout());

        /*Button for submit*/
        button = new JButton("Submit");
        button.setBounds(620,30,100,40);
        button.addActionListener(this);

        /*default*/
        panel.add(text);
        panel2.add(textField);
        panel3.add(text2);
        panel4.add(output);
        this.setTitle("365 Assignment 1");
        this.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,480);
        this.setVisible(true);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button)
        {
            output.selectAll();
            output.replaceSelection("");
            String inputText = textField.getText();
            System.out.println(inputText);
            output.append("- Panera Bread\n- Mcdonalds\n- Tops\n- KFC");

        }
    }
}
