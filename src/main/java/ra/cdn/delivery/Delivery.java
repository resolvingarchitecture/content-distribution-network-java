package ra.cdn.delivery;

import ra.common.Envelope;

import java.util.logging.Logger;

public class Delivery {

    private static final Logger LOG = Logger.getLogger(Delivery.class.getName());

    public boolean retrieveFromLocal(Envelope e) {
        LOG.warning("Retrieve from Local Delivery not yet implemented in CDN.");
        return false;
    }

}
