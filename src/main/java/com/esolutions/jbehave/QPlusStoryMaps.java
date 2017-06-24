package com.esolutions.jbehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStoryMaps;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.WebDriverHtmlOutput;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.STATS;
import static org.jbehave.core.reporters.Format.XML;

/**
 * Created by Emanuel on 04/02/2016.
 */
public class QPlusStoryMaps extends JUnitStoryMaps {
    public QPlusStoryMaps() {
        configuredEmbedder().useMetaFilters(metaFilters());
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(false))
                .useStoryParser(new RegexStoryParser(new ExamplesTableFactory(new LoadFromClasspath(this.getClass()))))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
                        .withFailureTrace(true)
                        .withFailureTraceCompression(true)
                        .withFormats(STATS, CONSOLE, WebDriverHtmlOutput.WEB_DRIVER_HTML, XML));
    }

    @Override
    protected List<String> metaFilters() {
        return asList(); // will be specified by values in the pom.xml file when run from Maven
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/stories/*.story", "");
    }
}