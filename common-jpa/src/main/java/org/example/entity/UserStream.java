package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.UUID;

@Entity
public class UserStream {
    @Id
    private UUID id;
    @OneToMany
    private List<User> viewers;
    @OneToOne
    private Chat chat;
    private String preview;
    private String name;
}
