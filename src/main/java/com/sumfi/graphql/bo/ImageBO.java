package com.sumfi.graphql.bo;

import com.sumfi.graphql.domain.webtoon.Image;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class ImageBO {
    public Image getImage(UUID id) {
        return Image.builder().thumbnail("thumbnail URL").imageList(Arrays.asList("episode imgae1 URL")).build();
    }
}
