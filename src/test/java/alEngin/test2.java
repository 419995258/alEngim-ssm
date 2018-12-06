package alEngin;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zyyd.base.entity.BaseArea;

import java.security.Key;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import redis.RedisUtil;


public class test2 {




    @Test
    public void test3(){
        String areaIds = "34a1a184-b745-4374-b868-04f976abbd70,bdcd51a0-1abe-4f9c-ae13-2c16f3c2e720,3bf36d29-fe02-4ebc-86f9-8f3a2280c4e2,84565e57-898b-4e80-8133-e0af6536cc8c,486169ad-49b9-436c-8799-eeb52484d185,fa6ae8d4-bec1-4430-b75c-3fd2d809af92,2dad2c7e-4cbf-4bc3-ab08-14c7c0d03457,dc510c76-37d3-4e27-803a-ce6794248d0e,";
        /* 开始遍历 */
        String[] areaIdss = areaIds.split(",");
        if(areaIdss.length > 0){

            for (int i = 0; i < areaIdss.length; i++) {
                System.out.println(areaIdss[i]);
            }


        }
    }
}
