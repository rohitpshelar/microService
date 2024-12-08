package com.example.user.service.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;

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
}
