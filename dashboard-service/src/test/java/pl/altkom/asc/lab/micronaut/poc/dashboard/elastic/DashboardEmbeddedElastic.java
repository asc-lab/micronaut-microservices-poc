package pl.altkom.asc.lab.micronaut.poc.dashboard.elastic;

import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.IOException;

public class DashboardEmbeddedElastic {
    private static EmbeddedElastic embeddedElastic = null;

    static EmbeddedElastic getInstance() {
        if (embeddedElastic == null) {
            try {
                embeddedElastic = createAndRun();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return embeddedElastic;
    }

    private static EmbeddedElastic createAndRun() throws IOException, InterruptedException {
        return EmbeddedElastic.builder()
                .withElasticVersion("6.6.2")
                .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9350)
                .withSetting(PopularProperties.HTTP_PORT, 9351)
                .withSetting(PopularProperties.CLUSTER_NAME, "my_cluster")
                .build()
                .start();

    }
}
