package com.khmer.share.ui;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.khmer.share.R;
import com.khmer.share.databinding.ActivityMainBinding;
import com.khmer.share.ui.fragment.Callback;
import com.khmer.share.ui.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements Callback<UserViewModel> {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.mainToolbar);

        mBinding.fabCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide(view);
                show(mBinding.controllers);
            }
        });
    }

    private void show(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        anim.setDuration(300);
        anim.start();
        view.setVisibility(View.VISIBLE);
    }

    private void hide(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f);
        anim.setDuration(300);
        anim.start();
        view.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, JoinActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResult(UserViewModel item) {
        mBinding.setUser(item);
        mBinding.drawerLayout.closeDrawer(Gravity.RIGHT);
    }
}
