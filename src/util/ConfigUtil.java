package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import config.FileConfig;
import config.PathConfig;
import config.SSHConfig;

/**
 * ConfigUtil 클래스는 애플리케이션의 구성 정보를 로드하는 유틸리티 클래스입니다.
 * 이 클래스는 'config/config.properties' 파일에서 구성 정보를 읽어오며,
 * 해당 정보는 SSH 연결 설정 및 기타 필요한 설정 정보에 사용됩니다.
 * Properties 클래스를 사용하여 파일 내용을 읽어오고, 필요한 구성 정보를
 * 쉽게 검색할 수 있도록 메소드를 제공합니다. ConfigUtil의 목적은
 * 애플리케이션의 설정을 중앙화하고, 코드 내에서 설정값을 쉽게 접근할 수 있도록
 * 하는 데 있습니다.
 * 또한 싱글톤 패턴을 활용하여 자원의 사용을 최적화 하려고 하였습니다.
 */
public class ConfigUtil {

    private static final ConfigUtil instance = new ConfigUtil();
    private Properties properties;

    private ConfigUtil() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigUtil getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public SSHConfig loadSSHConfig() {
        String username = getProperty("username");
        String hostname = getProperty("hostname");
        String password = getProperty("password");
        int port = Integer.parseInt(getProperty("port"));

        return new SSHConfig(username, hostname, password, port);
    }

    public SSHConfig loadSSHConfigWithKey() {
        String username = getProperty("username");
        String hostname = getProperty("hostname");
        int port = Integer.parseInt(getProperty("port"));
        String privateKeyPath = getProperty("privateKey");
        return new SSHConfig(username, hostname, port, privateKeyPath);
    }

    public PathConfig loadPathConfig() {

        String localUploadPath = getProperty("local.upload.path");
        String loacalDownloadPath = getProperty("local.download.path");
        String remoteUploadPath = getProperty("remote.upload.path");
        String remoteDownloadPath = getProperty("remote.download.path");

        return new PathConfig(localUploadPath, loacalDownloadPath, remoteUploadPath, remoteDownloadPath);
    }

    public FileConfig loadFileConfig() {

        String uploadFile = getProperty("uploadFile");
        String downloadFile = getProperty("downloadFile");

        return new FileConfig(uploadFile, downloadFile);
    }
}
