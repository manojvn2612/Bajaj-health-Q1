@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void initiateProcess() {
        String registrationNumber = "REG12347";
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of(
                "name", "Manoj Nayak",
                "regNo", "1032221958",
                "email", "1032221958@mitwpu.edu"
        );

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(url, entity, WebhookResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            WebhookResponse data = response.getBody();
            if (data != null) {
                String sqlQuery = SqlSolver.solveForRegNo(registrationNumber);
                submitAnswer(data.getWebhook(), data.getAccessToken(), sqlQuery);
            }
        }
    }

    private void submitAnswer(String webhookUrl, String accessToken, String sqlQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, String> body = Map.of("finalQuery", sqlQuery);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(webhookUrl, entity, String.class);
    }
}
