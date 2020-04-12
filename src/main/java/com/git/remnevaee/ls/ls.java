package com.git.remnevaee.ls;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedWriter;
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

        private long Size() {
            return file.length();
        }

        private String Name() {
            return file.getName();
        }

        protected String LastModificate() {
            final long timeModified = file.lastModified();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            return sdf.format(new Date(timeModified));
        }

        protected String СlearSize() {
            long clearSize = Size();
            String[] unit = new String[]{"B", "Kb", "Mb", "Gb"};
            int count = 0;
            while (clearSize >= 1024) {
                clearSize /= 1024;
                count++;
            }
            return clearSize + " " + unit[count];
        }

        protected String FilePermissions() {
            StringBuffer sb = new StringBuffer();
            if (file.canWrite()) {
                sb.append("Write ");
            } else {
                sb.append("NoWrite ");
            }
            if (file.canRead()) {
                sb.append("Read ");
            } else {
                sb.append("NoRead ");
            }
            if (file.canExecute()) {
                sb.append("Execute ");
            } else {
                sb.append("NoWriteExecute");
            }
            return sb.toString();
        }

    }
}
