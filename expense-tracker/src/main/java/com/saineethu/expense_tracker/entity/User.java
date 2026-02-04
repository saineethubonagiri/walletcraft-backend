package com.saineethu.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable= false)
    private String firstName;
    @Column(nullable= false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;

    //store hashed
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist(){
        if(createdAt == null){
            createdAt = Instant.now();
        }
    }
}
