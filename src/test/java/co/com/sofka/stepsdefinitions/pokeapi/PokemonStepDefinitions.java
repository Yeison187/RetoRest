package co.com.sofka.stepsdefinitions.pokeapi;

import co.com.sofka.models.pokemones.Result;
import co.com.sofka.questions.GetPokemones;
import co.com.sofka.questions.ResponseCode;
import co.com.sofka.tasks.GetUsers;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
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
                seeThat("El código de respuesta", ResponseCode.was(),equalTo(200))
        );
    }

    @Then("^se validara que en los datos de retorno se encuentre el pokemon (.+)$")
    public void validarInformacion (String pokemon){
        Result pokemones = new GetPokemones().answeredBy(actor).getResults().stream()
                .filter(x -> x.getName().equals("bulbasaur") ).findFirst().orElse(null);

        actor.should(
                seeThat("El nombre del pokemon no esta",act -> pokemones,notNullValue())
        );

        actor.should(
                seeThat("Nombre del pokemon", act -> pokemones.getName(),equalTo(pokemon))
        );
    }



}
