package com.xxx.modules.controller;

/**
 * ClassName: StudentController
 * Description：
 *
 * @Auth zzx
 * @Create 2025/11/2 9:21
 * @Version 1.0
 */


import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 学生管理接口
 * 用于演示 Swagger + Knife4j 注解用法
 */
@Api(tags = "学生管理接口") // 控制器分类名（会在左侧菜单中显示）
@RestController
@RequestMapping("/student")
public class StudentController {

    /**
     * 查询所有学生信息
     */
    @ApiOperation(value = "查询学生列表", notes = "获取所有学生的基本信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    @GetMapping("/list")
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1L, "张三", 20, "计算机学院"));
        list.add(new Student(2L, "李四", 22, "数学学院"));
        return list;
    }

    /**
     * 根据ID查询学生
     */
    @ApiOperation(value = "根据ID查询学生", notes = "通过学生ID查询详细信息")
    @ApiImplicitParam(name = "id", value = "学生ID", required = true, example = "1", paramType = "path", dataType = "long")
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return new Student(id, "王五", 21, "物理学院");
    }

    /**
     * 新增学生
     */
    @ApiOperation(value = "添加学生信息", notes = "新增一个学生记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "学生姓名", required = true, example = "赵六", paramType = "query"),
            @ApiImplicitParam(name = "age", value = "学生年龄", required = true, example = "19", paramType = "query"),
            @ApiImplicitParam(name = "college", value = "所在学院", example = "外国语学院", paramType = "query")
    })
    @PostMapping("/add")
    public String addStudent(@RequestParam String name,
                             @RequestParam int age,
                             @RequestParam(required = false) String college) {
        return "添加成功：" + name + "，年龄：" + age + "，学院：" + college;
    }

    /**
     * 修改学生信息
     */
    @ApiOperation(value = "修改学生信息", notes = "根据ID修改学生的姓名和学院信息")
    @PutMapping("/update/{id}")
    public String updateStudent(
            @ApiParam(value = "学生ID", example = "1") @PathVariable Long id,
            @ApiParam(value = "学生姓名", example = "张三丰") @RequestParam String name,
            @ApiParam(value = "所在学院", example = "软件学院") @RequestParam String college) {
        return "修改成功，ID=" + id + "，新姓名：" + name + "，学院：" + college;
    }

    /**
     * 删除学生
     */
    @ApiOperation(value = "删除学生", notes = "根据ID删除学生记录")
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@ApiParam(value = "学生ID", example = "2") @PathVariable Long id) {
        return "删除成功，ID=" + id;
    }

    // 模拟学生实体类
    @ApiModel(description = "学生实体类")
    static class Student {
        @ApiModelProperty(value = "学生ID", example = "1")
        public Long id;

        @ApiModelProperty(value = "学生姓名", example = "张三")
        public String name;

        @ApiModelProperty(value = "学生年龄", example = "20")
        public int age;

        @ApiModelProperty(value = "所在学院", example = "计算机学院")
        public String college;

        public Student(Long id, String name, int age, String college) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.college = college;
        }

        public Student() {}
    }
}
