package ui;

import javax.swing.*;
import java.awt.*;

// constructs the pop-up window to add a fish
public class NewFish {
    JFrame frame;
    JPanel panel;
    JTextField species;
    JTextField weight;
    JLabel speciesLabel;
    JLabel weightLabel;

    // EFFECTS: creates the pop-up window for inputting data when adding a new fish
    public NewFish() {
        initializeFields();

        panel.add(speciesLabel);
        panel.add(species);
        panel.add(weightLabel);
        panel.add(weight);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(300,200));
    }

    // EFFECTS: initializes the fields for adding a new fish
    private void initializeFields() {
        frame = new JFrame("New Fish");
        panel = new JPanel();

        speciesLabel = new JLabel("Enter the species of the fish!");
        weightLabel = new JLabel("Enter the weight of the fish!");

        species = new JTextField();
        species.setMaximumSize(new Dimension(300, 25));
        weight = new JTextField();
        weight.setMaximumSize(new Dimension(300, 25));
    }

    //  EFFECTS: returns the panel
    public JPanel returnJPanel() {
        return panel;
    }

    // EFFECTS: retrieves the species of the fish that has been entered
    public String getSpecies() {
        return species.getText();
    }

    // EFFECTS: parses the given string of the weight into an integer
    public int getWeight() {
        return Integer.parseInt(weight.getText());
    }
}

