package com.serai.challenge.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Topping {
    TOPPING_1(1),
    TOPPING_2(2),
    TOPPING_3(3),
    TOPPING_4(4),
    TOPPING_5(5),
    TOPPING_6(6),
    TOPPING_7(7),
    TOPPING_8(8),
    TOPPING_9(9);
    private int type;
    private Topping(int type){
        this.type = type;
    }
    public Integer getId(){
        return Integer.valueOf(this.type);
    }
    private static final List<Topping> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Topping random(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
