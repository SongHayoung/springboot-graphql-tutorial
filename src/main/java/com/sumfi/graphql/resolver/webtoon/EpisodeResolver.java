package com.sumfi.graphql.resolver.webtoon;

import com.sumfi.graphql.bo.EpisodeBO;
import com.sumfi.graphql.dataloader.DataLoaderRegistryFactory;
import com.sumfi.graphql.domain.webtoon.Episode;
import com.sumfi.graphql.domain.webtoon.WebToon;
import com.sumfi.graphql.model.Utils.Pair;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EpisodeResolver implements GraphQLResolver<WebToon> {
    private final EpisodeBO episodeBO;

    public CompletableFuture<List<Episode>> episodes(WebToon webToon, List<Integer> episodeNos, DataFetchingEnvironment env) {
        log.info("request episode data for webToon id : {}", webToon.getId());
        log.info("episode no of requests : {}", episodeNos.stream().map(Object::toString).collect(Collectors.joining(", ")));

        DataLoader<UUID, List<Episode>> dataLoader = env.getDataLoader(DataLoaderRegistryFactory.EPISODE_DATA_LOADER);

        return dataLoader.load(webToon.getId(), episodeNos);
    }
}
