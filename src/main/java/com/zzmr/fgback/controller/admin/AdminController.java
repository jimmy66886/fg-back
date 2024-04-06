package com.zzmr.fgback.controller.admin;

import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.constant.JwtClaimsConstant;
import com.zzmr.fgback.dto.AdminLoginDto;
import com.zzmr.fgback.properties.JwtProperties;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.UserService;
import com.zzmr.fgback.util.JwtUtils;
import com.zzmr.fgback.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzmr
 * @create 2024-02-03 11:54
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "管理员相关接口")
@RequestMapping("/admin/user")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("管理员登陆")
    public Result login(@RequestBody AdminLoginDto adminLoginDto) {
        // 登录成功，生成token

        User user = userService.login(adminLoginDto);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, 1);

        String token = JwtUtils.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(user, userLoginVo);
        userLoginVo.setToken(token);
        return Result.success(userLoginVo);
    }

}
