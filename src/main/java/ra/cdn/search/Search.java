package ra.cdn.search;

import ra.common.Envelope;

import java.util.logging.Logger;

public class Search {

    private static final Logger LOG = Logger.getLogger(Search.class.getName());

    public boolean updateLocalIndex(Envelope e) {
        LOG.warning("Update Index not yet implemented in CDN.");
        return false;
    }

    public Envelope buildGlobalUpdateIndexRequest(Envelope e) {
        LOG.warning("Build Global Index Update Request not yet implemented in CDN.");
        Envelope e2 = Envelope.documentFactory();
        // TODO: build envelope
        return e2;
    }

    public boolean searchLocal(Envelope e) {
        LOG.warning("Search Local not yet implemented in CDN.");
        return false;
    }

}
