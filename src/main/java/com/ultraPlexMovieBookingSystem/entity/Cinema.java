package com.ultraPlexMovieBookingSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cinema")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long cinemaId;

    @Column(name = "cinema_name")
    private String cinemaName;

    private String location;

    @Column(name = "total_screens")
    private int totalScreens;

    @OneToMany(mappedBy = "screenId")
    private List<Screen> screens;

    @OneToMany(mappedBy = "movieId")
    private List<Movie> movies;

}
