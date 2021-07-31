package com.sumfi.graphql.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumfi.graphql.domain.webtoon.WebToon;
import com.sumfi.graphql.enums.WebToonErrorCode;
import com.sumfi.graphql.handler.exception.WebToonException;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class WebToonError implements GraphQLError {
    private WebToonException e;

    public WebToonError(WebToonException e) {
        this.e = e;
    }

    @Override
    public String getMessage() {
        return e.getErrorCode().getErrorMessage();
    }

    @JsonIgnore
    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @JsonIgnore
    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}
