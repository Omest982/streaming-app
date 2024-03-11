package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String password;
    private boolean isLive;
    @OneToOne
    private UserStream userStream;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Follow> followers;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Follow> followed;
    @ManyToMany
    private List<User> blockedUsers;
    private String bio;
}
