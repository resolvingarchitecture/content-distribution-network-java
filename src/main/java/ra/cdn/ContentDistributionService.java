package ra.cdn;

import ra.cdn.delivery.Delivery;
import ra.cdn.distribution.Distribution;
import ra.cdn.search.Search;
import ra.common.Envelope;
import ra.common.messaging.MessageProducer;
import ra.common.route.Route;
import ra.common.service.BaseService;
import ra.common.service.ServiceStatus;
import ra.common.service.ServiceStatusObserver;
import ra.util.Config;

import java.util.*;
import java.util.logging.Logger;

/**
 * Content Distribution as a service (along with delivery and search)
 */
public class ContentDistributionService extends BaseService {

    private static final Logger LOG = Logger.getLogger(ContentDistributionService.class.getName());

    public static final String OPERATION_DISTRIBUTE = "DISTRIBUTE";
    public static final String OPERATION_DELIVER = "DELIVER";
    public static final String OPERATION_SEARCH = "SEARCH";

    public static final String RA_CDN_CONFIG = "ra-cdn.config";
    public static final String RA_CDN_DIR = "ra.cdn.dir";

    private Distribution distribution;
    private Delivery delivery;
    private Search search;

    public ContentDistributionService(MessageProducer producer, ServiceStatusObserver observer) {
        super(producer, observer);
        distribution = new Distribution();
        delivery = new Delivery();
        search = new Search();
    }

    @Override
    public void handleDocument(Envelope envelope) {
        Route r = envelope.getDynamicRoutingSlip().getCurrentRoute();
        switch(r.getOperation()) {
            case OPERATION_DISTRIBUTE: {distribute(envelope);break;}
            case OPERATION_DELIVER: {deliver(envelope);break;}
            case OPERATION_SEARCH: {search(envelope);break;}
            default: {deadLetter(envelope);break;}
        }
    }

    private void distribute(Envelope e) {
        // TODO: determine if this is just a save from a remote distribute or a local call to distribute out

        // A request to distribute out randomly
        if(distribution.random(e)) {
            // Return to bus signalling success
            send(e);
            // Update Local Index
            search.updateLocalIndex(e);
            // Update Global Index
            Envelope e2 = search.buildGlobalUpdateIndexRequest(e);
            // Propagate index
            send(e2);
        } else {
            deadLetter(e);
        }
    }

    private void deliver(Envelope e) {
        if(delivery.retrieveFromLocal(e)) {
            // Return to bus
            send(e);
        } else {
            deadLetter(e);
        }
    }

    private void search(Envelope e) {
        if(search.searchLocal(e)) {
            // Return to bus
            send(e);
        } else {
            deadLetter(e);
        }
    }

    @Override
    public boolean start(Properties p) {
        super.start(p);
        LOG.info("Initializing...");
        updateStatus(ServiceStatus.INITIALIZING);
        try {
            config = Config.loadFromClasspath(RA_CDN_CONFIG, p, false);
        } catch (Exception e) {
            LOG.severe(e.getLocalizedMessage());
            return false;
        }
        config.put(RA_CDN_DIR, getServiceDirectory().getAbsolutePath());
        updateStatus(ServiceStatus.STARTING);
        return true;
    }

    @Override
    public boolean pause() {
        LOG.warning("Pausing not yet supported.");
        return false;
    }

    @Override
    public boolean unpause() {
        LOG.warning("Pausing not yet supported.");
        return false;
    }

    @Override
    public boolean restart() {
        LOG.info("Restarting...");
        shutdown();
        start(config);
        LOG.info("Restarted.");
        return true;
    }

    @Override
    public boolean shutdown() {
        LOG.info("Shutting down...");
        updateStatus(ServiceStatus.SHUTDOWN);
        LOG.info("Shutdown.");
        return true;
    }

    @Override
    public boolean gracefulShutdown() {
        return shutdown();
    }

}
