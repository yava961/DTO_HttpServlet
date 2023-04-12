package com.example.DTO.service.converter;

import com.example.DTO.entity.Shop;
import com.example.DTO.entity.ShopDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShopConverter {
    public ShopDTO converterToDto(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setStreet(shop.getStreet());
        shopDTO.setCity(shop.getCity());
        shopDTO.setName(shop.getName());
        shopDTO.setHasWebsite(shop.getHasWebsite());
        return shopDTO;
    }

    public Shop converterToEntity(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(shopDTO.getId());
        shop.setStreet(shopDTO.getStreet());
        shop.setCity(shopDTO.getCity());
        shop.setName(shopDTO.getName());
        shop.setHasWebsite(shopDTO.getHasWebsite());
        return shop;

    }
}
