package boss.portal.mapper;

import boss.portal.entity.Permission;
import boss.portal.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * create by
 *
 * @author dingxu
 * @date 2018/10/29 17:40
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
