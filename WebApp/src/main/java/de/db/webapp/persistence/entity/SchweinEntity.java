package de.db.webapp.persistence.entity;

import jakarta.persistence.Column;

import java.util.UUID;

public class SchweinEntity {

    private UUID id;
    private String name;

    @Column(nullable = false)
    private int gewicht;
}
