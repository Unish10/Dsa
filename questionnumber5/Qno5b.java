package questionnumber5;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Qno5b {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Qno5b().createGUI());
    }

    public void createGUI() {

        JFrame frame = new JFrame("Weather Fetch Simulation");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea resultArea = new JTextArea(12, 30);
        JButton sequentialBtn = new JButton("Fetch Sequentially");
        JButton parallelBtn = new JButton("Fetch in Parallel");

        JPanel panel = new JPanel();
        panel.add(sequentialBtn);
        panel.add(parallelBtn);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        List<String> cities = List.of("Kathmandu", "Pokhara", "Lalitpur", "Biratnagar");

        sequentialBtn.addActionListener(e -> {
            resultArea.setText("");
            long start = System.currentTimeMillis();

            for (String city : cities) {
                fetchWeather(city, resultArea);
            }

            long end = System.currentTimeMillis();
            resultArea.append("\nSequential Time: " + (end - start) + " ms");
        });

        parallelBtn.addActionListener(e -> {
            resultArea.setText("");
            long start = System.currentTimeMillis();

            List<Thread> threads = new ArrayList<>();

            for (String city : cities) {
                Thread t = new Thread(() -> fetchWeather(city, resultArea));
                threads.add(t);
                t.start();
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted: " + ex.getMessage());
                }
            }

            long end = System.currentTimeMillis();
            resultArea.append("\nParallel Time: " + (end - start) + " ms");
        });

        frame.setVisible(true);
    }

    private void fetchWeather(String city, JTextArea area) {
        try {
            Thread.sleep(1000); 
            SwingUtilities.invokeLater(() ->
                    area.append(city + ": 25°C, Clear Sky\n"));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Weather fetch interrupted for " + city);
        }
    }
}
