package com.example.test;

import java.util.Objects;

public class MenuCode {
    private final String code;

    MenuCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MenuCode menuCode)) return false;
        return Objects.equals(code, menuCode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
