package com.git.remnevaee.ls;

import com.sun.org.apache.xpath.internal.objects.XNull;
import com.sun.org.apache.xpath.internal.objects.XString;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ls {
        public File file;

        public ls(File file) {
            this.file = file;
        }


        public long Size() {
            return file.length();
        }

        public String Name() {
            return file.getName();
        }

        public String LastModificate() {
            final long timeModified = file.lastModified();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            return sdf.format(new Date(timeModified));
        }

        public String СlearSize() {
            long clearSize = Size();
            String[] unit = new String[]{"B", "Kb", "Mb", "Gb"};
            int count = 0;
            while (clearSize >= 1024) {
                clearSize /= 1024;
                count++;
            }
            return clearSize + " " + unit[count];
        }

        public String FilePermissions() throws IOException {
            StringBuffer strBuf = new StringBuffer();
            if (file.canWrite()) {
                strBuf.append("w");
            } else {
                strBuf.append("-");
            }
            if (file.canRead()) {
                strBuf.append("r");
            } else {
                strBuf.append("-");
            }
            if (file.canExecute()) {
                strBuf.append("x");
            } else {
                strBuf.append("-");
            }
            return strBuf.toString();
        }

        public String FileBitMask() throws IOException {
            int strBuf = 0;
            if (file.canWrite()) strBuf += 10;
            if (file.canRead()) strBuf += 100;
            if (file.canExecute()) strBuf += 1;
            String result = String.valueOf(strBuf);
            return result;
        }


    public String ClearSize() {
        long clearSize = Size();
        String[] unit = new String[]{"B", "Kb", "Mb", "Gb"};
        int count = 0;
        while (clearSize >= 1024) {
            clearSize /= 1024;
            count++;
        }
        return clearSize + " " + unit[count];
    }
}
