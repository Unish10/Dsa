package questionnumber5;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

class TouristSpot {
    String name;
    int cost;
    int time;
    int interestScore;

    public TouristSpot(String name, int cost, int time, int interestScore) {
        this.name = name;
        this.cost = cost;
        this.time = time;
        this.interestScore = interestScore;
    }
}

public class Qno5a {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Qno5a().createGUI());
    }

    public void createGUI() {

        JFrame frame = new JFrame("Tourist Spot Optimizer");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField budgetField = new JTextField(10);
        JTextField timeField = new JTextField(10);
        JTextArea resultArea = new JTextArea(10, 30);

        JButton optimizeBtn = new JButton("Optimize Trip");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Budget:"));
        panel.add(budgetField);
        panel.add(new JLabel("Time Available:"));
        panel.add(timeField);
        panel.add(optimizeBtn);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        optimizeBtn.addActionListener(e -> {

            int budget = Integer.parseInt(budgetField.getText());
            int time = Integer.parseInt(timeField.getText());

            List<TouristSpot> spots = new ArrayList<>();
            spots.add(new TouristSpot("Museum", 50, 2, 8));
            spots.add(new TouristSpot("Beach", 30, 3, 9));
            spots.add(new TouristSpot("Park", 20, 1, 6));
            spots.add(new TouristSpot("Mall", 40, 2, 7));

            spots.sort((a, b) -> Double.compare(
                (double)b.interestScore / b.cost, 
                (double)a.interestScore / a.cost));

            int totalCost = 0;
            int totalTime = 0;

            resultArea.setText("Recommended Spots:\n");

            for (TouristSpot spot : spots) {

                if (totalCost + spot.cost <= budget &&
                        totalTime + spot.time <= time) {

                    resultArea.append(spot.name + "\n");
                    totalCost += spot.cost;
                    totalTime += spot.time;
                }
            }

            resultArea.append("\nTotal Cost: " + totalCost);
            resultArea.append("\nTotal Time: " + totalTime);
        });

        frame.setVisible(true);
    }
}