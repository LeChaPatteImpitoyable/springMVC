package com.ying.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ying.client.base.BaseResponse;
import com.ying.client.base.RespCode;
import com.ying.common.Constants;
import com.ying.common.exception.BackendException;

import net.sf.jxls.transformer.XLSTransformer;

public class XmenUtils {

	private static final Logger LOG = LoggerFactory.getLogger(XmenUtils.class);

	public static final String DEFAULT_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

	public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DEFAULT_WEEK_FORMAT = "yyyy-MM-dd EEEE  HH:mm:ss";

	public static final String TIME_WEEK_FORMAT = "yyyy-MM-dd EEEE  HH:mm:ss";

	public static final String WECHAT_TIME_FORMAT = "yyyyMMddHHmmss";

	public static final String DATE_FORMAT = "yyyyMMdd";

	public static final String DATE_FORMAT_NOSPID = "yyyy-MM-dd";

	public static final String DATE_FORMAT_SECONDS = "yyyy-MM-dd HH:mm";

	public static final String DATE_FORMAT_SECONDS1 = "MM-dd HH:mm";

	public static final String DATE_FORMAT_HOURS = "yyyy-MM-dd HH";

	public static final DateFormat DEFAULT_DISPLAY_HOURS = new SimpleDateFormat(DATE_FORMAT_HOURS);

	public static final DateFormat DEFAULT_DISPLAY_TIME = new SimpleDateFormat(DEFAULT_TIME_FORMAT);

	public static final DateFormat DEFAULT_DISPLAY_DATE = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

	public static final DateFormat TIME_DISPLAY = new SimpleDateFormat(TIME_FORMAT);

	private static final DateFormat DATE_DISPLAY = new SimpleDateFormat(DATE_FORMAT);

