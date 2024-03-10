package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class Chat {
    @Id
    private UUID id;
    private boolean isDelayed;
    private boolean isOnlyFollowed;
    private boolean isEnabled;
    @OneToMany
    private List<UserMessage> messages;
}
