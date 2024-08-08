package com.qxdn.dtdemo.sagaaction.impl;

import com.qxdn.dtdemo.sagaaction.ItemAction;
import com.qxdn.dtdemo.dal.model.Item;
import com.qxdn.dtdemo.dal.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service("itemAction")
public class ItemActionImpl implements ItemAction {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public boolean decreaseStock(String businessKey, Long itemId, Integer count, Map<String, Object> params) {
        log.info("开始尝试减少库存 businessKey={}",businessKey);
        Item item = itemRepository.findItemById(itemId);
        if (item.getCount() < count) {
            throw new RuntimeException("库存不足");
        }
        item.setCount(item.getCount() - count);
        itemRepository.save(item);
        return true;
    }

    @Override
    public boolean compensateDecreaseStock(String businessKey, Long itemId, Integer count, Map<String, Object> params) {
        log.info("开始尝试回滚减少库存 businessKey={}",businessKey);
        Item item = itemRepository.findItemById(itemId);
        item.setCount(item.getCount() + count);
        itemRepository.save(item);
        return true;
    }


}
