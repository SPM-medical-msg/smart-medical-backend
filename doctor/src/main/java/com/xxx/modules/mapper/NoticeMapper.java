package com.xxx.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-01-20
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 查询所有公告（包含科室名称）
     */
    @Select("SELECT n.*, d.dept_name as deptName " +
            "FROM notice n " +
            "LEFT JOIN dept d ON n.dept_id = d.id " +
            "ORDER BY n.create_time DESC")
    List<Notice> selectAllWithDeptName();

    /**
     * 根据科室ID查询公告
     */
    @Select("SELECT n.*, d.dept_name as deptName " +
            "FROM notice n " +
            "LEFT JOIN dept d ON n.dept_id = d.id " +
            "WHERE n.dept_id = #{deptId} " +
            "ORDER BY n.create_time DESC")
    List<Notice> selectByDeptId(@Param("deptId") Integer deptId);

    /**
     * 根据ID查询详情（包含科室名称）
     */
    @Select("SELECT n.*, d.dept_name as deptName " +
            "FROM notice n " +
            "LEFT JOIN dept d ON n.dept_id = d.id " +
            "WHERE n.id = #{id}")
    Notice selectByIdWithDeptName(@Param("id") Integer id);

    /**
     * 按条件查询（可选）
     */
    @Select("<script>" +
            "SELECT n.*, d.dept_name as deptName " +
            "FROM notice n " +
            "LEFT JOIN dept d ON n.dept_id = d.id " +
            "<where>" +
            "  <if test='deptId != null'>" +
            "    AND n.dept_id = #{deptId}" +
            "  </if>" +
            "  <if test='title != null and title != \"\"'>" +
            "    AND n.title LIKE CONCAT('%', #{title}, '%')" +
            "  </if>" +
            "</where>" +
            "ORDER BY n.create_time DESC" +
            "</script>")
    List<Notice> selectByCondition(@Param("deptId") Integer deptId,
                                   @Param("title") String title);
}