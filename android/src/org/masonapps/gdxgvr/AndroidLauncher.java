package org.masonapps.gdxgvr;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import org.masonapps.gdxgvr.vr.GVRApplication;

public class AndroidLauncher extends GVRApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new VrTestGame(), config);
	}
}
