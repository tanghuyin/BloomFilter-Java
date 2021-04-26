package BloomFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<String> readFile(String filename) {
        List<String> list = new ArrayList<>();
        try {
            File file = new File(filename);
            if (file.isFile() && file.exists()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }
                reader.close();
            } else {
                System.out.println("Can not find this file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
