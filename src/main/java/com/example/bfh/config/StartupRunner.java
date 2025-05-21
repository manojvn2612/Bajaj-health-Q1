@Component
public class StartupRunner implements ApplicationRunner {

    private final WebhookService webhookService;

    public StartupRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(ApplicationArguments args) {
        webhookService.initiateProcess();
    }
}
