package boss.portal.filter;

import boss.portal.entity.Permission;
import boss.portal.entity.Role;
import boss.portal.entity.RolePermit;
import boss.portal.mapper.PermissionMapper;
import boss.portal.mapper.RoleMapper;
import boss.portal.mapper.RolePermitMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * create by
 *
 * @author dingxu
 * @date 2018/10/30 19:01
 */
@Component
public class CustomFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermitMapper rolePermitMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object; //当前请求对象
        if (isMatcherAllowedRequest(fi)) return null ; //return null 表示允许访问，不做拦截
        List<ConfigAttribute> configAttributes = getMatcherConfigAttribute(fi.getRequestUrl());
        return configAttributes.size() > 0 ? configAttributes : deniedRequest(); //返回当前路径所需角色，如果没有则拒绝访问
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class< ? > aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    /**
     * 获取当前路径所需要的角色
     * @param url 当前路径
     * @return 所需角色集合
     */
    public List<ConfigAttribute> getMatcherConfigAttribute(String url){
        ArrayList<ConfigAttribute> roles = new ArrayList<>();
        EntityWrapper<Permission> wrapper = new EntityWrapper<>();
        wrapper.eq("permission", url);
        List<Permission> permissions = permissionMapper.selectList(wrapper);
        for (Permission permission : permissions) {
            EntityWrapper<RolePermit> wrapper1 = new EntityWrapper();
            wrapper1.eq("permit_id", permission.getId());
            List<RolePermit> rolePermits = rolePermitMapper.selectList(wrapper1);
            for (RolePermit rolePermit : rolePermits) {
                Role role = roleMapper.selectById(rolePermit.getRoleId());
                roles.add(new SecurityConfig(role.getRole()));
            }

        }
        roles.stream().forEach(System.out::println);
        return roles;
       /* return roleResourceRepository.findByResource_ResUrl(url).stream()
                .map(roles -> new SecurityConfig(roles.getRole().getRoleCode()))
                .collect(Collectors.toList());*/
    }

    /**
     * 判断当前请求是否在允许请求的范围内
     * @param fi 当前请求
     * @return 是否在范围中
     */
    private boolean isMatcherAllowedRequest(FilterInvocation fi){
        return allowedRequest().stream().map(AntPathRequestMatcher::new)
                .filter(requestMatcher -> requestMatcher.matches(fi.getHttpRequest()))
                .toArray().length > 0;
    }

    /**
     * @return 定义允许请求的列表
     */
    private List<String> allowedRequest(){
        return Arrays.asList("/login","/css/**","/fonts/**","/js/**","/scss/**","/img/**");
    }

    /**
     * @return 默认拒绝访问配置
     */
    private List<ConfigAttribute> deniedRequest(){
        return Collections.singletonList(new SecurityConfig("ROLE_DENIED"));
    }
}
