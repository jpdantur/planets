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

    /**
     *
     * @param planets
     * Creates a solar system with three planets
     */
    SolarSystemImpl(Planet ... planets) {
        if (planets.length != 3)
            throw new IllegalArgumentException("Must have 3 planets");
        planet1 = planets[0];
        planet2 = planets[1];
        planet3 = planets[2];
    }

    /**
     *
     * @return Object with amount of dry periods, rainy periods, day with highest rain and optimal periods
     */
    public SystemData getData() {
        return new SystemData(dryPeriods,rainyPeriods,rainPeakDay,optimalPeriods);
    }

    /**
     * Evolves the status of the solar system by one day
     * @return The climate of the current status
     */
    public Climate getClimateAndEvolve() {
        Climate climate = getClimate();
        planet1.evolve(1);
        planet2.evolve(1);
        planet3.evolve(1);
        currentDay ++;
        currentClimate = climate;
        return climate;
    }

    /**
     *
     * @param planetsTriangle A triangle with the planets in the solar system
     * @return the Rain Power of the solar system, calculated using the area of the triangle
     */
    private double getRainPower(Triangle2D planetsTriangle) {
        return planetsTriangle.getArea();
    }


    /**
     * Update amount of dry periods, rain periods and optimal periods. Update day with rain peak.
     * Updates current climate status
     * @return The climate of the current status of the solar system.
     *
     */
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

    /**
     * Check if solar system has dry climate. This happens when planets are aligned with the sun.
     * @return true if solar system has dry climate
     */
    private boolean isDry() {
        return planet1.getAngle() % 180 == planet2.getAngle() % 180 &&
                planet2.getAngle() % 180 == planet3.getAngle() % 180;
    }

    /**
     *
     * @return A triangle with the three planets cartesian coordinates
     */
    private Triangle2D getPlanetsTriangle() {
        return new Triangle2D(planet1.toCartesian(),planet2.toCartesian(),planet3.toCartesian());
    }

    /**
     *
     * @param planetsTriangle The three planets in a triangle with cartesian coordinates
     * @return true if solar system has rain climate.
     * This happens when the triangle formed by the three planets contains the sun (located in the origin)
     *
     */
    private boolean isRain(Triangle2D planetsTriangle) {
        return planetsTriangle.contains(new Point2D.Double(0.0,0.0));
    }

    /**
     *
     * @param planetsTriangle The three planets in a triangle with cartesian coordinates
     * @return true when the solar system has optimal climate. This happens when the the three planets form a straight line.
     */
    private boolean isOptimal(Triangle2D planetsTriangle) {
        return planetsTriangle.isAligned();
    }
}
