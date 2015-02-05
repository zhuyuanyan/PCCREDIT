package com.cardpay.pccredit.pbccrcReport.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEX {
	public static int continuousNumbers(String str){
		int maxCount = 0;
		String regex = "\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			String s = m.group();
			if(s.length()>maxCount){
				maxCount = s.length();
			}
		}
		return maxCount;
	}
}