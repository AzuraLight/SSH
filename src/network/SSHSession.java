package network;
import com.jcraft.jsch.Session;

import util.ConfigUtil;

/**
 * SSH 연결을 시연하는 예제 클래스
 * 이 클래스는 SSHSessionManager를 사용하여 SSH 서버에 연결하고,
 * 연결 상태를 확인한 후에 연결을 종료합니다.
 */
public class SSHSession {

    public static void main(String[] args) {

        ConfigUtil configUtil = ConfigUtil.getInstance();

        try (SSHSessionManager sessionManager = new SSHSessionManager(configUtil);) {
            Session session = sessionManager.getSession();
            if (session.isConnected()) {
                System.out.println("Connected to the server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
