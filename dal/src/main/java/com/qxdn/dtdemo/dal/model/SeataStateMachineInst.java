package com.qxdn.dtdemo.dal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "seata_state_machine_inst")
public class SeataStateMachineInst {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(name = "machine_id",nullable = false)
    private String machineId;

    @Column(name="tenant_id",nullable = false)
    private String tenantId;

    @Column(name = "parentId")
    private String parentId;

    @Column(name = "gmt_started",columnDefinition = "TIMESTAMP")
    private Date gmtStarted;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="start_params")
    private String startParams;

    @Column(name = "business_key")
    private String businessKey;

    @Column(name = "gmt_end",columnDefinition = "TIMESTAMP")
    private Date gmtEnd;

    @Lob
    @Column(name = "excep",columnDefinition = "LONGBLOB")
    private byte[] excep;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "end_params")
    private String endParams;

    private String status;

    @Column(name = "compensation_status")
    private String compensationStatus;

    @Column(name = "is_running",columnDefinition = "TINYINT")
    private Boolean isRunning;

    @Column(name = "gmt_updated",nullable = false,columnDefinition = "TIMESTAMP")
    private Date gmtUpdated;
}
