package com.serai.challenge.model;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Size {
    SIZE_1(1),
    SIZE_2(2),
    SIZE_3(3);
    private long type;
    private Size(long type){
        this.type = type;
    }
    public Long getId(){
        return Long.valueOf(this.type);
    }
    private static final List<Size> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Size random(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
