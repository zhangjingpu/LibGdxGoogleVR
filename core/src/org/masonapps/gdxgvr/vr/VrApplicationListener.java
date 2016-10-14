package org.masonapps.gdxgvr.vr;

import com.badlogic.gdx.ApplicationListener;

/**
 * Created by Bob on 10/9/2016.
 */

public abstract class VrApplicationListener implements ApplicationListener {

    @Override
    public void render() {
        // not supported
    }

    public abstract void onNewFrame(GdxHeadTransform gdxHeadTransform);

    public abstract void onDrawEye(GdxEye gdxEye);

    public abstract void onCardboardTrigger();

    public abstract void onCardboardBack();
}
