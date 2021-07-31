package com.sumfi.graphql.resolver.webtoon.query;

import com.sumfi.graphql.bo.ImageBO;
import com.sumfi.graphql.bo.WebToonBO;
import com.sumfi.graphql.context.UserGraphQLContext;
import com.sumfi.graphql.domain.user.WebtoonUser;
import com.sumfi.graphql.domain.webtoon.Image;
import com.sumfi.graphql.domain.webtoon.WebToon;
import com.sumfi.graphql.utils.CursorUtil;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.servlet.context.GraphQLServletContext;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebToonResolver implements GraphQLQueryResolver {
    private final ImageBO imageBO;
    private final WebToonBO webToonBO;

    public DataFetcherResult<WebToon> webToon(UUID id, DataFetchingEnvironment env) {
        UserGraphQLContext context = env.getContext();
        String authHeader = context.getHttpServletRequest().getHeader("Auth");
        WebtoonUser webtoonUser = context.getWebtoonUser();

        log.info("getWebtoon request accepted id: {}", id);
        log.info("getWebtoon request accepted header: {}", authHeader);
        log.info("getWebtoon request from user: {}", webtoonUser.getUserId());

        WebToon webToon = webToonBO.getWebToon(id);

        if(env.getSelectionSet().contains("image")) {
            log.info("request need image for webtoon");

            Image image = imageBO.getImage(id);

            return DataFetcherResult.<WebToon>newResult()
                    .data(webToon)
                    .localContext(image)

                    .build();
        }

        return DataFetcherResult.<WebToon>newResult()
                .data(webToon)
                .build();
    }

    public Connection<WebToon> webtoons(int first, @Nullable String after) {

        List<Edge<WebToon>> edges = webToonBO.getWebtoons(first, after)
                .stream()
                .map(webtoon -> new DefaultEdge<>(webtoon, CursorUtil.from(webtoon.getId())))
                .collect(toList());

        return new DefaultConnection<>(edges, new DefaultPageInfo(
                CursorUtil.getFirstCursorFrom(edges),
                CursorUtil.getLastCursorFrom(edges),
                Objects.nonNull(after),
                edges.size() == first
        ));
    }
}
