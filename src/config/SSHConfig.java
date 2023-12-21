package config;

/**
 * SSHConfig 클래스는 SSH 연결에 필요한 구성 정보를 캡슐화합니다.
 * 이 클래스는 SSH 서버의 사용자 이름, 호스트 이름, 비밀번호, 포트 번호와 같은
 * 정보를 저장하며, 이 정보는 SSH 연결을 설정하는 데 사용됩니다.
 * SSHConfig 객체는 ConfigUtil을 통해 로드되며, SSH 연결 관리에 필요한
 * 기본 정보를 제공합니다. 이 클래스의 목적은 SSH 연결에 필요한 설정 정보를
 * 중앙화하고, 일관된 접근 방식을 제공하는 데 있습니다.
 */
public class SSHConfig {

    String username;
    String hostname;
    String password;
    int port;

    public SSHConfig(String username, String hostname, String password, int port) {
        this.username = username;
        this.hostname = hostname;
        this.password = password;
        this.port = port;
    }

    // Getter
    public String getUsername() {
        return username;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }
}
