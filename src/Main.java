import java.io.*;
import java.util.zip.*;

class Main {

    public static void Main(String[] args) {
        if (args.length!=2) {
            System.out.println("Usage: GZip [Source] [Iterations]");
            return;
        }

        filename = args[0];
        iterations = args[1];

        for (int i = 0; i < iterations; i++) {
            gzip(filename);
            filename = filename + ".gz";
        }
    }

    public static void gzip(String filename) {
        String zipname = filename + ".gz";
        GZIPOutputStream zipout;
        try {
            FileOutputStream out = new FileOutputStream(zipname);
            zipout = new GZIPOutputStream(out);
        }
        catch (IOException e) {
            System.out.println("Couldn't create " + zipname + ".");
            return;
        }
        byte[] buffer = new byte[sChunk];
        // compress the file
        try {
            FileInputStream in = new FileInputStream(filename);
            int length;
            while ((length = in.read(buffer, 0, sChunk)) != -1)
                zipout.write(buffer, 0, length);
            in.close();
        }
        catch (IOException e) {
            System.out.println("Couldn't compress " + filename + ".");
        }
        try { zipout.close(); }
        catch (IOException e) {}
    }
}