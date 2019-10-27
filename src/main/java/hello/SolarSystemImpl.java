package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.interfaces.SolarSystem;
import hello.model.Climate;
import hello.model.Planet;
import hello.model.SystemData;
import hello.util.Triangle2D;

import java.awt.geom.Point2D;

@JsonIgnoreProperties({"planet1","planet2","planet3","rainPeak","currentDay"})
public class SolarSystemImpl implements SolarSystem {

    private Planet planet1;
    private Planet planet2;
    private Planet planet3;
    private int dryPeriods = 0;
    private int rainyPeriods = 0;
    private double rainPeak = 0;
    private int currentDay = 0;
    private int rainPeakDay = 0;
    private int optimalPeriods = 0;
    private Climate currentClimate = Climate.otro;

    SolarSystemImpl(Planet ... planets) {
        if (planets.length != 3)
            throw new IllegalArgumentException("Must have 3 planets");
        planet1 = planets[0];
        planet2 = planets[1];
        planet3 = planets[2];
    }

    private double getRainPower(Triangle2D planetsTriangle) {
        return planetsTriangle.getArea();
    }
    public SystemData getData() {
        return new SystemData(dryPeriods,rainyPeriods,rainPeakDay,optimalPeriods);
    }
    public Climate getClimateAndEvolve() {
        Climate climate = getClimate();
        planet1.evolve(1);
        planet2.evolve(1);
        planet3.evolve(1);
        currentDay ++;
        currentClimate = climate;
        return climate;
    }

    private Climate getClimate() {
        if(isDry()) {
            if (currentClimate != Climate.sequia)
                dryPeriods++;
            return Climate.sequia;
        }
        Triangle2D planetsTriangle = getPlanetsTriangle();
        if (isRain(planetsTriangle)) {
            if (currentClimate != Climate.lluvia)
                rainyPeriods++;
            double rainPower = getRainPower(planetsTriangle);
            if (rainPower > rainPeak) {
                rainPeak = rainPower;
                rainPeakDay = currentDay;
            }
            return Climate.lluvia;
        }
        if (isOptimal(planetsTriangle)) {
            if (currentClimate != Climate.optimo)
                optimalPeriods++;
            return Climate.optimo;
        }
        return Climate.otro;

    }

    private boolean isDry() {
        return planet1.getAngle() % 180 == planet2.getAngle() % 180 &&
                planet2.getAngle() % 180 == planet3.getAngle() % 180;
    }

    private Triangle2D getPlanetsTriangle() {
        return new Triangle2D(planet1.toCartesian(),planet2.toCartesian(),planet3.toCartesian());
    }
    private boolean isRain(Triangle2D planetsTriangle) {
        return planetsTriangle.contains(new Point2D.Double(0.0,0.0));
    }

    private boolean isOptimal(Triangle2D planetsTriangle) {
        return planetsTriangle.isAligned();
    }
}
