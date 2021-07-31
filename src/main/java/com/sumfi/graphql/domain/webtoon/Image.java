package com.sumfi.graphql.domain.webtoon;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class Image {
    private String thumbnail;
    private List<String> imageList;
}
