package com.jimas.dbconn.util;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.jimas.common.util.DateUtil;

public class DateUtilTest {

    @Test
    public void testFormatDate() {
        fail("Not yet implemented");
    }

    @Test
    public void testFormat() {
        fail("Not yet implemented");
    }

    @Test
    public void testParseStrAutoToDate() {
        fail("Not yet implemented");
    }

    @Test
    public void testDateToStamp() {
        
        String dateToStamp = DateUtil.dateToStamp("2017-03-08 15:12:54");
        System.out.println(dateToStamp);
    }

    @Test
    public void testStampToDate() {
        String s="1488957174000";
        String stampToDate = DateUtil.stampToDate(s);
        System.out.println(stampToDate);
    }

}
