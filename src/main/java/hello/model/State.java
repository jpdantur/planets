package hello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class State {

    private final int dia;
    private final Climate clima;
    @JsonIgnore private final double area;

    public State(int dia, Climate clima, double area) {
        this.dia = dia;
        this.clima = clima;
        this.area = area;
    }

    public int getDia() {
        return dia;
    }

    public double getArea() {
        return area;
    }

    public Climate getClima() {
        return clima;
    }


}
