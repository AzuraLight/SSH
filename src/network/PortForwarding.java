package network;

import java.util.Scanner;

import com.jcraft.jsch.Session;

import util.ConfigUtil;

/**
 * JSch를 사용하여 포트 포워딩을 설정하는 예제
 * 지정된 로컬 포트를 원격 서버의 특정 포트로 포워딩합니다.
 * 본 예제는 기존의 SSHSessionManager의 Autoclosable을 활용하지 않고,
 * 사용자의 입력을 대기하였다가 입력이 들어오면, 해당 세션을 종료하는
 * 방식으로 구현되었습니다.
 */
public class PortForwarding {

    public static void main(String[] args) {
        ConfigUtil configUtil = ConfigUtil.getInstance();
        SSHSessionManager sessionManager = null;

        try {
            sessionManager = new SSHSessionManager(configUtil);
            Session session = sessionManager.getSession();
            if (session.isConnected()) {
                System.out.println("Connected to the server.");
            }

            // 포워딩 정의
            int localPort = 8080;
            String remoteHost = "127.0.0.1";
            int remotePort = 80;
            session.setPortForwardingL(localPort, remoteHost, remotePort);

            System.out.println("Port forwarding established.");

            // Scanner를 활용해서 엔터 키 입력 대기
            System.out.println("Press Enter to exit...");
            try (Scanner scanner = new Scanner(System.in)) {
                scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sessionManager != null) {
                sessionManager.close(); // 세션 종료
            }
        }

    }
}
