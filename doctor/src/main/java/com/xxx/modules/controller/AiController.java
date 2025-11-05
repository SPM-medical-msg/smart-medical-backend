package com.xxx.modules.controller;

import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.Sample;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/ai")
@Api(tags="AI")
public class AiController {

    @GetMapping("/getAiAnswer")
    public Result getAiAnswer(String question, Integer userId){
        try {
            if (StringUtils.isBlank(question)){
                return ResultUtil.error(-1,"问题不得为空");
            }
            String aiAnswer = Sample.getAiAnswer(question);

            return ResultUtil.success(1,"正常",aiAnswer);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(-1,"异常");
        }

    }
}
