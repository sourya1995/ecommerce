package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.ItemEntity;
import com.sourya.ecommerceAPI.model.Item;

import java.util.List;

public interface ItemService {
    ItemEntity toEntity(Item m);
    List<ItemEntity> toEntityList(List<Item> items);
    Item toModel(ItemEntity e);
    List<Item> toModelList(List<ItemEntity> items);

}
