package hello.interfaces;

import hello.model.Climate;
import hello.model.SystemData;

public interface SolarSystem {
    SystemData getData();
    Climate getClimateAndEvolve();
}
