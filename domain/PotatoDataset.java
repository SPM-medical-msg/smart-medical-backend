package com.potato.admin.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PotatoDataset {
    private Long id;
    private String sampleId;
    private String sampleName;
    private LocalDate collectionDate;
    private String collectionLocation;
    private BigDecimal plantHeight;
    private BigDecimal plantWidth;
    private Integer leafCount;
    private BigDecimal stemDiameter;
    private BigDecimal rootLength;
    private String growthStage;
    private String variety;
    private String soilType;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal lightIntensity;
    private String remark;
    private String dataSource;
    private String fileName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
