public class Main {
    public static void main(String[] args) {
        String path = "";

        if (args.length >= 2) {
            path = args[1];
        }

        new Window(path);
    }
}
