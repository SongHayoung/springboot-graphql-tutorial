package com.sumfi.graphql.dataloader;

import com.sumfi.graphql.bo.EpisodeBO;
import com.sumfi.graphql.bo.StarScoreBO;
import com.sumfi.graphql.domain.webtoon.Episode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {
    public static final String STAR_SCORE_DATA_LOADER = "STAR_SCORE_DATA_LOADER";
    public static final String EPISODE_DATA_LOADER = "EPISODE_DATA_LOADER";

    private final Executor starscoreTaskExecutor;
    private final Executor episodeTaskExecutor;

    private final StarScoreBO starScoreBO;
    private final EpisodeBO episodeBO;

    public DataLoaderRegistry create() {
        DataLoaderRegistry registry = new DataLoaderRegistry();

        registry.register(STAR_SCORE_DATA_LOADER, createStarScoreDataLoader());
        registry.register(EPISODE_DATA_LOADER, createEpisodeDataLoader());

        return registry;
    }

    private DataLoader<UUID, Double> createStarScoreDataLoader() {
        return DataLoader.newMappedDataLoader(webtoonIds ->
            CompletableFuture.supplyAsync(() -> {
                log.info("Starscore DataLoading of WebtoonIds For: {}", webtoonIds.stream().map(UUID::toString).collect(Collectors.joining(", ")));
                return starScoreBO.getStarScoreFor(webtoonIds);
            }, starscoreTaskExecutor)
        );
    }

    @SuppressWarnings("unsupported")
    private DataLoader<UUID, List<Episode>> createEpisodeDataLoader() {

        return DataLoader.newMappedDataLoader((webtoonIds, env) ->
                CompletableFuture.supplyAsync(() -> {
                    log.info("Episode DataLoading of WebtoonIds For: {}", webtoonIds.stream().map(UUID::toString).collect(Collectors.joining(", ")));
                    return episodeBO.getWebtoonsEpisodesFrom((Map) env.getKeyContexts());
                }, episodeTaskExecutor)
        );
    }
}
