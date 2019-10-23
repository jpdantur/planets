package hello.model;

public class State {

    private final long dia;
    private final String clima;

    public State(long dia, String clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public long getDia() {
        return dia;
    }

    public String getClima() {
        return clima;
    }
}
