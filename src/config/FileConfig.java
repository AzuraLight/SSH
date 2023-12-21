package config;

/**
 * FileConfig 클래스는 SFTP에 필요한 파일 정보를 캡슐화합니다.
 * 이 클래스는 업로드 파일, 다운로드 파일 같은
 * 정보를 저장하며, 이 정보는 SFTP를 통해 파일 업로드 및 다운로드에 사용됩니다.
 * FileConfig 객체는 ConfigUtil을 통해 로드되며, 파일 업로드 및 다운로드에 필요한
 * 파일 정보를 제공합니다. 이 클래스의 목적은 SFTP 사용에 필요한 파일 정보를
 * 중앙화하고, 일관된 접근 방식을 제공하는 데 있습니다.
 */
public class FileConfig {

    private String uploadFile;
    private String downloadFile;

    public FileConfig(String uploadFile, String downloadFile) {
        this.uploadFile = uploadFile;
        this.downloadFile = downloadFile;
    }

    // Getter for the upload
    public String getUploadFile() {
        return this.uploadFile;
    }

    // Getter for the download
    public String getDownloadFile() {
        return this.downloadFile;
    }
}
