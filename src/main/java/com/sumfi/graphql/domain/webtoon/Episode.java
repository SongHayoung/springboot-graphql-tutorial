package com.sumfi.graphql.domain.webtoon;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Episode {
    private int episodeNo;
    private LocalDate releaseDate;
    private String title;
}
