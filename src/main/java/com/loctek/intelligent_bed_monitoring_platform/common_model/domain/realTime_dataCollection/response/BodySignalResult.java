package com.loctek.intelligent_bed_monitoring_platform.common_model.domain.realTime_dataCollection.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BodySignalResult {
    // 心跳或呼吸数据
    private Short data;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    // 时间
    private Date time;

    public BodySignalResult(Short data, Date time) {
        this.data = data;
        this.time = time;
    }
}
