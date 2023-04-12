package com.example.DTO.repository;

import com.example.DTO.entity.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepo extends CrudRepository<Shop, Long> {
}
