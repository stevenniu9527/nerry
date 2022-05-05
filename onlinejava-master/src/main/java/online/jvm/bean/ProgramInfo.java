package online.jvm.bean;

public class ProgramInfo {
    private String filePath;
    private String fileName;

    public ProgramInfo(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }
}
