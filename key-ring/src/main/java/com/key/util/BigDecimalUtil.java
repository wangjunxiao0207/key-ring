package com.key.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
    /**
     * 创建BigDecimal对象
     * @param v
     * @return
     */
    public static BigDecimal newBigDecimal(String v) {
        return new BigDecimal(v);
    }

    public static BigDecimal newBigDecimal(double v) {
        return newBigDecimal(Double.toString(v));
    }

    public static BigDecimal newBigDecimal(float v) {
        return newBigDecimal(Float.toString(v));
    }

    public static BigDecimal newBigDecimal(int v) {
        return newBigDecimal(Integer.toString(v));
    }

    /*
    *  加法
    * */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return v1.add(v2);
    }

    public static BigDecimal add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    public static BigDecimal add(double v1, double v2) {
        return add(Double.toString(v1), Double.toString(v2));
    }

    public static BigDecimal add(int v1, int v2) {
        return add(Integer.toString(v1), Integer.toString(v2));
    }

    public static BigDecimal add(float v1, float v2) {
        return add(Float.toString(v1), Float.toString(v2));
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2);
    }

    public static BigDecimal sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    public static BigDecimal sub(double v1, double v2) {
        return sub(Double.toString(v1), Double.toString(v2));
    }

    public static BigDecimal sub(float v1, float v2) {
        return sub(Float.toString(v1), Float.toString(v2));
    }

    public static BigDecimal sub(int v1, int v2) {
        return sub(Integer.toString(v1), Integer.toString(v2));
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return v1.multiply(v2);
    }

    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }
    public static BigDecimal mul(double v1, double v2) {
        return mul(Double.toString(v1), Double.toString(v2));
    }
    public static BigDecimal mul(float v1, float v2) {
        return mul(Float.toString(v1), Float.toString(v2));
    }
    public static BigDecimal mul(int v1, int v2) {
        return mul(Integer.toString(v1), Integer.toString(v2));
    }

    /**
     * 除法
     * 如果除不尽，则进行四舍五入， 保留两位小数
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        // 如果除不尽，则进行四舍五入，保留两位小数
        return v1.divide(v2, 2, BigDecimal.ROUND_HALF_UP);
    }
    public static BigDecimal div(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }
    public static BigDecimal div(double v1, double v2) {
        return div(Double.toString(v1), Double.toString(v2));
    }
    public static BigDecimal div(float v1, float v2) {
        return div(Float.toString(v1), Float.toString(v2));
    }
    public static BigDecimal div(int v1, int v2) {
        return div(Integer.toString(v1), Integer.toString(v2));
    }


    /**
     * a < b 返回 -1
     * a == b 返回 0
     * a > b 返回1
     * @param a
     * @param b
     * @return
     */
    public static int compare(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }
    public static int compare(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return compare(b1, b2);
    }
    public static int compare(double v1, double v2) {
        return compare(Double.toString(v1), Double.toString(v2));
    }
    public static int compare(float v1, float v2) {
        return compare(Float.toString(v1), Float.toString(v2));
    }
    public static int compare(int v1, int v2) {
        return compare(Integer.toString(v1), Integer.toString(v2));
    }

    public static void main(String[] args) {
        System.out.println(compare(1, 2));
        System.out.println(compare(1, 1));
        System.out.println(compare(1, 0));
    }



}
