package jbehave.runner;


import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import jbehave.converters.ZonedDateTimeJsonConverter;
import jbehave.converters.ZonedDateTimeJsonConverter2;
import jbehave.steps.ZaparaSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitReportingRunner.class)
public class ZaparaRunner extends JUnitStories {

    private Configuration configuration;

    public ZaparaRunner() {
        super();
        configuration = new MostUsefulConfiguration()
                .useParameterConverters(new ParameterConverters().addConverters(new ZonedDateTimeJsonConverter2()));
        configuredEmbedder().embedderControls().doVerboseFailures(true);
        configuredEmbedder().useMetaFilters(Arrays.asList("-skip"));

        System.out.println("instantiating runner");
    }

    @Override
    public Configuration configuration() {
        return configuration;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new ZaparaSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("stories/zapara.story");
    }


}
