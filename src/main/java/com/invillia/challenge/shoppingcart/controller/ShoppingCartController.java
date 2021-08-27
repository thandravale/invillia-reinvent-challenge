package com.invillia.challenge.shoppingcart.controller;

import com.invillia.challenge.shoppingcart.dto.ItemDto;
import com.invillia.challenge.shoppingcart.dto.ItemResponseDto;
import com.invillia.challenge.shoppingcart.entity.Cart;
import com.invillia.challenge.shoppingcart.entity.Product;
import com.invillia.challenge.shoppingcart.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/{user-id}/items/{SKU}")
    public ResponseEntity<ItemResponseDto> addItem (@PathVariable("user-id") Integer userId, @PathVariable("SKU") String sku , @RequestBody ItemDto item) {
        final ItemResponseDto itemResponse = shoppingCartService.AddItem(userId, sku, item);
        return ResponseEntity.ok().body(itemResponse);
    }

    @PutMapping(path = "/{user-id}/items/{SKU}")
    public ResponseEntity<ItemResponseDto> editItem (@PathVariable("user-id") Integer userId,@PathVariable("SKU") String sku, @RequestBody ItemDto item) {
        final ItemResponseDto itemResponse = shoppingCartService.editItem(userId, sku, item);
        return ResponseEntity.ok().body(itemResponse);
    }
//
//    @PatchMapping(path = "/{user-id}/items/{SKU}?quantity={quantity}")
//    public ResponseEntity<ItemResponseDto> editQuantity (@PathVariable("user-id") String userId,@PathVariable("SKU") String sku, @PathVariable("quantity")) {
//
//    }
//
    @DeleteMapping(path = "{user-id}/items/{SKU}")
    public ResponseEntity<ItemResponseDto> deleteItem (@PathVariable("user-id") Integer userId,@PathVariable("SKU") String sku) {
        final ItemResponseDto itemResponse = shoppingCartService.deleteItem(userId, sku);
        return ResponseEntity.ok().body(itemResponse);
    }
//
//    @GetMapping(path = "{user-id}")
//    public ResponseEntity<CartDto> getCart (@PathVariable("user-id") String userId) {
//
//    }

    private ItemResponseDto convertProductToItemResponseDto(Cart cart) {
        return modelMapper.map(cart, ItemResponseDto.class);
    }

}
