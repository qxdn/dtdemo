package com.qxdn.dtdemo.dal.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Item extends BaseModel{

    private String name;

    private Integer count = 0;

    /**
     * 用于事务预留
     */
    private Integer transactionalCount = 0;
}
