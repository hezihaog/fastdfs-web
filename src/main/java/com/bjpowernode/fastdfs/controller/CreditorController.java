package com.bjpowernode.fastdfs.controller;

import com.bjpowernode.fastdfs.model.CreditorInfo;
import com.bjpowernode.fastdfs.service.CreditorService;
import com.bjpowernode.fastdfs.util.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/1 8:33 下午
 * <p>
 */
@Controller
public class CreditorController {
    @Autowired
    private CreditorService creditorService;

    /**
     * 获取所有债权信息
     */
    @RequestMapping("/")
    public String creditors(Model model) {
        List<CreditorInfo> list = creditorService.selectAl();
        model.addAttribute("creditorList", list);
        return "creditors";
    }

    /**
     * 跳转到上传页面
     *
     * @param id 债权记录Id
     */
    @GetMapping("/upload/{id}")
    public String toUpload(@PathVariable Integer id, Model model) {
        CreditorInfo creditorInfo = creditorService.selectById(id);
        model.addAttribute("creditorInfo", creditorInfo);
        return "upload";
    }

    /**
     * 文件上传
     * MultipartFile，为Spring提供的类，专门用于封装请求中的文件数据
     * 属性名必须与表单中文件域的名字完全相同，否则无法获取文件数据
     *
     * @param id     债权记录Id
     * @param myFile 文件
     */
    @PostMapping("/upload")
    public String upload(Integer id, MultipartFile myFile, Model model) throws IOException {
//        //获取文件对应的字节数组
//        System.out.println(myFile.getBytes());
//        //获取文件对应的输入流
//        System.out.println(myFile.getInputStream());
//        //获取文件类型
//        System.out.println(myFile.getContentType());
//        //获取表单元素名
//        System.out.println(myFile.getName());
//        //获取文件名
//        System.out.println(myFile.getOriginalFilename());
//        //获取文件大小
//        System.out.println(myFile.getSize());
//        //判断文件是否为空，如果没有上传文件或文件大小为0，这个值都是true
//        System.out.println(myFile.isEmpty());

        //获取文件对应的字节数组
        byte[] buffFile = myFile.getBytes();
        //获取文件名
        String fileName = myFile.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            return "error";
        }
        //上传，注意有些文件是没有拓展名，这里可能会出错，但是大部分文件都有
        int beginIndex = fileName.lastIndexOf(".");
        if (beginIndex == -1) {
            return "error";
        }
        String fileExtName = fileName.substring(beginIndex + 1);
        String[] result = FastDFSUtil.upload(buffFile, fileExtName);
        if (result == null || result.length != 2) {
            return "error";
        }
        //构造信息对象
        CreditorInfo creditorInfo = new CreditorInfo();
        creditorInfo.setId(id);
        creditorInfo.setFileSize(myFile.getSize());
        creditorInfo.setFileType(myFile.getContentType());
        creditorInfo.setOldFileName(myFile.getOriginalFilename());
        creditorInfo.setGroupName(result[0]);
        creditorInfo.setRemoteFilePath(result[1]);
        //更新文件信息
        boolean isSuccess = creditorService.updateFileInfo(creditorInfo);
        if (!isSuccess) {
            return "error";
        }
        //跳转到上传成功页面
        model.addAttribute("message", "文件上传成功，点击确定返回列表页面");
        model.addAttribute("url", "/");
        return "success";
    }

    /**
     * 下载债权的文件
     *
     * @param id 需要下载的文件主键
     * @return 响应实体，Spring提供的类，是Spring响应数据时对应的数据对象，包含响应时的编码、响应头信息、以及响应时的具体数据
     */
    @RequestMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id) {
        //从数据库中查询记录信息
        CreditorInfo creditorInfo = creditorService.selectById(id);
        if (creditorInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String groupName = creditorInfo.getGroupName();
        String remoteFilePath = creditorInfo.getRemoteFilePath();
        if (groupName == null || groupName.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (remoteFilePath == null || remoteFilePath.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //下载文件
        byte[] buffFile = FastDFSUtil.download(groupName, remoteFilePath);
        //响应头
        HttpHeaders headers = new HttpHeaders();
        //设置响应文件类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //设置响应的文件大小，用于提供自动下载进度
        headers.setContentLength(creditorInfo.getFileSize());
        //设置下载时的默认文件名
        headers.setContentDispositionFormData("attachment", creditorInfo.getOldFileName());
        //创建响应实体对象，参数一：响应时具体的数据，参数二为响应时的头信息，参数三为响应时的状态码
        return new ResponseEntity<>(buffFile, headers, HttpStatus.OK);
    }

    /**
     * 删除债权的文件
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        creditorService.deleteById(id);
        //重定向回首页
        return "redirect:/";
    }
}