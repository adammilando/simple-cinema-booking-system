package org.livecodeJPA.Service;

import org.livecodeJPA.Model.Film;
import org.livecodeJPA.Model.Rating;
import org.livecodeJPA.Repository.*;

import java.util.List;

public class filmService implements IService<Film> {

    private filmRepo filmRepo;
    private ratingService ratingService;

    public filmService(filmRepo filmRepo, ratingService ratingService) {
        this.filmRepo = filmRepo;
        this.ratingService = ratingService;
    }

    public void insert(Film film, Long ratingId){
        Rating rating = ratingService.getById(ratingId);
        film.setRating(rating);
        filmRepo.add(film);
    }
    @Override
    public void add(Film film) { filmRepo.add(film);
    }

    @Override
    public void update(Film film) {
        filmRepo.update(film);
    }

    @Override
    public List<Film> findAll(Integer page, Integer pageSize) {
        List<Film> films = filmRepo.findAll(page, pageSize);
        return films;
    }

    @Override
    public Film getById(Long id) {
        return filmRepo.getById(id);
    }

    @Override
    public void delete(Long id) {
        filmRepo.delete(id);
    }
}
