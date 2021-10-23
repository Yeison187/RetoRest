package co.com.sofka.stepsdefinitions.pokeapi;

import co.com.sofka.models.pokemones.Result;
import co.com.sofka.questions.GetPokemones;
import co.com.sofka.questions.ResponseCode;
import co.com.sofka.tasks.GetUsers;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;




public class PokemonStepDefinitions {

    private static final String restApiUrl = "https://pokeapi.co/api";
    Actor actor;

    @Given("que el usuario necesita contultar que pokemones estan registrados, se hace la petición")
    public void consumirApi(){
        actor = Actor.named("Yeison")
                .whoCan(CallAnApi.at(restApiUrl));
        actor.attemptsTo(
                GetUsers.fromPage("/pokemon")
        );
    }

    @When("se valide que el código de respuesta exitoso")
    public void validarCodigoRespuesta(){
        actor.should(
                seeThat("El código de respuesta", ResponseCode.was(),equalTo(SC_OK))
        );
    }

    @Then("^se validara que en los datos de retorno se encuentre el pokemon (.+)$")
    public void validarInformacion (String pokemon){
        Result pokemones = new GetPokemones().answeredBy(actor).getResults().stream()
                .filter(x -> x.getName().equals(pokemon) ).findFirst().orElse(null);

        actor.should(
                seeThat("La respuesta ",act -> pokemones,notNullValue())
        );

        actor.should(
                seeThat("Nombre del pokemon", act -> pokemones.getName(),equalTo(pokemon))
        );
    }

    @Then("^se validara que en los datos de retorno no encuentre el pokemon (.+)$")
    public void validar (String pokemon){
        Result pokemones = new GetPokemones().answeredBy(actor).getResults().stream()
                .filter(x -> x.getName().equals(pokemon) ).findFirst().orElse(null);

        actor.should(
                seeThat("La respuesta ",act -> pokemones,notNullValue())
        );

        actor.should(
                seeThat("Nombre del pokemon", act -> pokemones.getName(),equalTo(pokemon))
        );
    }

    @Given("que el usuario realiza una petición no valida")
    public void peticionInvalida(){
        actor = Actor.named("Yeison")
                .whoCan(CallAnApi.at(restApiUrl));
        actor.attemptsTo(
                GetUsers.fromPage("/pokemonzs")
        );

    }

    @Then("el codigo de respuesta debe ser el de no encontrado")
    public void notFound(){
        actor.should(
                seeThat("El código de respuesta", ResponseCode.was(),equalTo(SC_NOT_FOUND))
        );
    }



}
