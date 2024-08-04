package com.qxdn.dtdemo.dal.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Account extends BaseModel {

    private Long userId;

    private Long money;
}
