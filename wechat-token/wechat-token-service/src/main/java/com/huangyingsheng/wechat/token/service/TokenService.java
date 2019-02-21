package com.huangyingsheng.wechat.token.service;

import com.alibaba.fastjson.JSON;
import com.huangyingsheng.wechat.token.daoconfig.SqlSessionFactoryUtil;
import com.huangyingsheng.wechat.token.dao.TokenMapper;
import com.huangyingsheng.wechat.token.entity.WechatAccessToken;
import com.huangyingsheng.wechat.token.frameworkmodel.JobException;
import com.huangyingsheng.wechat.token.http.HttpHelper;
import com.huangyingsheng.wechat.token.wechat.TokenModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.MessageFormat;
import java.util.Date;

public class TokenService {

    public void getToken(String appId, String secret) throws JobException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
        url = MessageFormat.format(url, appId, secret);
        String s = HttpHelper.doGet(url);
        System.out.println("token is:" + s);
        TokenModel tokenModel = JSON.parseObject(s, TokenModel.class);

        WechatAccessToken wechatAccessToken = new WechatAccessToken();
        wechatAccessToken.setAccessToken(tokenModel.getAccess_token());
        wechatAccessToken.setExpiresIn(tokenModel.getExpires_in());

        wechatAccessToken.setEffective(1);
        wechatAccessToken.setDisplay(1);
        wechatAccessToken.setCreateBy("system");
        wechatAccessToken.setCreateTime(new Date());
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.sqlSessionFactory;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            int effectiveHistory = tokenMapper.effectiveHistory();
            int insert = tokenMapper.insert(wechatAccessToken);
            sqlSession.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

    }

}
