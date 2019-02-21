package com.huangyingsheng.wechat.token.job.jobs;

import com.huangyingsheng.wechat.token.frameworkmodel.JobException;
import com.huangyingsheng.wechat.token.service.TokenService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TokenJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("1");
        String appId = "wx80a7631fb6f3e4d2";
        String secret = "3e20b7572a6e58b2133df18963b02283";

        try {
            System.out.println("pre gettoken");
            new TokenService().getToken(appId, secret);
        } catch (JobException e) {
            e.printStackTrace();
        }
    }

}
