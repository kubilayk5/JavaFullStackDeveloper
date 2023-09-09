package com.kubilaykizilhan.data.repository;

import com.kubilaykizilhan.data.entity.BlogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends CrudRepository<BlogEntity, Long> {
}
