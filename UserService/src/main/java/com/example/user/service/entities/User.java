package com.example.user.service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String uid;
    @Column
    private String name;
    @Column
    @Email(regexp=".*@.*\\..*", message = "Email should be valid")
    private String email;
    @Column
    private String about;

    @Transient
    private List<Rating> ratingList;
}
