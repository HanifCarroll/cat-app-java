package com.hanifcarroll.CatApp.cat;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CatRepository extends CrudRepository<Cat, Long> {

    @Modifying
    @Query("UPDATE Cat c SET c.name = :name, c.description = :description, c.pictureURL = :pictureURL WHERE c.id = :id")
    void updateCat(
            @Param("id") long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("pictureURL") String pictureURL
    );
}
