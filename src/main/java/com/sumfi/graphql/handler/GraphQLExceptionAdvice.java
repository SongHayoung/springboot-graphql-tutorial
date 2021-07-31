package com.sumfi.graphql.handler;

import com.sumfi.graphql.annotations.GraphQLAdvice;
import com.sumfi.graphql.error.WebToonError;
import com.sumfi.graphql.handler.exception.WebToonException;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@GraphQLAdvice
public class GraphQLExceptionAdvice {
    @ExceptionHandler(WebToonException.class)
    public WebToonError handle(WebToonException e) {
        log.error(String.format("Webtoon Exception caused by [%s], errorCode : [%s]", e.getMessage(), e.getErrorCode()));

        return new WebToonError(e);
    }
}
