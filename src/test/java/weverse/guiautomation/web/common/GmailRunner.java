package weverse.guiautomation.web.common;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GmailRunner {

        @Test
        void testHasEmails() {
                GmailService service = new GmailService();
                System.out.println("# 총 이메일 개수: " + service.getEmailsCount());

                boolean exist = service.isMailExist();
                System.out.println("# 메일 제목 'Weverse' 존재 여부: " + exist);

            try {
                System.out.println("이메일 제목:" + service.getEmailSubject());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
}
