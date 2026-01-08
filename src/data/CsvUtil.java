package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<String[]> readAll(String filePath) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }
                rows.add(line.split(",", -1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rows;
    }

    public static void appendLine(String filePath, String line) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write("\n" + line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readHeader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        } catch (Exception e) {
            return "";
        }
    }

    public static void writeAll(String filePath, String header, List<String> lines) {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            fw.write(header == null ? "" : header);
            for (String line : lines) {
                fw.write("\n" + line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
