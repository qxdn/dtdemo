package com.qxdn.dtdemo.dal.repository;

import com.qxdn.dtdemo.dal.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemById(Long itemId);
}
