package org.example;

import org.example.entity.NominativeCase;

public class TestData {

    static public NominativeCase getNominativeCase() {
        return NominativeCase.builder()
                .singular("vyras")
                .plural("vyrai")
                .build();
    }

    static public NominativeCase mockNominativeCase(String singular, String plural) {
        return NominativeCase.builder()
                .singular(singular)
                .plural(plural)
                .build();
    }
}
