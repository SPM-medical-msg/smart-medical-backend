package com.potato.admin.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VideoRecord {
    private Long id;
    private String fileName;
    private String displayName;
    private String filePath;
    private Long fileSize;
    private Integer duration;
    private String sampleId;
    private LocalDate collectionDate;
    private String videoType;
    private String thumbnailPath;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
