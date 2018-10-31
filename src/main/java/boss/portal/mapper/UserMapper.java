package boss.portal.mapper;

import boss.portal.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * create by
 *
 * @author dingxu
 * @date 2018/10/29 17:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
