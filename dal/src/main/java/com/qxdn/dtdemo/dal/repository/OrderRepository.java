package com.qxdn.dtdemo.dal.repository;

import com.qxdn.dtdemo.dal.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
