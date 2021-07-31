package com.sumfi.graphql.resolver.webtoon.mutation;

import com.sumfi.graphql.domain.webtoon.WebToon;
import com.sumfi.graphql.dto.WebToonInput;
import com.sumfi.graphql.enums.WebToonType;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class WebToonInputMutation implements GraphQLMutationResolver {
    public WebToon createWebToon(WebToonInput input) {
        log.info("create Webtoon title : {}", input.getTitle());

        return WebToon.builder().id(UUID.randomUUID()).title(input.getTitle()).webToonType(input.getWebToonType()).build();
    }

    public WebToon updateWebtoon(UUID id, String title) {
        log.info("updating Webtoon id : {} title : {}", id.toString(), title);

        return WebToon.builder().id(id).title(title).webToonType(WebToonType.Originals).build();
    }
}