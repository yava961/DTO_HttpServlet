package com.example.DTO.service.impl;

import com.example.DTO.entity.Shop;
import com.example.DTO.entity.ShopDTO;
import com.example.DTO.repository.ShopRepo;
import com.example.DTO.service.ShopService;
import com.example.DTO.service.converter.ShopConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ShopConverter shopConverter;

    @Override
    public Shop createNewShop(ShopDTO shopDTO) {
        return shopRepo.save(shopConverter.converterToEntity(shopDTO));
    }

    @Override
    public List<ShopDTO> getAllShops() {
        List<ShopDTO> shops = new ArrayList<>();
        shopRepo.findAll().forEach(shop -> shops.add(shopConverter.converterToDto(shop)));
        return shops;
    }

    @Override
    public ShopDTO getShopById(Long id) {
        Shop shop = shopRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        return shopConverter.converterToDto(shop);
    }

    @Override
    public Shop updateShopById(Long id, Shop shop) {
        if (shopRepo.findById(id).isEmpty()) {
            return null;
        } else {
            shop.setId(id);
            shopRepo.save(shop);
            return shop;
        }
    }

    @Override
    public void deleteShopById(Long id) {
        if (shopRepo.existsById(id)) {
            shopRepo.deleteById(id);
        } else {
            throw new RuntimeException("Shop with id : " + id + " not exist");
        }
    }
}
