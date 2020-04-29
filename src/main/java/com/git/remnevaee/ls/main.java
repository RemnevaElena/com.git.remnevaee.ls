package com.git.remnevaee.ls;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.IOException;


public class main {

    public void main(String[] args) throws IOException {
        Ls ls = new Ls();
        CmdLineParser parser = new CmdLineParser(ls);
        try {
            parser.parseArgument(args);
            ls.launch();
        } catch (CmdLineException e) {
            e.printStackTrace();
        }

        if (args.length == 0) {
            System.out.println("ls [-l] [-h] [-r] [-o output.file] directory_or_file");
            System.exit(0);
        }
    }
}

