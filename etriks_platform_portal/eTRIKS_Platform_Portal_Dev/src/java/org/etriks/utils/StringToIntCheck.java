package org.etriks.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pliu on 7/22/15.
 */
public class StringToIntCheck {

    public static boolean isNumeric(String num){
        Pattern p=Pattern.compile("([0-9]*)");
        Matcher m=p.matcher(num);
        return m.matches();
    }
}
