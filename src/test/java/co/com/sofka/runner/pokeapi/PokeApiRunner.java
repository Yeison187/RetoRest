package co.com.sofka.runner.pokeapi;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/pokeapi/pokemos.feature"},
        glue = {"co.com.sofka.stepsdefinitions.pokeapi"}
)

public class PokeApiRunner {
}
