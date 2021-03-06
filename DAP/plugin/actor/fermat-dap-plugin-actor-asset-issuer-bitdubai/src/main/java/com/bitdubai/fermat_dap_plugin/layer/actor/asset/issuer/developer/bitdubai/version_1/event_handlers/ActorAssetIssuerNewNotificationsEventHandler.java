package com.bitdubai.fermat_dap_plugin.layer.actor.asset.issuer.developer.bitdubai.version_1.event_handlers;

import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.all_definition.events.EventSource;
import com.bitdubai.fermat_api.layer.all_definition.events.exceptions.UnexpectedEventException;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEvent;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventHandler;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventMonitor;
import com.bitdubai.fermat_dap_api.layer.all_definition.enums.EventType;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantGetActorAssetNotificationException;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.issuer.developer.bitdubai.version_1.AssetIssuerActorPluginRoot;

/**
 * Created by Nerio on 29/02/16.
 */
public class ActorAssetIssuerNewNotificationsEventHandler implements FermatEventHandler {

    AssetIssuerActorPluginRoot assetIssuerActorPluginRoot;

    FermatEventMonitor fermatEventMonitor;

    public ActorAssetIssuerNewNotificationsEventHandler(AssetIssuerActorPluginRoot assetActorUserPluginRoot) {
        this.assetIssuerActorPluginRoot = assetActorUserPluginRoot;
    }

    @Override
    public void handleEvent(FermatEvent fermatEvent) throws FermatException {
        if (this.assetIssuerActorPluginRoot.getStatus() == ServiceStatus.STARTED) {
            if (fermatEvent.getSource() == EventSource.NETWORK_SERVICE_ACTOR_ASSET_ISSUER) {
                System.out.println("ACTOR ASSET ISSUER - HANDLER NOTIFICACIONES PENDIENTES A ACTOR ASSET ISSUER!!!");
                try {
                    assetIssuerActorPluginRoot.processNotifications();
                } catch (CantGetActorAssetNotificationException e) {
                    this.fermatEventMonitor.handleEventException(e, fermatEvent);
                } catch (Exception e) {
                    this.fermatEventMonitor.handleEventException(e, fermatEvent);
                }
            }
        } else {
            EventType eventExpected = EventType.ACTOR_ASSET_NETWORK_SERVICE_NEW_NOTIFICATIONS;
            EventSource eventSource = EventSource.NETWORK_SERVICE_ACTOR_ASSET_ISSUER;
            String context = "Event received: " + fermatEvent.getEventType().toString() + " - " + fermatEvent.getEventType().getCode() + "\n" +
                    "Event Source received: "+ fermatEvent.getSource().toString() + " - " + fermatEvent.getSource().getCode() + "\n" +
                    "Event expected: " + eventExpected.toString() + " - " + eventExpected.getCode() + "\n" +
                    "Event Source: "+ eventSource.toString() + " - " + eventSource.getCode();

            throw new UnexpectedEventException(context);
//            throw new TransactionServiceNotStartedException();
        }
    }
}