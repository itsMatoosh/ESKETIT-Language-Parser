package me.matoosh.esketit;

import java.io.File;

public class Main {

    /**
     * The parser.
     */
    public static EsketitParser parser;


    public static void main(String[] args) throws Exception {
        if(args.length < 1) {
            System.out.println("No .esketit file specified!");
            return;
        }
        String fileName = args[0];
        if(fileName == null || fileName == "") {
            System.out.println("No .esketit file specified!");
            return;
        }

        //Check if it exists
        File file = new File(fileName);
        if(!file.exists()) {
            System.out.println("File " + file.getName() + " doesn't exist!");
            return;
        }

        //Parse the file.
        parser = new EsketitParser(file);
        parser.parse();

        //Run the file.
        String result = parser.run();
        if(result != "success") {
            System.out.println("Script returned: " + result);
        }
    }
}
