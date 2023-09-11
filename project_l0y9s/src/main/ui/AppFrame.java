package ui;

import model.Event;
import model.EventLog;
import model.Fish;
import model.Ship;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// creates the main frame for the application
public class AppFrame extends JFrame {
    private static final String JSON_FILE = "./data/ship.json";
    private Ship ship;
    private Title title;

    private JLabel totalWeight;

    private DefaultListModel<String> fishListModel;
    private JList<String> fishjlist;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ImageIcon gambar;

    public AppFrame(String judul) {
        super(judul);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 800);
        setVisible(true);
        centreOnScreen();
        setResizable(false);

        initializeBase();
        initializeTop();
        initializeButtons();
        setupFishPanel();
        this.revalidate();

        title = new Title();
        this.add(title, BorderLayout.NORTH);
        printLogOnExit();
    }

    private void printLogOnExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                printLog(EventLog.getInstance());
            }
        });
    }

    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes top pane for ship's name and current total weight
    private void initializeTop() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel namePane = initializeNamePanel();
        JPanel weightPane = initializeWeightPanel();

        headerPanel.add(namePane);
        headerPanel.add(Box.createHorizontalStrut(50));
        headerPanel.add(weightPane);
        headerPanel.setBackground(new Color(128, 255, 255));

        add(headerPanel, BorderLayout.PAGE_START);
    }

    // MODIFIES: this
    // EFFECTS: initializes main model and data fields
    private void initializeBase() {
        jsonReader = new JsonReader(JSON_FILE);
        jsonWriter = new JsonWriter(JSON_FILE);
        ship = new Ship("Serba Prima");
        gambar = new ImageIcon("./data/Tuna1.png");
        fishListModel = new DefaultListModel<>();
    }

    // MODIFIES: this
    // EFFECTS: initializes the name of the ship on the top
    private JPanel initializeNamePanel() {
        JLabel name = new JLabel("Serba Prima");
        JPanel namePanel = new JPanel();
        namePanel.add(name);
        namePanel.setPreferredSize(new Dimension(300, 30));
        namePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return namePanel;
    }

    // MODIFIES: this
    // EFFECTS: initializes weight panel in top border
    private JPanel initializeWeightPanel() {
        totalWeight = new JLabel("Total Weight: " + ship.getCurrentWeight());
        JPanel weightPanel = new JPanel();
        weightPanel.add(totalWeight);
        weightPanel.setPreferredSize(new Dimension(200, 30));
        weightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return weightPanel;
    }

    // MODIFIES: this
    // EFFECTS: setup configurations for fish panels
    private void setupFishPanel() {
        fishjlist = new JList<>(fishListModel);
        fishjlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fishjlist.setSelectedIndex(0);

        JPanel fishPanel = new JPanel();
        fishPanel.add(fishjlist);
        fishPanel.setPreferredSize(new Dimension(700, 800));
        fishPanel.setBackground(new Color(230, 255, 255));
        GridLayout layout = new GridLayout(1, 40);
        fishPanel.setLayout(layout);

        add(fishPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons for actions
    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addFish = new JButton("Add Fish");
        addFish.setActionCommand("Add");
        addFish.addActionListener(new ButtonReaction());

        JButton clearAll = new JButton("Clear All Fish");
        clearAll.setActionCommand("Clear");
        clearAll.addActionListener(new ButtonReaction());

        JButton saveShip = new JButton("Save Ship");
        saveShip.setActionCommand("Save");
        saveShip.addActionListener(new ButtonReaction());

        JButton loadShip = new JButton("Load Ship");
        loadShip.setActionCommand("Load");
        loadShip.addActionListener(new ButtonReaction());

        buttonPanel.add(addFish);
        buttonPanel.add(clearAll);
        buttonPanel.add(saveShip);
        buttonPanel.add(loadShip);
        buttonPanel.setBackground(new Color(128, 255, 255));
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    // EFFECTS: updates list of fish and total current weight of ship on screen
    public void updateAll() {
        fishListModel.clear();
        List<Fish> caughtFish = ship.getCaughtFish();
        for (Fish fish : caughtFish) {
            fishListModel.addElement(fish.getSpecies() + " " + fish.getWeight());
        }
        updateTotalWeight();
    }

    // EFFECTS: updates the total current weight of the ship on the top panel
    private void updateTotalWeight() {
        int currentWeight = ship.getCurrentWeight();
        totalWeight.setText("Total Weight: " + currentWeight);
    }

    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    private class ButtonReaction implements ActionListener {

        // EFFECTS: processes button clicks and runs appropriate methods
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Add":
                    addFishOption();
                    break;
                case "Clear":
                    clearAllOption();
                    break;
                case "Save":
                    saveOption();
                    break;
                default:
                    loadOption();
            }
        }

        // MODIFIES: this, ship
        // EFFECTS: creates a new window to input info for new fish, adds the fish and updates the screen
        private void addFishOption() {
            NewFish newFishPopUp = new NewFish();
            JPanel panel = newFishPopUp.returnJPanel();

            int optionPaneValue = JOptionPane.showConfirmDialog(null,
                    panel,
                    "Nice Fish Bro",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, gambar);

            if (optionPaneValue == JOptionPane.OK_OPTION) {
                String species = newFishPopUp.getSpecies();
                int weight = newFishPopUp.getWeight();
                Fish newFish = new Fish(species, weight);
                if (ship.addWeight(newFish)) {
                    ship.addAFish(newFish);
                }
                updateAll();
            }
        }

        // MODIFIES: this, ship
        // EFFECTS: Deletes all fish from ship and updates screen
        private void clearAllOption() {
            ship.removeAllWeight();
            ship.removeAllFish();
            updateAll();
        }

        // EFFECTS: saves the ship to file
        private void saveOption() {
            try {
                jsonWriter.open();
                jsonWriter.write(ship);
                jsonWriter.close();
                System.out.println("Saved " + ship.getName() + " to " + JSON_FILE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_FILE);
            }
        }

        // MODIFIES: this
        // EFFECTS: loads ship from file
        private void loadOption() {
            try {
                ship = jsonReader.read();
                updateAll();
                System.out.println("Loaded " + ship.getName() + " from " + JSON_FILE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_FILE);
            }
        }
    }
}

