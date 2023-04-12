package com.example.DTO.service;

import com.example.DTO.entity.Shop;
import com.example.DTO.entity.ShopDTO;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    Shop createNewShop(ShopDTO shopDTO);

    List<ShopDTO> getAllShops();

    ShopDTO getShopById(Long id);

    Shop updateShopById(Long id, Shop shop);

    void deleteShopById(Long id);
}
