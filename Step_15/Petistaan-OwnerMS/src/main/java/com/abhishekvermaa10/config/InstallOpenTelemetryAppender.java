package com.abhishekvermaa10.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;
import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Component
public class InstallOpenTelemetryAppender implements InitializingBean {

	private final OpenTelemetry openTelemetry;

	@Override
	public void afterPropertiesSet() {
		OpenTelemetryAppender.install(this.openTelemetry);
	}

}
