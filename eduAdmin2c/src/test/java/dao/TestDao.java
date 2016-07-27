package dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.class8.eduAdmin.constant.UserStatusConst;
import com.class8.eduAdmin.dao.UserDao;
import com.class8.eduAdmin.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-core.xml")
public class TestDao {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSaveUser(){
		User user = new User();
		user.setUsername("yuzhaocai");
		user.setPassword("class8");
		user.setSalt("xtbbbbdx");
		user.setRealname("yuzhaocai");
		user.setEmail("87064695@qq.com");
		user.setPhone("18610941318");
		user.setStatus(UserStatusConst.NORMAL);
		user.setCreator(1);
		user.setCreateTime(new Date());
		user.setOrganizationId(1);
		userDao.saveUser(user);
	}
}
