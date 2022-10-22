package com.if5a.kamus.databases;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_ENGLISH_INDONESIA_NAME = "english_indonesia";

    static final class EnglishIndonesiaColumns implements BaseColumns{
     static  String ENGLISH_INDONESIA_TITLE = "title";
     static String ENGLISH_INDONESIA_DESCRIPTION = "description";
    }
}
