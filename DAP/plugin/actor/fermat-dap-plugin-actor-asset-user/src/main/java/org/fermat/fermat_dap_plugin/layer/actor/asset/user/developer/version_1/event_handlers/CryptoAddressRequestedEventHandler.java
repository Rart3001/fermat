package org.fermat.fermat_dap_plugin.layer.actor.asset.user.developer.version_1.event_handlers;

import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.all_definition.events.exceptions.UnexpectedEventException;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEvent;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventHandler;
import com.bitdubai.fermat_ccp_api.all_definition.enums.EventType;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.events.CryptoAddressesNewsEvent;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.interfaces.CryptoAddressRequest;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.interfaces.CryptoAddressesManager;

import org.fermat.fermat_dap_plugin.layer.actor.asset.user.developer.version_1.AssetUserActorPluginRoot;

import java.util.List;

/**
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 23/09/2015.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class CryptoAddressRequestedEventHandler implements FermatEventHandler {

    AssetUserActorPluginRoot assetActorUserPluginRoot;
    CryptoAddressesManager cryptoAddressesNetworkServiceManager;

    public CryptoAddressRequestedEventHandler(AssetUserActorPluginRoot assetActorUserPluginRoot, CryptoAddressesManager cryptoAddressesNetworkServiceManager){
        this.assetActorUserPluginRoot = assetActorUserPluginRoot;
        this.cryptoAddressesNetworkServiceManager = cryptoAddressesNetworkServiceManager;
    }

    @Override
    public void handleEvent(FermatEvent fermatEvent) throws FermatException {

        if (this.assetActorUserPluginRoot.getStatus() == ServiceStatus.STARTED) {

            if (fermatEvent instanceof CryptoAddressesNewsEvent) {
                final List<CryptoAddressRequest> list;
                list = cryptoAddressesNetworkServiceManager.listAllPendingRequests();

                for (final CryptoAddressRequest request : list) {
                    if (request.getIdentityTypeResponding().equals(Actors.DAP_ASSET_USER)) {
                        if(request.getCryptoAddress()!=null) {
                            if (request.getCryptoAddress().getAddress() != null) {
                                assetActorUserPluginRoot.handleCryptoAddressesNewsEvent();
                            }
                        }
                    }
                }

            } else {
                EventType eventExpected = EventType.CRYPTO_ADDRESSES_NEWS;
                String context = "Event received: " + fermatEvent.getEventType().toString() + " - " + fermatEvent.getEventType().getCode() + "\n" +
                        "Event expected: " + eventExpected.toString() + " - " + eventExpected.getCode();
                throw new UnexpectedEventException(context);
            }
        }
    }
}