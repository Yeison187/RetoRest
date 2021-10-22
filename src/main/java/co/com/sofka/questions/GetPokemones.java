package co.com.sofka.questions;

import co.com.sofka.models.pokemones.Pokemones;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetPokemones implements Question {
    @Override
    public Pokemones answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Pokemones.class);
    }
}
