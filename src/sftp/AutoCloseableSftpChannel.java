package sftp;

import com.jcraft.jsch.ChannelSftp;

/**
 * AutoCloseableSftpChannel클래스는 AutoClosable 인터페이스를 활용하여
 * ChannelSftp를 확장한 구현체 클래스입니다.
 * 본 클래스는 try-with-resources를 활용하고, 자원관리의 효율성을 얻기 위해
 * 작성하였으며, ChannelSftp를 종료하는 기능을 관리하고 있습니다.
 */
public class AutoCloseableSftpChannel implements AutoCloseable {

    private ChannelSftp channelSftp;

    public AutoCloseableSftpChannel(ChannelSftp channelSftp) {
        this.channelSftp = channelSftp;
    }

    public ChannelSftp getChannel() {
        return channelSftp;
    }

    @Override
    public void close() throws Exception {
        if (channelSftp.isConnected()) {
            channelSftp.disconnect();
        }
    }
}
