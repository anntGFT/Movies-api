package com.sinensia.moviesapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Movie {
    @Column(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "id")
    private User userId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean favorite;
    @Column(name = "personal_rating")
    private int personalRating;
    private String notes;
}
