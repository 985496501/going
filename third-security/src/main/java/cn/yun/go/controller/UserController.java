package cn.yun.go.controller;

import cn.yun.go.common.Results;
import cn.yun.go.dto.UserDTO;
import cn.yun.go.dto.UserRetrievePwdDTO;
import cn.yun.go.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jinyun liu
 * @date 2020/5/22
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户管理所有接口-刘津运")
public class UserController {

    @PostMapping(value = "login")
    @ApiOperation(value = "用户登录接口", notes = "用户数据账号和密码, 返回用户和密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", required = true, dataType = "string", example = "admin"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", example = "123456"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误, 联系后台接口人员")
    })
    public Results<UserVO> login(HttpServletRequest servletRequest, String userName, String password) {
        //multipart/form-data
        // 不写这个默认是表单consumes = "application/x-www-form-urlencoded",
        System.out.println(userName + " " + password);

        String header = servletRequest.getHeader("access-token");
        System.out.println(header);
        return new Results<>(200, "SUCCESS", new UserVO(userName, password));
    }


    @PostMapping(value = "register")
    @ApiOperation(value = "用户注册接口", notes = "用户数据账号和密码, 返回用户和密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", required = true, dataType = "string", example = "admin"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", example = "123456"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误, 联系后台接口人员")
    })
    public Results<UserDTO> register(@RequestBody UserDTO userDTO) {
        //multipart/form-data
        // 不写这个默认是表单consumes = "application/x-www-form-urlencoded",
        System.out.println(userDTO);
        return new Results<>(200, "SUCCESS", userDTO);
    }

    @PostMapping(value = "retrieve")
    @ApiOperation(value = "用户找回密码接口", notes = "用户通过手机号找回密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", required = true, dataType = "string", example = "admin"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "string", example = "17862328960"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误, 联系后台接口人员")
    })
    public Results<UserRetrievePwdDTO> retrieve(@RequestBody UserRetrievePwdDTO userRetrievePwdDTO) {
        //multipart/form-data
        // 不写这个默认是表单consumes = "application/x-www-form-urlencoded",
        System.out.println(userRetrievePwdDTO);
        return new Results<>(200, "SUCCESS", userRetrievePwdDTO);
    }

    @GetMapping("login/{id}")
    public String hello(@PathVariable Long id) {

        return "hello security" + id;
    }
}
