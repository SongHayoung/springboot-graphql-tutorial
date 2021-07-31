package com.sumfi.graphql.domain.webtoon;

import com.sumfi.graphql.enums.WebToonType;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Builder
@Value
public class WebToon {
    private UUID id;
    private String title;
    private WebToonType webToonType;
    private Image image;
    private Bgm bgm;
    private List<Episode> episodes;
    private Double starScore;
}
