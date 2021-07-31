package com.sumfi.graphql.dto;


import com.sumfi.graphql.enums.WebToonType;
import lombok.Getter;

@Getter
public class WebToonInput {
    private String title;
    private WebToonType webToonType;
}
