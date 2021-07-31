package com.sumfi.graphql.utils;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CursorUtil {
    public static ConnectionCursor from(UUID id) {
        return new DefaultConnectionCursor(
                Base64.getEncoder().encodeToString(id.toString().getBytes(StandardCharsets.UTF_8)));
    }

    public static UUID decodeFrom(String id) {
        return UUID.fromString(new String(Base64.getDecoder().decode(id)));
    }

    public static <T> ConnectionCursor getFirstCursorFrom(List<Edge<T>> edges) {
        return Optional.ofNullable(edges.get(0))
                .map(Edge::getCursor)
                .orElse(null);
    }

    public static <T> ConnectionCursor getLastCursorFrom(List<Edge<T>> edges) {
        return Optional.ofNullable(edges.get(edges.size() - 1))
                .map(Edge::getCursor)
                .orElse(null);
    }
}
