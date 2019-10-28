package hello.model;

import java.awt.geom.Point2D;

public class Planet {
    private int radius;
    private int angle = 90;
    private int speed;
    private boolean clockwise;

    public Planet(int radius, int speed, boolean clockwise){
        this.radius = radius;
        this.speed = speed;
        this.clockwise = clockwise;
    }


    /**
     *
     * @param days Amount of days to evolve the planet
     * Evolves the planet status based on the speed and the direction
     */
    public void evolve(int days) {
        angle += days * speed * (clockwise ? -1 : 1);
        angle = Math.floorMod(angle, 360);
    }
    public int getAngle() {
        return angle;
    }

    /**
     *
     * @return A point with the cartesian coordinates of the planet
     */
    public Point2D toCartesian() {
        return new Point2D.Double(radius*Math.cos(angle*Math.PI/180), radius*Math.sin(angle*Math.PI/180));
    }
}
