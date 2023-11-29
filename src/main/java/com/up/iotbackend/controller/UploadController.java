package com.up.iotbackend.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


@Controller
@CrossOrigin
@RequestMapping("/upload")
@Api(value = "上传文件接口",tags = {"上传文件"},description = "上传文件")
public class UploadController {
    @Value("${savePath.profilePhotoPath}")
    private String ProfilePhotoPath;   //图标物理存储路径
    /**
      * bgimg及apk上传
      * @param bgImg 背景图资源
      * @param selfDefine 自定义资源
      * @param logo logo图资源
      * @return 图映射的虚拟访问路径
      */
    @PostMapping("/img")
    @ResponseBody
    public ResultData UploadImgController(@RequestParam(value = "file") MultipartFile file)
    {
        StpUtil.checkLogin();
        if(Objects.isNull(file))
        {
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
        try {
            //看fileName后缀是apk或者img
            String suffixName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString()+suffixName;
            if(suffixName.equalsIgnoreCase(".APK") && file.getSize() < 100000000){
                file.transferTo(new File(ProfilePhotoPath + "banpai.apk"));
                newFileName="banpai.apk";
            }
            if(suffixName.equalsIgnoreCase(".PNG") || suffixName.equalsIgnoreCase(".JPEG")
                    || suffixName.equalsIgnoreCase(".JPG") && file.getSize() < 20000000){
                file.transferTo(new File(ProfilePhotoPath + newFileName));
            }
            HashMap feedback = new HashMap();
            feedback.put("url","https://upcloudtech.cn/resource/img/"+newFileName);
            return ResultData.success(feedback);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.error(500, e.toString());
        }
    }
//    @Value("${savePath.profilePhotoMapper}")
//    private String ProfilePhotoMapperPath;   //图标映射路径

//    /**
//     * 头像上传
//     * @param bgImg 背景图资源
//     * @param selfDefine 自定义资源
//     * @param logo logo图资源
//     * @return 图映射的虚拟访问路径
//     */
//    @PostMapping("/img")
//    @ResponseBody
//    public ResultData UploadImgController(@RequestParam(value = "bgImg",required = false) MultipartFile bgImg,
//                                          @RequestParam(value = "selfDefine",required = false) MultipartFile selfDefine,
//                                          @RequestParam(value = "logo",required = false) MultipartFile logo) {
//        try {
//            String bgImgFile= "";
//            String selfDefineFile= "";
//            String logoFile= "";
//            if(bgImg != null){
//                bgImgFile = bgImg.getOriginalFilename().substring(bgImg.getOriginalFilename().lastIndexOf("."));
//                if (bgImgFile.equalsIgnoreCase(".PNG") || bgImgFile.equalsIgnoreCase(".JPEG")
//                        || bgImgFile.equalsIgnoreCase(".JPG")&& bgImg.getSize() < 10585760) {
//                    bgImg.transferTo(new File(ProfilePhotoPath + bgImg.getOriginalFilename()));
//                }else {
//                    return ResultData.error(500, "bgImgFile上传文件非图片或大小超过10MB");
//                }
//            }
//            if(selfDefine != null){
//                selfDefineFile = selfDefine.getOriginalFilename().substring(selfDefine.getOriginalFilename().lastIndexOf("."));
//                if (selfDefineFile.equalsIgnoreCase(".PNG") || selfDefineFile.equalsIgnoreCase(".JPEG")
//                        || selfDefineFile.equalsIgnoreCase(".JPG")&& selfDefine.getSize() < 10585760) {
//                    selfDefine.transferTo(new File(ProfilePhotoPath + selfDefine.getOriginalFilename()));
//                } else {
//                    return ResultData.error(500, "selfDefineFile上传文件非图片或大小超过10MB");
//                }
//            }
//            if(logo!= null){
//                logoFile = logo.getOriginalFilename().substring(logo.getOriginalFilename().lastIndexOf("."));
//                if (logoFile.equalsIgnoreCase(".PNG") || logoFile.equalsIgnoreCase(".JPEG")
//                        || logoFile.equalsIgnoreCase(".JPG")&& logo.getSize() < 10585760) {
//                    logo.transferTo(new File(ProfilePhotoPath + logo.getOriginalFilename()));
//                } else {
//                    return ResultData.error(500, "logoFile上传文件非图片或大小超过10MB");
//                }
//            }
//            if(bgImg != null ||selfDefine != null ||logo!= null ){
//                return ResultData.success("update image");
//            }else {
//                return ResultData.success("update nothing");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultData.error(500, e.toString());
//        }
//    }
}


