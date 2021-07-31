package com.sumfi.graphql.context;

import com.sumfi.graphql.domain.user.WebtoonUser;
import graphql.kickstart.servlet.context.GraphQLServletContext;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoaderRegistry;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class UserGraphQLContext implements GraphQLServletContext {
    private final WebtoonUser webtoonUser;
    private final GraphQLServletContext context;

    public WebtoonUser getWebtoonUser() {
        return webtoonUser;
    }

    @Override
    public List<Part> getFileParts() {
        return context.getFileParts();
    }

    @Override
    public Map<String, List<Part>> getParts() {
        return context.getParts();
    }

    @Override
    public HttpServletRequest getHttpServletRequest() {
        return context.getHttpServletRequest();
    }

    @Override
    public HttpServletResponse getHttpServletResponse() {
        return context.getHttpServletResponse();
    }

    @Override
    public Optional<Subject> getSubject() {
        return context.getSubject();
    }

    @Override
    public @NonNull DataLoaderRegistry getDataLoaderRegistry() {
        return context.getDataLoaderRegistry();
    }
}
