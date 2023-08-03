package com.kainos.ea;

import com.kainos.ea.controller.AuthController;
import com.kainos.ea.controller.CapabilityController;
import com.kainos.ea.controller.JobRolesController;
import io.dropwizard.Application;
import io.dropwizard.bundles.redirect.HttpsRedirect;
import io.dropwizard.bundles.redirect.PathRedirect;
import io.dropwizard.bundles.redirect.RedirectBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * The main application class for the web service.
 */
public class WebServiceApplication extends Application<WebServiceConfiguration> {

  public static void main(final String[] args) throws Exception {
    new WebServiceApplication().run(args);
  }

  @Override
  public String getName() {
    return "WebService";
  }

  @Override
  public void initialize(final Bootstrap<WebServiceConfiguration> bootstrap) {
    bootstrap.addBundle(new SwaggerBundle<WebServiceConfiguration>() {
        @Override
        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
              WebServiceConfiguration configuration
        ) {
            return configuration.getSwagger();
        }
    });
  }

  @Override
  public void run(final WebServiceConfiguration configuration,
                  final Environment environment) {
    environment.jersey().register(new JobRolesController());
    environment.jersey().register(new CapabilityController());
    environment.jersey().register(new AuthController());
  }
}
