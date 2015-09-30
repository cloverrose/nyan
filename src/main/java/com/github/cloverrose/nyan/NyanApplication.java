package com.github.cloverrose.nyan;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.github.cloverrose.nyan.resources.NyanResource;
import com.github.cloverrose.nyan.health.TemplateHealthCheck;

public class NyanApplication extends Application<NyanConfiguration> {
    public static void main(String[] args) throws Exception {
        new NyanApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<NyanConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(NyanConfiguration configuration,
                    Environment environment) {
	final NyanResource resource = new NyanResource(
	    configuration.getTemplate(),
	    configuration.getDefaultName()
        );
	environment.jersey().register(resource);

	final TemplateHealthCheck healthCheck =
	    new TemplateHealthCheck(configuration.getTemplate());
	environment.healthChecks().register("template", healthCheck);
    }
}
