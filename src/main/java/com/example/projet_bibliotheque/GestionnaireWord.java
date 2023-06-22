package com.example.projet_bibliotheque;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class GestionnaireWord {
    public  GestionnaireWord(){}

    public List<String> readLines(File filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        List<String> lines = bufferedReader.lines()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        bufferedReader.close();
        return lines;
    }
    //Create Word
    public void createWord(List<String> lines) throws IOException {
        //Check the generated path. If it is not there, create it.
        Path path = Paths.get("./generated");
        XWPFDocument document = new XWPFDocument();
        if (!path.toFile().exists()) Files.createDirectories(path);
        //Create Word docs.
        for (String line : lines) {
            //Blank Document
            //Write the Document in file system
            FileOutputStream out = new FileOutputStream("generated/" + "createdWord" + "_" + line + ".docx");
            //create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("VK Number (Parameter): " + line + " here you type your text...\n");
            document.write(out);
            //Close document
            out.close();
            System.out.println("createdWord" + "_" + line + ".docx" + " written successfully");
        }
    }
}
