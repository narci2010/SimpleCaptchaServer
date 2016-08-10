package org.small.utils;

import nl.captcha.text.producer.TextProducer;

import java.util.Random;

/**
 * EasyCharTextProducer
 *
 * @author jiang haiguo
 * @date 2016/8/10
 */
public class EasyCharTextProducer implements TextProducer {

    /**
     * 数字，字母
     */
    private static final char[] eng_numChars = {'a', 'c', 'd', 'e', 'f', 'h', 'j',
            'k', 'm', 'n', 'p', 'r', 's', 't', 'w', 'x', 'y', '3', '4', '5',
            '7', '8'};

    /**
     * 数字
     */
    private static final char[] numChars = {'1', '2', '3', '4', '5',
            '7', '8', '9'};

    /**
     * 字母
     */
    private static final char[] engChars = {'a', 'c', 'd', 'e', 'f', 'h', 'j',
            'k', 'm', 'n', 'p', 'r', 's', 't', 'w', 'x', 'y'};

    /**
     * 汉字
     */
    private static final char[] chsChars = {};

    private int length;

    private String catchaStyle;

    public EasyCharTextProducer() {
        this(4, "eng");
    }

    public EasyCharTextProducer(int length, String catchaStyle) {
        super();
        this.length = length;
        this.catchaStyle = catchaStyle;
    }

    public String getText() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        char[] chars;
        if ("num".equals(catchaStyle)) {
            chars = numChars;
        } else if ("eng_num".equals(catchaStyle)) {
            chars = eng_numChars;
        } else if ("chs".equals(catchaStyle)) {
            chars = chsChars;
        } else {
            chars = engChars;
        }
        int lastChar = chars.length;
        for (int i = 0; i < length; i++) {
            int r = random.nextInt(lastChar);
            sb.append(chars[r]);
        }
        return sb.toString();
    }

}