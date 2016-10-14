package org.masonapps.gdxgvr.vr;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Bob on 10/14/2016.
 */

public class VrCamera extends Camera {

    public VrCamera() {
        super();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("call onDrawEye(GdxEye gdxEye) instead");
    }

    @Override
    public void update(boolean updateFrustum) {
        throw new UnsupportedOperationException("call onDrawEye(GdxEye gdxEye, boolean updateFrustum) instead");
    }

    @Override
    public void lookAt(Vector3 target) {
        throw new UnsupportedOperationException("call lookAt() on parent matrix onDrawEye(GdxEye gdxEye, Matrix4 parentMat) or onDrawEye(GdxEye gdxEye, ModelInstance parent) instead");
    }

    @Override
    public void lookAt(float x, float y, float z) {
        throw new UnsupportedOperationException("call lookAt() on parent matrix onDrawEye(GdxEye gdxEye, Matrix4 parentMat) or onDrawEye(GdxEye gdxEye, ModelInstance parent) instead");
    }

    @Override
    public void rotate(float angle, float axisX, float axisY, float axisZ) {
        throw new UnsupportedOperationException("call rotate() on parent matrix onDrawEye(GdxEye gdxEye, Matrix4 parentMat) or onDrawEye(GdxEye gdxEye, ModelInstance parent) instead");
    }

    @Override
    public void rotate(Vector3 axis, float angle) {
        throw new UnsupportedOperationException("call rotate() on parent matrix onDrawEye(GdxEye gdxEye, Matrix4 parentMat) or onDrawEye(GdxEye gdxEye, ModelInstance parent) instead");
    }

    @Override
    public void rotate(Quaternion quat) {
        throw new UnsupportedOperationException("call rotate() on parent matrix onDrawEye(GdxEye gdxEye, Matrix4 parentMat) or onDrawEye(GdxEye gdxEye, ModelInstance parent) instead");
    }

    @Override
    public void rotate(Matrix4 transform) {
        throw new UnsupportedOperationException("call rotate() on parent matrix onDrawEye(GdxEye gdxEye, Matrix4 parentMat) or onDrawEye(GdxEye gdxEye, ModelInstance parent) instead");
    }

    public void onDrawEye(GdxEye gdxEye) {
        onDrawEye(gdxEye, null, true);
    }

    public void onDrawEye(GdxEye gdxEye, ModelInstance parent) {
        onDrawEye(gdxEye, parent.transform, true);
    }

    public void onDrawEye(GdxEye gdxEye, boolean updateFrustum) {
        onDrawEye(gdxEye, null, updateFrustum);
    }

    public void onDrawEye(GdxEye gdxEye, Matrix4 parentMat) {
        onDrawEye(gdxEye, parentMat, true);
    }

    public void onDrawEye(GdxEye gdxEye, Matrix4 parentMat, boolean updateFrustum) {
//        view.setToLookAt(position, tmp.set(position).add(direction), up);
        view.setToTranslation(-position.x, -position.y, -position.z);
        if (parentMat != null) {
            view.mulLeft(parentMat);
        }
        view.mulLeft(gdxEye.getView());
        projection.set(gdxEye.getPerspective());
        combined.set(projection);
        Matrix4.mul(combined.val, view.val);

        if (updateFrustum) {
            invProjectionView.set(combined);
            Matrix4.inv(invProjectionView.val);
            frustum.update(invProjectionView);
        }
    }
}
