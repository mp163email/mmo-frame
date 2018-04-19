package com.mmo.frame.utils;

import com.ws.util.path.helper.PathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * Copyright(C),2015,GY Game
 * @author shadow
 * @Created 2016年1月6日
 * Note: 屏蔽字过滤
 */
public class GMsgFilter
{
	private static final Logger logger = LoggerFactory.getLogger(GMsgFilter.class);

	// 信息内容过滤参数
	private static List<String> _contentFilterRegs = new ArrayList<String>();
	// 名称过滤参数
	private static List<String> _nameFilterRegs = new ArrayList<String>();

	private static String[] _replaceContent = null;

	private static final byte[] _chars = "*********************************************************************************"
			.getBytes();

	static
	{
		initFilterRegs(_contentFilterRegs, PathHelper.getRootPath()
				+ "filter" + File.separator + "filter.txt");
		initFilterRegs(_nameFilterRegs, PathHelper.getRootPath()
				+ "filter" + File.separator + "filter.txt");
		if (_contentFilterRegs.size() > 0)
		{
			int maxLen = _contentFilterRegs.get(_contentFilterRegs.size() - 1)
					.length();
			_replaceContent = new String[maxLen];
			for (int i = 0; i < maxLen; i++)
			{
				_replaceContent[i] = new String(_chars, 0, i + 1);
			}
		}
	}

	/**
	 * 解析过滤配置文件
	 * 
	 * @param filepath
	 *            文件路径
	 */
	public static void initFilterRegs(List<String> args, String filepath)
	{
		InputStream in = null;
		BufferedReader reader = null;
		try	{
			in = new FileInputStream(filepath);
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String s = reader.readLine();
			while (s != null)
			{
				if (s.length() > 0)
				{
					args.add(s.trim());
				}
				s = reader.readLine();
			}
			Collections.sort(args, new Comparator<String>()
			{
				public int compare(String arg0, String arg1)
				{
					if (arg0.length() > arg1.length())
					{
						return 1;
					} 
					else if(arg0.length() == arg1.length())
					{
						return 0;
					}
					else
					{
						return -1;
					}
				}
			});
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}

	public synchronized static void init()
	{
		GMsgFilter.isLawfullyName("");
	}

	/**
	 * 判断名称是否合法
	 * 
	 * @param name
	 *            要检查的名称
	 */
	public static boolean isLawfullyName(String name)
	{
		if (name == null || name.equals(""))
			return false;
		int len = _nameFilterRegs.size();
		int ban = len / 2 + len % 2;
		for (int i = 0; i < ban; i++)
		{
			String reg = _nameFilterRegs.get(i);
			String reg2 = _nameFilterRegs.get(len - 1 - i);
			if (name.contains(reg) || name.contains(reg2))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 过滤字符串中的非法字符
	 * 
	 * @param msg
	 *            要过滤的字符串
	 * @return 过滤后的结果
	 */
	public static String doContentFliter(String msgParam)
	{
		String msg = msgParam;
		int len = _contentFilterRegs.size();
		if (msg == null || msg.equals("") || len == 0)
			return msg;
		for (int i = 0; i < len; i++)
		{
			String reg = _contentFilterRegs.get(i);
			if (reg.length() > msg.length())
			{
				break;
			}
			if (msg.indexOf(reg) != -1)
			{
				msg = msg.replace(reg, _replaceContent[reg.length() - 1]);
			}
		}
		return msg;
	}

	public static void main(String[] args)
	{
		System.out.println(GMsgFilter.isLawfullyName("fuck"));
	}
}
