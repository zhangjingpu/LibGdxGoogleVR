package org.masonapps.gdxgvr.vr;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Bob on 10/10/2016.
 */
public class GdxHeadTransform {

    public Matrix4 headView;
    public Quaternion quaternion;
    public Vector3 right;
    public Vector3 up;
    public Vector3 forward;
    public Vector3 translation;
    private Vector3 eulerAngles;

    public GdxHeadTransform() {
        headView = new Matrix4();
        quaternion = new Quaternion();
        right = new Vector3();
        up = new Vector3();
        forward = new Vector3();
        translation = new Vector3();
        eulerAngles = new Vector3();
    }

    public void setQuaternion(float[] array, int offset) {
        this.quaternion.set(array[offset], array[offset + 1], array[offset + 2], array[offset + 3]);
    }

    public void setRight(float[] array, int offset) {
        this.right.set(array[offset], array[offset + 1], array[offset + 2]);
    }

    public void setUp(float[] array, int offset) {
        this.up.set(array[offset], array[offset + 1], array[offset + 2]);
    }

    public void setForward(float[] array, int offset) {
        this.forward.set(array[offset], array[offset + 1], array[offset + 2]);
    }

    public void setTranslation(float[] array, int offset) {
        this.translation.set(array[offset], array[offset + 1], array[offset + 2]);
    }

    public void setEulerAngles(float[] array, int offset) {
        this.eulerAngles.set(array[offset], array[offset + 1], array[offset + 2]);
    }

    public void setHeadView(float[] headView) {
        this.headView.set(headView);
    }

    public Vector3 getEulerAngles() {
        return eulerAngles;
    }
}
