package com.et.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.MyNews;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewsController() {
      
    }
    public static final String HTML_DIR="E:\\html";
	MyNews myNews = new MyNews();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Date date  = new Date();
		String dateStr = sdf.format(date);
		Map root = new HashMap();
		root.put("title", title);
		root.put("content", content);
		root.put("createtime", dateStr);
		String uuid =UUID.randomUUID().toString();
		//����ģ������ʵ��
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		try {
			//ָ��ģ���ļ���Դ
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/"));
			//����ģ����μ���ģ�� ѡ��Ĭ��
			cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
			Template template  = cfg.getTemplate("newsdatail.ftl");
			String saveFile = HTML_DIR+"/"+uuid+".html";
			myNews.insertNews(title, content, uuid+".html",dateStr);
			Writer out = new OutputStreamWriter(new FileOutputStream(saveFile));
			template.process(root, out);
			out.flush();
			out.close();
			
			response.getWriter().println("�����ɹ�.....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
				doGet(request, response);
		
	}

}
