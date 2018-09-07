package com.hanifcarroll.CatApp.rating;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RatingRepository extends CrudRepository<Rating, Long> {
}
