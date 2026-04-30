package com.abhishekvermaa10.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;

/**
 * @author abhishekvermaa10
 */
@Component
public class InstallOpenTelemetryAppender implements InitializingBean {

	private final OpenTelemetry openTelemetry;
	
	public InstallOpenTelemetryAppender(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }

	@Override
	public void afterPropertiesSet() {
		OpenTelemetryAppender.install(this.openTelemetry);
	}

}
