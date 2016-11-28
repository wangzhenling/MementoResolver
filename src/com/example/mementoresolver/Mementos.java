package com.example.mementoresolver;

import android.net.Uri;
import android.provider.BaseColumns;

public class Mementos {

	public static final String AUTHORITY="com.example.providers.memento";
	public static final class Memento implements BaseColumns {
		
		public static final String _ID="_id";//memento_tb����_id�ֶ�
		public static final String SUBJECT="subject";
		public static final String BODY="body";
		public static final String DATE="date";
		//�ṩ����mementos����URI
		public static final Uri MEMENTOS_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/mementos");
		//�ṩ��������mementoURI
		public static final Uri MEMENTO_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/memento");
	}
}
