package hello.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolarSystem {
    private List<Planet> planets = new ArrayList<>();

    public SolarSystem(Planet ... planets) {
        this.planets.addAll(Arrays.asList(planets));
    }

    public void evolve(int days) {
        for(Planet p: planets) {
            p.evolve(days);
        }
    }
}
