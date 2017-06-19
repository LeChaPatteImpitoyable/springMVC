package com.ying.common;

public class Constants {

	public static final String UTF8 = "UTF-8";

	public static class DATE_FORMAT {

		public static final String DATE_FORMAT_NOSPID = "yyyy-MM-dd";
	}

	public static final int NUMBER_FALSE = 0;
	public static final int NUMBER_TRUE = 1;

	public static final String SEMICOLON = ";";
	public static final String COMMA = ",";
	public static final String UNDERLINE = "_";
	public static final String COLON = ":";

	public static final String LINE_SEPARATOR = "\r\n";

	public static final int SYNC_COUNT_ONE_TIME = 20;
	// true false
	public static final String TRUE = "1";
	public static final String FALSE = "0";
	public static final int MAX_SIZE = 100;
	public static final int MIN_SIZE = 10;

	public static final String SUCCESS = "1";
	public static final String FAIL = "0";

	public static final long DAY = 24 * 60 * 60 * 1000;

	public static final long MINUTE = 60 * 1000;

	public static final String RANDOM_BASE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static final int IS_DELETE = 1;

	public static final int IS_NOT_DELETE = 0;

	public static class WEEK_DAY {
		public static int MONDAY = 1;
		public static int TUESDAY = 2;
		public static int WEDNESDAY = 3;
		public static int THURSDAY = 4;
		public static int FRIDAY = 5;
		public static int SATURDAY = 6;
		public static int SUNDAY = 7;
	}

	public static class HTTP_METHOD {
		public static final String POST = "POST";
		public static final String GET = "GET";
	}

	public static class HTTP_CONTENT_TYPE {
		public static final String HEAD_KEY = "Content-type";
		public static final String JSON = "application/json";
		public static final String BIN = "application/octet-stream";
		public static final String FORM = "application/x-www-form-urlencoded";
		public static final String XML = "text/xml";
		public static final String IMAGE = "image/png";
		public static final String HTML = "text/html";
		public static final String CHARSET = "charset";
	}

	public static class NUMBER {
		public static final int ZERO = 0;
		public static final int ONE = 1;
		public static final int TWO = 2;
		public static final int THREE = 3;
		public static final int FOUR = 4;
		public static final int FIVE = 5;
		public static final int SIX = 6;
		public static final int SEVEN = 7;
		public static final int EIGHT = 8;
		public static final int NINE = 9;
		public static final int TEN = 10;
		public static final int ELEVEN = 11;
		public static final int TWELVE = 12;
		public static final int THIRTEEN = 13;
		public static final int FOURTEEN = 14;
		public static final int TWENTY = 20;
		public static final int FIFTY = 50;
	}

	public static class TIME {
		public static final long ONE_DAY_MILLS = 24l * 60l * 60l * 1000l;
		public static final long NINETY_DAY_MILLS = 24l * 60l * 60l * 1000l * 90l;
	}
	
	public static class RELATION_STATUS{
		public static final int UNBOUNDED = 0;//未绑定
		public static final int BOUNDED = 1;//已绑定
	}
	
	public static class IS_SIGN_IN{
		public static final int NO = 0;//未签到
		public static final int YES = 1;//已签到
	}
	
	public static class EXAMINATION_TYPE{
		public static final int SKIP = 1;//跳绳
		public static final int RUN_50M = 2;//50m跑步
		public static final int RUN_50Mx8 = 3;//50m x8跑步
		public static final int SIT_UP = 4;//仰卧起坐
		public static final int SEATED_PRECURSOR = 5;//坐位体前驱
		public static final int VITAL_CAPACITY = 6;//肺活量
	}
}
