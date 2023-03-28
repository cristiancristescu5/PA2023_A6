package org.example;

import java.util.stream.IntStream;
import javax.swing.*;
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox linesCombo;
    JButton createButton;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));
        linesLabel = new JLabel("Number of dots: ");
        String [] numbers = new String[101];
        IntStream.range(1,101).forEach(i -> numbers[i-1] = Float.toString((float) (i-1)/100));
        linesCombo = new JComboBox<>(numbers);
        createButton = new JButton("Create new game");

        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);

    }
}