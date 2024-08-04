package com.qxdn.dtdemo.dal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity(name = "undo_log")
@Table(indexes = {
        @Index(name = "ux_undo_log", columnList = "xid,branch_id", unique = true),
        @Index(name = "ix_log_created", columnList = "log_created")
})
public class UndoLog {

    @Id
    @Column(name = "branch_id",columnDefinition = "BIGINT")
    private Long branchId;

    @Id
    @Column(name = "xid",columnDefinition = "VARCHAR(128)")
    private String xid;

    @Column(name = "context",columnDefinition = "VARBINARY(128)")
    private String context;

    @Lob
    @Column(name = "rollback_info",columnDefinition = "LONGBLOB")
    private byte[] rollbackInfo;

    @Column(name = "log_status",columnDefinition = "INT(11)")
    private Long logStatus;

    @Column(name = "log_created",columnDefinition = "DATETIME(6)")
    private Date logCreated;

    @Column(name = "log_modified",columnDefinition = "DATETIME(6)")
    private Date logModified;
}
