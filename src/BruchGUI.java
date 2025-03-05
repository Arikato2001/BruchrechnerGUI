import javax.swing.*;
import java.awt.*;

public class BruchGUI extends JFrame {
    private JTextField zaehler1Field, nenner1Field, zaehler2Field, nenner2Field;
    private JComboBox<String> operationBox;
    private JLabel resultLabel;

    public BruchGUI() {
        setTitle("Bruchrechner");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(new GridLayout(6, 2));  // 6 Zeilen für die Labels und Eingabefelder

        // Labels und Eingabefelder
        content.add(new JLabel("Zähler 1:"));
        zaehler1Field = new JTextField();
        content.add(zaehler1Field);

        content.add(new JLabel("Nenner 1:"));
        nenner1Field = new JTextField();
        content.add(nenner1Field);

        content.add(new JLabel("Zähler 2:"));
        zaehler2Field = new JTextField();
        content.add(zaehler2Field);

        content.add(new JLabel("Nenner 2:"));
        nenner2Field = new JTextField();
        content.add(nenner2Field);

        // Dropdown für Operationen
        content.add(new JLabel("Operation:"));
        String[] operations = {"+", "-", "*", "/"};
        operationBox = new JComboBox<>(operations);
        content.add(operationBox);

        // Berechnen Button
        JButton berechnenButton = new JButton("Berechnen");
        berechnenButton.addActionListener(e -> berechnen()); // Direkt auf Methode verweisen
        content.add(berechnenButton);

        // Ergebnislabel
        resultLabel = new JLabel();
        content.add(resultLabel);


        setContentPane(content);
        setVisible(true);
    }

    private void berechnen() {
        try {
            int zaehler1 = Integer.parseInt(zaehler1Field.getText());
            int nenner1 = Integer.parseInt(nenner1Field.getText());
            int zaehler2 = Integer.parseInt(zaehler2Field.getText());
            int nenner2 = Integer.parseInt(nenner2Field.getText());

            Bruch bruch1 = new Bruch(zaehler1, nenner1);
            Bruch bruch2 = new Bruch(zaehler2, nenner2);
            Bruch result;

            switch ((String) operationBox.getSelectedItem()) {
                case "+":
                    result = bruch1.add(bruch2);
                    break;
                case "-":
                    result = bruch1.sub(bruch2);
                    break;
                case "*":
                    result = bruch1.mul(bruch2);
                    break;
                case "/":
                    result = bruch1.div(bruch2);
                    break;
                default:
                    result = null;
            }

            resultLabel.setText("Ergebnis gekürzt: " + result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Bitte gültige Zahlen eingeben!");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new BruchGUI();
    }
}
