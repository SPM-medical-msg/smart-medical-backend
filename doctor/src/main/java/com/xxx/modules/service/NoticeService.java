package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Notice;
import java.util.List;
/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-01-20
 */
public interface NoticeService extends IService<Notice>{

    /**
     *  获取所有公告接口
     * @param pageNum
     * @param pageSize
     * @param notice
     * @return
     */
    Result <?>selectNoticeList(Notice notice, Integer pageNum, Integer pageSize);


    /**
     *  获取单个公告接口
     * @param id
     * @return
     */
    Result<?> selectNoticeInfo(Integer id);

    /**
     * 保存公告接口
     * @param notice
     * @return
     */
    Result<?> saveNoticeInfo(Notice notice);

    /**
     * 更新公告接口
     * @param notice
     * @return
     */
    Result<?> updateNoticeInfo(Notice notice);

    /**
     * 根据id删除公告接口
     * @param id
     * @return
     */
    Result<?> delNoticeInfo(Integer id);

    /**
     * 根据id集合批量删除公告接口
     * @param idList
     * @return
     */
    Result<?> delBatchNoticeInfo(String idList);




}