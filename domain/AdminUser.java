package com.potato.admin.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdminUser {
    private Long id;
    private String username;      // 用户账号
    private String nickname;      // 用户昵称
    private String email;         // 用户邮箱
    private String mobile;        // 手机号码
    private Integer sex;          // 用户性别（0男 1女 2未知）
    private String avatar;        // 头像地址
    private String password;      // 密码
    private Integer status;       // 帐号状态（0正常 1停用）
    private String creator;       // 创建者
    private LocalDateTime createTime; // 创建时间
    private String updater;       // 更新者
    private LocalDateTime updateTime; // 更新时间
    private String remark;        // 备注
    private Integer deleted;      // 删除标志
}