package com.sumfi.graphql.resolver.webtoon;

import com.sumfi.graphql.domain.webtoon.Image;
import com.sumfi.graphql.domain.webtoon.WebToon;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageResolver implements GraphQLResolver<WebToon> {
    private final Executor webtoonTaskExecutor;

    public CompletableFuture<DataFetcherResult<Image>> image(WebToon webToon, DataFetchingEnvironment env) {
        log.info("requesting image data for webtoon id: {}", webToon.getId());

        /*
        throw new WebToonException(String.format("No Images on webtoon title : %s", webToon.getTitle()), WebToonErrorCode.NO_IMAGE);
         */

        return CompletableFuture.supplyAsync(() -> {
            Image image = env.getLocalContext();

            log.info("get image data from local context. thumbnail : {}", image.getThumbnail());

            // ThreadUtils.ThreadStop(3);

            return DataFetcherResult.<Image>newResult()
                    .data(image)
                    .build();
        }, webtoonTaskExecutor);
    }
}
