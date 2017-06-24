package com.esolutions.jbehave;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.Stepdoc;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.jbehave.web.selenium.*;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.STATS;
import static org.jbehave.core.reporters.Format.XML;

/**
 * Created by Emanuel on 04/02/2016.
 */
public class QPlusStories extends JUnitStories {
    private SeleniumContext context = new SeleniumContext();
    private ContextView contextView = new LocalFrameContextView().sized(500, 100);
    private ApplicationContext appContext = new SpringApplicationContextFactory("qplus-steps.xml").createApplicationContext();

    public QPlusStories() {
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        CrossReference xref = new CrossReference().withJsonOnly();
        StoryReporterBuilder storyReporterBuilder = (StoryReporterBuilder) appContext.getBean("storyReporterBuilder");
        return new SeleniumConfiguration()
                .useSeleniumContext(context)
                .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStepdocReporter(new StepdocReporter() {
                    public void stepdocs(List<Stepdoc> list, List<Object> list1) {
                    }

                    public void stepdocsMatching(String s, List<Stepdoc> list, List<Object> list1) {
                    }
                })
                .useStoryReporterBuilder(storyReporterBuilder
                        .withCodeLocation(codeLocationFromClass(embeddableClass))
                        .withDefaultFormats()
                        .withFormats(STATS, CONSOLE, WebDriverHtmlOutput.WEB_DRIVER_HTML, XML)
                        .withCrossReference(xref));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), appContext);
    }

    protected List<String> metaFilters() {
        return asList(); // will be specified by values in the pom.xml file when run from Maven
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder()
                .findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/*.story"), null);
    }
}