	public static final DateFormat NOSPID_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_NOSPID);

	public static final DateFormat DATE_FORMAT_SECONDS_DISPLAY = new SimpleDateFormat(DATE_FORMAT_SECONDS);

	public static final String EMPTY = "";

	public static final String pay_number_regexp = "^[+]?(([1-9]\\d*[.]?)|(0.))(\\d{0,2})?$";

	public static final String DAY_START = " 00:00:00";

	public static final String DAY_END = " 23:59:59";
	/**
	 * 正则表达式：快递单号
	 */
	public static final String EXPRESS_NUMBER = "^[a-zA-Z0-9]\\w{0,}$";
	public static final String ORDER_NUMBER = "^[0-9]\\d{1,23}$";
	public static final String BRACELET_NUMBER = "^[a-zA-Z]{2}[0-9]{4}$";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	private static Properties VELOCITY_PROPS = new Properties();

	/**
	 * is empty
	 * 
	 * @param list
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * is empty
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(String[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * is empty
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * is empty
	 * 
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.size() == 0;
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * convert double to string type
	 * 
	 * @param data
	 * @return
	 */
	public static String convertDoubleToStr(double data) {
		return String.valueOf(data);
	}

	/**
	 * split str by ";"
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> splitBySem(String str) {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isEmpty(str)) {
			return result;
		}

		try {
			String[] array = str.split(";");
			result = Arrays.asList(array);
		} catch (Exception e) {
			// do log
			return null;
		}

		return result;
	}

	/**
	 * split sem string to integer
	 * 
	 * @param input
	 * @return
	 */
	public static List<Integer> splitSemstrToInt(String input) {

		if (StringUtils.isEmpty(input)) {
			return null;
		}

		String[] array = splitStrToArray(input);
		if (isEmpty(array)) {
			return null;
		}
		List<Integer> result = new ArrayList<Integer>();

		for (String s : array) {
			result.add(Integer.valueOf(s));
		}
		return result;
	}

	/**
	 * split sem string to integer
	 * 
	 * @param input
	 * @return
	 */
	public static int[] splitSemstrToArray(String input) {

		if (StringUtils.isEmpty(input)) {
			return null;
		}

		String[] array = splitStrToArray(input);
		if (isEmpty(array)) {
			return null;
		}

		int[] ret = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			ret[i] = Integer.parseInt(array[i]);
		}
		return ret;
	}

	/**
	 * split sem string to float
	 * 
	 * @param input
	 * @return
	 */
	public static List<Float> splitSemstrToFloat(String input) {

		if (StringUtils.isEmpty(input)) {
			return null;
		}

		String[] array = splitStrToArray(input);
		if (isEmpty(array)) {
			return null;
		}
		List<Float> result = new ArrayList<Float>();

		for (String s : array) {
			result.add(Float.valueOf(s));
		}
		return result;
	}

	/**
	 * split sem string
	 * 
	 * @param input
	 * @return
	 */
	public static String[] splitStrToArray(String input) {
		String[] array = null;
		try {
			array = input.split(";");
		} catch (Exception e) {
			// do log
			return null;
		}
		return array;
	}

	/**
	 * format date
	 * 
	 * @param data
	 * @return
	 */
	public static String toDisplayTime(String data) {
		if (StringUtils.isEmpty(data)) {
			return EMPTY;
		}
		return DEFAULT_DISPLAY_TIME.format(new Date(Long.valueOf(data)));
	}

	/**
	 * format date
	 * 
	 * @param data
	 * @return
	 */
	public static String toDisplayDate(String data) {
		if (StringUtils.isEmpty(data)) {
			return EMPTY;
		}
		return DEFAULT_DISPLAY_TIME.format(new Date(Long.valueOf(data)));
	}

	public static Date toDisplayDateToSecond(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		try {
			return TIME_DISPLAY.parse(date);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 
	 * @param timeMills
	 * @param formattedStr
	 * @return
	 */
	public static String formatDate(String timeMills, String formattedStr) {

		if (StringUtils.isEmpty(timeMills) || StringUtils.isEmpty(formattedStr)) {
			LOG.error("input param is null!!!");
			return null;
		}

		Date date = new Date(Long.valueOf(timeMills));

		DateFormat df = new SimpleDateFormat(formattedStr);

		return df.format(date);
	}

	/**
	 * format date
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		try {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String ninetyDaysAge() {
		return String.valueOf(System.currentTimeMillis() - Constants.TIME.NINETY_DAY_MILLS);
	}

	/**
	 * decode bigdata
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] decodeBase64(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}

		byte[] b;
		try {
			b = s.getBytes(Constants.UTF8);
		} catch (UnsupportedEncodingException e) {
			b = s.getBytes();
		}

		if (!Base64.isArrayByteBase64(b)) {
			return null;
		}

		return Base64.decodeBase64(b);
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getTime() {
		Calendar cale = Calendar.getInstance();
		return TIME_DISPLAY.format(cale.getTime());
	}
	
	public static String getDate() {
		Calendar cale = Calendar.getInstance();
		return DATE_DISPLAY.format(cale.getTime());
	}

	/*
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static Date getSpecifiedDayBefore(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_NOSPID).parse(specifiedDay);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		return c.getTime();
	}

	public static String getDate(Date dateTime) {
		String date = null;
		date = new SimpleDateFormat(DATE_FORMAT_NOSPID).format(dateTime);

		return date;
	}

	/**
	 * 获得指定日期的结束时间 23:59:59
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Date getSpecifiedDayEndTime(Date specifiedDay) {
		if (specifiedDay == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		Date date = specifiedDay;

		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTime();

	}

	/**
	 * 获得指定日期的结束时间 23:59:59
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Date getSpecifiedDayEndTime(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_NOSPID).parse(specifiedDay);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}

		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTime();

	}

	public static Date getSpecifiedDayStartTime(Date specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = specifiedDay;

		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();

	}

	public static Date getSpecifiedDayStartTime(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_NOSPID).parse(specifiedDay);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}

		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();

	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Date getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_NOSPID).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		return c.getTime();

	}

	public static String stringToStringDate(String dateString) {
		try {
			Date date = new Date(Long.valueOf(dateString));
			return NOSPID_DATE_FORMAT.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date) {
		try {
			return NOSPID_DATE_FORMAT.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param c
	 * @param length
	 * @param content
	 * @return
	 */
	public static String flushLeft(char c, long length, String content) {
		String str = "";
		StringBuffer cb = new StringBuffer();
		if (content.length() >= length) {
			return content;
		}
		for (int i = 0; i < length - content.length(); i++) {
			cb.append(c);
		}
		str = cb.append(content).toString();
		return str;
	}

	public static boolean isPayNumber(String str) {

		// 验证是否符合金额数值规则
		if (str != null && str.length() > 0) {
			Pattern pattern = Pattern.compile(pay_number_regexp);
			return pattern != null ? pattern.matcher(str).matches() || String.valueOf(0).equals(str) : false;
		}
		return false;
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @param date
	 *            时间(2015-07-08 12:29:01)
	 * @param now
	 *            (new Date())
	 * @return 1(date>=now) -1(date<now)
	 */
	public static int compareDate(Date date, Date now) {
		int result = -2;
		try {
			if (date.getTime() >= now.getTime()) {
				result = 1;
			} else if (date.getTime() < now.getTime()) {
				result = -1;
			}
		} catch (Exception e) {
			result = -2;
		}
		return result;
	}

	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public static String mobileToUsername(String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return null;
		}

		StringBuffer sb = new StringBuffer();

		try {
			sb.append(mobile.substring(0, 3));
			sb.append("****");
			sb.append(mobile.substring(7, mobile.length()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return mobile;
		}

		return sb.toString();
	}

	/**
	 * 截取前number 个字符
	 * 
	 * @param data
	 * @param number
	 * @return
	 */
	public static String interceptString(String data, int number) {
		if (StringUtils.isEmpty(data) || number <= 0) {
			return data;
		}
		if (data.length() > number) {
			return data.substring(0, number);
		}
		return data;
	}

	/**
	 * 
	 * @param position
	 * @return
	 */
	public static String getLoginSpot(String position) {

		if (StringUtils.isEmpty(position)) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		String[] posArray = position.split(";");

		if (posArray != null && posArray.length > 0) {
			for (String pos : posArray) {
				try {
					double posV = Double.valueOf(pos) * 100;
					sb.append((posV - posV % 5) / 100).append(";");
				} catch (NumberFormatException e) {
					LOG.error("getLoginSpot error!!!", e);
				}
			}
		}

		return sb.toString();
	}

	public static String obtainBeforeDate(String datetime, int number) {
		if (datetime != null && datetime.length() > 0 && number >= 0) {
			try {
				Date date = TIME_DISPLAY.parse(datetime);
				long time = 24 * 60 * 60 * 1000 * number;
				long differtime = date.getTime() - time;
				return TIME_DISPLAY.format(differtime);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public static String getDateTime() {
		try {
			Calendar cale = Calendar.getInstance();
			return TIME_DISPLAY.format(cale.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * @param size
	 * @return
	 */
	public static int verifySize(int size) {
		if (size <= Constants.NUMBER.ZERO) {
			size = Constants.MIN_SIZE;
		}
		if (size >= Constants.MAX_SIZE) {
			size = Constants.MAX_SIZE;
		}
		return size;
	}

	/**
	 * 计算底部四个帖子的分享数
	 * 
	 * @param postId
	 * @return
	 */
	public static int calcShareNumber(String postId) {
		int idsLength = 0;
		// 1，参数检查
		if (postId != null && postId.length() > 0) {
			String ids[] = postId.trim().split(";");
			idsLength = ids.length;
		}
		return idsLength;
	}

	/**
	 * copy from old pro
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	private static byte[] encrypt(String content, String password) {
		try {
			if (password != null && password.length() > 0) {

				KeyGenerator kgen = KeyGenerator.getInstance("AES");
				// 防止linux下 随机生成key
				SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
				secureRandom.setSeed(password.getBytes());
				kgen.init(128, secureRandom);
				SecretKey secretKey = kgen.generateKey();
				byte[] enCodeFormat = secretKey.getEncoded();
				SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
				Cipher cipher = Cipher.getInstance("AES");// 创建密码器
				byte[] byteContent = content.getBytes("utf-8");
				cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
				byte[] result = cipher.doFinal(byteContent);
				return result; // 加密
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String encodeBase64(String s) {
		try {
			return encodeBase64(s.getBytes(Constants.UTF8));
		} catch (UnsupportedEncodingException e) {
			return encodeBase64(s.getBytes());
		}
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public static String encodeBase64(byte[] b) {
		byte[] b2 = Base64.encodeBase64(b);

		try {
			return new String(b2, Constants.UTF8);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
			return new String(b2);
		}
	}

	public static String encryptAES(String content, String password) {
		byte[] encryptResult = encrypt(content, password);
		String encrypted = byteArrayToHexString(encryptResult);
		// BASE64位加密
		String result = encodeBase64(encrypted);

		return result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
	}

	/**
	 * 
	 * @param origin
	 * @return
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(resultString.getBytes("UTF-8"));
			resultString = byteArrayToHexString(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	/**
	 * 
	 * @param template
	 * @param context
	 */
	public static String renderTemplate(String template, VelocityContext context) {
		VelocityEngine engine = new VelocityEngine(VELOCITY_PROPS);
		StringWriter writer = new StringWriter();
		engine.evaluate(context, writer, "", template);
		return writer.toString();
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (byte aB : b) {
			resultSb.append(byteToHexString(aB));
		}
		return resultSb.toString();
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 
	 * @param data
	 * @param split
	 * @return
	 */
	public static int[] stringToIntArray(String data, String split) {
		if (StringUtils.isEmpty(data) || StringUtils.isEmpty(split)) {
			return null;
		}
		String datas[] = data.split(split);
		if (datas == null || datas.length <= 0) {
			return null;
		}
		int dataArray[] = new int[datas.length];
		for (int i = 0; i < datas.length; i++) {
			dataArray[i] = Integer.valueOf(datas[i]);
		}
		return dataArray;
	}

	public static int[] listToIntArray(List<Integer> data) {
		if (XmenUtils.isEmpty(data)) {
			return null;
		}
		int[] datas = new int[data.size()];
		for (int i = 0, size = data.size(); i < size; i++) {
			datas[i] = data.get(i);
		}
		return datas;
	}

	public static String getCenterPosition(String position) {
		if (StringUtils.isEmpty(position)) {
			return null;
		}
		String positionArray[] = position.split(";");
		if (XmenUtils.isEmpty(positionArray)) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < positionArray.length; i++) {
			try {
				double posi = Double.valueOf(positionArray[i]) * 100;
				stringBuffer.append((posi - posi % 5) / 100);
				stringBuffer.append(";");
			} catch (Exception e) {
				return null;
			}
		}
		return stringBuffer.toString();
	}

	public static String doHttpGet(String urlStr, int timeout) throws Exception {

		HttpURLConnection conn = null;
		BufferedReader in = null;
		OutputStream out = null;

		try {

			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(Constants.HTTP_METHOD.GET);
			conn.setRequestProperty(Constants.HTTP_CONTENT_TYPE.HEAD_KEY, Constants.HTTP_CONTENT_TYPE.JSON);

			StringBuilder sb = new StringBuilder();
			String line;

			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constants.UTF8));

			while ((line = in.readLine()) != null) {
				sb.append(line).append(Constants.LINE_SEPARATOR);
			}

			return sb.toString();
		} catch (SocketTimeoutException e) {
			LOG.error("[SocketTimeoutException]remoteCall:url[" + urlStr + "] timout:[" + timeout + "]  msg="
					+ e.getMessage());
			throw e;
		} catch (MalformedURLException e) {
			LOG.error("[MalformedURLException]remoteCall:url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} catch (IOException e) {
			LOG.error("[IOException]remoteCall:url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} finally {
			if (out != null) {
				out.flush();
			}
			closeIO(in, out);
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static void closeIO(Closeable... cs) {
		if (cs != null) {
			for (Closeable c : cs) {
				closeIO(c);
			}
		}
	}

	public static void closeIO(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * do post call
	 * 
	 * @param urlStr
	 * @param param
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPost(String urlStr, String param, String contentType, int timeout) throws Exception {

		HttpURLConnection conn = null;
		BufferedReader in = null;
		OutputStream out = null;

		try {

			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(Constants.HTTP_METHOD.POST);
			if (StringUtils.isEmpty(contentType)) {
				conn.setRequestProperty(Constants.HTTP_CONTENT_TYPE.HEAD_KEY, Constants.HTTP_CONTENT_TYPE.FORM);
			} else {
				conn.setRequestProperty(Constants.HTTP_CONTENT_TYPE.HEAD_KEY, contentType);
			}

			out = conn.getOutputStream();
			out.write(param.getBytes(Constants.UTF8));
			out.flush();

			StringBuilder sb = new StringBuilder();
			String line;

			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constants.UTF8));

			while ((line = in.readLine()) != null) {
				sb.append(line).append(Constants.LINE_SEPARATOR);
			}

			return sb.toString();
		} catch (SocketTimeoutException e) {
			LOG.error("[SocketTimeoutException]remoteCall:url[" + urlStr + "] timout:[" + timeout + "]  msg="
					+ e.getMessage());
			throw e;
		} catch (MalformedURLException e) {
			LOG.error("[MalformedURLException]remoteCall:url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} catch (IOException e) {
			LOG.error("[IOException]remoteCall:url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} finally {
			if (out != null) {
				out.flush();
			}
			closeIO(in, out);
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> parseWeChatXml(String inputXml) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(inputXml);
			Element root = document.getRootElement();

			List<Element> childElements = root.elements();
			for (Element child : childElements) {
				resultMap.put(child.getName(), child.getStringValue());
			}
		} catch (DocumentException e) {
			LOG.error(e.getMessage(), e);
		}

		return resultMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> parseAlipayXml(String inputXml) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(inputXml);
			Element root = document.getRootElement();

			List<Element> childElements = root.elements();
			Element findTrade = null;

			Element responseElement = null;
			for (Element child : childElements) {
				if (!child.getName().equalsIgnoreCase("request")) {
					if (!child.getName().equalsIgnoreCase("response")) {
						resultMap.put(child.getName(), child.getStringValue());
					} else {
						responseElement = child;
					}
				}
			}

			if (responseElement != null) {
				findTrade = responseElement.element("trade");
			}

			if (findTrade != null) {
				List<Element> tradeChildElements = findTrade.elements();
				for (Element tradeChild : tradeChildElements) {
					resultMap.put(tradeChild.getName(), tradeChild.getStringValue());
				}
			}

		} catch (DocumentException e) {
			LOG.error(e.getMessage(), e);
		}

		return resultMap;
	}

	/**
	 * 
	 */
	public static String addDoubleValue(String a, String b) {

		if (StringUtils.isEmpty(a) && StringUtils.isEmpty(b)) {
			return "0.00";
		}

		if (StringUtils.isEmpty(a)) {
			return b;
		}

		if (StringUtils.isEmpty(b)) {
			return a;
		}

		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);

		BigDecimal result = b1.add(b2);
		String ret = result.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

		return ret;
	}

	/**
	 * 
	 */
	public static String reduceDoubleValue(String a, String b) {

		if (StringUtils.isEmpty(a) && StringUtils.isEmpty(b)) {
			return "0.00";
		}

		if (StringUtils.isEmpty(a)) {
			return b;
		}

		if (StringUtils.isEmpty(b)) {
			return a;
		}

		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);

		BigDecimal result = b1.subtract(b2);
		String ret = result.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

		return ret;
	}

	/**
	 * 
	 */
	public static String changeFen2Yuan(long fen) {

		BigDecimal b1 = new BigDecimal(Double.toString(fen));
		BigDecimal b2 = new BigDecimal(Double.toString(100));
		BigDecimal yuan = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
		return yuan.toString();
	}

	/**
	 * 
	 */
	public static String discountPrice(String price, int discount) {

		BigDecimal orignPrice = new BigDecimal(price);
		BigDecimal discountB = new BigDecimal(Double.toString(discount));
		BigDecimal hundred = new BigDecimal(Double.toString(100));

		return orignPrice.multiply(discountB).divide(hundred, 2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static String convertFen2Yuan(BigDecimal fen) {
		BigDecimal b2 = new BigDecimal(Double.toString(100));
		return fen.divide(b2, 2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static int changeYuan2Fen(String yuan) {

		final Double hundred = 100.00;
		BigDecimal b1 = new BigDecimal(yuan);
		BigDecimal b2 = new BigDecimal(Double.toString(hundred));
		double fen = b1.multiply(b2).doubleValue();
		int price = (int) fen;
		return price;
	}

	public static int changeD2I(String yuan) {

		final Double hundred = 10.0;
		BigDecimal b1 = new BigDecimal(yuan);
		BigDecimal b2 = new BigDecimal(Double.toString(hundred));
		double fen = b1.multiply(b2).doubleValue();
		int price = (int) fen;
		return price;
	}

	public static String changeI2D(long fen) {

		BigDecimal b1 = new BigDecimal(Double.toString(fen));
		BigDecimal b2 = new BigDecimal(Double.toString(10));
		BigDecimal yuan = b1.divide(b2, 1, BigDecimal.ROUND_HALF_UP);
		return yuan.toString();
	}

	/**
	 * 
	 */
	public static String retainTwoDecimal(double price) {
		return String.format("%.2f", price);
	}

	public static String retainTwoDecimal(String price) {
		if (StringUtils.isEmpty(price)) {
			return "0.00";
		}
		return String.format("%.2f", Double.valueOf(price));
	}

	public static String booleanToString(boolean input) {
		return input ? Constants.TRUE : Constants.FALSE;
	}

	/**
	 * get random
	 */
	public static String getSpecLengthRandom(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(Constants.RANDOM_BASE.length());
			sb.append(Constants.RANDOM_BASE.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	public static String putString2MapJson(String mapJson, String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			LOG.warn("保存内容为空key ＝" + key + "，value=" + value);
			return null;
		}

		if (StringUtils.isEmpty(mapJson)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(key, value);
			return JSONObject.toJSONString(map);
		}
		try {
			JSONObject map = JSONObject.parseObject(mapJson);
			map.put(key, value);
			return JSONObject.toJSONString(map);
		} catch (Exception e) {
			LOG.error("解析map对象错误", e);
			throw new BackendException(RespCode.JSON_ERROR);
		}

	}

	public static String getStringFromMapJson(String mapJson, String key) {
		if (StringUtils.isEmpty(mapJson) || StringUtils.isEmpty(key)) {
			return null;
		}
		try {
			JSONObject object = JSONObject.parseObject(mapJson);
			Object valueO = object.get(key);
			if (valueO == null) {
				LOG.warn("jsonMap中没有key ＝" + key + "相关的信息");
				return null;
			}
			return String.valueOf(valueO);
		} catch (Exception e) {
			LOG.error("解析map对象错误", e);
			throw new BackendException(RespCode.JSON_ERROR);
		}

	}

	/**
	 * 
	 */
	public static int[] convert2IntArray(String[] input) {
		if (XmenUtils.isEmpty(input)) {
			return null;
		}

		int[] ret = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			ret[i] = Integer.valueOf(input[i]);
		}
		return ret;
	}

	/**
	 * 
	 * @param data
	 * @param other
	 * @return
	 */
	public static String mergeData(String data, String append) {
		String result = null;
		if (StringUtils.isEmpty(data) || data.length() < 2) {
			if (!StringUtils.isEmpty(append)) {
				result = append + ";";
			}
		} else {
			if (!StringUtils.isEmpty(append)) {
				result = data + append + ";";
			} else {
				result = data;
			}
		}
		if (StringUtils.isEmpty(result)) {
			return null;
		}

		return result.replace(";", ",").substring(0, result.length() - 1);
	}

	public static boolean stringEqual(String s1, String s2) {
		boolean flag = false;
		if (StringUtils.isEmpty(s1) || StringUtils.isEmpty(s2)) {
			flag = false;
		} else if (!StringUtils.isEmpty(s1)) {
			flag = s1.equalsIgnoreCase(s2);
		} else {
			flag = s2.equalsIgnoreCase(s1);
		}
		return flag;
	}

	/**
	 * integer集合去重
	 * 
	 * @param list
	 */
	public static int[] integerCollectionDuplicateRemoval(List<Integer> list) {
		if (isEmpty(list)) {
			return null;
		}
		Set<Integer> set = new HashSet<Integer>();
		for (Integer it : list) {
			set.add(it);
		}
		int[] result = new int[set.size()];
		int i = 0;
		for (Iterator<Integer> integer = set.iterator(); integer.hasNext(); i++) {
			result[i] = integer.next().intValue();
		}
		return result;
	}

	public static double changeMeters2Kilometer(int meters) {

		double yuan = 1.0 * meters / 1000;
		BigDecimal b = new BigDecimal(yuan);
		// 保留2位小数
		yuan = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return yuan;
	}

	/**
	 * Fri Jan 08 08:00:23 CST 2016 格式化当前时间
	 */
	public static Date formatStandardTime() {
		Calendar cal = Calendar.getInstance();
		int min = cal.get(Calendar.MINUTE);

		if (min == 0) {
			return cal.getTime();
		} else if (min > 0 && min <= 30) {
			cal.set(Calendar.MINUTE, 30);
		} else if (min == 30) {
			return cal.getTime();
		} else if (min > 30 && min <= 59) {
			cal.add(Calendar.HOUR_OF_DAY, 1);
			cal.set(Calendar.MINUTE, 0);
		}
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 08:00 ---> Fri Jan 08 08:00:00 CST 2016
	 * 
	 * @param takeoutTime
	 */
	public static Date convertHourToDateBySpecialDate(Date specialDate, String takeoutTime) {

		Calendar specialCal = Calendar.getInstance();
		specialCal.setTime(specialDate);
		String[] array = takeoutTime.split("\\:");

		Calendar cal = Calendar.getInstance();
		cal.set(specialCal.get(Calendar.YEAR), specialCal.get(Calendar.MONTH), specialCal.get(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(array[0]));
		cal.set(Calendar.MINUTE, Integer.valueOf(array[1]));
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 根据准备的小时数计算准备的时间点
	 * 
	 * @param now
	 * @param prepare
	 * @return
	 */
	public static Calendar calcPrepareTimeByPrepareHour(Date now, double prepare) {

		String pre = String.valueOf(prepare);
		String[] preArr = pre.split("\\.");

		int hour = Integer.valueOf(preArr[0]);
		int minute = Integer.valueOf(preArr[1]);

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		if (minute != 0) {
			cal.add(Calendar.MINUTE, 30);
		}

		return cal;
	}

	/**
	 * delay天数
	 * 
	 * @param prepare
	 * @return
	 */
	public static int getDelayDays(double prepare) {
		return (int) (prepare / 24);
	}

	/**
	 * delay小时数
	 * 
	 * @param prepare
	 * @return
	 */
	public static double getDelayHours(double prepare) {
		return prepare % 24;
	}

	/**
	 * date --> 08:00
	 * 
	 * @param input
	 * @return
	 */
	public static String splitToHour(Date input) {
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(input);
	}

	public static String splitToDate(Date input, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(input);
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static Date convertTakeoutDate(String time) {
		DateFormat df = new SimpleDateFormat("HH:mm");
		try {
			return df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static double diffHours(Calendar start, Calendar end) {
		double diffHours = 0;
		while (start.before(end)) {
			start.add(Calendar.HOUR_OF_DAY, 30);
			diffHours += 0.5;
		}
		return diffHours;
	}

	/**
	 * 转换时间 2.5 --> {2(hour), 5(minute)}
	 * 
	 * @param input
	 * @return
	 */
	public static int[] convertTakeoutHour(double input) {

		int[] result = new int[2];
		int hour = (int) input;
		int minute = (int) ((input - (int) input) * 10);
		result[0] = hour;
		result[1] = minute;
		return result;
	}

	/**
	 * 12:30 --> 12.5 12:00 --> 12.0
	 * 
	 * @return
	 */
	public static double convertToDec(double time) {
		if ((time - (int) time) > 0) {
			return (int) time + 0.5;
		}
		return time;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String increaseTakeoutDate(String input) {
		try {
			Date date = new SimpleDateFormat(DATE_FORMAT_NOSPID).parse(input);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			return new SimpleDateFormat(DATE_FORMAT_NOSPID).format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String increaseTakeoutMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return new SimpleDateFormat(DATE_FORMAT_NOSPID).format(cal.getTime());
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static int calcCommonDays(String input) {
		try {
			Date date = new SimpleDateFormat(DATE_FORMAT_NOSPID).parse(input);
			Calendar start = Calendar.getInstance();
			start.setTime(date);

			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			end.add(Calendar.DAY_OF_MONTH, -1);
			end.set(Calendar.HOUR_OF_DAY, 0);
			end.set(Calendar.MINUTE, 0);
			end.set(Calendar.SECOND, 0);
			end.set(Calendar.MILLISECOND, 0);

			int cnt = 0;
			for (Calendar tmp = start; tmp.before(end);) {
				tmp.add(Calendar.DAY_OF_MONTH, 1);
				cnt++;
			}

			return cnt;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 增加黑名单列表
	 * 
	 * @param blackList
	 * @param id
	 * @return
	 */
	public static String addToBlackList(String blackList, int id) {
		if (StringUtils.isEmpty(blackList)) {
			return id + ";";
		}
		int[] ids = splitSemstrToArray(blackList);
		for (int i : ids) {
			if (i == id) {
				return blackList;
			}
		}
		return blackList + id + ";";
	}

	/**
	 * 从黑名单中删除id
	 * 
	 * @param blackList
	 * @param id
	 * @return
	 */
	public static String deleteFromBlackList(String blackList, int id) {
		if (StringUtils.isEmpty(blackList)) {
			throw new IllegalArgumentException("blacklist is null.");
		}

		String[] array = blackList.split(";");

		StringBuffer sb = new StringBuffer();
		for (String str : array) {
			if (Integer.parseInt(str) == id) {
				continue;
			}
			sb.append(str).append(";");
		}

		return sb.toString();
	}

	/**
	 * 数组中是否包含指定的id
	 * 
	 * @param array
	 * @param id
	 * @return
	 */
	public static boolean arrayContainId(String[] array, String id) {
		if (isEmpty(array)) {
			return false;
		}

		if (StringUtils.isEmpty(id)) {
			return false;
		}

		for (String str : array) {
			if (str.equals(id)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 数组中是否包含指定的id
	 * 
	 * @param array
	 * @param id
	 * @return
	 */
	public static boolean arrayContainId(int[] arrays, int id) {
		if (isEmpty(arrays)) {
			return false;
		}

		if (StringUtils.isEmpty(id)) {
			return false;
		}

		for (int array : arrays) {
			if (array == id) {
				return true;
			}
		}

		return false;
	}

	public static String addId2ArrayString(String array, int id) {
		if (StringUtils.isEmpty(array)) {
			return id + Constants.SEMICOLON;
		}

		int[] arrays = XmenUtils.splitSemstrToArray(array);
		if (XmenUtils.isEmpty(arrays)) {
			return id + Constants.SEMICOLON;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] != id) {
				sb.append(arrays[i]).append(Constants.SEMICOLON);
			}
		}
		sb.append(id).append(Constants.SEMICOLON);
		return sb.toString();
	}

	public static String addString2ArrayString(String array, String id) {
		if (StringUtils.isEmpty(array)) {
			return id + Constants.SEMICOLON;
		}

		String[] arrays = XmenUtils.splitStrToArray(array);
		if (XmenUtils.isEmpty(arrays)) {
			return id + Constants.SEMICOLON;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrays.length; i++) {
			if (!arrays[i].equalsIgnoreCase(id)) {
				sb.append(arrays[i]).append(Constants.SEMICOLON);
			}
		}
		sb.append(id).append(Constants.SEMICOLON);
		return sb.toString();
	}

	public static String removeId4ArrayString(String array, int id) {
		if (StringUtils.isEmpty(array)) {
			return null;
		}

		int[] arrays = XmenUtils.splitSemstrToArray(array);
		if (XmenUtils.isEmpty(arrays)) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] != id) {
				sb.append(arrays[i]).append(Constants.SEMICOLON);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param coCode
	 *            快递code
	 * @param number
	 *            快递单号
	 * @return
	 */
	public static String makeKuaidiniaoParam(String coCode, String number, String appId, String appKey) {
		String baseParam = "{'OrderCode':'','ShipperCode':'" + coCode + "','LogisticCode':'" + number + "'}";
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("RequestData", URLEncoder.encode(baseParam, "UTF-8"));
			params.put("EBusinessID", appId);
			params.put("RequestType", "1002");
			String dataSign = encryptKuaidiniao(baseParam, appKey);
			params.put("DataSign", URLEncoder.encode(dataSign, "UTF-8"));
			params.put("DataType", "2");

			StringBuilder param = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (param.length() > 0) {
					param.append("&");
				}
				param.append(entry.getKey());
				param.append("=");
				param.append(entry.getValue());
			}
			return param.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 快递鸟签名生成
	 * 
	 * @param content
	 *            内容
	 * @param keyValue
	 *            Appkey
	 * @param charset
	 *            编码方式
	 * @throws UnsupportedEncodingException
	 *             ,Exception
	 * @return DataSign签名
	 */
	public static String encryptKuaidiniao(String content, String keyValue)
			throws UnsupportedEncodingException, Exception {
		if (keyValue != null) {
			return encodeBase64(MD5Encode(content + keyValue));
		}
		return encodeBase64(MD5Encode(content));
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml2Map(String inputXml) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(inputXml);
			Element root = document.getRootElement();

			List<Element> childElements = root.elements();
			for (Element child : childElements) {
				resultMap.put(child.getName(), child.getStringValue());
			}
		} catch (DocumentException e) {
			LOG.error(e.getMessage(), e);
		}

		return resultMap;
	}

	/**
	 * 
	 * @return
	 */
	public static String getPreMonthday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return TIME_DISPLAY.format(cal.getTime());
	}

	// /**
	// *
	// * @param pos
	// * @return
	// */
	// public static List<BusinessOrderDTO>
	// fillSellerOrders(List<BusinessOrderPO> pos){
	//
	// if(XmenUtils.isEmpty(pos)){
	// return null;
	// }
	//
	// List<BusinessOrderDTO> dtos = new ArrayList<BusinessOrderDTO>();
	// for(BusinessOrderPO po : pos){
	// BusinessOrderDTO dto = new BusinessOrderDTO();
	// dto.setId(po.getId());
	// dto.setSellerId(po.getSellerId());
	// dto.setStatus(po.getStatus());
	// dto.setBuyerId(po.getBuyerId());
	// dto.setOrderNo(po.getOrderNo());
	// dto.setReceiverAddress(po.getReceiverAddress());
	// dto.setReceiverTelephone(po.getReceiverTelephone());
	// dto.setBidPrice(po.getBidPrice());
	// dto.setTime(po.getCreateTime());
	// dtos.add(dto);
	// }
	//
	// return dtos;
	// }

	public static String getRandom(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * str是否在data中
	 * 
	 * @param data
	 * @param str
	 * @return
	 */
	public static boolean contains(String[] data, String str) {
		if (XmenUtils.isEmpty(data) || StringUtils.isEmpty(str)) {
			return false;
		}
		for (String string : data) {
			boolean flag = string.contentEquals(str);
			if (flag) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(int[] datas, int find) {
		if (XmenUtils.isEmpty(datas) || StringUtils.isEmpty(find)) {
			return false;
		}
		for (int data : datas) {
			if (data == find) {
				return true;
			}
		}
		return false;
	}

	// 去掉所有空格
	public static String replaceBlank(String content) {
		String replacedContent = null;
		if (content != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(content);
			replacedContent = m.replaceAll("");
		}
		return replacedContent;
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取某个期限后的日期
	 * 
	 * @param nowDate
	 *            "20160101"
	 * @param format
	 *            "yyyy/MM/dd"
	 * @param type
	 *            Calendar.MONTH
	 * @param how
	 *            1
	 * @return
	 */
	public static Date getFutureDate(long date, int type, int how) {
		Date date2 = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.add(type, how);
		date2 = calendar.getTime();
		return date2;
	}

	/**
	 * 比较两个日期是否是同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean equalsDate(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		String strDate1 = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + ""
				+ calendar.get(Calendar.DATE);
		calendar.setTime(date2);
		String strDate2 = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + ""
				+ calendar.get(Calendar.DATE);
		return strDate1.equals(strDate2);
	}

	/**
	 * 留两位小数转换成百分数
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static String decimalConverPercent(float number1, int number2) {

		double percentage = number1 / number2;
		String str = String.format("%.2f", percentage);
		percentage = Double.parseDouble(str);

		int result = (int) (percentage * 100);
		return String.valueOf(result) + "%";
	}

	public static String handleSearchKeyWords(String keyword) {
		if (StringUtils.isEmpty(keyword)) {
			return keyword;
		}
		keyword.trim();
		keyword = keyword.replace("\\", "\\\\").replace("'", "''").replace("%", "\\%").replace("_", "\\_");
		return keyword;
	}

	/**
	 * dir 请确保目录存在 且有读写权限 fileName 规范:前缀+日期 如:exp20161212 StringBuffer中文件的格式为:
	 * 订单号 状态 支付方式\r\n 12345 1 5\r\n
	 * 
	 * @param sb
	 * @param mode
	 * @param dir
	 * @return
	 */
	public static boolean saveDate(StringBuffer sb, String fileName, String dir) {
		File file = new File(dir, fileName);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(sb.toString());
		} catch (IOException e) {
			LOG.error("写入文件时异常！");
			return false;
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				LOG.error("写入文件，关闭流时异常！");
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验快递单
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isExpressNumber(String expressNumber) {
		return Pattern.matches(EXPRESS_NUMBER, expressNumber);
	}

	public static boolean isOrderNumber(String orderNo) {
		return Pattern.matches(ORDER_NUMBER, orderNo);
	}

	public static boolean checkBraceletNO(String braceletNO) {
		return Pattern.matches(BRACELET_NUMBER, braceletNO);
	}

	public static double stringToDouble(String data, int scale) {
		if (StringUtils.isEmpty(data)) {
			return 0.0;
		}
		double dataDouble = Double.parseDouble(data);
		BigDecimal bigDecimal = new BigDecimal(dataDouble);
		return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 将时间精确到小时输出 2016-10-12 11:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date timeScalHours(Date date, String scalTime) {
		String dateStr = NOSPID_DATE_FORMAT.format(date);
		String dateHours = dateStr + scalTime;
		try {
			return TIME_DISPLAY.parse(dateHours);
		} catch (ParseException e) {
			LOG.error("将时间精确到小时输出!e" + e);
		}
		return null;
	}

	/**
	 * 将"2016-01-21 星期一 12:00-12:30"
	 * 
	 * @param time
	 */
	public static Date[] StringTime2Date(String time) {
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		Date[] date = new Date[2];
		String timeSart = "";
		String timeEnd = "";
		String timeTrim = time.trim();
		String timeStr = timeTrim.replaceAll("\\s+", " ");
		String[] timeArry = timeStr.split(" ");
		if (timeArry.length == 3) {
			String[] f = timeArry[2].split("-");
			timeSart = timeArry[0] + " " + f[0] + ":00";
			timeEnd = timeArry[0] + " " + f[1] + ":00";
		}
		if (StringUtils.isEmpty(timeSart) || StringUtils.isEmpty(timeEnd)) {
			return null;
		}
		Date timeSartFinal = toDisplayDateToSecond(timeSart);
		Date timeEndFinal = toDisplayDateToSecond(timeEnd);
		date[0] = timeSartFinal;
		date[1] = timeEndFinal;
		return date;

	}

	/**
	 * 获取星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	public static Date stringToDate(String dateString) {
		try {
			return NOSPID_DATE_FORMAT.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date stringToDateTime(String date, DateFormat format) {
		try {
			return format.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 读取Excel表格的内容
	 * 
	 * @param file
	 * @return
	 */
	public static List<Map<Integer, String>> readExcelContent(File file) {
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(new FileInputStream(file));
		} catch (Exception ex) {
			try {
				wb = new HSSFWorkbook(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Map<Integer, String>> maps = new ArrayList<Map<Integer, String>>();
		// 循环工作表
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 循环行Row
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				Row row = sheet.getRow(j);
				Map<Integer, String> map = new HashMap<Integer, String>();
				for (int k = 0; k < row.getLastCellNum(); k++) {
					Cell cell = row.getCell(k);
					int type = cell.getCellType();
					String value = "";
					if (type == HSSFCell.CELL_TYPE_NUMERIC) {
						DecimalFormat df = new DecimalFormat("#");
						String number = df.format(cell.getNumericCellValue());
						value += number;
					} else if (type == HSSFCell.CELL_TYPE_BOOLEAN) {
						// 返回布尔类型
						boolean bool = cell.getBooleanCellValue();
						value += bool;
					} else if (type == HSSFCell.CELL_TYPE_STRING) {
						// 返回字符串类型
						String str = cell.getRichStringCellValue().getString();
						value += str;
					}
					map.put(k, value);
				}
				maps.add(map);
			}
		}

		return maps;
	}

	/**
	 * 获取各个年龄段的标准身高 体重
	 * 
	 * @param age
	 * @param sex
	 * @return
	 */
	public static Map<String, String> getStandardStatureAndWeightByAge(int age, String sex) {

		Map<String, String> map = new HashMap<String, String>();
		if ((age > 0) && (age < 3)) {
			map.put("stature", "76.5");
			map.put("weight", "10");
		}
		if (sex.equals("男")) {
			if ((age >= 3) && (age < 6)) {
				map.put("stature", "110");
				map.put("weight", "17");
			} else if ((age >= 6) && (age < 9)) {
				map.put("stature", "132");
				map.put("weight", "25");
			} else if ((age >= 9) && (age < 12)) {
				map.put("stature", "145");
				map.put("weight", "37");
			} else if ((age >= 12) && (age < 16)) {
				map.put("stature", "168");
				map.put("weight", "55");
			} else {
				map.put("stature", "175");
				map.put("weight", "60");
			}
		} else if (sex.equals("女")) {// 女
			if ((age >= 3) && (age < 6)) {
				map.put("stature", "106");
				map.put("weight", "17");
			} else if ((age >= 6) && (age < 9)) {
				map.put("stature", "128");
				map.put("weight", "23");
			} else if ((age >= 9) && (age < 12)) {
				map.put("stature", "143");
				map.put("weight", "34");
			} else if ((age >= 12) && (age < 16)) {
				map.put("stature", "158");
				map.put("weight", "42");
			} else {
				map.put("stature", "168");
				map.put("weight", "50");
			}
		}

		return map;

	}

	public static void checkRespStatus(BaseResponse resp) {
		if (!RespCode.RESP_OK.getCode().equals(resp.getRespStatus().getCode())) {
			throw new RuntimeException(resp.getRespStatus().getCode());
		}
	}
	
	public static void createExcel(String srcFilePath, List<?> list, String destFilePath){
		try { 
			XLSTransformer transformer = new XLSTransformer();
			
//			URL url = this.getClass().getClassLoader().getResource("");
			
//			String srcFilePath = url.getPath() + templateFileName;
			Map<String,Object> map = new HashMap<String,Object>(); 
			map.put("list", list);
//			String destFilePath = url.getPath() + resultFileName;
			
			transformer.transformXLS(srcFilePath, map, destFilePath);
		} catch (Exception e) {
			throw new RuntimeException("error happens...", e);
		}
	}
}
