package com.sumfi.graphql.handler;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WebToonGraphQLErrorHandler implements GraphQLErrorHandler {
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        list.forEach(error -> log.error(error.getMessage()));
        list.forEach(error -> error.getMessage());
        return list;
    }
}
