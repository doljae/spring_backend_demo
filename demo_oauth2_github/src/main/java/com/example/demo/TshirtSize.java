package com.example.demo;

import lombok.Getter;

@Getter
public enum TshirtSize {
    S("S"), M("M"), L("L"), XL("XL"), XXL("2XL");

    private final String text;

    TshirtSize(String text) {
        this.text = text;
    }
}
