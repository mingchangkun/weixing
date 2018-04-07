package com.bj.utility;

import com.bj.pojo.News;
import com.bj.pojo.NewsMessage;
import com.bj.pojo.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVNET = "event"; //事件类型
    public static final String MESSAGE_SUBSCRIBE = "subscribe";//订阅
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";//取消订阅
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    public static final String MESSAGE_SCANCODE= "scancode_push";

    /**
     * xml转为map集合
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request){
        try {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;
        ins = request.getInputStream();
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for(Element e : list){
            map.put(e.getName(), e.getText());
        }
        ins.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将文本消息对象转为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 组装文本消息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String initText(String toUserName,String fromUserName,String content){
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }

    /**
     * 主菜单
     * @return
     */
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
        sb.append("1、保单介绍\n");
        sb.append("2、用户查询介绍\n");
        sb.append("3、剩余时间\n\n");
        sb.append("回复？调出此菜单。");
        return sb.toString();
    }

    public static String firstMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("此微信公众号提供保单服务，主要设计保单介绍、保单查询、保单活动等");
        return sb.toString();
    }

    public static String secondMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("保险单简称为保单，保险人与被保险人订立保险合同的正式书面证明。");
        sb.append("保险单必须完整地记载保险合同双方当事人的权利义务及责任。保险单记载的内容是合同双方履行的依据。保险单是保险合同成立的证明。");
        return sb.toString();
    }

    public static String threeMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("词组翻译使用指南\n\n");
        sb.append("使用示例：\n");
        sb.append("翻译足球\n");
        sb.append("翻译中国足球\n");
        sb.append("翻译football\n\n");
        sb.append("回复？显示主菜单。");
        return sb.toString();
    }
    /**
     * 图文消息转为xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }
    public static String initNewsMessage(String toUserName,String fromUserName,String Prourl){
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("什么是保单");
        news.setDescription("/保险单简称为保单，保险人与被保险人订立保险合同的正式书面证明。保险单必须完整地记载保险合同双方当事人的权利义务及责任。保险单记载的内容是合同双方履行的依据。保险单是保险合同成立的证明。");
        news.setPicUrl(Prourl);
        news.setUrl("www.imooc.com");

        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());
        message = newsMessageToXml(newsMessage);
        return message;
    }
/*
    *//**
     * 图片消息转为xml
     * @param imageMessage
     * @return
     *//*
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    *//**
     * 音乐消息转为xml
     * @param musicMessage
     * @return
     *//*
    public static String musicMessageToXml(MusicMessage musicMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }
    *//**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     *//*


    *//**
     * 组装图片消息
     * @param toUserName
     * @param fromUserName
     * @return
     *//*
    public static String initImageMessage(String toUserName,String fromUserName){
        String message = null;
        Image image = new Image();
        image.setMediaId("JTH8vBl0zDRlrrn2bBnMleySuHjVbMhyAo0U2x7kQyd1ciydhhsVPONbnRrKGp8m");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);
        message = imageMessageToXml(imageMessage);
        return message;
    }

    *//**
     * 组装音乐消息
     * @param toUserName
     * @param fromUserName
     * @return
     *//*
    public static String initMusicMessage(String toUserName,String fromUserName){
        String message = null;
        Music music = new Music();
        music.setThumbMediaId("WsHCQr1ftJQwmGUGhCP8gZ13a77XVg5Ah_uHPHVEAQuRE5FEjn-DsZJzFZqZFeFk");
        music.setTitle("see you again");
        music.setDescription("速7片尾曲");
        music.setMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");
        music.setHQMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");

        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMsgType(MESSAGE_MUSIC);
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setMusic(music);
        message = musicMessageToXml(musicMessage);
        return message;
    }*/
}
