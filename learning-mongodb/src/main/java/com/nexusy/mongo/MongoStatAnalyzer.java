package com.nexusy.mongo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lan
 * @since 2015-09-24
 */
public class MongoStatAnalyzer {

    public static void main(String[] args) {
        analyzeWrite();
//        analyzeQuery();
    }

    private static void analyzeWrite(){
        List<Integer> speed = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        File file = new File("F:/mongostat.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            int j = 0;
            while ((line = br.readLine()) != null) {
                if (!line.contains("insert")) {
                    line = line.trim();
                    String[] fields = line.split(" ");
                    if (fields.length > 1 && "*0".equals(fields[0])) {
                        speed.add(0);
                    } else {
                        speed.add(Integer.valueOf(fields[0]));
                    }
                    if (j != 0) {
                        sum.add(sum.get(j - 1) + speed.get(j));
                    } else {
                        sum.add(speed.get(j));
                    }
                    j++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder x = new StringBuilder("[");
        StringBuilder y = new StringBuilder("[");
        StringBuilder z = new StringBuilder("[");
        int i = 0;
        for (Integer sp : speed) {
            if (i > 0) {
                x.append(",");
                y.append(",");
                z.append(",");
            }
            x.append(i);
            y.append(sp);
            z.append(sum.get(i));
            i++;
        }
        x.append("]");
        y.append("]");
        z.append("]");
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }

    private static void analyzeQuery(){
        List<Integer> speed = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        File file = new File("F:/mongostat.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            int j = 0;
            int index = -1;
            while ((line = br.readLine()) != null) {
                if (!line.contains("insert")) {
                    line = line.substring(index);
                    line = line.trim();
                    String[] fields = line.split(" ");
                    if (fields.length > 1 && "*0".equals(fields[0])) {
                        speed.add(0);
                    } else {
                        speed.add(Integer.valueOf(fields[0]));
                    }
                    if (j != 0) {
                        sum.add(sum.get(j - 1) + speed.get(j));
                    } else {
                        sum.add(speed.get(j));
                    }
                    j++;
                } else {
                    index = line.indexOf("insert") + 6;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder x = new StringBuilder("[");
        StringBuilder y = new StringBuilder("[");
        StringBuilder z = new StringBuilder("[");
        int i = 0;
        for (Integer sp : speed) {
            if (i > 0) {
                x.append(",");
                y.append(",");
                z.append(",");
            }
            x.append(i);
            y.append(sp);
            z.append(sum.get(i));
            i++;
        }
        x.append("]");
        y.append("]");
        z.append("]");
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }

}
