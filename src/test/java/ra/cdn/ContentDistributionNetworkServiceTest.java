package ra.cdn;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Properties;
import java.util.logging.Logger;

public class ContentDistributionNetworkServiceTest {

    private static final Logger LOG = Logger.getLogger(ContentDistributionNetworkServiceTest.class.getName());

    private static ContentDistributionNetworkService service;
    private static MockProducer producer;
    private static Properties props;
    private static boolean ready = false;

    @BeforeClass
    public static void init() {
        LOG.info("Init...");
        props = new Properties();

        producer = new MockProducer();
        service = new ContentDistributionNetworkService(producer, null);

        ready = service.start(props);
    }

    @AfterClass
    public static void tearDown() {
        LOG.info("Teardown...");
        service.gracefulShutdown();
    }

//    @Test
//    public void sendTest() {
//
//    }
//
//    @Test
//    public void pickUpTest() {
//
//    }
}
