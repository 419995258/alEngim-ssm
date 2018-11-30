package shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class ApiToken implements AuthenticationToken {

    private static final long serialVersionUID = -7838912794581842158L;

    private String clientKey;// 客户标识（可以是用户名、app id等等）
    private String digest;// 消息摘要
    private String timeStamp;// 时间戳
    private String host;// 客户端IP

    public ApiToken(String clientKey,String timeStamp,String digest
            ,String host){
        this.clientKey = clientKey;
        this.timeStamp = timeStamp;
        this.digest = digest;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return this.clientKey;
    }
    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
