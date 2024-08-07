package com.qxdn.dtdemo.service.impl;

import com.qxdn.dtdemo.dal.model.Item;
import com.qxdn.dtdemo.dal.repository.ItemRepository;
import com.qxdn.dtdemo.service.ItemService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Transactional
    @Override
    public void purchaseItem(Long itemId, Integer count) {
        log.info("start item service xid:{}", RootContext.getXID());
        Item item = itemRepository.findItemById(itemId);
        if (item.getCount() < count){
            throw new RuntimeException("库存不足");
        }
        item.setCount(item.getCount() - count);
        itemRepository.save(item);
    }
}
