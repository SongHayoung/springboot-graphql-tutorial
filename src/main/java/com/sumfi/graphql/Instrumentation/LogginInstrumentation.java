package com.sumfi.graphql.Instrumentation;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class LogginInstrumentation extends SimpleInstrumentation {
    public static String CORRELATION_ID = "correlation_id";
    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters instrumentationExecutionParameters) {
        MDC.put(CORRELATION_ID, instrumentationExecutionParameters.getExecutionInput().getExecutionId().toString());

        log.info("Request received query : {} variables : {}", instrumentationExecutionParameters.getQuery(), instrumentationExecutionParameters.getVariables());

        return SimpleInstrumentationContext.whenCompleted((executionResult, throwable) -> {
            if (Objects.isNull(throwable)) {
                log.info("Request completed successful");
            } else {
                log.error("Request failed cause: {}", throwable.getMessage());
            }
            MDC.clear();
        });
    }
}
