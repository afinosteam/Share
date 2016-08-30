package com.khmer.share.ui.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.khmer.share.BR;
import com.khmer.share.R;
import com.khmer.share.api.adapter.core.CompositeItemBinder;
import com.khmer.share.api.adapter.core.ItemBinder;
import com.khmer.share.api.binding.RecyclerViewBindings;
import com.khmer.share.api.handler.ClickHandler;
import com.khmer.share.api.handler.RecyclerScrollHandler;
import com.khmer.share.databinding.FragmentUserBinding;
import com.khmer.share.ui.binder.UserBinder;
import com.khmer.share.ui.model.Data;
import com.khmer.share.ui.viewmodel.UserViewModel;
import com.khmer.share.ui.viewmodel.UsersViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private FragmentUserBinding mBinding;
    private GraphRequest mGraphRequest;
    private UsersViewModel mUsersViewModel;

    private Callback<UserViewModel> mCallback;

    private String query;
    private boolean hasNext = false;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (Exception e) {
            throw new IllegalStateException("Callback not found in activity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUsersViewModel = new UsersViewModel();
        mBinding.setUsers(mUsersViewModel);
        mBinding.setView(this);

        mBinding.refreshMe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers(true);
                mBinding.refreshMe.setRefreshing(false);
            }
        });

        RecyclerViewBindings.setHandler(mBinding.recyclerFriends, new RecyclerScrollHandler((LinearLayoutManager) mBinding.recyclerFriends.getLayoutManager()) {
            @Override
            public void onLoadMore() {
                getUsers(false);
            }
        });
        getUsers(true);
    }

    private void getUsers(boolean firstTime) {
        mGraphRequest = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/invitable_friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if (response.getJSONObject() == null) return;
                        Log.i("Result", response.getJSONObject().toString());
                        Data data = new Gson().fromJson(response.getJSONObject().toString(), Data.class);
                        try {
                            hasNext = !TextUtils.isEmpty(data.getPaging().getNext());
                            if (TextUtils.isEmpty(data.getPaging().getPrevious())) {
                                mUsersViewModel.setItems(data.getData()).sort(false);
                            } else {
                                if (hasNext)
                                    mUsersViewModel.addItems(data.getData()).sort(false);
                            }
                            query = hasNext ? data.getPaging().getCursors().getAfter() : "";
                            Log.i("after", query);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,first_name,last_name,name,picture{url}");
        bundle.putString("pretty", "0");
        bundle.putString("limit", String.valueOf(25));

        if (!firstTime) {
            if (!TextUtils.isEmpty(query))
                bundle.putString(hasNext ? "after" : "before", query);
        }
        mGraphRequest.setParameters(bundle);
        mGraphRequest.executeAsync();

    }

    public ClickHandler<UserViewModel> clickHandler = new ClickHandler<UserViewModel>() {
        @Override
        public void onClicked(UserViewModel item) {
            if (mCallback != null)
                mCallback.onResult(item);
        }
    };

    public ItemBinder<UserViewModel> itemViewBinder = new CompositeItemBinder<>(
            new UserBinder(BR.user, R.layout.item_user)
    );
}
