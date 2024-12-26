package org.example.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nominative_case")
@Builder
@Getter
@Setter
public class NominativeCase {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String singular;

    @Column(nullable = false)
    private String plural;
}
