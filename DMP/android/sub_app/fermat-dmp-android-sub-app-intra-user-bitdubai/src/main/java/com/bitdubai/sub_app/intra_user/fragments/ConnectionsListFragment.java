package com.bitdubai.sub_app.intra_user.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitdubai.fermat_android_api.engine.PaintActionBar;
import com.bitdubai.fermat_android_api.ui.adapters.FermatAdapter;
import com.bitdubai.fermat_android_api.ui.enums.FermatRefreshTypes;
import com.bitdubai.fermat_android_api.ui.fragments.FermatListFragment;
import com.bitdubai.fermat_android_api.ui.interfaces.FermatListItemListeners;
import com.bitdubai.fermat_android_api.ui.interfaces.FermatWorkerCallBack;
import com.bitdubai.fermat_android_api.ui.util.FermatWorker;
import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.all_definition.enums.UISource;
import com.bitdubai.fermat_api.layer.all_definition.navigation_structure.enums.Activities;
import com.bitdubai.fermat_api.layer.all_definition.navigation_structure.interfaces.FermatScreenSwapper;
import com.bitdubai.fermat_api.layer.dmp_engine.sub_app_runtime.enums.SubApps;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantAcceptRequestException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantGetIntraUsersListException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantLoginIntraUserException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantShowLoginIdentitiesException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.IntraUserConectionDenegationFailedException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserInformation;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserLoginIdentity;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserModuleManager;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserSearch;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.UnexpectedSubAppExceptionSeverity;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.UnexpectedUIExceptionSeverity;
import com.bitdubai.sub_app.intra_user.adapters.CheckBoxListItem;
import com.bitdubai.sub_app.intra_user.adapters.ListAdapter;
import com.bitdubai.sub_app.intra_user.common.Views.Utils;
import com.bitdubai.sub_app.intra_user.common.adapters.IntraUserConnectionsAdapter;
import com.bitdubai.sub_app.intra_user.common.models.IntraUserConnectionListItem;
import com.bitdubai.sub_app.intra_user.session.IntraUserSubAppSession;
import com.bitdubai.sub_app.intra_user.util.CommonLogger;
import com.intra_user.bitdubai.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

/**
 * Created by Matias Furszyfer on 2015.08.31..
 */

public class ConnectionsListFragment extends FermatListFragment<IntraUserConnectionListItem> implements FermatListItemListeners<IntraUserConnectionListItem>, SearchView.OnQueryTextListener, SearchView.OnCloseListener, ActionBar.OnNavigationListener {

    private final int POPUP_MENU_WIDHT = 325;

    int MAX = 5;
    int OFFSET= 0;


    IntraUserModuleManager intraUserModuleManager;
    private ErrorManager errorManager;
    private ArrayList<IntraUserConnectionListItem> intraUserItemList;

    private SearchView mSearchView;
    private boolean isStartList=false;
    private int mNotificationsCount=0;


    /**
     *  UI
     */
    private LinearLayout empty;
    private ProgressDialog dialog;

    public static ConnectionsListFragment newInstance(){
        ConnectionsListFragment fragment = new ConnectionsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // setting up  module
            intraUserModuleManager = ((IntraUserSubAppSession) subAppsSession).getIntraUserModuleManager();
            errorManager = subAppsSession.getErrorManager();
            //intraUserItemList = getMoreDataAsync(FermatRefreshTypes.NEW, 0); // get init data
            isStartList = true;

            mNotificationsCount = intraUserModuleManager.getIntraUsersWaitingYourAcceptance(MAX,OFFSET).size();

//            System.out.println("ACAAAAAA");
//            System.out.println(System.currentTimeMillis());
//            paintCheckBoxInActionBar();
//            System.out.println(System.currentTimeMillis());
//            System.out.println("ACAAAAAA");

            // TODO: display unread notifications.
            // Run a task to fetch the notifications count
            new FetchCountTask().execute();

        } catch (Exception ex) {
            CommonLogger.exception(TAG, ex.getMessage(), ex);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (dialog != null)
            dialog.dismiss();
        dialog = null;
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading connections");
        dialog.setMessage("Please wait...");
        dialog.show();
        showView(false, empty);
        new FermatWorker(getActivity(), new FermatWorkerCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onPostExecute(Object... result) {
                if (isAttached) {
                    dialog.dismiss();
                    dialog = null;
                    if (adapter != null) {
                        intraUserItemList = (ArrayList<IntraUserConnectionListItem>) result[0];
                        adapter.changeDataSet(intraUserItemList);
                        isStartList = true;

                    }
                    showEmpty();
                }
            }

            @Override
            public void onErrorOccurred(Exception ex) {
                if (isAttached) {
                    dialog.dismiss();
                    dialog = null;
                    Toast.makeText(getActivity(), "Some Error Occurred: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    showEmpty();
                }
            }
        }) {

            @Override
            protected Object doInBackground() throws Exception {

                return getMoreDataAsync(FermatRefreshTypes.NEW, 0); // get init data

            }
        }.execute();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.intra_user_menu, menu);

