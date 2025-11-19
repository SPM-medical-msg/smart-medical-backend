package com.xxx.modules.controller;

import com.xxx.modules.utils.IpUtils;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author PSW
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/common/file")
@Slf4j
public class CommonController {


    @Value("${file.path}")
    private String filePath;

    @PostMapping("/singleUploadFile")
    public Result singleUploadFile(@RequestParam(name = "file",required = true) MultipartFile file, HttpServletRequest request) {
        String fileName = "";
        if (!file.isEmpty()) {
            try {
                //图片命名
//                fileName = file.getOriginalFilename();
                File newFile = new File(filePath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                // 获取原始文件名
                String originalFilename = file.getOriginalFilename();
                // 获取文件扩展名
                assert originalFilename != null;
                String fileExt = originalFilename.substring(originalFilename.lastIndexOf("."));
                // 生成新的文件名
                fileName = TimeUtil.dateRandom18()+"_"+originalFilename;
                File imageFile = new File(newFile,fileName);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(imageFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return  ResultUtil.error(-1,"路径为空");
            } catch (IOException e) {
                e.printStackTrace();
                log.info("上传底图接口/uploadBaseImage出现异常,异常信息如下====>{}",e.getMessage());
                return  ResultUtil.error(-1,"上传文件异常");
            }
        }
//        return  ResultUtil.success(1,"图片上传成功！","文件名称"+fileName+"==="+"文件路径==>/search/compare/"+  fileName);

        return  ResultUtil.successForDataAndImage(1,"上传文件成功",fileName, IpUtils.getBaseUrl(request));
    }

    @PostMapping("/singleUploadAudioFile")
    public Result singleUploadAudioFile(@RequestParam(name = "audio",required = true) MultipartFile file, HttpServletRequest request) {
        String fileName = "";
        if (!file.isEmpty()) {
            try {
                //图片命名
//                fileName = file.getOriginalFilename();
                File newFile = new File(filePath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                // 获取原始文件名
                String originalFilename = file.getOriginalFilename();
                // 获取文件扩展名
                assert originalFilename != null;
                String fileExt = originalFilename.substring(originalFilename.lastIndexOf("."));
                // 生成新的文件名
                fileName = TimeUtil.dateRandom18()+"_"+originalFilename;
                File imageFile = new File(newFile,fileName);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(imageFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return  ResultUtil.error(-1,"路径为空");
            } catch (IOException e) {
                e.printStackTrace();
                log.info("上传底图接口/uploadBaseImage出现异常,异常信息如下====>{}",e.getMessage());
                return  ResultUtil.error(-1,"上传文件异常");
            }
        }
//        return  ResultUtil.success(1,"图片上传成功！","文件名称"+fileName+"==="+"文件路径==>/search/compare/"+  fileName);

        return  ResultUtil.successForDataAndImage(1,"上传文件成功",fileName, IpUtils.getBaseUrl(request));
    }

    @PostMapping("/upload")
    public Result upCqltjtCustomer(@RequestParam("files") MultipartFile[] files,
                                   HttpServletRequest request) throws Exception {
//        ReslutData reslutData = new ReslutData();
        List<String> fileNameList = new ArrayList<>();
        for (MultipartFile file:files){
            String fileName = "";
            if (!file.isEmpty()) {
                try {
                    //图片命名
                    fileName = Objects.requireNonNull(file.getOriginalFilename()).replaceAll(".+\\.", System.currentTimeMillis()+".");
                    File newFile = new File(filePath);
                    if (!newFile.exists()) {
                        newFile.mkdirs();
                    }
                    File imageFile = new File(newFile,fileName);
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(imageFile));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    fileNameList.add(fileName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("上传底图接口/uploadBaseImage出现异常,异常信息如下====>{}",e.getMessage());
                }
            }
        }
        //具体代码逻辑实现功能区，由读者完善吧。
        return ResultUtil.successForDataAndImage(1,"正常",fileNameList, IpUtils.getBaseUrl(request));
    }

    public static void main(String[] args) {
        String imgName = "1.jpg";
        imgName = imgName.replaceAll(".+\\.", System.currentTimeMillis()+".");
        System.out.println(imgName);
    }


    private static final int BUFFER_SIZE = 4 * 1024;

    @RequestMapping(path = "chunkDownload", method = {RequestMethod.GET, RequestMethod.POST})
    public void chunkdownload(HttpServletRequest request, HttpServletResponse response) throws Exception {

        File file = new File("D:\\file\\202408295771324816_111.docx");

        // 文件总大小
        long fileSize = file.length();

        // 设置 Content-Type 和 相关响应头
        // (这里分片下载响应头设置, 其实可以参考ResourceHttpRequestHandler#handleRequest,
        //  和 video标签学习 & xgplayer视频播放器分段播放mp4 - https://blog.csdn.net/qq_16992475/article/details/130945997)
        response.setContentType("application/octect-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setHeader("Accept-Ranges", "bytes");

        // 检查请求头中是否有Range请求头,
        // (可参考：video标签学习 & xgplayer视频播放器分段播放mp4 - https://blog.csdn.net/qq_16992475/article/details/130945997)
        String rangeHeader = request.getHeader("Range");

        // 没有Range请求头, 则下载整个文件
        if (rangeHeader == null) {

            response.setHeader("Content-Length", String.valueOf(fileSize));
            InputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            // 字节缓冲数组
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            // 读取多少, 写多少, 直到读取完毕为止
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
            out.close();

        } else {

            // 分片下载
            // (可参考: 参考ResourceHttpRequestHandler#handleRequest中的做法)

            // 开始索引
            long start = 0;

            // 结束索引
            long end = fileSize - 1;

            // 获取Range请求头的范围, 格式为：Range: bytes=0-8055,
            // (其中可能没有结束位置, 若没有位置, 取文件大小-1)
            String[] range = rangeHeader.split("=")[1].split("-");

            // 如果Range请求头中没有结束位置, 取文件大小-1
            if (range.length == 1) {

                start = Long.parseLong(range[0]);

                end = fileSize - 1;

            } else {

                // 解析开始位置 和 结束位置
                start = Long.parseLong(range[0]);

                end = Long.parseLong(range[1]);
            }

            // 此次要写出的数据
            long contentLength = end - start + 1;

            // 返回头里存放每次读取的开始和结束字节
            response.setHeader("Content-Length", String.valueOf(contentLength));
            // 响应状态码206
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

            // Content-Range响应头格式为：Content-Range: bytes 0-8055/9000
            response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileSize);

            InputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            // 跳到第start字节
            in.skip(start);

            // 字节缓冲数组
            byte[] buffer = new byte[BUFFER_SIZE];

            // 读取的字节数量
            int bytesRead = -1;

            // 写出的字节数量
            long bytesWritten = 0;


            while ((bytesRead = in.read(buffer)) != -1) {

                // 如果 已写入的数据 + 当前已读到的数据 超过了 此次要写出的数据, 则只能写入请求范围内的数据
                if (bytesWritten + bytesRead > contentLength) {
                    out.write(buffer, 0, (int) (contentLength - bytesWritten));
                    break;
                } else {
                    out.write(buffer, 0, bytesRead);
                    bytesWritten += bytesRead;
                }
            }
            in.close();
            out.close();

        }

    }


    @PostMapping({"/chunkDownLoad"})
    public void chunkDownLoad(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {
//    String fileName = request.getParameter("fileName");
        File file = new File("D:\\file\\202408289551305984_3、【操作手册】后台管理系统.docx");
        long fileSize = file.length();
        response.setContentType("application/octect-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        String rangeHeader = request.getHeader("Range");
        if (rangeHeader == null) {
            response.setHeader("Content-Length", String.valueOf(fileSize));
            InputStream in = new FileInputStream(file);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1)
                servletOutputStream.write(buffer, 0, bytesRead);
            in.close();
            servletOutputStream.close();
        }
        else {
            long start = 0L;
            long end = fileSize - 1L;
            String[] range = rangeHeader.split("=")[1].split("-");
            if (range.length == 1) {
                start = Long.parseLong(range[0]);
                end = fileSize - 1L;
            } else {
                start = Long.parseLong(range[0]);
                end = Long.parseLong(range[1]);
            }
            long contentLength = end - start + 1L;
            response.setHeader("Content-Length", String.valueOf(contentLength));
            response.setStatus(206);
            response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileSize);
            InputStream in = new FileInputStream(file);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            in.skip(start);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            long bytesWritten = 0L;
            while ((bytesRead = in.read(buffer)) != -1) {
                if (bytesWritten + bytesRead > contentLength) {
                    servletOutputStream.write(buffer, 0, (int)(contentLength - bytesWritten));
                    break;
                }
                servletOutputStream.write(buffer, 0, bytesRead);
                bytesWritten += bytesRead;
            }
            in.close();
            servletOutputStream.close();
        }
    }


    @GetMapping({"/getFileTotalSize"})
    public Result getFileTotalSize(String fileName) throws Exception {
//        if (StringUtils.isBlank(fileName)){
//            return ResultUtil.error(-1,"文件名不得为空");
//        }
        File file = new File("D:\\file\\202408289551305984_3、【操作手册】后台管理系统.docx");
//        log.info("文件路径...{}",filePath + fileName);
        if (file.exists()) {
            long fileSize = file.length();
            System.out.println("File size in bytes: " + fileSize);
            return ResultUtil.success(1,"正常",fileSize);
        } else {
            return ResultUtil.error(-1,"文件不存在");
        }
    }
}

