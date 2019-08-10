package com.huangyingsheng.wechat.token.dao;

import com.huangyingsheng.wechat.token.entity.WechatAccessToken;

public interface TokenMapper {

    int insert(WechatAccessToken wechatAccessToken);

    int effectiveHistory(String dateStr);

}
