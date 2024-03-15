package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private boolean isLive;

    private String serverUrl;
    private String streamKey;
    private String ingressId;

    private String thumbnaiUrl;
//    @OneToOne
//    private UserStream userStream;
//    @OneToMany(cascade = CascadeType.REMOVE)
//    private List<Follow> followers;
//    @OneToMany(cascade = CascadeType.REMOVE)
//    private List<Follow> followed;
//    @ManyToMany
//    private List<User> blockedUsers;
//    private String bio;
}
