package com.huangyingsheng.wechat.token.service;

import com.alibaba.fastjson.JSON;
import com.huangyingsheng.wechat.token.daoconfig.SqlSessionFactoryUtil;
import com.huangyingsheng.wechat.token.dao.TokenMapper;
import com.huangyingsheng.wechat.token.entity.WechatAccessToken;
import com.huangyingsheng.wechat.token.frameworkmodel.JobException;
import com.huangyingsheng.wechat.token.http.HttpHelper;
import com.huangyingsheng.wechat.token.wechat.TickeModel;
import com.huangyingsheng.wechat.token.wechat.TokenModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.MessageFormat;
import java.util.Date;

public class TokenService {

    public void getToken(String appId, String secret) throws JobException {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
        tokenUrl = MessageFormat.format(tokenUrl, appId, secret);
        String tokenHTML = HttpHelper.doGet(tokenUrl);
        System.out.println("token is:" + tokenHTML);
        TokenModel tokenModel = JSON.parseObject(tokenHTML, TokenModel.class);

        String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";
        ticketUrl=MessageFormat.format(ticketUrl,tokenModel.getAccess_token());
        String tickeHTML= HttpHelper.doGet(ticketUrl);
        System.out.println("ticke is:" + tickeHTML);
        TickeModel tickeModel = JSON.parseObject(tickeHTML, TickeModel.class);

        WechatAccessToken wechatAccessToken = new WechatAccessToken();
        wechatAccessToken.setAppId(appId);
        wechatAccessToken.setAccessToken(tokenModel.getAccess_token());
        wechatAccessToken.setTicket(tickeModel.getTicket());
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
