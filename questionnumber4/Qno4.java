package questionnumber4;

import java.util.*;

public class Qno4 {

    public static class EnergySource {
        String name;
        int capacity;
        double costPerUnit;
        boolean renewable;

        public EnergySource(String name, int capacity, double costPerUnit, boolean renewable) {
            this.name = name;
            this.capacity = capacity;
            this.costPerUnit = costPerUnit;
            this.renewable = renewable;
        }
    }

    public static void main(String[] args) {

        
        List<EnergySource> sources = new ArrayList<>();
        sources.add(new EnergySource("Solar", 50, 2.0, true));
        sources.add(new EnergySource("Hydro", 40, 3.0, true));
        sources.add(new EnergySource("Diesel", 100, 6.0, false));

        
        Map<String, Integer> demand = new HashMap<>();
        demand.put("DistrictA", 60);
        demand.put("DistrictB", 30);

        allocateEnergy(sources, demand);
    }

    public static void allocateEnergy(List<EnergySource> sources, Map<String, Integer> demand) {

    
        sources.sort(Comparator.comparingDouble(s -> s.costPerUnit));

        double totalCost = 0;
        int renewableUsed = 0;
        int totalEnergyUsed = 0;

        System.out.println("----- Energy Allocation Report -----");

        for (String district : demand.keySet()) {

            int remaining = demand.get(district);

            System.out.println("\nDistrict: " + district);
            System.out.println("Demand: " + remaining);

            for (EnergySource source : sources) {

                if (remaining <= 0)
                    break;

                int energyUsed = Math.min(source.capacity, remaining);
                source.capacity -= energyUsed;  // Reduce available capacity
                remaining -= energyUsed;

                double cost = energyUsed * source.costPerUnit;
                totalCost += cost;
                totalEnergyUsed += energyUsed;

                if (source.renewable)
                    renewableUsed += energyUsed;

                System.out.println("Used " + energyUsed + " from " + source.name +
                        " | Cost: " + cost);
            }

            if (remaining > 0) {
                System.out.println("WARNING: Demand not fully satisfied!");
            }
        }

        double renewablePercentage = (renewableUsed * 100.0) / totalEnergyUsed;

        System.out.println("\n----- Summary -----");
        System.out.println("Total Energy Used: " + totalEnergyUsed);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Renewable Energy %: " + renewablePercentage + "%");
    }
}

