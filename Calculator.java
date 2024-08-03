import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    private final TextField display;
    private final Button[] numbers;
    private final Button add, subtract, multiply, divide, decimal, equals, delete, clear, sqrt, percent, modulus, negate;

    private double operand1, result;
    private char operator;

    public Calculator() {
        setLayout(null);
        setSize(400, 650);
        setTitle("Simple Calculator");

        setBackground(Color.YELLOW);

        display = new TextField();
        display.setBounds(50, 50, 300, 50);
        add(display);

        numbers = new Button[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = new Button(String.valueOf(i));
            numbers[i].addActionListener(this);
            numbers[i].setBackground(Color.BLUE);
            numbers[i].setForeground(Color.WHITE);
        }

        add = new Button("+");
        subtract = new Button("-");
        multiply = new Button("*");
        divide = new Button("/");
        decimal = new Button(".");
        equals = new Button("=");
        delete = new Button("Del");
        clear = new Button("Clr");
        sqrt = new Button("√");
        percent = new Button("%");
        modulus = new Button("Mod");
        negate = new Button("+/-");

        Button[] operations = {add, subtract, multiply, divide, decimal, equals, delete, clear, sqrt, percent, modulus, negate};
        for (Button button : operations) {
            button.addActionListener(this);
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
        }

        Panel numberPanel = new Panel();
        numberPanel.setBounds(50, 150, 300, 350);
        numberPanel.setLayout(new GridLayout(6, 4, 10, 10));

        numberPanel.add(numbers[1]);
        numberPanel.add(numbers[2]);
        numberPanel.add(numbers[3]);
        numberPanel.add(add);
        numberPanel.add(numbers[4]);
        numberPanel.add(numbers[5]);
        numberPanel.add(numbers[6]);
        numberPanel.add(subtract);
        numberPanel.add(numbers[7]);
        numberPanel.add(numbers[8]);
        numberPanel.add(numbers[9]);
        numberPanel.add(multiply);
        numberPanel.add(decimal);
        numberPanel.add(numbers[0]);
        numberPanel.add(equals);
        numberPanel.add(divide);
        numberPanel.add(sqrt);
        numberPanel.add(percent);
        numberPanel.add(modulus);
        numberPanel.add(negate);

        add(numberPanel);

        delete.setBounds(50, 520, 145, 50);
        clear.setBounds(205, 520, 145, 50);
        add(delete);
        add(clear);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (int i = 0; i < 10; i++) {
            if (source == numbers[i]) {
                display.setText(display.getText() + i);
            }
        }

        if (source == decimal) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        if (source == add || source == subtract || source == multiply || source == divide || source == modulus) {
            operand1 = Double.parseDouble(display.getText());
            operator = ((Button) source).getLabel().charAt(0);
            display.setText("");
        }

        if (source == equals) {
            double operand2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
                case 'M':  // For modulus
                    result = operand1 % operand2;
                    break;
            }
            display.setText(String.valueOf(result));
            operand1 = result;
        }

        if (source == clear) {
            display.setText("");
        }

        if (source == delete) {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }

        if (source == sqrt) {
            double value = Double.parseDouble(display.getText());
            result = Math.sqrt(value);
            display.setText(String.valueOf(result));
        }

        if (source == percent) {
            double value = Double.parseDouble(display.getText());
            result = value / 100;
            display.setText(String.valueOf(result));
        }

        if (source == negate) {
            double value = Double.parseDouble(display.getText());
            result = value * -1;
            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
