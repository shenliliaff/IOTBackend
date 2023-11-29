//package com.up.iotbackend.controller;
//
//
//import com.up.iotbackend.entity.ResultData;
//import com.up.iotbackend.entity.ResultEnum;
//import com.up.iotbackend.entity.TblUser;
//import com.up.iotbackend.service.ITblUserService;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author Feng.An
// * @since 2022-12-15
// */
//@Controller
//@CrossOrigin
//@RequestMapping(value = "tbl-user", produces = "text/json;charset=UTF-8")
//public class TblUserController {
//    @Autowired
//    ITblUserService userService;
//
//    @ApiOperation(value = "getAllUsers", notes = "getAllUsers", httpMethod = "GET")
//    @GetMapping("/get-all-users")
//    @ResponseBody
//    public List<TblUser> getAllUsers(){
//        return userService.getAllUsers();
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "id", value = "编号")
//    })
//    @ApiOperation(value = "getName", notes = "getName", httpMethod = "GET")
//    @GetMapping("/get-name")
//    @ResponseBody
//    public String getNameById(Integer id){
//        return userService.getNameById(id);
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "id", value = "编号")
//    })
//    @ApiOperation(value = "updateName", notes = "updateName", httpMethod = "POST")
//    @PostMapping("/update-name")
//    @ResponseBody
//    public ResultData updateNameById(TblUser user){
//         return userService.updateUser(user);
//    }
//
//    @ApiOperation(value = "checkCode", notes = "checkCode", httpMethod = "POST")
//    @PostMapping("/api/CheckCode")
//    @ResponseBody
//    public String checkCode(){
//        //checkcode无论成功失败写一条log
//        return "{\"Status\": 1,\"BeepType\": 1,\"BeepTime\": 1000}";
////        return "{\"Status\": 1, \"StatusDesc\": 合法卡, \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
//    }
//
//
//    @ApiOperation(value = "queryCmd", notes = "queryCmd", httpMethod = "POST")
//    @PostMapping("/api/QueryCmd")
//    @ResponseBody
//    public String queryCmd(Object req){
//        return "{\"CmdID\": \"123\",\"CmdCode\": 5,\"CmdParams\":{\"ViewId\":\"beilun3\",\"UID\":\"beilun4\"}}";
//    }
//
//    //心跳接口通着就在线，不通就不在线
//    //远程开门，也要记log
//    //门禁记录查询
//    //查看场馆列表 getAllLocations
//
//    //查看location列表
//    //单条修改location
//    //查看设备名称
//    //修改设备名称
//
//    /**
//     * getLocationDevicesById
//     * getAllVenues
//     * getDevicesByLocation
//     */
//}
