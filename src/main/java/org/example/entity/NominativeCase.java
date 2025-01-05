package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NominativeCase {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String singular;

    @Column(nullable = false)
    private String plural;
}

