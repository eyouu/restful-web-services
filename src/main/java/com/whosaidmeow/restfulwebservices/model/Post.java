package com.whosaidmeow.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.EAGER;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 5)
    private String description;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = EAGER)
    private User user;
}
