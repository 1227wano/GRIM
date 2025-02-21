package com.grim.auth.util;

public class HtmlUtils {
    
    /**
     * @param input 사용자가 입력한 값
     * @return 게시판에서 사용자가 컨텐트를 작성안하고 타이틀과 첨부파일만 한다면 null값이 아닌 빈 문자열을 넣어 비어두는 리턴
     * 
     * XSS 방지를 위한 메서드
     * 필요한 곳에 사용하자!
     * 
     */
    public static String escapeHtml(String input) {
        if (input == null) {
            return "";
        }
        StringBuilder escaped = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '<':
                    escaped.append("&lt;");
                    break;
                case '>':
                    escaped.append("&gt;");
                    break;
                case '&':
                    escaped.append("&amp;");
                    break;
                case '"':
                    escaped.append("&quot;");
                    break;
                case '\'':
                    escaped.append("&#39;");
                    break;
                default:
                    escaped.append(c);
            }
        }
        return escaped.toString();
    }
}

