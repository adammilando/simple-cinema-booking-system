package org.livecodeJPA.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_rating")
public class Rating {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Enumerated(EnumType.STRING)
//    private ratingCode code;
//    @Column(name = "description")
//    private String description;
//
//    @OneToMany(mappedBy = "rating")
//    private List<Film> films = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "code", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ratingCode rating;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "rating")
    private List<Film> films;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public ratingCode getRating() {
        return rating;
    }

    public void setRating(ratingCode rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
