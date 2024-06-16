import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {

    private JTextField temperatureInput;
    private JComboBox<String> unitComboBox;
    private JLabel celsiusLabel;
    private JLabel fahrenheitLabel;
    private JLabel kelvinLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        temperatureInput = new JTextField(10);
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);
        celsiusLabel = new JLabel("Celsius: ");
        fahrenheitLabel = new JLabel("Fahrenheit: ");
        kelvinLabel = new JLabel("Kelvin: ");
        JButton convertButton = new JButton("Convert");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Temperature:"));
        add(temperatureInput);
        add(new JLabel("Original Unit:"));
        add(unitComboBox);
        add(celsiusLabel);
        add(fahrenheitLabel);
        add(kelvinLabel);
        add(new JLabel());
        add(convertButton);

        setVisible(true);
    }

    private void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(temperatureInput.getText());
            String originalUnit = (String) unitComboBox.getSelectedItem();

            double celsius = 0, fahrenheit = 0, kelvin = 0;

            if ("Celsius".equals(originalUnit)) {
                celsius = inputTemp;
                fahrenheit = celsius * 9 / 5 + 32;
                kelvin = celsius + 273.15;
            } else if ("Fahrenheit".equals(originalUnit)) {
                fahrenheit = inputTemp;
                celsius = (fahrenheit - 32) * 5 / 9;
                kelvin = celsius + 273.15;
            } else if ("Kelvin".equals(originalUnit)) {
                kelvin = inputTemp;
                celsius = kelvin - 273.15;
                fahrenheit = celsius * 9 / 5 + 32;
            }

            celsiusLabel.setText("Celsius: " + String.format("%.2f", celsius));
            fahrenheitLabel.setText("Fahrenheit: " + String.format("%.2f", fahrenheit));
            kelvinLabel.setText("Kelvin: " + String.format("%.2f", kelvin));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
   public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}
