package com.sumfi.graphql.resolver.webtoon;

import com.sumfi.graphql.domain.webtoon.Bgm;
import com.sumfi.graphql.domain.webtoon.WebToon;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Component
@RequiredArgsConstructor
public class BgmResolver implements GraphQLResolver<WebToon> {
    private final Executor webtoonTaskExecutor;

    public CompletableFuture<Bgm> bgm(WebToon webToon) {
        log.info("requesting bgm data for webtoon id: {}", webToon.getId());

        return CompletableFuture.supplyAsync(
                () -> {
                    log.info("getting bgms for webtoon id: {}", webToon.getId());

                    // ThreadUtils.ThreadStop(3);

                    return Bgm.builder().url("Bgm Url Resource").build();
                }, webtoonTaskExecutor);
    }
}
