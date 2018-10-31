package boss.portal.controller;

import boss.portal.entity.User;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoxinguo on 2017/9/13.
 */
@RestController
@RequestMapping("/users")
@Api(value = "用户管理", description = "用户管理")
public class UserController extends BaseController {

    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value = "查询用户列表")
    @GetMapping("/userList")
    //@PreAuthorize("hasRole('USER')")
    public List<User> userList(){
        List<User> users = userMapper.selectList(new EntityWrapper<>());
        logger.info("users: {}", users);
        return users;
    }

    @ApiOperation(value = "查询用户权限")
    @GetMapping("/authorityList")
    //@PreAuthorize("hasRole('ADMIN')")
    public List<String> authorityList(){
        List<String> authentication = getAuthentication();
        return authentication;
    }

}
