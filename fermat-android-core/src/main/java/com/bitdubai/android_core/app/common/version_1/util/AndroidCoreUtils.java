package com.bitdubai.android_core.app.common.version_1.util;

import com.bitdubai.android_core.app.common.version_1.util.interfaces.BroadcasterInterface;
import com.bitdubai.fermat_api.layer.osa_android.broadcaster.BroadcasterType;
import com.bitdubai.fermat_api.layer.osa_android.broadcaster.FermatBundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mati on 2016.02.02..
 */
public class AndroidCoreUtils implements com.bitdubai.fermat_api.layer.osa_android.broadcaster.AndroidCoreUtils {

    private BroadcasterInterface context;
    private ExecutorService executor = Executors.newFixedThreadPool(2);


    private static final AndroidCoreUtils instance = new AndroidCoreUtils() ;

    public static AndroidCoreUtils getInstance(){
        return instance;
    }

    @Override
    public void publish(final BroadcasterType broadcasterType, final String code) {
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    if(context!=null) context.publish(broadcasterType, code);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void publish(final BroadcasterType broadcasterType,final String appCode, final String code) {
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    if(context!=null) context.publish(broadcasterType,appCode,code);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void publish(final BroadcasterType broadcasterType, final String appCode, final FermatBundle bundle) {
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    if(context!=null) context.publish(broadcasterType,appCode,bundle);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int publish(final BroadcasterType broadcasterType, final FermatBundle bundle) {
        int id = 0;
        try {
            id = (context!=null)? context.publish(broadcasterType,bundle):0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public BroadcasterInterface getContext() {
        return context;
    }

    public void setContextAndResume(BroadcasterInterface context) {
        this.context = context;
    }

    public void clear(){
        this.context = null;
    }


}
