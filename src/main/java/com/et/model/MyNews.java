package com.et.model;

import java.util.List;
import java.util.Map;

public class MyNews {
	/**
	 * �������
	 * @param title
	 * @param contenet
	 * @param newPath
	 * @throws Exception
	 */
	public void insertNews(String title,String contenet,String  newPath,String createTime) throws Exception{
		String sql="insert into mynews(title,content,htmlpath,createtime) values('"+title+"','"+contenet+"','"+newPath+"','"+createTime+"')";
		System.out.println(sql);
		MySqlTools.execute(sql);
	}
	/**
	 * ��ѯ��������
	 * @throws Exception 
	 */
	public List<Map> queryNews() throws Exception{
		String sql = "select * from mynews";
		System.out.println(sql);
		return MySqlTools.query(sql);
	}
}
