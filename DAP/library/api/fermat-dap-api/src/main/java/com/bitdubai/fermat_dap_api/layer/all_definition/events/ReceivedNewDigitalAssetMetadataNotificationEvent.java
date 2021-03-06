package com.bitdubai.fermat_dap_api.layer.all_definition.events;

import com.bitdubai.fermat_dap_api.layer.all_definition.enums.EventType;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUser;

/**
 * Created by Nerio on 15/10/15.
 */
public class ReceivedNewDigitalAssetMetadataNotificationEvent extends AbstractDAPEvent {

    private ActorAssetUser actorAssetUser;

    public ActorAssetUser getActorAssetUser() {
        return actorAssetUser;
    }

    public void setActorAssetUser(ActorAssetUser actorAssetUser) {
        this.actorAssetUser = actorAssetUser;
    }

    public ReceivedNewDigitalAssetMetadataNotificationEvent(EventType eventType) {
        super(eventType);
    }
}
