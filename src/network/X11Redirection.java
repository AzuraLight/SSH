package network;

import java.util.Scanner;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import util.ConfigUtil;

/**
 * JSch를 사용하여 X11 리다이렉션을 수행하는 예제
 * SSH 세션 내에서 ChannelExec 클래스를 활용하여 
 * X11 애플리케이션을 실행하고, 출력을 로컬 시스템으로 리다이렉션합니다.
 */
public class X11Redirection {

    public static void main(String[] args) {

        ConfigUtil configUtil = ConfigUtil.getInstance();
        SSHSessionManager sessionManager = null;

        try {
            sessionManager = new SSHSessionManager(configUtil);
            Session session = sessionManager.getSession();

            // 연결 상태 확인 및 출력
            if (session.isConnected()) {
                System.out.println("Connected to the server.");
            } else {
                System.out.println("Session is not connected.");
                return; // 연결이 안 되어 있다면 메소드 종료
            }

            // X11 리다이렉션 활성화
            session.setX11Host("localhost");
            session.setX11Port(6000);

            // ChannelExec를 활용하여 원격 SSH 서버에서 명령을 실행 - X11을 사용하는 명령 실행
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand("xeyes");
            channelExec.setXForwarding(true);
            channelExec.connect();

            // Scanner를 활용해서 엔터 키 입력 대기
            System.out.println("Press Enter to exit...");
            try (Scanner scanner = new Scanner(System.in)) {
                scanner.nextLine();
            }

            // ChannelExec 종료
            channelExec.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sessionManager != null) {
                sessionManager.close(); // 세션 종료
            }
        }
    }
}
