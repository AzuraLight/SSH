package sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import config.FileConfig;
import config.PathConfig;
import network.SSHSessionManager;
import util.ConfigUtil;

/**
 * JSch를 사용하여 SSH를 통한 파일 전송(SFTP)을 수행하는 예제
 * SFTP 채널을 열고, 로컬 파일을 원격 서버로 업로드 및 다운로드 합니다.
 * 이 클래스는 ConfigUtil을 사용하여 설정 파일에서 경로, 파일 정보를 로드하고,
 * JSch 라이브러리를 사용하여 SSH 세션을 생성 및 관리하고 있습니다.
 */
public class SFTPFileTransfer {

    private SSHSessionManager sessionManager;
    private PathConfig pathConfig;
    private FileConfig fileConfig;

    public SFTPFileTransfer(SSHSessionManager sessionManager, PathConfig pathConfig, FileConfig fileConfig) {
        this.sessionManager = sessionManager;
        this.pathConfig = pathConfig;
        this.fileConfig = fileConfig;
    }

    public void transferFiles() {

        String localUpload = pathConfig.getLocalUploadPath() + "/" + fileConfig.getUploadFile();
        String localDownload = pathConfig.getLocalDownloadPath() + "/" + fileConfig.getDownloadFile();
        String remoteUpload = pathConfig.getRemoteUploadPath() + "/" + fileConfig.getUploadFile();
        String remoteDownload = pathConfig.getRemoteDownloadPath() + "/" + fileConfig.getDownloadFile();

        try {
            Session session = sessionManager.getSession();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            try (AutoCloseableSftpChannel autoCloseableSftpChannel = new AutoCloseableSftpChannel(
                    (ChannelSftp) channel)) {
                ChannelSftp sftpChannel = autoCloseableSftpChannel.getChannel();

                // 로컬 파일을 원격 서버로 업로드
                sftpChannel.put(localUpload, remoteUpload);
                System.out.println("upload successfully");

                // 원격 서버의 파일을 로컬로 다운로드
                sftpChannel.get(remoteDownload, localDownload);
                System.out.println("download successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionManager.close();
        }
    }

    public static void main(String[] args) {

        ConfigUtil configUtil = ConfigUtil.getInstance();
        SSHSessionManager sessionManager = new SSHSessionManager(configUtil);
        PathConfig pathConfig = configUtil.loadPathConfig();
        FileConfig fileConfig = configUtil.loadFileConfig();

        SFTPFileTransfer fileTransfer = new SFTPFileTransfer(sessionManager, pathConfig, fileConfig);
        fileTransfer.transferFiles();
    }
}
