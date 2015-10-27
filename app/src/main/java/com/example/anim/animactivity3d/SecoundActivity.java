package com.example.anim.animactivity3d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecoundActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);

        button = (Button) this.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatedStartActivity();
            }
        });
    }

    private void animatedStartActivity() {
        // we only animateOut this activity here.
        // The new activity will animateIn from its onResume() - be sure to implement it.
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // disable default animation for new intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        ActivitySwitcher.animationOut(findViewById(R.id.secound), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
            @Override
            public void onAnimationFinished() {
                startActivity(intent);
            }
        });
    }
    @Override
    public void finish() {
        // we need to override this to performe the animtationOut on each
        // finish.
        ActivitySwitcher.animationOut(findViewById(R.id.secound), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
            @Override
            public void onAnimationFinished() {
                SecoundActivity.super.finish();
                // disable default animation
                overridePendingTransition(0, 0);
            }
        });
    }
    @Override
    protected void onResume() {
        ActivitySwitcher.animationIn(findViewById(R.id.secound), getWindowManager());
        super.onResume();
    }
}
