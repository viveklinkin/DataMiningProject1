package main.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOps {

    public static List<String> readFile(String filePath) {
        File f = new File(filePath);
        List<String> res = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    res.add(line);
                }
            }
            
            br.close();

        } catch (IOException e) {
            System.err.println("Error reading file");
        }
        return res;
    }

    public static void writeFile(String filePath, String data) {
        File f = new File(filePath);
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(data);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }

    }

    public static void writeFile(String filePath, List<String> data) {
        File f = new File(filePath);
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter(f));
            for (String x : data) {
                bw.write(x + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }

    }
}
