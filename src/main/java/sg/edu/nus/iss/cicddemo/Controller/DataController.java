package sg.edu.nus.iss.cicddemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@RestController
public class DataController {
    @GetMapping("/")
    public String healthCheck() {
        return "<html><body>" +
               "<h1>CICD Demo Application</h1>" +
               "<p>HEALTH CHECK OK!</p>" +
               "<h2>Available Endpoints:</h2>" +
               "<ul>" +
               "<li><a href='/version'>Version Info</a></li>" +
               "<li><a href='/nations'>Random Nations</a></li>" +
               "<li><a href='/currencies'>Random Currencies</a></li>" +
               "<li><a href='/search?query=test'>Search Function</a></li>" +
               "<li><a href='/insecure-page'>Insecure Page</a></li>" +
               "<li><a href='/set-cookie'>Set Cookie</a></li>" +
               "<li><a href='/redirect?url=https://example.com'>Open Redirect</a></li>" +
               "<li><a href='/error-disclosure?file=test.txt'>Error Disclosure</a></li>" +
               "<li><a href='/frame-me'>Clickjacking Demo</a></li>" +
               "<li><a href='/content-sniff'>Content Type Sniffing</a></li>" +
               "<li><a href='/sensitive-data'>Sensitive Data</a></li>" +
               "<li><a href='/server-info'>Server Information</a></li>" +
               "<li><a href='/session'>Session Management</a></li>" +
               "<li><a href='/comment?comment=Hello'>HTML Injection</a></li>" +
               "<li><a href='/private-data'>Private Data</a></li>" +
               "<li><a href='/download?filename=test.txt'>File Download</a></li>" +
               "</ul>" +
               "<h3>Forms:</h3>" +
               "<form method='POST' action='/submit-form'>" +
               "Username: <input name='username' value='testuser'>" +
               "Action: <input name='action' value='login'>" +
               "<input type='submit' value='Submit'>" +
               "</form>" +
               "<form method='POST' action='/upload'>" +
               "File Name: <input name='fileName' value='test.txt'>" +
               "Content: <textarea name='fileContent'>Sample content</textarea>" +
               "<input type='submit' value='Upload'>" +
               "</form>" +
               "</body></html>";
    }

    @GetMapping("/version")
    public String version() {
        return "The actual version is 1.0.0";
    }

    @GetMapping("/nations")
    public JsonNode getRandomNations() {
        var objectMapper = new ObjectMapper();
        var faker = new Faker();
        var nations = objectMapper.createArrayNode();
        for (var i = 0; i < 10; i++) {
            var nation = faker.nation();
            nations.add(objectMapper.createObjectNode()
                    .put("nationality", nation.nationality())
                    .put("capitalCity", nation.capitalCity())
                    .put("flag", nation.flag())
                    .put("language", nation.language()));
        }
        return nations;
    }

    @GetMapping("/currencies")
    public JsonNode getRandomCurrencies() {
        var objectMapper = new ObjectMapper();
        var faker = new Faker();
        var currencies = objectMapper.createArrayNode();
        for (var i = 0; i < 20; i++) {
            var currency = faker.currency();
            currencies.add(objectMapper.createObjectNode()
                    .put("name", currency.name())
                    .put("code", currency.code()));
        }
        return currencies;

    }

}
