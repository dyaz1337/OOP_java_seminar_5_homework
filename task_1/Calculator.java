public class CalculatorModel {
    public double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}

public class CalculatorView extends JFrame {
    private JTextField num1Field = new JTextField(10);
    private JTextField num2Field = new JTextField(10);
    private JTextField resultField = new JTextField(10);
    private JButton addButton = new JButton("+");
    private JButton subtractButton = new JButton("-");
    private JButton multiplyButton = new JButton("*");
    private JButton divideButton = new JButton("/");

    public CalculatorView() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Number 1:"));
        panel.add(num1Field);
        panel.add(new JLabel("Number 2:"));
        panel.add(num2Field);
        panel.add(new JLabel("Result:"));
        panel.add(resultField);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public double getNum1() {
        return Double.parseDouble(num1Field.getText());
    }

    public double getNum2() {
        return Double.parseDouble(num2Field.getText());
    }

    public void setResult(double result) {
        resultField.setText(Double.toString(result));
    }

    public void addCalculationListener(ActionListener listener) {
        addButton.addActionListener(listener);
        subtractButton.addActionListener(listener);
        multiplyButton.addActionListener(listener);
        divideButton.addActionListener(listener);
    }
}

public class CalculatorPresenter implements ActionListener {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.view.addCalculationListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = view.getNum1();
            double num2 = view.getNum2();
            String operator = e.getActionCommand();
            double result = model.calculate(num1, num2, operator);
            view.setResult(result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorPresenter presenter = new CalculatorPresenter(model, view);
    }
}