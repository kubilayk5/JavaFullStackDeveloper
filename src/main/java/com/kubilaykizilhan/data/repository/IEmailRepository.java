package com.kubilaykizilhan.data.repository;

import com.kubilaykizilhan.data.entity.EmailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmailRepository extends CrudRepository<EmailEntity,Long> {

    // Delivered Query (Kendi sorgumu yazdım)
    Optional<EmailEntity> findByEmailTo (String emailTo);
}