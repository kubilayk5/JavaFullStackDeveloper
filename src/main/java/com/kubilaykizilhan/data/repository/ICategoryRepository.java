package com.kubilaykizilhan.data.repository;

import com.kubilaykizilhan.data.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ICategoryRepository extends CrudRepository<CategoryEntity,Long> {

    //Delivered Query(Kendi sorgumu yazdım.)
    Optional<CategoryEntity> findByCategoryName(String categoryName);
}
