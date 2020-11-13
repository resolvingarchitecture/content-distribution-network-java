# RA Content Distribution Network

## Roadmap
* 1.0: Simple MVP
    * 0.1: Random Content Distribution
    * 0.3: Direct Content Delivery
    * 0.5: Shared Network Search DB
    * 0.7: Symmetrically Encrypt/Decrypt Content
* TBD - Upgrades to be later prioritized
    * Distribution
        * Multiple copies
        * Sharding
        * Content Embedding
    * Delivery
        * Guaranteed Delivery - different levels of guaranteed delivery
        * Delayed Delivery - delays added to relays to slow final resting spot to date/time of delay
        * Delayed Visibility/Availability - doesn't show up in search results until a specified date/time
        * Periodic Shifts - periodic movement of content to other nodes
        * Streaming
            * Audio
            * Video
            * Single Source
            * Multiple Source from Shards
            * Parallel Feeds (same data) for improving service levels
    * Search
        * Decentralized Search - ensure search is always available albeit slower without having to share with all nodes
        * Content hashed with SHA-1; SHA-256 optional

    * Encryption
        *
    * Identities
        * OpenPGP
        * Asymmetric Encryption
        * Asymmetric Key Based Access Controls (AKBAC)

## Design

### Identity


### Dependencies
This service is dependent on the following services (must be accessible via send(Envelope)/receive(Envelope):

* a configurable Peer Manager Service to build up a pool of peers to which the CDN can work
* a configurable Network Manager Service to communicate with these peers
* ra.did.DIDService and ra.keyring.KeyRingService for identity management for publishing with an identity or if not, will be anonymous.
Asymmetric Encryption will be available if identities are present. Symmetric encryption (password based) will be available regardless.

It is designed to be used with the RA service bus project but can be
adapted to any environment.

### Distribution
Distribute content to other known peers.

### Delivery


### Search


### Streaming


#### Audio


#### Video
