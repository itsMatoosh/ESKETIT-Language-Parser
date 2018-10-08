package me.matoosh.esketit;

import me.matoosh.esketit.rules.PrintLnRule;
import me.matoosh.esketit.rules.VariableNameRule;
import me.matoosh.esketit.rules.VariableTypeRule;
import me.matoosh.esketit.rules.VariableValueAssignRule;
import org.joor.Reflect;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EsketitParser {

    /**
     * The esketit file.
     */
    public File esketitFile;

    /**
     * The initial file.
     */
    public List<String> initialFile;
    /**
     * The parsed file.
     */
    public List<String> parsedFile = new ArrayList<>();

    /**
     * The full file parsed.
     */
    String fullFileParsed;

    /**
     * Java template for the parsed classes.
     */
    String mainClassTemplate0 =
            "package skrrt.lilpump;\n" +
                    "\n" +
                    "public class Main implements java.util.function.Supplier<String> {\n" +
                    "\n" +
                    "    public String get() {\n";
    String mainClassTemplate1 =
            "   return \"success\"; }\n" +
                    "}";

    /**
     * Replace strings.
     */
    public static ParserRule[] parserRules = new ParserRule[]{
            new VariableTypeRule(),
            new VariableNameRule(),
            new VariableValueAssignRule(),
            new PrintLnRule()
    };

    public EsketitParser(File file) {
        this.esketitFile = file;
    }

    /**
     * Parses the given .esketit file.
     * Stores the parsed file in memory.
     */
    public void parse() throws Exception {
        System.out.println("Parsing file " + esketitFile.getName() + "...");

        initialFile = Files.readAllLines(esketitFile.toPath());
        if (!initialFile.get(0).equals("CB on the beat")) {
            throw new InvalidFileHeaderException();
        }

        //Replacing each of the lines with their java alternatives.
        for (String line :
                initialFile) {
            if (line.contains("CB on the beat")) continue;

            for (ParserRule rule :
                    parserRules) {
                if (line.contains(rule.getTriggerPhrase())) {
                    line = rule.applyRule(line);
                }
            }
            parsedFile.add(line);
        }

        //Writing code...
        fullFileParsed = mainClassTemplate0;
        for (int i = 0; i < parsedFile.size(); i++) {
            fullFileParsed = fullFileParsed + parsedFile.get(i);
        }
        fullFileParsed = fullFileParsed + mainClassTemplate1;
    }

    /**
     * Runs the parsed file.
     */
    public String run() {
        System.out.println("Executing " + esketitFile.getName() + "...");
        System.out.println();

        if (fullFileParsed == null || fullFileParsed == "") {
            return "file-empty";
        }


        Supplier<String> script = Reflect.compile(
                "skrrt.lilpump.Main",
                fullFileParsed
        ).create().get();

        try {
            return script.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
}
