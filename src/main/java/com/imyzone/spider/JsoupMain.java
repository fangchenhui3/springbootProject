package com.imyzone.spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Titie:
 * Description:
 * JDK:
 * Tomcat:
 * Author: fangchenhui
 * CreateTime:2017/7/1 14:20
 * version: 1.0
 **/
public class JsoupMain {
    //private static final String saveImgPath="D://mm131";
    //图片保存路径
    //从每个图片的url中获取路径
    public static void getDoc(String webUrl,String imgPath) throws IOException {
        Connection connection= Jsoup.connect(webUrl);
        connection.timeout(20000);
        Document doc=connection.get();
        //获取当前有多少页
        String pageList= doc.select(".page-ch").first().text();
        String pageN="";
        //用正则表达式获取页码
        if(pageList != null && !"".equals(pageList)){
            for(int i=0;i<pageList.length();i++){
                if(pageList.charAt(i)>=48 && pageList.charAt(i)<=57){
                    pageN+=pageList.charAt(i);
                }
            }
        }
        int pageNumber=Integer.parseInt(pageN);
        List<String> urlList=new ArrayList<String>();
        urlList.add(webUrl);


        for (int i = 2; i <= pageNumber; i++) {
            String newUrl=webUrl.substring(0,webUrl.length()-5)+"_"+i+".html";
            urlList.add(newUrl);
        }
        for (int j = 0; j <urlList.size(); j++) {
            String newImgUrl=urlList.get(j);
            getImg(newImgUrl,imgPath);
            System.out.println(newImgUrl+"爬取完毕");
        }

    }

    public static void getImg(String Imgurl,String path) throws IOException{
    	/*int count=0;
    	if(count==5){
    		count=0;

    	}*/

        try {
            Connection connection= Jsoup.connect(Imgurl);
            //防扒机制
            connection.timeout(20000);

            Document doc=connection.get();
            if(connection.response().statusCode()!=200){
                connection.wait(20000);
            }
            //获取后缀为png和jpg的图片的元素集合
            Elements imgElements=doc.select(".content-pic");
            Elements pngs = imgElements.select("img[src~=(?i)\\.(png|jpe?g)]");
            //遍历元素
            for(Element e:pngs){
                String src=e.attr("src");//获取img中的src路径
                //获取后缀名
                String imageName = src.substring(src.lastIndexOf("/") + 1,src.length());
                //连接url
                URL url = new URL(src);
                URLConnection uri=url.openConnection();
                //获取数据流
                InputStream is=uri.getInputStream();
                //写入数据流
                OutputStream os = new FileOutputStream(new File(path,imageName));
                byte[] buf = new byte[1024];
                int l=0;
                while((l=is.read(buf))!=-1){
                    os.write(buf, 0, l);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }



    }
}
