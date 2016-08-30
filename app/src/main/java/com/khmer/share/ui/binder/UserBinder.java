package com.khmer.share.ui.binder;

import com.khmer.share.api.adapter.core.ConditionalDataBinder;
import com.khmer.share.ui.viewmodel.UserViewModel;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public class UserBinder extends ConditionalDataBinder<UserViewModel> {
    public UserBinder(int bindingVariable, int layoutId) {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(UserViewModel model) {
        return true;
    }
}
