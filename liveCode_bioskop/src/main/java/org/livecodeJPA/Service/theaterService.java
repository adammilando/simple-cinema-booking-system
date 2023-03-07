package org.livecodeJPA.Service;
import org.livecodeJPA.Model.Film;
import org.livecodeJPA.Model.Theater;
import org.livecodeJPA.Repository.theaterRepo;

import java.util.List;

public class theaterService implements IService<Theater> {
    private theaterRepo theaterRepo;
    private filmService filmService;

    public theaterService(org.livecodeJPA.Repository.theaterRepo theaterRepo, org.livecodeJPA.Service.filmService filmService) {
        this.theaterRepo = theaterRepo;
        this.filmService = filmService;
    }

    public void insert(Theater theater, Long id){
        Film film = filmService.getById(id);
        theater.getFilms().setId(id);
        theaterRepo.add(theater);
    }

    @Override
    public void add(Theater theater) {
        theaterRepo.add(theater);
    }

    @Override
    public void update(Theater theater) {
        theaterRepo.update(theater);
    }

    @Override
    public List<Theater> findAll(Integer page, Integer pageSize) {
        List<Theater> theaters = theaterRepo.findAll(page,pageSize);
        return theaters;
    }

    @Override
    public Theater getById(Long id) {
        Theater theater = theaterRepo.getById(id);
        return theater;
    }

    @Override
    public void delete(Long id) {
        theaterRepo.delete(id);
    }
}
