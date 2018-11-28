package alEngin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

import redis.RedisUtil;


@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext.xml"}) //加载配置文件
public class test {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test(){
        System.out.println(UUID.randomUUID());
        redisUtil.set("a","aaa");
        System.out.println(redisUtil.get("a"));
        redisUtil.delete("a");
        System.out.println(redisUtil.get("a"));

        redisUtil.delete("c");
    }
}
