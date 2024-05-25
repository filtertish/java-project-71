import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import hexlet.code.Differ;

@Command(name = "gendiff", version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String firstFilePath;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String secondFilePath;

    @Option(names = {"-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String formatRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Prints version information and exit.")
    private boolean versionRequested = false;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println(Differ.generate(firstFilePath, secondFilePath));
    }

}
