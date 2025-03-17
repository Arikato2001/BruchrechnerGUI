import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BruchGUI extends JFrame {
    private JTextField zaehler1Field, nenner1Field, zaehler2Field, nenner2Field;
    private JComboBox<String> operationBox;
    private JLabel resultLabel;

    public BruchGUI() {
        setTitle("Bruchrechner");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Hauptpanel mit GridBagLayout für flexible Anordnung
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Bruch 1
        gbc.gridx = 0; gbc.gridy = 0;
        zaehler1Field = new JTextField(5);
        panel.add(zaehler1Field, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        nenner1Field = new JTextField(5);
        panel.add(nenner1Field, gbc);

        // Operator
        gbc.gridx = 1; gbc.gridy = 1;
        String[] operations = {"+", "-", "*", "/"};
        operationBox = new JComboBox<>(operations);
        panel.add(operationBox, gbc);

        // Bruch 2
        gbc.gridx = 2; gbc.gridy = 0;
        zaehler2Field = new JTextField(5);
        panel.add(zaehler2Field, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        nenner2Field = new JTextField(5);
        panel.add(nenner2Field, gbc);

        // Berechnen-Button
        gbc.gridx = 1; gbc.gridy = 3;
        JButton berechnenButton = new JButton("Berechnen");
        berechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                berechnen();
            }
        });
        panel.add(berechnenButton, gbc);

        // Ergebnis-Anzeige
        gbc.gridx = 1; gbc.gridy = 4;
        resultLabel = new JLabel("Ergebnis: ");
        panel.add(resultLabel, gbc);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void berechnen() {
        try {
            int zaehler1 = Integer.parseInt(zaehler1Field.getText());
            int nenner1 = Integer.parseInt(nenner1Field.getText());
            int zaehler2 = Integer.parseInt(zaehler2Field.getText());
            int nenner2 = Integer.parseInt(nenner2Field.getText());

            // Fehlerbehandlung: Nenner darf nicht 0 sein
            if (nenner1 == 0 || nenner2 == 0) {
                resultLabel.setText("Nenner darf nicht 0 sein!");
                return; // Methode abbrechen, wenn ungültige Eingabe
            }

            Bruch bruch1 = new Bruch(zaehler1, nenner1);
            Bruch bruch2 = new Bruch(zaehler2, nenner2);
            Bruch result;

            switch ((String) operationBox.getSelectedItem()) {
                case "+": result = bruch1.add(bruch2); break;
                case "-": result = bruch1.sub(bruch2); break;
                case "*": result = bruch1.mul(bruch2); break;
                case "/": result = bruch1.div(bruch2); break;
                default: result = null;
            }

            resultLabel.setText("Ergebnis: " + result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Fehler: Bitte nur ganze Zahlen eingeben!");
        }
    }

    public static void main(String[] args) {
        new BruchGUI();
    }
}
