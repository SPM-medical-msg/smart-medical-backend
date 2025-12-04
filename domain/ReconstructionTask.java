package com.potato.admin.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReconstructionTask {
    private Long id;
    private String taskCode;
    private String sampleId;
    private String datasetName;
    private String status;
    private String resultPath;
    private String previewHtml;
    private String remark;
    private LocalDateTime submitTime;
    private LocalDateTime finishTime;
}
