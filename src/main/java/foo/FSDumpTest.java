package foo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FSDumpTest {
    public static void main(String[] args) {
        FileOutputStream fop = null;
        File file;
        String content = "This is the text content";
        byte[] contentInBytes = content.getBytes();
        file = new File(args[0]);

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