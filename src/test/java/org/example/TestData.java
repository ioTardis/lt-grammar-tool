package org.example;

import org.example.entity.GenetiveCase;
import org.example.entity.NominativeCase;

public class TestData {

    static public NominativeCase getNominativeCase() {
        return NominativeCase.builder()
                .singular("vyras")
                .plural("vyrai")
                .build();
    }

    static public GenetiveCase getGenitiveCase() {
        return GenetiveCase.builder()
                .singular("vyro")
                .plural("vyrų")
                .build();
    }

    static public NominativeCase mockNominativeCase(String singular, String plural) {
        return NominativeCase.builder()
                .singular(singular)
                .plural(plural)
                .build();
    }

    static public GenetiveCase mockGenitiveCase(String singular, String plural) {
        return GenetiveCase.builder()
                .singular(singular)
                .plural(plural)
                .build();
    }
}
