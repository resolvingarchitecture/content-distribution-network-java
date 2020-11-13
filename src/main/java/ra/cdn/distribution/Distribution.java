package ra.cdn.distribution;

import ra.common.Envelope;

import java.util.logging.Logger;

public class Distribution {

    private static final Logger LOG = Logger.getLogger(Distribution.class.getName());

    public boolean random(Envelope e) {
        LOG.warning("Random Distribution not yet implemented in CDN.");
        return false;
    }

}
