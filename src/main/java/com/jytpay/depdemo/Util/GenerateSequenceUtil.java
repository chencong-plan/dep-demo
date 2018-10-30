package com.jytpay.depdemo.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateSequenceUtil {

    /**
     * .log
     */
    private static final Logger logger = LoggerFactory.getLogger(GenerateSequenceUtil.class);

    /**
     * The FieldPosition.
     */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /**
     * This Format for format the data to special format.
     */
    private final static Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private final static Format projectDateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * This Format for format the number to special format.
     */
    private final static NumberFormat numberFormat = new DecimalFormat("0000000");
    private final static NumberFormat projectNumberFormat = new DecimalFormat("000000");

    private final static String MACHINE_NUMBER = "127";

    /**
     * This int is the sequence number ,the default value is 0.
     */
    private static int seq = 1;

    private static int projectSeq = 1;

    private static final int MAX = 9999;

    /**
     * 时间格式生成序列
     *
     * @return Long
     */
    public static synchronized Long generateSequenceNo() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 1;
        } else {
            seq++;
        }
        return Long.valueOf(sb.toString());
    }

    /**
     * 时间格式生成序列
     *
     * @return String
     */
    public static synchronized String generateSequenceNoString() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 1;
        } else {
            seq++;
        }
        sb.insert(0, MACHINE_NUMBER);
        return sb.toString();
    }


    /**
     * <pre>
     * 生成项目编号 yyyyMMdd+000001
     * </pre>
     *
     * @return
     */
    public static synchronized String generateProjectNoSequence() {
        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        projectNumberFormat.format(projectSeq, sb, HELPER_POSITION);
        if (projectSeq == MAX) {
            projectSeq = 1;
        } else {
            projectSeq++;
        }
        sb.insert(0, MACHINE_NUMBER);
        return sb.toString();
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		UID uid = new UID();
        for (int i = 0; i < 10; i++)
            System.out.println(GenerateSequenceUtil.generateProjectNoSequence());

//		DecimalFormat df=(DecimalFormat)DecimalFormat.getInstance();
//		df.applyLocalizedPattern("00000000000");
//		for(int i=1;i<100000000000;i++){
//			System.out.println(df.format(i));
////		}

    }
}