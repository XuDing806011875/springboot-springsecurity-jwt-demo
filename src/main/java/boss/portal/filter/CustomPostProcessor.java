package boss.portal.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 * create by
 *
 * @author dingxu
 * @date 2018/10/30 18:59
 */
@Component
public class CustomPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
    @Autowired
    CustomFilterSecurityMetadataSource filterSecurityMetadataSource;
    @Autowired
    CustomAccessDecisionManager accessDecisionManager;
    @Override
    public <T extends FilterSecurityInterceptor> T postProcess(T fsi) {
        fsi.setAccessDecisionManager(accessDecisionManager); //权限决策处理类
        fsi.setSecurityMetadataSource(filterSecurityMetadataSource); //路径（资源）拦截处理
        return fsi;
    }
}
