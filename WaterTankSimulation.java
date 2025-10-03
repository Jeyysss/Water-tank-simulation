import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WaterTankSimulation {
    public static void main(String[] args) {
        // Simulation parameters
        double startTemp = 25.0;    // Starting temperature
        double targetTemp = 80.0;   // Target temperature
        double heatingRate = 1.5;   // °C per minute
        double coolingFactor = 0.2; // Heat loss per minute
        int timeSteps = 60;         // Total time steps (minutes)

        double[] temperatures = new double[timeSteps + 1];
        temperatures[0] = startTemp;

        Random rand = new Random();

        // Simulate temperature over time
        for (int t = 1; t <= timeSteps; t++) {
            double noise = rand.nextGaussian() * 0.2; // small random variation
            double newTemp = temperatures[t - 1] + heatingRate - coolingFactor + noise;

            if (newTemp > targetTemp) {
                newTemp = targetTemp;
            }

            temperatures[t] = newTemp;
        }

        // Save data to CSV
        try {
            FileWriter csvWriter = new FileWriter("water_tank_temperature.csv");
            csvWriter.append("Time(min),Temperature(C)\n");
            for (int t = 0; t <= timeSteps; t++) {
                csvWriter.append(t + "," + String.format("%.2f", temperatures[t]) + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Optional: Print temperatures in console
        System.out.println("Time(min)\tTemperature(C)");
        for (int t = 0; t <= timeSteps; t++) {
            System.out.println(t + "\t\t" + String.format("%.2f", temperatures[t]));
        }
    }
}
