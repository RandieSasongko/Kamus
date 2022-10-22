package com.pab2.kamus.databases;

import android.provider.BaseColumns;

public class DatabaseContracts {
    static final String TABLE_ENGLISH_INDONESIA_NAME = "english_indonesia";
//    static final String TABLE_INDONESIA_ENGLISH_NAME = "indonesia_english";

    static final class EnglishIndonesiaColumns implements BaseColumns {
        // id ada di basecolumns
        static String ENGLISH_INDONESIA_TITLE = "title";
        static String ENGLISH_INDONESIA_DESCRIPTION = "description";
    }
}
