package org.livecodeJPA.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_theater")
public class Theater {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "theater_number")
//    private String theaterNumber;
//    @Column(name = "stock")
//    private int stock;
//    @ManyToOne
//    @JoinColumn(name = "film_id")
//    private Film films;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "stock", nullable = false)
    private int stock;
    @Column(name = "theater_number")
    private String theaterNumber;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film films;
    @OneToMany(mappedBy = "theater")
    private List<Seat> seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheaterNumber() {
        return theaterNumber;
    }

    public void setTheaterNumber(String theaterNumber) {
        this.theaterNumber = theaterNumber;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Film getFilms() {
        return films;
    }

    public void setFilms(Film films) {
        this.films = films;
    }

}
