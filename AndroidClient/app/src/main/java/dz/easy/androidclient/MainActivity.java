package dz.easy.androidclient;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

import dz.easy.androidclient.Activities.StartActivity;
import dz.easy.androidclient.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        final PathView pathView = (PathView) findViewById(R.id.pathView);
        final PathView pathView2 = (PathView) findViewById(R.id.pathView2);

                pathView.getPathAnimator().
                        delay(1000).
                        duration(3500).
                        listenerStart(new PathView.AnimatorBuilder.ListenerStart() {
                            @Override
                            public void onAnimationStart() {

                            }
                        }).
                        listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                            @Override
                            public void onAnimationEnd() {
                                Intent i = new Intent(MainActivity.this , StartActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }).
                        interpolator(new AccelerateDecelerateInterpolator()).
                        start();

                pathView.useNaturalColors();
                pathView.setFillAfter(true);


        pathView2.getPathAnimator().
                delay(1000).
                duration(2500).
                listenerStart(new PathView.AnimatorBuilder.ListenerStart() {
                    @Override
                    public void onAnimationStart() {

                    }
                }).
                listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {

                    }
                }).
                interpolator(new AccelerateDecelerateInterpolator()).
                start();

        pathView2.useNaturalColors();
        pathView2.setFillAfter(true);
            }


}
