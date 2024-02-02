package com.ustc.normal;

import java.util.ArrayList;
import java.util.List;

public class LeeCode1276 {
    public static void main(String[] args) {
        numOfBurgers(2385088,164763);
    }
    public static List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        Integer two_x = tomatoSlices - 2*cheeseSlices;
        Integer x = 0;
        if(tomatoSlices - 2*cheeseSlices<0 || (tomatoSlices - 2*cheeseSlices)%2 != 0){
            return new ArrayList<>();
        }else{
            x = (tomatoSlices - 2*cheeseSlices)/2;
        }

        Integer y = cheeseSlices - x;
        List<Integer> result = new ArrayList<>();
        result.add(x);
        result.add(y);

        return result;
    }
}
