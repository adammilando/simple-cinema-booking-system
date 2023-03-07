package org.livecodeJPA.Service;

import org.livecodeJPA.Model.Rating;
import org.livecodeJPA.Repository.ratingRepo;

import java.util.List;

public class ratingService implements IService<Rating> {
    private ratingRepo ratingRepo;

    public ratingService(ratingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }


    @Override
    public void add(Rating rating) {
        ratingRepo.add(rating);
    }

    @Override
    public void update(Rating rating) {
        ratingRepo.update(rating);
    }

    @Override
    public List<Rating> findAll(Integer page, Integer pageSize) {
        List<Rating> ratings =ratingRepo.findAll(page, pageSize);
        return ratings;
    }

    @Override
    public Rating getById(Long id) {
        return ratingRepo.getById(id);
    }

    @Override
    public void delete(Long id) {
        ratingRepo.delete(id);
    }
}
