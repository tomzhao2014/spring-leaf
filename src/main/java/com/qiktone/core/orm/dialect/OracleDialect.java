package com.qiktone.core.orm.dialect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toUpperCase().endsWith(" FOR UPDATE")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append("SELECT * FROM ( SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) ROW_ ) WHERE ROWNUM_ > "+offset+" AND ROWNUM_ <= "+(offset + limit));
		if (isForUpdate) {
			pagingSelect.append(" FOR UPDATE");
		}
		return pagingSelect.toString();
	}

	@Override
	public String getCountString(String sql) {
		sql		= sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
		int orderIndex  = getLastOrderInsertPoint(sql);
		
		int formIndex   = getAfterFormInsertPoint(sql);
		String select   = sql.substring(0, formIndex);
		
		//如果SELECT 中包含 DISTINCT 只能在外层包含COUNT
		if (select.toUpperCase().indexOf("SELECT DISTINCT") != -1 || sql.toUpperCase().indexOf("GROUP BY")!=-1) {
			return new StringBuffer(sql.length())
				.append("SELECT COUNT(1) COUNT FROM (").append(sql.substring(0, orderIndex)).append(" ) T").toString();
		} else {
			return new StringBuffer(sql.length())
				.append("SELECT COUNT(1) COUNT ").append(sql.substring(formIndex, orderIndex)).toString();
		}
	}
	
	/**
	 * 得到SQL第一个正确的FROM的的插入点
	 */
	private static int getAfterFormInsertPoint(String querySelect) {
		String regex = "\\s+FROM\\s+";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(querySelect);
		while (matcher.find()) {
			int fromStartIndex = matcher.start(0);
			String text = querySelect.substring(0, fromStartIndex);
			if (isBracketCanPartnership(text)) {
				return fromStartIndex;
			}
		}
		return 0;
	}
	
	/**
	 * 得到最后一个Order By的插入点位置
	 * @return 返回最后一个Order By插入点的位置
	 */
	private static int getLastOrderInsertPoint(String querySelect){
		int orderIndex = querySelect.toUpperCase().lastIndexOf("order by");
		if(orderIndex == -1){
			orderIndex = querySelect.length();
		}
		if (!isBracketCanPartnership(querySelect.substring(orderIndex,querySelect.length()))) {
			throw new RuntimeException("分页必须要有Order by 语句!");
		}
		return orderIndex;
	}
	
	/**
	 * 判断括号"()"是否匹配,并不会判断排列顺序是否正确
	 * 
	 * @param text 要判断的文本
	 * @return 如果匹配返回TRUE,否则返回FALSE
	 */
	private static boolean isBracketCanPartnership(String text) {
		if (text == null
				|| (getIndexOfCount(text, '(') != getIndexOfCount(text, ')'))) {
			return false;
		}
		return true;
	}
	
	/**
	 * 得到一个字符在另一个字符串中出现的次数
	 * @param text	文本
	 * @param ch    字符
	 */
	private static int getIndexOfCount(String text, char ch) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			count = (text.charAt(i) == ch) ? count + 1 : count;
		}
		return count;
	}
	
}
