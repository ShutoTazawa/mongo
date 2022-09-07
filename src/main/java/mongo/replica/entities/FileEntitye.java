package mongo.replica.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "File")
public class FileEntitye {

	//ファイル名
    private String filename;
    //ファイル形式
    private String fileType;
    //ファイルサイズ
    private String fileSize;
    private String[] file;

    public FileEntitye() {
    }
    
	public FileEntitye(String filename, String fileType,String fileSize, String[] file) {
		this.filename = filename;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.file = file;
	}
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String[] getFile() {
        return file;
    }

    public void setFile(String[] file) {
        this.file = file;
    }
}
