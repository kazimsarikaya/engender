/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanaldiyar.projects.engender;

import com.sanaldiyar.projects.engender.lang.Command;
import com.sanaldiyar.projects.engender.lang.ParseException;
import com.sanaldiyar.projects.engender.lang.TMPLParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kazim
 */
public class Engender {

    public static File engender(String filename, Map<String, Object> data) throws IOException {
        return engender(new File(filename), data);
    }

    public static File engender(File file, Map<String, Object> data) throws IOException {
        return engender(new FileInputStream(file), data);
    }

    public static File engender(InputStream inputStream, Map<String, Object> data) throws IOException {
        File tempFile = File.createTempFile("engender-", "tmpl");
        tempFile.deleteOnExit();
        FileOutputStream fos = new FileOutputStream(tempFile);
        byte buffer[] = new byte[8192];
        int r;

        while ((r = inputStream.read(buffer)) > 0) {
            int i = 0;
            boolean tmpl = false;
            StringBuilder sbcommand = null;
            for (; i < r - 1;) {
                if (!tmpl) {
                    if (buffer[i] == '<' && buffer[i + 1] == '%') {
                        tmpl = true;
                        sbcommand = new StringBuilder();
                        i += 2;
                    } else {
                        fos.write(buffer[i]);
                        i++;
                    }
                } else {
                    if (buffer[i] == '%' && buffer[i + 1] == '>') {
                        tmpl = false;
                        String command = sbcommand.toString().trim();
                        try {
                            Command parse = TMPLParser.parse(command);
                            Object parsed = parse.applyData(data);
                            String result = convertToString(parsed);
                            fos.write(result.getBytes("utf-8"));
                        } catch (ParseException ex) {
                            Logger.getLogger(Engender.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        i += 2;
                    } else {
                        sbcommand.append((char) buffer[i++]);
                    }
                }
            }
            fos.flush();
        }
        fos.close();
        inputStream.close();
        return tempFile;
    }

    private static String convertToString(Object parsed) {
        if(parsed==null){
            return "";
        }
        String result;
        if (parsed instanceof Iterable) {
            StringBuilder sb = new StringBuilder();
            for (Object o : (Iterable) parsed) {
                sb.append(convertToString(o));
            }
            result = sb.toString();
        } else {
            result = parsed.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("deneme", "elma");
            Map<String, Object> vars = new HashMap<>();
            vars.put("var1", 1L);
            vars.put("var2", "2");
            data.put("vars", vars);
            data.put("items", new String[]{"string1","string2","string3"});
            File engender = engender("template.test", data);
            List<String> readAllLines = Files.readAllLines(engender.toPath(), Charset.forName("utf-8"));
            for (String line : readAllLines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Engender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
