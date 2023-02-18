package com.hiberus.price.repository;


import com.hiberus.price.model.Price;
import com.hiberus.price.model.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {

}
