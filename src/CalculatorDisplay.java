/**
 *
 *
 * https://www.cscprogrammingtutorials.com/2014/08/simple-java-gui-calculator.html?fbclid=IwAR0xC6lRNyVNhCDoM0BLE0XoEmw8ceqsY0jUfv3XVQefdBjgsSlDkxpODec
 */

import calculations.Calculations;
import calculatorOptions.CalculatorOptions;
import calculatorOptions.DisplayMode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.*;

public class CalculatorDisplay extends JFrame {

    /**
     * @param args the command line arguments
     */
    String memory = "Memory Currently Empty";
    String lastInput = "";

    String[] buttonLabels = {"  1  ", "  2  ", "  3  ", "  4  ", "  5  ", "  6  ", "  7  ", "  8  ", "  9  ",
                            "  0  ", "  +  ", "  -  ", "  *  ", "  /  ", "  ^  ", "Square", "SquareRoot", "Factorial",
                            "Inverse", "Sine", "Cosine", "Tangent", "InverseSine", "InverseCosine", "InverseTangent",
                            "Logarithm", "Inverselogarithm", "NaturalLogarithm",  "InverseNaturalLogarithm" ,
                            "  .  ", "  ,  ", "XOR","  (  ", ")"};
    private JButton[] jButtons;
    private JButton equals;
    private JButton clear;
    private JButton switchSign;
    private JButton switchDisplayMode;
    private JButton memoryStore;
    private JButton memoryClear;
    private JButton memoryRecall;

    String value;
    char operator;
    JTextArea inputWindow = new JTextArea(3, 1);
    //JLabel historyWindow = new JLabel();
    Parser parser = new Parser();

    public static void main(String[] args) {
        CalculatorDisplay calculator = new CalculatorDisplay();
        calculator.setSize(600, 400);
        calculator.setTitle("This Calculator is Going to Work");
        calculator.setResizable(true);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void doCalculation() {
        lastInput = inputWindow.getText();
        Calculations calculations = parser.parse(inputWindow.getText());
        Float answer = calculations.evaluate();
        try {
            DisplayMode mode = CalculatorOptions.getInstance().getDisplayMode();
            //TODO Error: Number too big, NaN, etc (notably inverseSine, inverseCosine, inverseTanget)
            inputWindow.setText(mode.convertToMode(answer));
        }
        catch (Exception e) {

            System.err.println("Error - INVALID MATH");
        };
    }


    public CalculatorDisplay() {
        add(new JScrollPane(inputWindow), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());


        jButtons = new JButton[buttonLabels.length];
        for(int i = 0; i<buttonLabels.length;i++){
            String buttonLabel = buttonLabels[i];
            jButtons[i] = new JButton(buttonLabel);
            buttonpanel.add(jButtons[i]);
            jButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                   inputWindow.append(buttonLabel);
                }
            });
        }

        add(buttonpanel, BorderLayout.CENTER);
       inputWindow.setForeground(Color.BLACK);
       inputWindow.setBackground(Color.WHITE);
       inputWindow.setLineWrap(true);
       inputWindow.setWrapStyleWord(true);
       inputWindow.setEditable(true);

        switchSign = new JButton("SwitchSign");
        buttonpanel.add(switchSign);
        switchSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                inputWindow.append("SwitchSign");
                doCalculation();
            }
        });


        switchDisplayMode = new JButton("SwitchDisplayMode");
        buttonpanel.add(switchDisplayMode);
        switchDisplayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                CalculatorOptions mode = CalculatorOptions.getInstance();
                mode.rotateMode();
                inputWindow.setText("");
                inputWindow.setText("Display mode is now: " + mode.getDisplayMode());
            }
        });


        memoryStore = new JButton("MemoryStore");
        buttonpanel.add(memoryStore);
        memoryStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                memory = inputWindow.getText();
                inputWindow.setText("");
                inputWindow.setText("Stored value successfully changed to: " + memory);
            }
        });

        memoryRecall = new JButton("MemoryRecall");
        buttonpanel.add(memoryRecall);
        memoryRecall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                inputWindow.append(memory);
            }
        });

        memoryClear = new JButton("MemoryClear");
        buttonpanel.add(memoryClear);
        memoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                memory = "Memory Currently Empty";
                inputWindow.setText(memory);
            }
        });

        clear = new JButton("CLEAR");
        buttonpanel.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                inputWindow.setText("");
            }
        });

        equals = new JButton("      =      ");
        buttonpanel.add(equals);
        equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                doCalculation();
            }
        });



        inputWindow.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent event){
                if(event.getKeyCode() == KeyEvent.VK_ENTER){
                    doCalculation();
                }
            }
            public void keyReleased(KeyEvent e) { /* ... */ }

            public void keyTyped(KeyEvent e) { /* ... */ }
        });

        inputWindow.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent event){
                if(event.getKeyCode() == KeyEvent.VK_DOWN){
                    inputWindow.setText("");
                    inputWindow.setText(lastInput);
                }
            }
            public void keyReleased(KeyEvent e) { /* ... */ }

            public void keyTyped(KeyEvent e) { /* ... */ }
        });

    }
}
