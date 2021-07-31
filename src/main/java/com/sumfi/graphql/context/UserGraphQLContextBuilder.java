package com.sumfi.graphql.context;

import com.sumfi.graphql.dataloader.DataLoaderRegistryFactory;
import com.sumfi.graphql.domain.user.WebtoonUser;
import graphql.kickstart.execution.GraphQLRequest;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
@RequiredArgsConstructor
public class UserGraphQLContextBuilder implements GraphQLServletContextBuilder {
    private final DataLoaderRegistryFactory dataLoaderRegistryFactory;

    @Override
    public GraphQLContext build() {
        throw new IllegalStateException("unsupported");
    }

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userHeader = httpServletRequest.getHeader("User");

        WebtoonUser webtoonUser = WebtoonUser.builder().userId(userHeader).build();

        DefaultGraphQLServletContext graphQLServletContext = DefaultGraphQLServletContext
                .createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(dataLoaderRegistryFactory.create())
                .build();

        return new UserGraphQLContext(webtoonUser, graphQLServletContext);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalStateException("unsupported");
    }
}
