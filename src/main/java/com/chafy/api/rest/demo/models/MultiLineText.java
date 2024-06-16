package com.chafy.api.rest.demo.models;

import java.util.*;
import java.util.stream.*;

public class MultiLineText {

    private final List<String> lines;

    public MultiLineText(String text) {
        this.lines = Arrays.asList(text.split("\n"));
    }

    public static MultiLineText of(String content) {
        return new MultiLineText(content);
    }

    @Override
    public String toString() {
        return lines.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiLineText that = (MultiLineText) o;
        return Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }
}
