package ui;

import javax.swing.*;
import java.awt.*;

// constructs the title of the application
public class Title extends JPanel {

    public Title() {
        this.setPreferredSize(new Dimension(900, 30));
        this.setBackground(Color.cyan);

        JLabel title = new JLabel("Fish Manager");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(title);
    }
}

