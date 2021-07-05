package test;

import com.gyh.GyhApp;
import com.gyh.user.bean.TempPerson;
import com.gyh.user.mapper.UserMapper;
import com.gyh.user.service.TransactionTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guoyanhong
 * @date 2018/9/11 16:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GyhApp.class)
public class springTest {
    @Autowired
    private TempPerson tempPerson;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void propertiesTest() {
        System.out.println(tempPerson);
    }

    @Test
    public void testOrg() {
        userMapper.testTorg();
        System.out.println(tempPerson);
    }

}