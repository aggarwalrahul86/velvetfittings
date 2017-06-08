package com.velvet.utils;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class StringUtils extends org.springframework.util.StringUtils {

	public static final NumberFormat AMOUNT_FORMATTER = new DecimalFormat(
			"###,###,###,##0.######");
	public static final String BLANK = "";
	public static final String BLANK_SPACE = " ";
	public static final char CHAR_COLON = ':';
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String DELIMINATOR_HASH = "#";
	public static final String DELIMINATOR_HYPHEN = "-";
	public static final String DELIMINATOR_TIDDLE = "~";
	public static final String DELIMINATOR_PIPE = "|";
	public static final String FALSE = "false";
	public static final String TRUE = "true";


	/**
	 * @param str
	 * @return returns true if str is alphanumeric, false otherwise
	 */
	public static boolean alphaNumeric(String str) {
		boolean b = false;
		if (!IsEmpty(str)) {
			b = Pattern.matches("[a-zA-Z0-9\\s]*", str);
		}
		return b;
	}


	/**
	 * @param input
	 * @return returns true if input is blank, false otherwise
	 */
	public static boolean IsBlank(String input) {
		if (input == null || BLANK.equals(input))
			return true;
		else
			return false;
	}

	/**
	 * @param str
	 * @return returns true if str is digit, false otherwise
	 */
	public static boolean isDigit(String str) {
		boolean b = false;
		if (!IsEmpty(str)) {

			b = Pattern.matches("\\d*", str);
		}
		return b;
	}

	/**
	 * @param input
	 * @return returns true if input is empty, false otherwise
	 */
	public static boolean IsEmpty(String input) {
		if (input == null || BLANK.equals(input.trim()))
			return true;
		else
			return false;
	}


	

	public static String[] Trim(String[] source) {
		String[] target = null;
		List<String> tempDestination = new ArrayList<String>();
		if (source != null) {
			for (int k = 0; k < source.length; k++) {
				if (!IsEmpty(source[k])) {
					tempDestination.add(source[k]);
				}

			}
			if (tempDestination.size() > 0) {
				target = new String[tempDestination.size()];
				tempDestination.toArray(target);
			}
		}
		return target;
	}

	private StringUtils() {

	}
	public static boolean isEmptyDrpDwn(String str){
		
		if(IsEmpty(str) || (str !=null && "-1".equals(str))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @param formatString
	 * @param argument
	 * @return returns formatted message
	 */
	public static String FormatMessage(String formatString, String argument) {
		return FormatMessage(formatString, new String[] { argument });
	}

	/**
	 * @param formatString
	 * @param arguments
	 * @return returns formatted message
	 */
	public static String FormatMessage(String formatString, String[] arguments) {
		return MessageFormat.format(formatString, (Object[]) arguments);
	}
	
	/**
	 * Insert the method's description here. Creation date: (11/5/2002 4:56:34
	 * PM)
	 * 
	 * @return java.lang.String
	 * @param strInput
	 *            java.lang.String
	 */
	public static String Trim(String strInput) {
		return (strInput != null) ? strInput.trim() : BLANK;
	}
	

}