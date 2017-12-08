package foo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FSDumpTest {
    public static void main(String[] args) {
        final String argsf = args[0];
        final File file;
        final String content = "This is the text content";
        final byte[] contentInBytes = content.getBytes();


        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runFileWritter(argsf, contentInBytes);
                }
            }).start();
        }


    }

    private static void runFileWritter(String arg, byte[] contentInBytes) {
        FileOutputStream fop = null;
        File file = new File(arg + Thread.currentThread().getId());
        try {
            while (true){
            // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                fop = new FileOutputStream(file);
            // get the content in bytes
                for (int i = 0; i < 1000; i++) {
                    fop.write(contentInBytes);
                    fop.flush();
                }
                fop.close();
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}