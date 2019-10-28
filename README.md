# About this app

Webapp which estimates climate for Solar System composed of planets Vulcano, Betasoide and Ferengi. Full explanation in spanish can be found [here](./Ejercicio Planetas.pdf)

# How to Run

To run the app locally, execute `./mvnw spring-boot:run` on the root directory. It is required to have java installed and the JAVA_HOME environment variable set up to the JDK location.

# Exposed Endpoints

## GET /clima?dia=X

Obtain climate for day X. Possible values: `lluvia`, `sequia`, `optimo`, `otro`. X must be between 0 and 3650.

## GET /calculo

Obtain a JSON object with the following aggregate data from days 0 to 3650:

```json
{
  "dryPeriods": "Amount of periods with dry climate. Periods are defined as sucessions of consecutive days with same climate.",
  "rainyPeriods": "Amount of periods with rainy climate",
  "rainPeakDay": "Day with highest rain".
  "optimalPeriods": "Amount of periods with optimal climate"
}
```
