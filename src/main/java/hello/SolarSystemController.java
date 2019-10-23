package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import hello.model.Planet;
import hello.model.State;
import hello.model.SolarSystem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class SolarSystemController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private SolarSystem system;

    @RequestMapping("/clima")
    public State getClimate(@RequestParam(value="dia", defaultValue="3650") String dia) {
        return new State(Integer.parseInt(dia),"lluvia");
    }

    @PostConstruct
    public void calculate() {
        Planet ferengi = new Planet(500,1,true);
        Planet betasoide = new Planet(2000,3,true);
        Planet vulcano = new Planet(1000,5,false);
        system = new SolarSystem(ferengi,betasoide,vulcano);
    }
}
