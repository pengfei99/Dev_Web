package org.bioaster.security.registration;

import java.util.Random;

/**
 * Created by pliu on 2/21/17.
 */
public class NumCaptcha {

    private final String id;
    private final int no1;
    private final int no2;

    private final String arg1;
    private final String arg2;
    private final int answer;

    public String getId() {
        return id;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public int getAnswer() {
        return answer;
    }

    public NumCaptcha(String id){
        this.id=id;
        no1=this.randomNum();

        no2=this.randomNum();
        arg1=this.NumToText(no1);
        arg2=this.NumToText(no2);
        answer=no1+no2;
    }

    private int randomNum(){
        Random randomGenerator=new Random();
        return randomGenerator.nextInt(10);
    }

    private String NumToText(int num){
        String numText;
        switch (num){
            case 0: numText="Zero";
                break;
            case 1: numText="One";
                break;
            case 2: numText="Two";
                break;
            case 3: numText="Three";
                break;
            case 4: numText="Four";
                break;
            case 5: numText="Five";
                break;
            case 6: numText="Six";
                break;
            case 7: numText="Seven";
                break;
            case 8: numText="Eight";
                break;
            case 9: numText="Nine";
                break;
            default: numText="Invalid Number";
                break;
        }
        return numText;
    }

}
