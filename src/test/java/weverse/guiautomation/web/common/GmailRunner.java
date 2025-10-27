package weverse.guiautomation.web.common;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GmailRunner {

        @Test
        void testHasEmails() {
            GmailService service = new GmailService();
            System.out.println("# 이메일 제목: " + service.getEmailSubject());
            System.out.println("# 인증 코드: " + service.extractAuthCode());
        }
}
