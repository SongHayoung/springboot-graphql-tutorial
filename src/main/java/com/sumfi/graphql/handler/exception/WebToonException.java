package com.sumfi.graphql.handler.exception;

import com.sumfi.graphql.enums.WebToonErrorCode;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class WebToonException extends GraphQLException implements GraphQLError {
    private WebToonErrorCode errorCode;

    public WebToonException(String message, WebToonErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public WebToonErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return WebToonExceptionHelper.makeExtensions(this);
    }
}
