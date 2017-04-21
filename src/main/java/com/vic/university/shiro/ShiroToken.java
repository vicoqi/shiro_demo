package com.vic.university.shiro;

import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;

//写一个类 继承UsernamePasswordToken 的原因是。为了能够获得到密码，因为paramAuthenticationToken没有get密码的方法。因为初期的时候，我不打算用 凭证匹配器。也就是我自己手动判断 密码是否正确
public class ShiroToken extends UsernamePasswordToken implements Serializable
{


    /**
     * 
     */
    private static final long serialVersionUID = -2013574731436985473L;

    public ShiroToken(String username, String pswd)
    {
        super(username, pswd);
        this.pswd = pswd;
    }

    private String pswd;

    public String getPswd()
    {
        return pswd;
    }

    public void setPswd(String pswd)
    {
        this.pswd = pswd;
    }
}