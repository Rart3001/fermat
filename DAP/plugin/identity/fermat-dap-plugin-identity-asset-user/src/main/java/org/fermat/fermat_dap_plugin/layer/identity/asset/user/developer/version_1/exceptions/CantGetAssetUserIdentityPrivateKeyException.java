package org.fermat.fermat_dap_plugin.layer.identity.asset.user.developer.version_1.exceptions;

import com.bitdubai.fermat_api.FermatException;

/**
 * Created by franklin on 02/11/15.
 */
public class CantGetAssetUserIdentityPrivateKeyException extends FermatException {


    public CantGetAssetUserIdentityPrivateKeyException(final String message, final Exception cause, final String context, final String possibleReason) {
        super(message, cause, context, possibleReason);
    }


}
