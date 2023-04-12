package com.example.DTO.web;

import com.example.DTO.entity.Shop;
import com.example.DTO.entity.ShopDTO;
import com.example.DTO.service.impl.ShopServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopServiceImpl shopService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public void createShop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader();
            String shopJson = reader.lines().collect(Collectors.joining());
            ShopDTO shopDTO = objectMapper.readValue(shopJson, ShopDTO.class);
            shopService.createNewShop(shopDTO);
            response.setStatus(HttpStatus.CREATED.value());
            response.getWriter().write(shopJson);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(e.getMessage());
        }
    }

    @GetMapping("/list")
    public String getAllShops(HttpServletResponse response) throws IOException {
        try {
            List<ShopDTO> list = shopService.getAllShops();
            response.setStatus(HttpStatus.OK.value());
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return e.getMessage();
        }
    }

    @GetMapping("/{id}")
    public void getShopById(@PathVariable Long id, HttpServletResponse response) {
        try {
            ShopDTO shopDTOById = shopService.getShopById(id);
            response.setStatus(HttpStatus.OK.value());
            objectMapper.writeValueAsString(shopDTOById);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShopById(@PathVariable("id") Long id, @RequestBody Shop shop) {
        try {
            Shop updateShop = shopService.updateShopById(id, shop);
            return new ResponseEntity(updateShop, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Shop> deleteShopById(@PathVariable("id") Long id) {
        try {
            shopService.deleteShopById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
