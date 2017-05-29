package com.lihaizhou.greendao3.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lihaizhou on 2017/5/29.
 */
@Entity
public class Student {
    @Id
    private long id;
    private String name;

    @Generated(hash = 1314737876)
    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
