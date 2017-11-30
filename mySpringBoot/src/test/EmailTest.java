import com.gyh.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner. class)
@SpringBootTest(classes=App.class)
public class EmailTest {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String formUser;

    @Test
    public void testSend(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(formUser);//发送者.
        message.setTo("1041954045@qq.com");//接收者.
        message.setSubject("这是我给你的邮件");//邮件主题.
        message.setText(" 哈哈哈哈哈");//邮件内容.
        javaMailSender.send(message);//发送邮件
    }
}