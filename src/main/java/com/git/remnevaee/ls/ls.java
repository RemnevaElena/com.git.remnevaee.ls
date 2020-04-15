package com.git.remnevaee.ls;

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

        protected String FilePermissions() throws IOException {
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
        protected String FileBitMask() throws IOException {
            int strBuf = 0;
            if (file.canWrite()) strBuf+=10;
            if (file.canRead()) strBuf+=100;
            if (file.canExecute()) strBuf+=1;
            String result = String.valueOf(strBuf);
            return result;
        }
        public void main(String[] args) throws IOException {
            String outputFileName = "";
            ArrayList<String> flags = new ArrayList<>();
            for (int i = 0; i < args.length - 1; i++) {
                switch (args[i]) {
                    case "-l":
                        flags.add("-l");
                        break;
                    case "-h":
                        flags.add("-h");
                        break;
                    case "-r":
                        flags.add("-r");
                        break;
                    case "-o":
                        flags.add("-o");
                        outputFileName = args[i + 1];
                        break;
                }
            }
            if (args.length == 0) {
                System.out.println("ls [-l] [-h] [-r] [-o output.file] directory_or_file");
                System.exit(0);
            }
            ArrayList<String> rez = new ArrayList<>();
            File d_or_f= new File(args[args.length - 1]);
            if (d_or_f.isFile()) {
                for (int i = 0; i < flags.size(); i++) {
                    if (flags.get(i) == "-l") {
                        ProgramFile pf = new ProgramFile(d_or_f);
                        String name = Name(d_or_f);

                    }
                }
            }

        }

        private String Name(File d_or_f) {
            return file.getName();
        }
    }
}
