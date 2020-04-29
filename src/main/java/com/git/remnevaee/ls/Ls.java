package com.git.remnevaee.ls;

import com.sun.org.apache.xpath.internal.objects.XNull;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Ls {
        public File file;
        //конструктор
        public Ls() {
            this.file = file;
        }

        @Option(name = "-h", metaVar = "human-readable")
        private String hr = null;

        @Option(name = "-l", metaVar = "long")
        private String l = null;

        @Option(name = "-r", metaVar = "rewerse")
        private String rewerse = null;

        @Option(name = "-o", metaVar = "output")
        private String outputFileName = null;

        @Argument
        private String[] arguments;

        public long Size() {
            return file.length();
        }

        public String Name() {
            return file.getName();
        }

        public String LastModificate() {//время последней модификации
            final long timeModified = file.lastModified();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            return sdf.format(new Date(timeModified));
        }

        public String СlearSize() {//размер в нормальном формате
            long clearSize = Size();
            String[] unit = new String[]{"B", "Kb", "Mb", "Gb"};
            int count = 0;
            while (clearSize >= 1024) {
                clearSize /= 1024;
                count++;
            }
            return clearSize + " " + unit[count];
        }

        public String FilePermissions() throws IOException { //права на выполнение/чтение/запись в иде rbk
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

        public String FileBitMask() throws IOException {//права на выполнение/чтение/запись в виде битовой маски
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

    private void long1() throws Exception {
            ArrayList<String> rez1 = new ArrayList<>();
            StringBuffer strBuf = new StringBuffer();
            if (file.isFile()) { //если файл, то в листочек записываем инфу у нем, если выпал флаг -l -h
                rez1.add(Name());
                rez1.add(FileBitMask());
                rez1.add(LastModificate());
                rez1.add(СlearSize());
            } else if (file.isDirectory()) {//если директория, то тоже  в листочек записываем инфу у ней, если выпал флаг -l -h
                for (File file : file.listFiles())
                    if (file.isFile()) {
                        rez1.add(Name());
                        rez1.add(FileBitMask());
                        rez1.add(LastModificate());
                        rez1.add(СlearSize());
                    }
            }
            if (rewerse != null) {
                int n = rez1.size() - 1;
                while (n >= 0) {
                    int k = n - 3;
                    while (k != n) {
                        strBuf.append(rez1.get(k) + " ");
                        k++;
                    }
                    strBuf.append("\n");
                    n -= 4;
                }
            } else {
                int n = 0;
                while (n != rez1.size() - 1) {
                    strBuf.append(rez1.get(n) + " ");
                    if (n % 4 == 0 && n != 0) strBuf.append("\n");
                    n++;
                }
            }
            if (outputFileName != null) {
                System.out.println(strBuf.toString());
            }
            else {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    writer.write(strBuf.toString());
                }
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        private void humanreadable() throws Exception{
            ArrayList<String> rez1 = new ArrayList<>();
            StringBuffer strBuf = new StringBuffer();
            if (file.isFile()) { //если файл, то в листочек записываем инфу у нем, если выпал флаг -l -h
                    rez1.add(Name());
                    rez1.add(ClearSize());
                    rez1.add(FilePermissions());
            } else if (this.file.isDirectory()) {//если директория, то тоже  в листочек записываем инфу у ней, если выпал флаг -l -h
                for (File file : file.listFiles()) {
                    if (file.isFile()) {
                        rez1.add(Name());
                        rez1.add(ClearSize());
                        rez1.add(FilePermissions());
                    }
                }
            }
            if (rewerse != null) {
                int n = rez1.size() - 1;
                while (n >= 0) {
                    int k = n - 2;
                    while (k != n) {
                        strBuf.append(rez1.get(k) + " ");
                        k++;
                    }
                    strBuf.append("\n");
                    n -= 3;
                }
            } else {
                int n = 0;
                while (n != rez1.size() - 1) {
                    strBuf.append(rez1 + " ");
                    if (n % 3 == 0 && n != 0) strBuf.append("\n");
                    n++;
                }
            }
            if (outputFileName != null) {
                System.out.println(strBuf.toString());
            }
            else {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    writer.write(strBuf.toString());
                }
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    public void launch() throws IOException {
        if (hr != null) {
            try {
                humanreadable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (l != null) {
            try {
                long1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//        private void writeText() throws Exception {
//            if (outputFileName.equals("")) {
//                if (hr == 1) System.out.println();
//                else if (l ==1) System.out.println(long1());
//            }
//            else writeToFile();
//        }
//
//        private void writeToConsole() throws Exception {
//            if (hr == 1) System.out.println(humanreadable());
//            else if (l ==1) System.out.println(long1());
//        }
//
//        private void writeToFile() throws Exception {
//            try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
//                if (hr == 1) writer.write(humanreadable());
//                else if (l ==1) writer.write(long1());
//            }
//            catch(IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
}
