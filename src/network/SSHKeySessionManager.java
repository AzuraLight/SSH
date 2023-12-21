package network;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import config.SSHConfig;
import util.ConfigUtil;

/**
 * SSHKeySessionManager 클래스는 SSH 연결 관리를 책임집니다.
 * 이 클래스는 ConfigUtil을 사용하여 설정 파일에서 SSH 연결 정보를 로드하고,
 * JSch 라이브러리를 사용하여 SSH 세션을 생성 및 관리합니다.
 * AutoCloseable 인터페이스를 구현하여, try-with-resources 구문을 통해
 * 자동으로 세션을 닫을 수 있도록 합니다. 이 클래스의 목적은 SSH 연결의
 * 세부 구현을 캡슐화하고 재사용 가능한 방식으로 제공하는 데 있습니다.
 * 
 * 본 클래스는 SSHSession 클래스에서 사용하는 비밀번호 인증을 대신하여
 * 키 인증을 사용하고 있습니다.
 */
public class SSHKeySessionManager implements AutoCloseable {

    private Session session;
    private ConfigUtil configUtil;

    public SSHKeySessionManager(ConfigUtil configUtil) {
        this.configUtil = configUtil;
        SSHConfig sshConfig = this.configUtil.loadSSHConfigWithKey();

        try {
            JSch jsch = new JSch();

            // 공개키 파일의 경로를 지정
            String privateKeyPath = sshConfig.getPrivateKeyPath();
            jsch.addIdentity(privateKeyPath);

            // 사용자 이름, 호스트 이름, 포트 번호를 사용하여 SSH 세션 생성
            session = jsch.getSession(sshConfig.getUsername(), sshConfig.getHostname(), sshConfig.getPort());

            // HostKey 확인을 건너뛰는 설정 (보안에 취약할 수 있음)
            session.setConfig("StrictHostKeyChecking", "no");

            // 세션 연결
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public Session getSession() {
        return session;
    }

    // 세션 연결 해제
    @Override
    public void close() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
