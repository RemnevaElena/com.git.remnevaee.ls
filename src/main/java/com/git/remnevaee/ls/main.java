package com.git.remnevaee.ls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class main {
    public void main(String[] args) throws IOException {
        String outputFileName = null;
        ArrayList<String> flags = new ArrayList<>();
        for (int i = 0; i < args.length - 1; i++) { //нахожу, какой флаг мне надо выполнить
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
        ArrayList<String> rez1 = new ArrayList<>();//для того, чтоб запаисывать туда, что я узнала о директории/директории
        File d_or_f = new File(args[args.length - 1]); //последний элемент в массиве, директория или файл
        ls test = new ls(d_or_f);
        if (d_or_f.isFile()) { //если файл, то в листочек записываем инфу у нем, если выпал флаг -l -h
            if (flags.contains("-l")) {
                rez1.add(test.Name());
                rez1.add(test.FileBitMask());
                rez1.add(test.LastModificate());
                rez1.add(test.СlearSize());
            } else if (flags.contains("-h")) {
                rez1.add(test.Name());
                rez1.add(test.ClearSize());
                rez1.add(test.FilePermissions());
            }
        } else if (d_or_f.isDirectory()) {//если директория, то тоже  в листочек записываем инфу у ней, если выпал флаг -l -h
            for (File file : d_or_f.listFiles()) {
                if (file.isFile()) {
                    if (flags.contains("-l")) {
                        rez1.add(test.Name());
                        rez1.add(test.FileBitMask());
                        rez1.add(test.LastModificate());
                        rez1.add(test.СlearSize());
                    } else if (flags.contains("-h")) {
                        rez1.add(test.Name());
                        rez1.add(test.ClearSize());
                        rez1.add(test.FilePermissions());
                    }
                }
            }
        }
        assert outputFileName != null;
        for (int i = 0; i < flags.size(); i++) { // здесь вывод или в файл или в консоль результата
            if (flags.get(i) == "-o" && (new File(outputFileName).isFile())) {
                File outFile = new File(outputFileName);
                BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
                if (flags.contains("-r")) {
                    if (rez1.size() % 4 == 0) {
                        int n = rez1.size() - 1;
                        while (n >= 0) {
                            bw.write(rez1.get(n - 3) + " " + rez1.get(n - 2) + " " +rez1.get(n - 1) + " " +rez1.get(n));
                            bw.newLine();
                            n -= 4;
                        }
                    }
                    if (rez1.size() % 3 == 0) {
                        int n = rez1.size() - 1;
                        while (n >= 0) {
                            bw.write(rez1.get(n - 2) + " " + rez1.get(n - 1) + " " +rez1.get(n));
                            bw.newLine();
                            n -= 3;
                        }
                    }
                }
                else {
                    if (rez1.size() % 4 == 0) {
                        int n = 0;
                        while (n <= rez1.size() - 1) {
                            bw.write(rez1.get(n) + " " + rez1.get(n + 1) + " " +rez1.get(n + 2) + " " +rez1.get(n + 3));
                            bw.newLine();
                            n += 4;
                        }
                    }
                    if (rez1.size() % 3 == 0) {
                        int n = 0;
                        while (n <= rez1.size() - 1) {
                            bw.write(rez1.get(n) + " " + rez1.get(n + 1) + " " +rez1.get(n + 2));
                            bw.newLine();
                            n += 3;
                        }
                    }
                }
                bw.flush();
                bw.close();
            }
            else {//то же самое, что и выше, только вывод в консоль
                if (flags.contains("-r")) {
                    if (rez1.size() % 4 == 0) {
                        int n = rez1.size() - 1;
                        while (n >= 0) {
                            System.out.println(rez1.get(n - 3) + " " + rez1.get(n - 2) + " " + rez1.get(n - 1) + " " + rez1.get(n));
                            n -= 4;
                        }
                    }
                    if (rez1.size() % 3 == 0) {
                        int n = rez1.size() - 1;
                        while (n >= 0) {
                            System.out.println(rez1.get(n - 2) + " " + rez1.get(n - 1) + " " + rez1.get(n));
                            n -= 3;
                        }
                    }
                } else {
                    if (rez1.size() % 4 == 0) {
                        int n = 0;
                        while (n <= rez1.size() - 1) {
                            System.out.println(rez1.get(n) + " " + rez1.get(n + 1) + " " + rez1.get(n + 2) + " " + rez1.get(n + 3));
                            n += 4;
                        }
                    }
                    if (rez1.size() % 3 == 0) {
                        int n = 0;
                        while (n <= rez1.size() - 1) {
                            System.out.println(rez1.get(n) + " " + rez1.get(n + 1) + " " + rez1.get(n + 2));
                            n += 3;
                        }
                    }

                }

            }}}
}

