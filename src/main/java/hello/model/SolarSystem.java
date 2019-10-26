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
        for (Planet p : planets) {
            p.evolve(days);
        }
    }

    public double getArea() {
        return 0;
    }

    public Climate getClimate() {
        if(isDry()) {
            return Climate.sequia;
        }
        if (isRain()) {
            return Climate.lluvia;
        }
        if (isOptimal()) {
            return Climate.optimo;
        }
        return Climate.otro;

    }

    private boolean isDry() {
        return planets.stream().map(planet -> planet.getAngle()%180).distinct().count() <= 1;
    }

    private boolean isRain() {
        return true;
    }

    private boolean isOptimal() {
        return true;
    }

}
