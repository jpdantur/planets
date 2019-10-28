package hello;

import java.util.ArrayList;
import java.util.List;

import hello.interfaces.SolarSystem;
import hello.model.Climate;
import hello.model.Planet;
import hello.model.State;
import hello.model.SystemData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class SolarSystemController {

    private List<State> systemStates = new ArrayList<>();
    private static final int TEN_YEARS = 3650;
    private SolarSystem system;

    @RequestMapping("/clima")
    public State getClimate(@RequestParam(value="dia") String dia) {
        if (Integer.parseInt(dia) < 0 || Integer.parseInt(dia) > TEN_YEARS) {
            throw new YearOutOfRangeException();
        }
        return systemStates.get(Integer.parseInt(dia));
    }

    @RequestMapping("/calculo")
    public SystemData getSolarSystemData(){
        return system.getData();
    }


    @PostConstruct
    public void calculate() {
        Planet ferengi = new Planet(500,1,true);
        Planet betasoide = new Planet(2000,3,true);
        Planet vulcano = new Planet(1000,5,false);
        system = new SolarSystemImpl(ferengi,betasoide,vulcano);
        for (int i = 0; i< TEN_YEARS; i++) {
            Climate climate = system.getClimateAndEvolve();
            systemStates.add(new State(i,climate));
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Dia tiene que estar entre 0 y " + (TEN_YEARS - 1))
    public static class YearOutOfRangeException extends IllegalArgumentException {
        YearOutOfRangeException(){}
    }
}
