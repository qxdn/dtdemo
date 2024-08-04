package com.qxdn.dtdemo.dal.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "`order`")
public class Order extends BaseModel{

    private Long userId;

    private Long itemId;

    private Integer count;

    private Integer amount;
}
