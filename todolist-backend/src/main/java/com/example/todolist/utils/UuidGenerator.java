package com.example.todolist.utils;

import java.io.Serializable;
import java.util.UUID;

public class UuidGenerator{

    public Serializable generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
