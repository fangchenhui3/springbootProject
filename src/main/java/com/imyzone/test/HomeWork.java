package com.imyzone.test;

/**
 * Titie:
 * Description:
 * JDK:
 * Tomcat:
 * Author: fangchenhui
 * CreateTime:2017/6/19 21:24
 * version: 1.0
 **/
public class HomeWork {
    public final int RAW_LOG = 1;
    public final int RAW_LOG_WITH_DATE = 2;
    public final int EMAIL_LOG = 1;
    public final int SMS_LOG = 2;
    public final int PRINT_LOG = 3;

    int type = 0;
    int method = 0;

    public HomeWork(int logType, int logMethod){
        this.type = logType;
        this.method = logMethod;
    }
    public void log(String msg){

        String logMsg = msg;

        if(this.type == RAW_LOG){
            logMsg = msg;
        } else if(this.type == RAW_LOG_WITH_DATE){
            String txtDate = DateUtil.getCurrentDateAsString();
            logMsg = txtDate + ": " + msg;
        }

        if(this.method == EMAIL_LOG){
            MailUtil.send(logMsg);
        } else if(this.method == SMS_LOG){
            SmsUtil.send(logMsg);
        } else if(this.method == PRINT_LOG){
            System.out.println(logMsg);
        }
    }
}