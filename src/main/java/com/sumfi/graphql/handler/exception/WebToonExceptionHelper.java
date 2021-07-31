package com.sumfi.graphql.handler.exception;

import java.util.LinkedHashMap;
import java.util.Map;

public class WebToonExceptionHelper {
    public static Map<String, Object> makeExtensions(WebToonException e) {
        Map<String, Object> extensions = new LinkedHashMap<>();

        extensions.put("errorCode", e.getErrorCode().getErrorCode());
        extensions.put("errorMessage", e.getErrorCode().getErrorMessage());

        return extensions;
    }
}
