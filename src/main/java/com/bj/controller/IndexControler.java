
package com.bj.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.bj.pojo.Products;
import com.bj.service.ProductsService;
import com.bj.utility.CheckUtil;
import com.bj.utility.MessageUtil;
import com.bj.utility.TextMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexControler extends BaseController{
	@Resource
	private ProductsService productsService;

	@RequestMapping(value = "/Weixin/wx.do",method = RequestMethod.GET)
	public void recive() throws IOException {
		System.out.println("微信端正在验证-----------------------");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if(CheckUtil.checkSignature(signature,timestamp,nonce)){
			responseOutPrint(echostr);
		}
	}

	@RequestMapping(value = "/Weixin/wx.do",method = RequestMethod.POST)
	public void index() {
		Map<String, String> map = MessageUtil.xmlToMap(request);
		String toUserName = map.get("ToUserName");
		String FromUserName = map.get("FromUserName");
		String CreateTime = map.get("CreateTime");
		String MsgType = map.get("MsgType");
		String Content = map.get("Content");
		String MsgId = map.get("MsgId");
		System.out.println(toUserName+"   "+FromUserName+"  "+CreateTime+"   "+MsgType+"    "+Content+"   "+MsgId);
		String message = null;
		if(MessageUtil.MESSAGE_TEXT.equals(MsgType)){
			if("1".equals(Content)){
				message = MessageUtil.initText(toUserName, FromUserName, MessageUtil.firstMenu());
			}else if("2".equals(Content)){
				message = MessageUtil.initText(toUserName, FromUserName, MessageUtil.secondMenu());
			}else if("3".equals(Content)){
				message = MessageUtil.initText(toUserName, FromUserName, MessageUtil.threeMenu());
			}else if("?".equals(Content) || "£¿".equals(Content)){
				message = MessageUtil.initText(toUserName, FromUserName, MessageUtil.menuText());
			}else if(Content.startsWith("·­Òë")){
				String word = Content.replaceAll("^·­Òë", "").trim();
				if("".equals(word)){
					message = MessageUtil.initText(toUserName, FromUserName, MessageUtil.threeMenu());
				}else{
					//message = MessageUtil.initText(toUserName, FromUserName, WeixinUtil.translate(word));
				}
			}
		}else if(MessageUtil.MESSAGE_EVNET.equals(MsgType)){
			String eventType = map.get("Event");
			if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
				message = MessageUtil.initText(toUserName, FromUserName, MessageUtil.menuText());
			}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
				message = MessageUtil.initText(toUserName, FromUserName, MessageUtil.menuText());
			}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
				String url = map.get("EventKey");
				message = MessageUtil.initText(toUserName, FromUserName, url);
			}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
				String key = map.get("EventKey");
				message = MessageUtil.initText(toUserName, FromUserName, key);
			}
		}else if(MessageUtil.MESSAGE_LOCATION.equals(MsgType)){
			String label = map.get("Label");
			message = MessageUtil.initText(toUserName, FromUserName, label);
		}
		responseOutPrint(message);
		/*if(MessageUtil.MESSAGE_TEXT.equals(MsgType)){
			TextMessage textMessage=new TextMessage();
			textMessage.setFromUserName(toUserName);
			textMessage.setToUserName(FromUserName);
			textMessage.setMsgType("text");
			textMessage.setCreateTime(new Date().getTime()+"");
			textMessage.setContent("你发送的消息是"+Content);
			String s = MessageUtil.textMessageToXml(textMessage);
			responseOutPrint(s);
		}*/
	}
}
