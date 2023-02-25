package airport_departure_queuing.common;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    //    static Logger logger = LoggerFactory.getLogger(Metrics.class);
    private static final CollectorRegistry collectorRegistry = new CollectorRegistry();
    public static final Counter estimationTotal = Counter.build()
            .name("estimation_total").help("Total estimation.").register(collectorRegistry);

    public static void startServer() {
        try {
            new HTTPServer.Builder()
                    .withPort(1234)
                    .build();
        } catch (IOException e) {
//            logger.error("Failed to start prometheus exporter: ", e);
        }
    }

    public static void dumpMetrics() {
        try {
            FileWriter writer = new FileWriter(Constants.metricsDumpFilePath);
            TextFormat.write004(writer, collectorRegistry.metricFamilySamples());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
