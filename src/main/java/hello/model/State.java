package hello.model;

public class State {

    private final int dia;
    private final Climate clima;

    public State(int dia, Climate clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public int getDia() {
        return dia;
    }

    public Climate getClima() {
        return clima;
    }


}
