package ra.cdn;

import ra.common.Envelope;
import ra.common.messaging.MessageProducer;
import ra.common.route.Route;
import ra.common.service.BaseService;
import ra.common.service.ServiceStatus;
import ra.common.service.ServiceStatusListener;
import ra.util.Config;

import java.util.*;
import java.util.logging.Logger;

/**
 * Mail Drop as a service
 */
public class ContentDistributionNetworkService extends BaseService {

    private static final Logger LOG = Logger.getLogger(ContentDistributionNetworkService.class.getName());

    public static final String OPERATION_DISTRIBUTE = "DISTRIBUTE";
    public static final String OPERATION_RETRIEVE = "RETRIEVE";
    public static final String OPERATION_SEARCH = "SEARCH";
    public static final String OPERATION_STREAM_AUDIO = "STREAM_AUDIO";
    public static final String OPERATION_STREAM_VIDEO = "STREAM_VIDEO";

    public static final String RA_CDN_CONFIG = "ra-cdn.config";
    public static final String RA_CDN_DIR = "ra.cdn.dir";

    protected Properties config;

    public ContentDistributionNetworkService(MessageProducer producer, ServiceStatusListener listener) {
        super(producer, listener);
    }

    @Override
    public void handleDocument(Envelope envelope) {
        Route r = envelope.getDynamicRoutingSlip().getCurrentRoute();
        switch(r.getOperation()) {
            case OPERATION_DISTRIBUTE: {distribute(envelope);break;}
            case OPERATION_RETRIEVE: {retrieve(envelope);break;}
            case OPERATION_SEARCH: {search(envelope);break;}
            case OPERATION_STREAM_AUDIO: {streamAudio(envelope);break;}
            case OPERATION_STREAM_VIDEO: {streamVideo(envelope);break;}
            default: {deadLetter(envelope);break;}
        }
    }

    private void distribute(Envelope e) {
        LOG.warning("Distribute not yet implemented.");
    }

    private void retrieve(Envelope e) {
        LOG.warning("Distribute not yet implemented.");
    }

    private void search(Envelope e) {
        LOG.warning("Distribute not yet implemented.");
    }

    private void streamAudio(Envelope e) {
        LOG.warning("Distribute not yet implemented.");
    }

    private void streamVideo(Envelope e) {
        LOG.warning("Distribute not yet implemented.");
    }

    @Override
    public boolean send(Envelope e) {
        // Persist message into mail box

        return true;
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
