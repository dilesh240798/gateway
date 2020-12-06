package com.scb.scroe.gateway.utils;

import org.apache.commons.io.input.ReversedLinesFileReader;
import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReaderWriterUtils {

    private ReaderWriterUtils() {
    }

    public static List<File> readFilesFromDirectory(String path) {
        File folder = new File(path);
        if (folder.isDirectory() && folder.listFiles() != null && folder.listFiles().length != 0)
            return Arrays.asList(folder.listFiles());
        return Collections.emptyList();
    }

    public static String readFileAsString(File file) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuffer output = new StringBuffer();
        String st;
        while ((st = in.readLine()) != null) {
            output.append(st);
        }
        in.close();
        return output.toString();
    }

    public static List<String> readFileAsList(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            List<String> inputList = in.lines().collect(Collectors.toList());
            in.close();
            return inputList;
        } catch (Exception e) {
//            LOGGER.error("Unable to process latest file {}", file.getAbsolutePath());
        }
        return Collections.emptyList();
    }

    public static void saveToFile(String message, String dir, String filename) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File(dir + filename + ".txt")));
            output.write(message);
            output.close();
        } catch (Exception e) {
//            log.error("Error in archiving the message", e);
        }
    }

    public static void archiveFile(File inputFile, String archivePath) throws Exception {
//		inputFile.renameTo(new File(archivePath + inputFile.getName() + Instant.now().toString().replace(":","")));
        Path srcPath = Paths.get(inputFile.toURI());
        Path destPath = Paths.get(archivePath, inputFile.getName() + Instant.now().toString().replace(":", ""));
        Files.move(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void moveFile(File sourceFile, String destinationPath) {
        try {
            File folder = new File(destinationPath);
            Path srcPath = Paths.get(sourceFile.toURI());
            Path destPath = Paths.get(folder.getAbsolutePath(), sourceFile.getName());
            Files.move(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
//            LOGGER.info("Moved {} file to {} location", sourceFile.getName(), destinationPath);
        } catch (Exception e) {
//            LOGGER.error("Unable to archive file {}", sourceFile.getAbsolutePath(), e);
        }
    }

    public static void moveFile(String fileName, String sourcePath, String destinationPath) {
        try {
            File sourceFolder = new File(sourcePath);
            File destinationFolder = new File(destinationPath);
            Path srcPath = Paths.get(sourceFolder.getAbsolutePath(), fileName);
            Path destPath = Paths.get(destinationFolder.getAbsolutePath(), fileName);
            Files.move(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
//            LOGGER.info("Moved {} file to {} location", fileName, destinationPath);
        } catch (Exception e) {
//            LOGGER.error("Unable to move file {}", fileName, e);
        }

    }

    public static BigInteger countNumberOfLinesInFile(Path path) throws IOException {
        BigInteger nLines = BigInteger.ZERO;
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        while (byteBuffer.hasRemaining()) {
            byte currentByte = byteBuffer.get();
            if (currentByte == '\n')
                nLines = nLines.add(BigInteger.ONE);
        }
        channel.close();
        return nLines;
    }

    public static Optional<String> readLastLineFromFile(Path path) throws IOException {
        Optional<String> lastLine = Optional.empty();
        ReversedLinesFileReader reader = new ReversedLinesFileReader(path, StandardCharsets.UTF_8);
        String line;
        if ((line = reader.readLine()) != null)
            lastLine = Optional.ofNullable(line);
        reader.close();
        return lastLine;
    }
}
