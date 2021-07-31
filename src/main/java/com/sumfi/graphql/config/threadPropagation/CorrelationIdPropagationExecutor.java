package com.sumfi.graphql.config.threadPropagation;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.util.concurrent.Executor;

import static com.sumfi.graphql.Instrumentation.LogginInstrumentation.CORRELATION_ID;

/**
 * @Author Sumfi
 *
 * logback MDC 전파를 위한 wrapper executor
 * delegate로 CORRELATION_ID를 하위 thread에 전파해서 로깅하도록 설정함
 */
@RequiredArgsConstructor
public class CorrelationIdPropagationExecutor implements Executor {
    private final Executor delegate;

    public static Executor wrap(Executor executor) {
        return new CorrelationIdPropagationExecutor(executor);
    }

    @Override
    public void execute(@NotNull Runnable command) {
        String id = MDC.get(CORRELATION_ID);

        delegate.execute(() -> {
            try {
                MDC.put(CORRELATION_ID, id);
                command.run();
            } finally {
                MDC.remove(CORRELATION_ID);
            }
        });
    }
}
