package boss.portal.web.springbootdemo2;

import boss.portal.entity.User;
import boss.portal.filter.CustomFilterSecurityMetadataSource;
import boss.portal.mapper.UserMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemo2ApplicationTests {
	@Autowired
	UserMapper userMapper;
	@Autowired
	CustomFilterSecurityMetadataSource metadataSource;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test01(){
		//userMapper.selectList(new EntityWrapper<>()).stream().forEach((u)-> System.out.println(u.getUsername()));
		User user = new User();
		user.setUsername("dingxu");
		User user1 = userMapper.selectOne(user);
		System.out.println(user1);
	}

	@Test
	public void test02(){
		/*List<ConfigAttribute> attribute = metadataSource.getMatcherConfigAttribute("/users/authorityList");
		attribute.stream().forEach(System.out::println);*/
		String string = "[ROLE_ADMIN]";
		string = string.substring(1, string.length() - 1);
		String[] split = string.split(",");
		Arrays.stream(split).forEach(System.out::println);

	}
}
