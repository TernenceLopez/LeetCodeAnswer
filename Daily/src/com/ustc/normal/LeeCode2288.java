package com.ustc.normal;


public class LeeCode2288 {
    public static void main(String[] args) {
        String  res = discountPrices("706hzu76jjh7yufr5x9ot60v149k5 $7651913186 pw2o $6", 28);
        System.out.println(res);
    }

    public static String discountPrices(String sentence, int discount) {
        String[] subStrs = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<subStrs.length; i++){
            if(subStrs[i].charAt(0)=='$' && subStrs[i].length()>1) {
                boolean validFlag = true;
                for(int j=1; j<subStrs[i].length(); j++){
                    if(subStrs[i].charAt(j)<'0' || subStrs[i].charAt(j)>'9'){
                        validFlag = false;
                        break;
                    }
                }

                if(validFlag){
                    String numStr = subStrs[i].substring(1);
                    Long num = Long.parseLong(numStr);
                    double result = (double) num / 100 * (100 - discount);
                    sb.append(String.format("$%.2f", result));
                    if(i!=subStrs.length-1) sb.append(" ");
                    continue;
                }
            }
            sb.append(subStrs[i]);
            if(i!=subStrs.length-1) sb.append(" ");
        }

        return sb.toString();
    }
}
