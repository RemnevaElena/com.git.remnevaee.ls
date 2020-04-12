package com.git.remnevaee.ls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
public class ls {
    public static void main(String[] args) {}

    protected static final class ProgramFile {
        private final File file;

        protected ProgramFile(File file) {
            this.file = file;
        }
    }
    private long Size() {
        return file.length();
    }
    private String Name () {
        return file.getName();
    }
    protected String LastModificate() {
        final long timeModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sdf.format(new Date(timeModified));
    },
}
