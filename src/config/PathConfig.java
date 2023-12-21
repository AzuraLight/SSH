package config;

/**
 * PathConfig 클래스는 SFTP에 필요한 경로 정보를 캡슐화합니다.
 * 이 클래스는 로컬 업로드 경로, 로컬 다운로드 경로, 원격 업로드 경로, 원격 다운로드 경로 같은
 * 정보를 저장하며, 이 정보는 SFTP를 통해 파일 업로드 및 다운로드에 사용됩니다.
 * PathConfig 객체는 ConfigUtil을 통해 로드되며, 파일 업로드 및 다운로드에 필요한
 * 경로 정보를 제공합니다. 이 클래스의 목적은 SFTP 사용에 필요한 경로 정보를
 * 중앙화하고, 일관된 접근 방식을 제공하는 데 있습니다.
 */
public class PathConfig {

    private String localUploadPath;
    private String localDownloadPath;
    private String remoteUploadPath;
    private String remoteDownloadPath;

    public PathConfig(String localUploadPath, String localDownloadPath, String remoteUploadPath,
            String remoteDownloadPath) {
        this.localUploadPath = localUploadPath;
        this.localDownloadPath = localDownloadPath;
        this.remoteUploadPath = remoteUploadPath;
        this.remoteDownloadPath = remoteDownloadPath;
    }

    // Getter for the LocalUploadPath
    public String getLocalUploadPath() {
        return this.localUploadPath;
    }

    // Getter for the LocalDownloadPath
    public String getLocalDownloadPath() {
        return this.localDownloadPath;
    }

    // Getter for the RemoteUploadPath
    public String getRemoteUploadPath() {
        return this.remoteUploadPath;
    }

    // Getter for the RemoteDownloadPath
    public String getRemoteDownloadPath() {
        return this.remoteDownloadPath;
    }

}
