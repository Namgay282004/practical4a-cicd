package sg.edu.nus.iss.cicddemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*") // DAST Issue: Overly permissive CORS
public class DastVulnerableController {

    // DAST Issue 1: Missing security headers
    @GetMapping("/insecure-page")
    public String insecurePage() {
        // This endpoint will be flagged for missing security headers
        return "<html><body><h1>Insecure Page</h1><p>This page lacks security headers</p></body></html>";
    }

    // DAST Issue 2: Reflected XSS vulnerability
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String query) {
        if (query == null) {
            query = "";
        }
        // XSS vulnerability - user input reflected without sanitization
        return "<html><body><h1>Search Results</h1><p>You searched for: " + query + "</p></body></html>";
    }

    // DAST Issue 3: Information disclosure through error messages
    @GetMapping("/error-disclosure")
    public ResponseEntity<String> errorDisclosure(@RequestParam String file) {
        try {
            // Simulate file operation that could expose system information
            if (file.contains("..") || file.contains("/")) {
                throw new Exception("Invalid file path: " + System.getProperty("user.dir") + "/" + file);
            }
            return ResponseEntity.ok("File processed: " + file);
        } catch (Exception e) {
            // Information disclosure - exposing internal paths and system info
            return ResponseEntity.badRequest().body("Error: " + e.getMessage() + 
                ". System: " + System.getProperty("os.name") + 
                ", Java: " + System.getProperty("java.version"));
        }
    }

    // DAST Issue 4: Insecure cookie settings
    @GetMapping("/set-cookie")
    public ResponseEntity<String> setCookie(HttpServletResponse response) {
        // Insecure cookie - no Secure, HttpOnly, or SameSite flags
        Cookie cookie = new Cookie("sessionId", "abc123456789");
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        // Missing security flags:
        // cookie.setSecure(true);
        // cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
        return ResponseEntity.ok("Cookie set without security flags");
    }

    // DAST Issue 5: Open redirect vulnerability
    @GetMapping("/redirect")
    public void redirect(@RequestParam String url, HttpServletResponse response) throws IOException {
        // Open redirect - no validation of redirect URL
        response.sendRedirect(url);
    }

    // DAST Issue 6: Clickjacking vulnerability (no X-Frame-Options)
    @GetMapping("/frame-me")
    public String frameMe() {
        return "<html><body><h1>This page can be framed!</h1>" +
               "<p>This page is vulnerable to clickjacking attacks.</p>" +
               "<button onclick='alert(\"Clicked!\")'>Click Me</button></body></html>";
    }

    // DAST Issue 7: Content type sniffing vulnerability
    @GetMapping("/content-sniff")
    public ResponseEntity<String> contentSniff() {
        HttpHeaders headers = new HttpHeaders();
        // Missing X-Content-Type-Options: nosniff header
        headers.set("Content-Type", "text/html");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body("<script>alert('This could be sniffed as script')</script>");
    }

    // DAST Issue 8: Missing HTTPS enforcement
    @GetMapping("/sensitive-data")
    public ResponseEntity<String> sensitiveData() {
        // This endpoint handles sensitive data but doesn't enforce HTTPS
        // Missing Strict-Transport-Security header
        return ResponseEntity.ok("Sensitive user data: SSN=123-45-6789, Credit Card=4111-1111-1111-1111");
    }

    // DAST Issue 9: Server information disclosure
    @GetMapping("/server-info")
    public ResponseEntity<String> serverInfo() {
        HttpHeaders headers = new HttpHeaders();
        // Disclosing server information
        headers.set("Server", "Apache Tomcat/9.0.0 (Custom Build)");
        headers.set("X-Powered-By", "Spring Boot 3.2.12");
        headers.set("X-Debug-Token", "debug-12345");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body("Server information disclosed in headers");
    }

    // DAST Issue 10: Form without CSRF protection
    @PostMapping("/submit-form")
    public String submitForm(@RequestParam String username, @RequestParam String action) {
        // This form endpoint lacks CSRF protection
        // In a real app, this could allow CSRF attacks
        return "Form submitted for user: " + username + " with action: " + action;
    }

    // DAST Issue 11: File upload without validation
    @PostMapping("/upload")
    public String fileUpload(@RequestBody String fileContent, @RequestParam String fileName) {
        // File upload without proper validation
        if (fileName.endsWith(".exe") || fileName.endsWith(".php") || fileName.endsWith(".jsp")) {
            return "Dangerous file uploaded: " + fileName;
        }
        return "File uploaded: " + fileName + " (size: " + fileContent.length() + " bytes)";
    }

    // DAST Issue 12: Directory traversal via parameter
    @GetMapping("/download")
    public String downloadFile(@RequestParam String filename) {
        // Directory traversal vulnerability
        String basePath = "/app/downloads/";
        String fullPath = basePath + filename;
        
        // Vulnerable - no validation against directory traversal
        try {
            java.io.File file = new java.io.File(fullPath);
            if (file.exists()) {
                return "Downloading file: " + file.getAbsolutePath();
            } else {
                return "File not found: " + fullPath;
            }
        } catch (Exception e) {
            return "Error accessing file: " + fullPath + " - " + e.getMessage();
        }
    }

    // DAST Issue 13: Weak session management
    @GetMapping("/session")
    public ResponseEntity<String> sessionManagement(@RequestParam(required = false) String sessionId) {
        HttpHeaders headers = new HttpHeaders();
        
        if (sessionId == null) {
            // Weak session ID generation - predictable pattern
            sessionId = "session_" + System.currentTimeMillis();
            headers.set("Set-Cookie", "JSESSIONID=" + sessionId + "; Path=/");
        }
        
        return ResponseEntity.ok()
                .headers(headers)
                .body("Session ID: " + sessionId);
    }

    // DAST Issue 14: HTML injection (different from XSS)
    @GetMapping("/comment")
    public String comment(@RequestParam String comment) {
        // HTML injection vulnerability
        return "<html><body><h2>User Comment:</h2>" +
               "<div class='comment'>" + comment + "</div>" +
               "</body></html>";
    }

    // DAST Issue 15: Cache control missing for sensitive content
    @GetMapping("/private-data")
    public ResponseEntity<String> privateData() {
        // Missing cache control headers for sensitive data
        return ResponseEntity.ok("Private user data - should not be cached");
    }
}
