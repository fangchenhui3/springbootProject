package com.imyzone.spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Titie:
 * Description:
 * JDK:
 * Tomcat:
 * Author: fangchenhui
 * CreateTime:2017/7/1 14:13
 * version: 1.0
 **/
public class mm131 implements Runnable{
    private  String getSubStr(String str,int num){
        String result = "";
        int i = 0;
        while(i < num) {
            int lastFirst = str.lastIndexOf('/');
            result = str.substring(lastFirst) + result;
            str = str.substring(0, lastFirst);
            i++;
        }
        return result.substring(1);
    }
    //创建目录
    public  boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            // System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return true;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return false;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
    public  void getRealUrlFromPage(List<String> list, String path) throws IOException {
        for (int j = 0; j < list.size(); j++) {
            String xgUrlSingle=list.get(j);
            Connection connection= Jsoup.connect(xgUrlSingle);
            connection.timeout(20000);
            Document doc_everyPage=connection.get();
            Elements element_everyPage=doc_everyPage.select(".list-left dd");
            for (Element e:element_everyPage) {
                if(e.className().equals("page")){//排除掉class为page的dd
                    continue;
                }
                String singleUrl=e.select("a").attr("href");
                System.out.println(singleUrl);
                int lastPath=singleUrl.lastIndexOf(".");
                int firstPath=singleUrl.lastIndexOf("/")+1;
                String imgPath=path+"//"+singleUrl.substring(firstPath,lastPath);
                boolean flag=createDir(imgPath);
                if(flag){
                    System.out.println("当前目录已经存在");
                    continue;
                }
                JsoupMain.getDoc(singleUrl,imgPath);

            }

        }
    }
    //解析当前url下面所有的页面list
    public  List<String> getPageList(String url,int lineNumber) throws IOException{
        Connection connection= Jsoup.connect(url);
        connection.timeout(20000);
        Document doc=connection.get();
        Element element=doc.select(".page-en").last();
        String linkHref = element.attr("href");
        int firstXingGan=linkHref.lastIndexOf("_")+1;
        int lastXingGan=linkHref.lastIndexOf(".");

        int lineNum=0;
        try {
            lineNum=Integer.parseInt(linkHref.substring(firstXingGan,lastXingGan));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> xingganUrlList=new ArrayList<String>();//存入所有的性感下面的list中的页面
        xingganUrlList.add(url);
        for (int i = 2; i <=lineNum; i++) {
            String xgurl=url+"list_"+lineNumber+"_"+i+".html";
            xingganUrlList.add(xgurl);
        }
        return xingganUrlList;
    }
    public   void spiderRun() throws IOException {
        String url="http://www.mm131.com";
        Connection connect= Jsoup.connect(url);
        connect.timeout(20000);//10s
        long start= System.currentTimeMillis();
        System.out.println("开始爬虫.........................................");
        Document doc=null;
        try {
            doc = connect.get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Elements elements=doc.select(".nav li");
        for (Element e:elements) {
            String navUrl=e.select("a").attr("href");
            if(navUrl.equals(url+"/")){
                continue;
            }
            int lineNumber=0;
            if(navUrl.equals("http://www.mm131.com/xinggan/")){
                lineNumber=6;
            }else if(navUrl.equals("http://www.mm131.com/qingchun/")){
                lineNumber=1;
            }else if(navUrl.equals("http://www.mm131.com/xiaohua/")){
                lineNumber=2;
            }else if(navUrl.equals("http://www.mm131.com/chemo/")){
                lineNumber=3;
            }else if(navUrl.equals("http://www.mm131.com/qipao/")){
                lineNumber=4;
            }else if(navUrl.equals("http://www.mm131.com/mingxing/")){
                lineNumber=5;
            }
            String pathTemp=getSubStr(navUrl,2);
            String path="D://mm131//"+pathTemp;
            try {
                List<String> xingganUrlList=getPageList(navUrl,lineNumber);
                getRealUrlFromPage(xingganUrlList,path);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        mm131 mm=new mm131();
        //mm.spiderRun();
        new Thread(mm,"线程1").start();
        new Thread(mm,"线程2").start();
        new Thread(mm,"线程3").start();
    }

    @Override
    public void run() {
        /*while (true) {
            //synchronized (this) {
                try {

                    // 任何线程获取"线程锁"以后都要先判断是否还有余票,防止等待的线程多打印车票
                    if (num > total)            return;
                    // 获取当前线程名字
                    String threadName = Thread.currentThread().getName();
                    // 格式化票号
                    String ticketNum = FormatTicketNum(num++);
                    // 打印火车票,休眠20毫秒模拟打印车票时间
                    Thread.sleep(20);
                    System.out.println(threadName + " 售出火车票No." + ticketNum);

                    // 某线程售完最后一张车票时,放出"车票已售罄"提示
                    if (num > total) {
                        System.out.println("车票已售罄!");
                        return;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           // }
        }*/
    }
}
