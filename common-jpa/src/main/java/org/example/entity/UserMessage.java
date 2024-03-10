package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class UserMessage {
    @Id
    private UUID id;
    private String message;
    @CreationTimestamp
    private Timestamp timeWrote;
    @ManyToOne
    private User user;
}
