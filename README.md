# SSH and SFTP Examples with Utilities

이 프로젝트는 Java를 사용하여 SSH 및 SFTP 연결을 관리하기 위한 예제와 유틸리티 모음입니다. 포트 포워딩, X11 리다이렉션, 파일 전송 등의 기능을 포함하고 있습니다.

## 기능

- SSH 세션 관리
- 포트 포워딩
- X11 리다이렉션
- SFTP를 통한 파일 업로드 및 다운로드
- 자동 자원 관리를 위한 AutoCloseable 구현

## 사용 방법

### SSH 세션 관리

`SSHSessionManager` 클래스는 SSH 세션을 관리합니다. 이 클래스는 세션 연결 및 종료를 캡슐화합니다.

```java
SSHSessionManager sessionManager = new SSHSessionManager(configUtil);
Session session = sessionManager.getSession();
```

### 포트 포워딩

`PortForwarding` 클래스는 지정된 로컬 포트를 원격 서버의 포트에 연결합니다.

```java
int localPort = 8080;
String remoteHost = "remote-server";
int remotePort = 80;
session.setPortForwardingL(localPort, remoteHost, remotePort);
```

### X11 리다이렉션

`X11Redirection` 클래스는 원격 SSH 서버에서 GUI 애플리케이션을 실행하고 결과를 로컬로 리다이렉션합니다.

```java
session.setX11Host("localhost");
session.setX11Port(6000);
session.execCommand("xeyes");
```

### SFTP 파일 전송

`SFTPFileTransfer` 클래스는 파일 업로드 및 다운로드 기능을 제공합니다.

```java
SFTPFileTransfer fileTransfer = new SFTPFileTransfer(sessionManager, pathConfig, fileConfig);
fileTransfer.transferFiles();
```

### 설정 관리

`ConfigUtil` 클래스는 프로젝트 설정을 관리합니다. config.properties 파일로부터 필요한 설정을 로드합니다.

```java
ConfigUtil configUtil = ConfigUtil.getInstance();
```

## 설치

이 프로젝트를 사용하기 위해서는 Java와 JSch 라이브러리가 필요합니다.

JSch 라이브러리를 다운로드하고 프로젝트 ROOT 아래에 lib 폴더를 만들고 이곳에 포함시킵니다. 

이 레포지토리를 클론하거나 다운로드합니다.

config.properties 파일에 필요한 설정을 추가합니다.

## 라이센스

이 프로젝트는 MIT 라이센스 하에 배포됩니다.
