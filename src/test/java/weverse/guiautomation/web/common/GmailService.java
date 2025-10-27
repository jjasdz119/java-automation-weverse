package weverse.guiautomation.web.common;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailService {

    private static final String user = "me";
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";
    private final String APPLICATION_NAME = "Weserve";
    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final List<String> SCOPES = List.of(GmailScopes.GMAIL_READONLY);
    private final String TOKENS_DIRECTORY_PATH;

    public GmailService() {
        TOKENS_DIRECTORY_PATH = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "test" +
                File.separator + "resources";
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("credentials.json 파일을 찾을 수 없음 " + CREDENTIALS_FILE_PATH);
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        File tokensDir = new File(TOKENS_DIRECTORY_PATH);
        if (!tokensDir.exists()) {
            tokensDir.mkdirs();
        }

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline").build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    // 이메일 목록 가져오기
    private List<Message> getMessages(ListMessagesResponse response) {
        List<Message> messages = new ArrayList<>();
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME).build();
            while (response.getMessages() != null) {
                messages.addAll(response.getMessages());
                if (response.getNextPageToken() != null) {
                    String pageToken = response.getNextPageToken();
                    response = service.users().messages().list(user)
                            .setPageToken(pageToken).execute();
                } else {
                    break;
                }
            }
            return messages;
        } catch (Exception e) {
            System.out.println("Exception log " + e);
            return messages;
        }
    }

    // 최근 Weverse 이메일에서 제목 추출
    public String getEmailSubject() {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME).build();

            Thread.sleep(3000);
            ListMessagesResponse response = service
                    .users().messages().list(user).setQ("subject:Weverse").execute();

            List<Message> messageList = response.getMessages();
            if (messageList == null || messageList.isEmpty()) {
                return "Weverse 이메일이 존재하지 않음";
            }

            Message message = service.users().messages().get(user, messageList.get(0).getId()).execute();

            // 제목 직접 추출
            if (message.getPayload() != null && message.getPayload().getHeaders() != null) {
                for (MessagePartHeader header : message.getPayload().getHeaders()) {
                    if ("Subject".equalsIgnoreCase(header.getName())) {
                        return header.getValue();
                    }
                }
            }
            return "제목을 찾을 수 없습니다";

        } catch (Exception e) {
            System.out.println("Exception log " + e);
            return "이메일 제목을 가져올 수 없습니다";
        }
    }

    // 인증코드 추출
    public String extractAuthCode() {
        String subject = getEmailSubject();
        Pattern pattern = Pattern.compile("\\d{6}");
        Matcher matcher = pattern.matcher(subject);

        if (matcher.find()) {
            return matcher.group(0);
        }
        return "인증 코드를 찾을 수 없음";
    }
}