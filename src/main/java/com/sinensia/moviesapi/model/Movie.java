package com.sinensia.moviesapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "movies")
public class Movie {
    @ManyToOne
    @JoinColumn(name = "id")
    private User id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movie_id;
    private boolean favorite;
    @Column(name = "personal_rating")
    private int personalRating;
    private String notes;
}
