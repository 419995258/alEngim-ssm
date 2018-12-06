package alEngin;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.Key;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    @Test
    public void test2(){

        SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("alEngin");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, sigAlg.getJcaName());
        System.out.println(signingKey);

        JSONObject json = new JSONObject();
        json.put("loginName","admin");
        json.put("passWord","y10pui3949op59pooy56y057t20t883y");
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")    //设置header
                .setSubject(json.toJSONString())
                .claim("token", "admin")   //设置payload的键值对
                .setIssuer("lwl")
                .signWith(sigAlg, signingKey);
        String jwt = builder.compact();
        System.out.println("生成的jwt:" + jwt);


        //获取claims
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)     //此处的key要与之前创建的key一致
                .parseClaimsJws(jwt)
                .getBody();
        //获取name和level
        String token = (String) claims.get("token");
        System.out.println("token:" + token);
        System.out.println("subject:" + claims.getSubject());
    }


}
