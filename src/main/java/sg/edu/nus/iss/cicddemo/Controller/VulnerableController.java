package sg.edu.nus.iss.cicddemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class VulnerableController {

    private static final Logger logger = Logger.getLogger(VulnerableController.class.getName());
    
    // Security Issue 1: Hard-coded credentials (Security Hotspot)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "password123";  // Hard-coded password
    
    // Security Issue 2: Weak cryptographic algorithm (Security Hotspot)
    @GetMapping("/hash/{input}")
    public String createHash(@PathVariable String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");  // MD5 is weak
            byte[] hashBytes = md5.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return "Hash: " + sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error generating hash";
        }
    }
    
    // Security Issue 3: SQL Injection vulnerability
    @GetMapping("/user")
    public String getUserData(@RequestParam String userId) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            
            // SQL Injection vulnerability - direct string concatenation
            String query = "SELECT * FROM users WHERE id = '" + userId + "'";
            logger.info("Executing query: " + query);  // Logging sensitive data
            
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return "User found: " + rs.getString("username");
            }
            return "User not found";
        } catch (Exception e) {
            // Security Issue 4: Information disclosure through error messages
            return "Database error: " + e.getMessage();
        }
    }
    
    // Security Issue 5: Path traversal vulnerability
    @GetMapping("/file")
    public String readFile(@RequestParam String filename) {
        try {
            java.io.File file = new java.io.File("/app/data/" + filename);  // No path validation
            java.util.Scanner scanner = new java.util.Scanner(file);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            return content.toString();
        } catch (Exception e) {
            return "Error reading file: " + e.getMessage();
        }
    }
    
    // Security Issue 6: Predictable random number generator
    @GetMapping("/random-token")
    public String generateToken() {
        Random random = new Random();  // Not cryptographically secure
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            token.append(random.nextInt(10));
        }
        return "Token: " + token.toString();
    }
    
    // Security Issue 7: Improper input validation
    @GetMapping("/calculate")
    public String calculate(@RequestParam String expression) {
        // Dangerous: executing user input without validation
        try {
            javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
            javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
            Object result = engine.eval(expression);  // Code injection vulnerability
            return "Result: " + result.toString();
        } catch (Exception e) {
            return "Calculation error: " + e.getMessage();
        }
    }
    
    // Security Issue 8: Missing authentication/authorization
    @GetMapping("/admin/delete-user/{userId}")
    public String deleteUser(@PathVariable String userId) {
        // No authentication or authorization checks
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setString(1, userId);
            int deleted = stmt.executeUpdate();
            return "Deleted " + deleted + " users";
        } catch (Exception e) {
            return "Delete failed: " + e.getMessage();
        }
    }
    
    // Security Issue 9: XML External Entity (XXE) vulnerability
    @GetMapping("/process-xml")
    public String processXml(@RequestParam String xmlData) {
        try {
            javax.xml.parsers.DocumentBuilderFactory factory = 
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
            // No XXE protection configured
            javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
            java.io.StringReader reader = new java.io.StringReader(xmlData);
            org.xml.sax.InputSource inputSource = new org.xml.sax.InputSource(reader);
            org.w3c.dom.Document doc = builder.parse(inputSource);
            return "XML processed successfully. Root element: " + doc.getDocumentElement().getNodeName();
        } catch (Exception e) {
            return "XML processing error: " + e.getMessage();
        }
    }
    
    // Security Issue 10: Insecure deserialization
    @GetMapping("/deserialize")
    public String deserializeObject(@RequestParam String data) {
        try {
            byte[] decodedData = java.util.Base64.getDecoder().decode(data);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                new java.io.ByteArrayInputStream(decodedData));
            Object obj = ois.readObject();  // Insecure deserialization
            return "Deserialized: " + obj.toString();
        } catch (Exception e) {
            return "Deserialization error: " + e.getMessage();
        }
    }
}
