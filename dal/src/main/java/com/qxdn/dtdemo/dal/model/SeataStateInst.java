package com.qxdn.dtdemo.dal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "seata_state_inst")
public class SeataStateInst {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(name = "machine_inst_id",nullable = false)
    private String machineInstId;

    @Column(nullable = false)
    private String name;

    private String type;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_method")
    private String serviceMethod;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "business_key")
    private String businessKey;

    @Column(name = "state_id_compensated_for")
    private String stateIdCompensatedFor;

    @Column(name = "state_id_retried_for")
    private String stateIdRetriedFor;

    @Column(name = "gmt_started",columnDefinition = "TIMESTAMP")
    private Date gmtStarted;

    @Column(name="is_for_update",columnDefinition = "TINYINT")
    private Boolean isForUpdate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="input_params",columnDefinition = "LONGTEXT")
    private String inputParams;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="output_params",columnDefinition = "LONGTEXT")
    private String outputParams;

    private String status;

    @Lob
    @Column(name = "excep",columnDefinition = "LONGBLOB")
    private byte[] excep;

    @Column(name = "gmt_updated",columnDefinition = "TIMESTAMP")
    private Date gmtUpdated;

    @Column(name = "gmt_end",columnDefinition = "TIMESTAMP")
    private Date gmtEnd;
}