        //MenuItem menuItem = new SearchView(getActivity());

        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setShowAsAction(searchItem, MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_ALWAYS);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnCloseListener(this);

        //MenuItem action_connection_request = menu.findItem(R.id.action_connection_request);
        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_notifications);
        LayerDrawable icon = (LayerDrawable) item.getIcon();

        // Update LayerDrawable's BadgeDrawable
        Utils.setBadgeCount(getActivity(), icon, mNotificationsCount);



//        List<String> lst = new ArrayList<String>();
//        lst.add("Matias");
//        lst.add("Work");
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lst);
//        MenuItem item = menu.findItem(R.id.spinner);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
//
//        spinner.setAdapter(itemsAdapter); // set the adapter to provide layout of rows and content
//        //s.setOnItemSelectedListener(onItemSelectedListener); // set the listener, to perform actions based on item selection

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {

            int id = item.getItemId();

            CharSequence itemTitle = item.getTitle();

            // Esto podria ser un enum de item menu que correspondan a otro menu
            if(itemTitle.equals("New Identity")){
                ((FermatScreenSwapper)getActivity()).changeActivity(Activities.CWP_INTRA_USER_CREATE_ACTIVITY.getCode(),null);

            }
//            if(id == R.id.action_connection_request){
//                Toast.makeText(getActivity(),"Intra user request",Toast.LENGTH_SHORT).show();
//            }
            if (item.getItemId() == R.id.action_notifications) {


                List<IntraUserInformation> lstIntraUserRequestWaiting = null;
                try {
                    lstIntraUserRequestWaiting = intraUserModuleManager.getIntraUsersWaitingYourAcceptance(MAX,OFFSET);


                    View view = getActivity().findViewById(R.id.action_notifications);
                    showListMenu(view,lstIntraUserRequestWaiting);

                } catch (CantGetIntraUsersListException e) {
                    e.printStackTrace();
                }

                return true;
            }


        } catch (Exception e) {
            errorManager.reportUnexpectedUIException(UISource.ACTIVITY, UnexpectedUIExceptionSeverity.UNSTABLE, FermatException.wrapException(e));
            makeText(getActivity(), "Oooops! recovering from system error",
                    LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Updates the count of notifications in the ActionBar.
     */
    private void updateNotificationsBadge(int count) {
        mNotificationsCount = count;

        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        getActivity().invalidateOptionsMenu();
    }

    private void paintCheckBoxInActionBar(){

        try {
            List<IntraUserLoginIdentity> availableIdentities =intraUserModuleManager.showAvailableLoginIdentities();

            List<CheckBoxListItem> lstCheckBox = new ArrayList<CheckBoxListItem>();

            for(IntraUserLoginIdentity intraUserLoginIdentity: availableIdentities){
                lstCheckBox.add(new CheckBoxListItem(null,intraUserLoginIdentity));
            }

            ListAdapter listAdapter = new ListAdapter(getActivity(),R.layout.itemlistrow,lstCheckBox);


            ((PaintActionBar) getActivity()).paintComboBoxInActionBar(listAdapter, this);
        } catch (CantShowLoginIdentitiesException e) {
            e.printStackTrace();
        }
    }

    /**
     * Determine if this fragment use menu
     *
     * @return true if this fragment has menu, otherwise false
     */
    @Override
    protected boolean hasMenu() {
        return true;
    }

    /**
     * Get layout resource
     *
     * @return int layout resource Ex: R.layout.fragment_view
     */
    @Override
    protected int getLayoutResource() {
        return R.layout.intra_user_conecction_list;
    }

    @Override
    protected int getSwipeRefreshLayoutId() {
        return R.id.swipe_refresh;
    }

    @Override
    protected int getRecyclerLayoutId() {
        return R.id.connections_recycler_view;
    }

    @Override
    protected boolean recyclerHasFixedSize() {
        return true;
    }

    @Override
    public ArrayList<IntraUserConnectionListItem> getMoreDataAsync(FermatRefreshTypes refreshType, int pos) {
        ArrayList<IntraUserConnectionListItem> data=null;

        try {
            List<IntraUserInformation> lstIntraUser = intraUserModuleManager.getAllIntraUsers(0,10);
            //List<WalletStoreCatalogueItem> catalogueItems = catalogue.getWalletCatalogue(0, 0);

            data = new ArrayList<>();
            for (IntraUserInformation intraUserInformation : lstIntraUser) {
                IntraUserConnectionListItem item = new IntraUserConnectionListItem(intraUserInformation.getName(),null,intraUserInformation.getProfileImage(),"connected");
                data.add(item);
            }

        } catch (Exception e) {
            errorManager.reportUnexpectedSubAppException(SubApps.CWP_WALLET_STORE,
                    UnexpectedSubAppExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_FRAGMENT, e);

            data = IntraUserConnectionListItem.getTestData(getResources());
        }
        data = IntraUserConnectionListItem.getTestData(getResources());

        return data;
    }

    /**
     * implement this function to handle the result object through dynamic array
     *
     * @param result array of native object (handle result field with result[0], result[1],... result[n]
     */
    @Override
    public void onPostExecute(Object... result) {
        isRefreshing = false;
        if (isAttached) {
            swipeRefreshLayout.setRefreshing(false);
            if (result != null && result.length > 0) {
                intraUserItemList = (ArrayList) result[0];
                if (adapter != null)
                    adapter.changeDataSet(intraUserItemList);
            }
        }
    }

    /**
     * Implement this function to handle errors during the execution of any fermat worker instance
     *
     * @param ex Throwable object
     */
    @Override
    public void onErrorOccurred(Exception ex) {
        isRefreshing = false;
        if (isAttached) {
            swipeRefreshLayout.setRefreshing(false);
            CommonLogger.exception(TAG, ex.getMessage(), ex);
        }
    }

    @Override
    public FermatAdapter getAdapter() {
        if (adapter == null) {
            adapter = new IntraUserConnectionsAdapter(getActivity(), intraUserItemList);
            adapter.setFermatListEventListener(this); // setting up event listeners
        }
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }


    /**
     * onItem click listener event
     *
     * @param data
     * @param position
     */
    @Override
    public void onItemClickListener(IntraUserConnectionListItem data, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Hubo un problema");
        builder.setMessage("No se pudieron obtener los detalles de la connexión seleccionada");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    /**
     * On Long item Click Listener
     *
     * @param data
     * @param position
     */
    @Override
    public void onLongItemClickListener(IntraUserConnectionListItem data, int position) {

    }

    @Override
    public boolean onQueryTextSubmit(String name) {
        swipeRefreshLayout.setRefreshing(true);
        IntraUserSearch intraUserSearch = intraUserModuleManager.searchIntraUser();
        intraUserSearch.setNameToSearch(name);
        //TODO: cuando esté el network service, esto va a descomentarse
//        try {
//            adapter.changeDataSet(intraUserSearch.getResult());
//
//        } catch (CantGetIntraUserSearchResult cantGetIntraUserSearchResult) {
//            cantGetIntraUserSearchResult.printStackTrace();
//        }


        ((IntraUserConnectionsAdapter)adapter).setAddButtonVisible(true);
        adapter.changeDataSet(IntraUserConnectionListItem.getTestDataExample(getResources()));
        swipeRefreshLayout.setRefreshing(false);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //Toast.makeText(getActivity(), "Probando busqueda completa", Toast.LENGTH_SHORT).show();
        if(s.length()==0 && isStartList){
            ((IntraUserConnectionsAdapter)adapter).setAddButtonVisible(false);
            adapter.changeDataSet(IntraUserConnectionListItem.getTestData(getResources()));
            return true;
        }
        return false;
    }

    @Override
    public boolean onClose() {
        if(!mSearchView.isActivated()){
            adapter.changeDataSet(IntraUserConnectionListItem.getTestData(getResources()));
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(int position, long idItem) {
        try {
            IntraUserLoginIdentity intraUserLoginIdentity = intraUserModuleManager.showAvailableLoginIdentities().get(position);
            intraUserModuleManager.login(intraUserLoginIdentity.getPublicKey());
            //TODO: para despues
            //adapter.changeDataSet(intraUserModuleManager.getAllIntraUsers());

            //mientras tanto testeo
            adapter.changeDataSet(IntraUserConnectionListItem.getTestData(getResources()));

            return true;
        } catch (CantShowLoginIdentitiesException e) {
            e.printStackTrace();
        } catch (CantLoginIntraUserException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    Sample AsyncTask to fetch the notifications count
    */
    class FetchCountTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            // example count. This is where you'd
            // query your data store for the actual count.
            return mNotificationsCount;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }

//    private static final String TITLE = "title";
//    private static final String ICON = "icon";
//
//    private List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
//
//    // Use this to add items to the list that the ListPopupWindow will use
//    private void addItem(String title, int iconResourceId) {
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put(TITLE, title);
//        map.put(ICON, iconResourceId);
//        data.add(map);
//    }

    // Call this when you want to show the ListPopupWindow
    private void showListMenu(View anchor,List<IntraUserInformation> lstIntraUserRequestWaiting) {
        ListPopupWindow popupWindow = new ListPopupWindow(getActivity());

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//        ListAdapter adapter = new SimpleAdapter(
//                context,
//                data,
//                android.R.layout.activity_list_item, // You may want to use your own cool layout
//                new String[] {TITLE, ICON}, // These are just the keys that the data uses
//                new int[] {android.R.id.text1, android.R.id.icon}); // The view ids to map the data to

        CustomListAdapter customListAdapter = new CustomListAdapter(getActivity(),lstIntraUserRequestWaiting);


        popupWindow.setAnchorView(anchor);
        popupWindow.setAdapter(customListAdapter);
        popupWindow.setWidth(POPUP_MENU_WIDHT); // note: don't use pixels, use a dimen resource
        popupWindow.getAnchorView().setBackgroundColor(Color.parseColor("#5C92BBBC"));
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeActivity(Activities.CWP_INTRA_USER_CONNECTION_REQUEST_ACTIVITY);
            }
        }); // the callback for when a list item is selected
        popupWindow.show();
    }
    public class CustomListAdapter extends ArrayAdapter<IntraUserInformation> {

        private final Activity context;
        private final List<IntraUserInformation> lstIntraUserRequest;

        public CustomListAdapter(Activity context, List<IntraUserInformation> lstIntraUserRequest) {
            super(context, R.layout.intra_user_notification_list_item, lstIntraUserRequest);
            this.context = context;
            this.lstIntraUserRequest = lstIntraUserRequest;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {

            final IntraUserInformation intraUser = lstIntraUserRequest.get(position);

            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.intra_user_notification_list_item, null, true);
            TextView textView_name = (TextView) rowView.findViewById(R.id.textView_name);
            TextView textView_phrase = (TextView) rowView.findViewById(R.id.textView_phrase);
            ImageView imageView_profile = (ImageView) rowView.findViewById(R.id.imageView_profile);


            ImageView imageView_accept = (ImageView) rowView.findViewById(R.id.imageView_accept);

            imageView_accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        intraUserModuleManager.acceptIntraUser(intraUser.getName(),intraUser.getPublicKey(),intraUser.getProfileImage());
                    } catch (CantAcceptRequestException e) {
                        e.printStackTrace();
                    }
                }
            });

            ImageView imageView_denied = (ImageView) rowView.findViewById(R.id.imageView_denied);

            imageView_denied.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        intraUserModuleManager.denyConnection(intraUser.getPublicKey());
                    } catch (IntraUserConectionDenegationFailedException e) {
                        e.printStackTrace();
                    }
                }
            });





            //txtTitle.setTextColor(Color.WHITE);
            textView_name.setText(intraUser.getName());

            switch (position){
                case 0:
                    imageView_profile.setImageResource(R.drawable.mati_profile);
                    break;
                case 1:
                    imageView_profile.setImageResource(R.drawable.caroline_profile_picture);
                    break;
                case 2:
                    imageView_profile.setImageResource(R.drawable.brant_profile_picture);
                    break;
                case 3:
                    imageView_profile.setImageResource(R.drawable.louis_profile_picture);
                    break;
                case 4:
                    imageView_profile.setImageResource(R.drawable.madaleine_profile_picture);
                    break;
            }


            //imageView_profile.setImageBitmap(UtilsFuncs.getRoundedShape(BitmapFactory.decodeByteArray(intraUser.getProfileImage(), 0, intraUser.getProfileImage().length)));
            //textView_phrase.setText(lstIntraUserRequest.get(position).getName());
            //txtTitle.setText(LogLevel.MINIMAL_LOGGING.toString());



//                setLogLevelImage();
//                if(imageId[position]!=0){
//                    imageView.setImageResource(R.drawable.ic_action_accept_grey);
//                }

            return rowView;
        }
    }

    /**
     * Show or Hide any view
     *
     * @param show true if you want to show the view, otherwise false
     * @param view View object to show or hide
     */
    public void showView(boolean show, View view) {
        if (view == null)
            return;
        view.setAnimation(AnimationUtils
                .loadAnimation(getActivity(), show ? R.anim.abc_fade_in : R.anim.abc_fade_out));
        if (show && (view.getVisibility() == View.GONE || view.getVisibility() == View.INVISIBLE)) {
            view.setVisibility(View.VISIBLE);
        } else if (!show && view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Show or hide empty view if needed
     */
    public void showEmpty() {
        if (!isAttached || empty == null)
            return;
        if (intraUserItemList == null || intraUserItemList.isEmpty()) {
            if (empty.getVisibility() == View.GONE || empty.getVisibility() == View.INVISIBLE) {
                empty.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
                empty.setVisibility(View.VISIBLE);
            }
        } else if (empty.getVisibility() == View.VISIBLE) {
            empty.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_out));
            empty.setVisibility(View.GONE);
        }
    }
}