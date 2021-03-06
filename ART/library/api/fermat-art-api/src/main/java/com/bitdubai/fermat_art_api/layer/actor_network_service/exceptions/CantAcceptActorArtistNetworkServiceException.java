package com.bitdubai.fermat_art_api.layer.actor_network_service.exceptions;

import com.bitdubai.fermat_art_api.all_definition.exceptions.ARTException;

/**
 * Created by Gabriel Araujo (gabe_512@hotmail.com) on 14/03/16.
 */
public class CantAcceptActorArtistNetworkServiceException extends ARTException {


    public static final String DEFAULT_MESSAGE = "CAN'T ACCEPT NEW USER";

    public CantAcceptActorArtistNetworkServiceException(final String message, final Exception cause, final String context, final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    public CantAcceptActorArtistNetworkServiceException(final String message, final Exception cause) {
        this(message, cause, "", "");
    }

    public CantAcceptActorArtistNetworkServiceException(final String message) {
        this(message, null);
    }

    public CantAcceptActorArtistNetworkServiceException(final Exception exception) {
        this(exception.getMessage());
        setStackTrace(exception.getStackTrace());
    }

    public CantAcceptActorArtistNetworkServiceException() {
        this(DEFAULT_MESSAGE);
    }
}
