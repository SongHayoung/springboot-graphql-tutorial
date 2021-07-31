package com.sumfi.graphql.model.Utils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Pair<F, S> {
    private F first;
    private S second;
}
