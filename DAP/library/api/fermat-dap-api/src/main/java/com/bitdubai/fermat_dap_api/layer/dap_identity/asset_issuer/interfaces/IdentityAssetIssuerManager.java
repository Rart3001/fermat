package com.bitdubai.fermat_dap_api.layer.dap_identity.asset_issuer.interfaces;

import com.bitdubai.fermat_api.layer.modules.common_classes.ActiveActorIdentityInformation;
import com.bitdubai.fermat_api.layer.modules.interfaces.FermatSettings;
import com.bitdubai.fermat_api.layer.modules.interfaces.ModuleManager;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_issuer.exceptions.CantCreateNewIdentityAssetIssuerException;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_issuer.exceptions.CantGetAssetIssuerIdentitiesException;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_issuer.exceptions.CantListAssetIssuersException;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_issuer.exceptions.CantUpdateIdentityAssetIssuerException;

import java.util.List;

/**
 * Created by Nerio on 07/09/15.
 * Modified by Franklin 02/10/2015
 */

/**
 * The interface <code>IdentityAssetIssuerManager</code>
 * provides the methods to create and obtain intra users associated to a Device User.
 */
public interface IdentityAssetIssuerManager extends ModuleManager<FermatSettings, ActiveActorIdentityInformation> {

    /**
     * The method <code>getAllIntraWalletUsersFromCurrentDeviceUser</code> will give us a list of all the intra wallet users associated to the actual Device User logged in
     *
     * @return the list of Asset Issuer users associated to the current logged in Device User.
     * @throws CantListAssetIssuersException if something goes wrong.
     */
    List<IdentityAssetIssuer> getIdentityAssetIssuersFromCurrentDeviceUser() throws CantListAssetIssuersException;

    /**
     * The method <code>getIdentityAssetIssuer</code> will give Identity Asset Issuer associated
     *
     * @return Identity Asset Issuer associated.
     * @throws CantGetAssetIssuerIdentitiesException if something goes wrong.
     */
    IdentityAssetIssuer getIdentityAssetIssuer() throws CantGetAssetIssuerIdentitiesException;

    /**
     * The method <code>createNewIntraWalletUser</code> creates a new intra wallet user Identity for the logged in Device User and returns the
     * associated public key
     *
     * @param alias        the alias that the user choose as intra user identity
     * @param profileImage the profile image to identify this identity
     * @return the intra user created
     * @throws CantCreateNewIdentityAssetIssuerException if something goes wrong.
     */
    IdentityAssetIssuer createNewIdentityAssetIssuer(String alias,
                                                     byte[] profileImage) throws CantCreateNewIdentityAssetIssuerException;

    /**
     * The method <code>updateIdentityAssetIssuer</code> change a identity information data
     *
     * @param identityPublicKey
     * @param identityAlias
     * @param profileImage
     * @throws CantUpdateIdentityAssetIssuerException
     */
    void updateIdentityAssetIssuer(String identityPublicKey, String identityAlias, byte[] profileImage) throws CantUpdateIdentityAssetIssuerException;

    /**
     * The method <code>hasIntraIssuerIdentity</code> returns if has a intra user identity created
     *
     * @return
     * @throws CantListAssetIssuersException
     */
    boolean hasIntraIssuerIdentity() throws CantListAssetIssuersException;

}
