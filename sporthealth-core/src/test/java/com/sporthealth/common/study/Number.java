package com.sporthealth.common.study;

/**
 * @program: sporthealth
 * @description: 打印数字的汉语
 * @author: kongdingchao
 * @create: 2019-03-23 19:32
 **/
public class Number {
    private final static String Zh_1[] = {"零", "一", "二", "三","四", "五", "六","七", "八", "九"};
    private final static String Zh_4[] = {"十", "百", "千"};
    private final static String Zh_L[] = {"万", "亿", "兆"};
    /**
    * @Description: 获取数值位数
    */
    public static int sizeOfInt(int num) {
        int nResult = 1;
        num /= 10;
        while (num > 0) {
            nResult++;
            num /= 10;
        }
        return nResult;
    }


    /**
     * @Description: 获取4位内数字的汉语
     */
    public static String getZhByInt4(int num) {
        StringBuffer sResult = new StringBuffer("");
        if (num >= 10000) {
            return sResult.toString();
        }
        int min = num % 10;
        boolean isLeftZero = true;
        for (int i = 3; i > 0; i--) {
            int value = (int)(num / Math.pow(10.0, i));
            if (value > 0) {
                sResult.append(Zh_1[value]);
                sResult.append(Zh_4[i - 1]);
                isLeftZero = false;
            }
            else {
                //防止最左边不能为0,且左边为零仍继续加零的出现
                if (!isLeftZero
                        && !(sResult.length() > 0 && sResult.charAt(sResult.length() - 1) == Zh_1[0].charAt(0))) {
                    sResult.append(Zh_1[0]);
                }

            }
            num %= Math.pow(10.0, i);
        }
        if (min > 0) {
            sResult.append(Zh_1[min]);
        }
        return sResult.toString();
    }

    /**
    * @Description: 去除多余的0
    */
    public static void removeRepeatZero(String str) {
        while (str.contains(Zh_1[0] + Zh_1[0])) {
            str.replace(Zh_1[0] + Zh_1[0], Zh_1[0]);
        }
    }
    /**
    * @Description: 获取数字的汉语
    */
    public static String getZhByInt(int num) {
        if (num < 10000) {//小于10000
            return getZhByInt4(num);
        }
        else {
            int size = sizeOfInt(num);//总位数
            int level = (size - 1) / 4;//1-万，2-亿，3-兆
            String sCurLevel = Zh_L[level - 1];//当前级别的：万，亿，兆
            int curLevelValue = (int)(Math.pow(10.0, 4 * level));//当前级别的单位：10000，100000000，1000000000000
            String first = getZhByInt4(num / curLevelValue) + sCurLevel;//当前级别的前部分
            String next = getZhByInt(num % curLevelValue);//剩余部分
            //若first最右边为0或next最大位不是千分位则加0
            if (num / curLevelValue % 10 == 0 || !next.contains(Zh_1[2])) {
                first = first + Zh_1[0];
            }
            String result = first + next;
            removeRepeatZero(result);
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(getZhByInt(304));
        //System.out.println(getZhByInt(1200));
        System.out.println(getZhByInt(1200202));
        System.out.println(getZhByInt(1230200304));
    }
}
