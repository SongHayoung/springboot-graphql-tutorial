package com.sumfi.graphql.resolver.webtoon;

import com.sumfi.graphql.dataloader.DataLoaderRegistryFactory;
import com.sumfi.graphql.domain.webtoon.WebToon;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class StarscoreResolver implements GraphQLResolver<WebToon> {
    public CompletableFuture<Double> starscore(WebToon webToon, DataFetchingEnvironment env) {
        log.info("Get Starscore Data load from id: {}", webToon.getId());

        DataLoader<UUID, Double> dataLoader = env.getDataLoader(DataLoaderRegistryFactory.STAR_SCORE_DATA_LOADER);

        return dataLoader.load(webToon.getId());
    }
}
