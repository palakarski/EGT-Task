package com.example.egttask.utils.providers;

import java.time.LocalDate;

public interface DateProvider {

    static LocalDate get() {
        return LocalDate.now();
    }

}
