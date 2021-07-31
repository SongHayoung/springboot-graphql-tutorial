package com.sumfi.graphql.bo;

import com.sumfi.graphql.domain.webtoon.Episode;
import com.sumfi.graphql.model.Utils.Pair;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EpisodeBO {
    public Map<UUID, List<Episode>> getWebtoonsEpisodesFrom(Map<UUID, List<Integer>> webtoonIdEpisodeNoMap) {
        return webtoonIdEpisodeNoMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> getEpisodesFrom(entry.getKey(), entry.getValue())));
    }

    public List<Episode> getEpisodesFrom(UUID webtoonId, List<Integer> episodeNos) {
        return episodeNos.stream()
                .map(episodeNo -> Episode.builder()
                        .episodeNo(episodeNo)
                        .releaseDate(LocalDate.now())
                        .title(String.format("%d 화", episodeNo))
                        .build())
                .collect(Collectors.toList());
    }
}
