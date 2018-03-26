package gov.goias.sso;

import gov.goias.sso.domain.UserAgent;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtil {


    public UserAgent create(HttpServletRequest request) {
//        final String referer = getReferer(request);
//        final String fullURL = getFullURL(request);
        final String clientIpAddr = getClientIpAddr(request);
        final String clientOS = getClientOS(request);
        final String clientBrowser = getClientBrowser(request);
        final String userAgent = getUserAgent(request);
//        System.out.println("\n" +
//                "User Agent \t" + userAgent + "\n" +
//                "Operating System\t" + clientOS + "\n" +
//                "Browser Name\t" + clientBrowser + "\n" +
//                "IP Address\t" + clientIpAddr + "\n" +
//                "Full URL\t" + fullURL + "\n" +
//                "Referrer\t" + referer);
        return UserAgent.builder().client(userAgent).ip(clientIpAddr).so(clientOS).browser(clientBrowser).build();
    }

    private String getReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    private String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        return queryString == null ? requestURL.toString() : requestURL.append('?')
                .append(queryString)
                .toString();
    }

    private String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private String getClientOS(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");
        final String lowerCaseBrowser = browserDetails.toLowerCase();
        if (lowerCaseBrowser.contains("windows")) {
            return "Windows";
        } else if (lowerCaseBrowser.contains("mac")) {
            return "Mac";
        } else if (lowerCaseBrowser.contains("x11")) {
            return "Unix";
        } else if (lowerCaseBrowser.contains("android")) {
            return "Android";
        } else if (lowerCaseBrowser.contains("iphone")) {
            return "IPhone";
        } else {
            return "UnKnown, More-Info: " + browserDetails;
        }
    }

    private String getClientBrowser(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");
        final String user = browserDetails.toLowerCase();
        String browser = "";
        if (user.contains("msie")) {
            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split(
                    "/")[0] + "-" + (browserDetails.substring(
                    browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera"))
                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split(
                        "/")[0] + "-" + (browserDetails.substring(
                        browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if (user.contains("opr"))
                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/",
                        "-")).replace(
                        "OPR", "Opera");
        } else if (user.contains("chrome")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf(
                "mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf(
                "mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "IE";
        } else {
            browser = "UnKnown, More-Info: " + browserDetails;
        }
        return browser;
    }

    private String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

}
