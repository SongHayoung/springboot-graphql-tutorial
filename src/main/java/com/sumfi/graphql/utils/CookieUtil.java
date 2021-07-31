package com.sumfi.graphql.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
    import java.util.Optional;

public class CookieUtil {
    public static Optional<String> getCookieValue(HttpServletRequest request, String name) {
        return Arrays.asList(
                    Optional.ofNullable(request.getCookies())
                    .orElse(new Cookie[0]))
                .stream()
                .filter(cookie -> cookie.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst();
    }
}
