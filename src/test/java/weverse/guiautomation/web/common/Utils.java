package weverse.guiautomation.web.common;

import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public String performGetRequest(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("GET 응답 코드: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return "GET 요청이 응답 코드로 실패했습니다: " + responseCode;
        }
    }

    // 특정 URL로 GET 요청을 보내고 응답 본문을 반환
    public static String sendGetRequest(String urlString, Map<String, String> headers) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 헤더 설정
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
            return null;
        } catch (Exception e) {
            System.out.println("API 호출 실패: " + e.getMessage());
            return null;
        }
    }
}

    //
//    // 브라우저 쿠키를 가져와서 헤더로 변환
//    public static Map<String, String> getCookieHeaders(WebDriver driver) {
//        Map<String, String> headers = new HashMap<>();
//
//        // 쿠키를 문자열로 변환
//        StringBuilder cookieString = new StringBuilder();
//        driver.manage().getCookies().forEach(cookie -> {
//            if (cookieString.length() > 0) {
//                cookieString.append("; ");
//            }
//            cookieString.append(cookie.getName()).append("=").append(cookie.getValue());
//        });
//
//        if (cookieString.length() > 0) {
//            headers.put("Cookie", cookieString.toString());
//        }
//
//        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
//        return headers;
//    }
//
//    // JSON 응답에서 특정 키의 값을 추출 (간단한 파싱)
//    public static String extractJsonValue(String json, String key) {
//        if (json == null) return null;
//
//        try {
//            String searchKey = "\"" + key + "\"";
//            int startIndex = json.indexOf(searchKey);
//            if (startIndex == -1) return null;
//
//            // 키 다음의 ':' 찾기
//            int colonIndex = json.indexOf(":", startIndex);
//            if (colonIndex == -1) return null;
//
//            // 값의 시작 찾기 (공백 건너뛰기)
//            int valueStart = colonIndex + 1;
//            while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
//                valueStart++;
//            }
//
//            // 값이 문자열인 경우
//            if (json.charAt(valueStart) == '"') {
//                int valueEnd = json.indexOf('"', valueStart + 1);
//                return json.substring(valueStart + 1, valueEnd);
//            }
//            // 값이 숫자나 boolean인 경우
//            else {
//                int valueEnd = valueStart;
//                while (valueEnd < json.length() &&
//                        !Character.isWhitespace(json.charAt(valueEnd)) &&
//                        json.charAt(valueEnd) != ',' &&
//                        json.charAt(valueEnd) != '}' &&
//                        json.charAt(valueEnd) != ']') {
//                    valueEnd++;
//                }
//                return json.substring(valueStart, valueEnd);
//            }
//        } catch (Exception e) {
//            System.out.println("JSON 파싱 실패: " + e.getMessage());
//            return null;
//        }
//    }
//
//
//    // AI 호출 후 responseBody 값에서 키값 추출
//    public static String getApiValue(String apiUrl, Map<String, String> headers, String key) {
//        String response = sendGetRequest(apiUrl, headers);
//        if (response == null) return null;
//        return extractJsonValue(response, key);
//    }
//
//    // 브라우저 로컬 스토리지 가져오기
//    public static String getLocalStorage(WebDriver driver, String key) {
//        Object value = ((org.openqa.selenium.JavascriptExecutor) driver)
//                .executeScript("return window.localStorage.getItem(arguments[0]);", key);
//        return value != null ? value.toString() : null;
//    }
//
//    // 브라우저 세션 스토리지 가져오기
//    public static String getSessionStorage(WebDriver driver, String key) {
//        Object value = ((org.openqa.selenium.JavascriptExecutor) driver)
//                .executeScript("return window.sessionStorage.getItem(arguments[0]);", key);
//        return value != null ? value.toString() : null;
//    }
//}


