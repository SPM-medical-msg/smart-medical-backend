
/**
 * ClassName: ExcelData
 * Description：
 *
 * @Auth zzx
 * @Create 2025/11/14 10:30
 * @Version 1.0
 */

package com.potato.admin.domain;

        import lombok.Data;

        import java.time.LocalDateTime;

/**
 * Excel数据实体类
 */
@Data
public class ExcelData {
    private Long id;
    private String sampleId;          // 样本ID
    private String columnFData;       // F列数据
    private String columnAData;       // A列数据
    private String columnBData;       // B列数据
    private String columnCData;       // C列数据
    private String columnDData;       // D列数据
    private String columnEData;       // E列数据
    private String columnGData;       // G列数据
    private String fileName;          // 来源文件名
    private Integer rowIndex;         // Excel行号
    private String creator;           // 创建者
    private LocalDateTime createTime; // 创建时间
    private String updater;           // 更新者
    private LocalDateTime updateTime; // 更新时间
    private String remark;            // 备注
    private Integer deleted;          // 删除标志
}

