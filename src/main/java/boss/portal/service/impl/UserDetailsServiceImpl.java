package boss.portal.service.impl;

import boss.portal.entity.Role;
import boss.portal.entity.User;
import boss.portal.entity.UserRole;
import boss.portal.mapper.RoleMapper;
import boss.portal.mapper.UserMapper;
import boss.portal.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author zhaoxinguo on 2017/9/13.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过构造器注入UserRepository
     * @param userRepository
     */
    //public UserDetailsServiceImpl(UserMapper userDao) {
    //    this.userMapper = userDao;
    //}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1 = new User();
        user1.setUsername(username);
        User user = userMapper.selectOne(user1);
        System.out.println("用户信息："+user);
        // 这里设置权限和角色
        ArrayList<String> roles = new ArrayList<>();
        EntityWrapper wrapper=new EntityWrapper<>();
        wrapper.eq("user_id", user.getId());
        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectById(userRole.getRoleId());
            System.out.println(role);
            roles.add(role.getRole());
        }
        System.out.println(userRoles.toString());
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getId(),user.getUsername(), user.getPassword(), roles);
    }

}
