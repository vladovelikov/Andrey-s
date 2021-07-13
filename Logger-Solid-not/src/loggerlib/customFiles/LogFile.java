package loggerlib.customFiles;
import loggerlib.customFiles.interfaces.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile implements File {

    private StringBuilder buffer;
    private FileWriter fileWriter;

    public LogFile() throws IOException {
        this.buffer = new StringBuilder();
        this.setFileWriter(System.getProperty("user.dir") + "\\Output.txt");
    }

    public void setFileWriter (String path) throws IOException {
            this.fileWriter = new FileWriter(path);
    }

    @Override
    public void write() {
        try {
            this.setFileWriter(System.getProperty("user.dir") + "\\Output.txt");
            String text = buffer.toString();
            this.fileWriter.append(text);
            this.fileWriter.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public int getSize() {
        int size = 0;
        for (char c : this.buffer.toString().toCharArray()) {
            if (Character.isAlphabetic(c)) {
                size += c;
            }
        }

        return size;
    }

    @Override
    public void appendBuffer(String text) {
        this.buffer.append(text).append("\r\n");
    }
}
