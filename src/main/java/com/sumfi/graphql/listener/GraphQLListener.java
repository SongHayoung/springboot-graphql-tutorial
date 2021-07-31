package com.sumfi.graphql.listener;

import com.sumfi.graphql.utils.CookieUtil;
import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class GraphQLListener implements GraphQLServletListener {
    private final Clock clock;

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        String key = CookieUtil.getCookieValue(request, "key").orElse(null);
        HttpSession session = request.getSession();

        log.info("Received GraphQL Request at {}", Instant.now(clock), key);

        if(Objects.isNull(key) || Objects.isNull(session.getAttribute(key))) {
            final String uuid = UUID.randomUUID().toString();

            session.setAttribute(uuid, Instant.now(clock));
            response.addCookie(new Cookie("key", uuid));
            log.info("GraphQLListener set Cookie {}", uuid);
        } else {
            log.info("Get Session {}", session.getAttribute(key));
        }

        return new RequestCallback() {
            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {

            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {

            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                log.info("Completed GraphQL Request at {}", Instant.now(clock));
            }
        };
    }
}
