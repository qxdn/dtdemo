package com.qxdn.dtdemo.dal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "seata_state_machine_def")
public class SeataStateMachineDef {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "tenant_id",nullable = false)
    private String tenantId;

    @Column(name = "app_name",nullable = false)
    private String appName;

    private String type;

    @Column(name = "comment_")
    private String comment;

    @Column(name = "ver",nullable = false)
    private String version;

    @Column(name = "gmt_create",columnDefinition = "TIMESTAMP")
    private Date gmtCreate;

    @Column(nullable = false)
    private String status;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="content",columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "recover_strategy")
    private String recoverStrategy;
}
