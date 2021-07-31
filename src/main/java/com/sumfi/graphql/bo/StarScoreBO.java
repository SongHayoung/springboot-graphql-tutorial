package com.sumfi.graphql.bo;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StarScoreBO {
    public Map<UUID, Double> getStarScoreFor(Set<UUID> webtoonIds) {
        return webtoonIds.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        webtoonId -> ThreadLocalRandom.current().nextDouble(0.0, 10.0)));
    }
}
