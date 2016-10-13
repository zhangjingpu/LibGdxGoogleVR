package org.masonapps.gdxgvr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;

import org.masonapps.gdxgvr.vr.GVRApplicationListener;
import org.masonapps.gdxgvr.vr.GdxEye;
import org.masonapps.gdxgvr.vr.GdxHeadTransform;

public class VrTestGame extends GVRApplicationListener {
    public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public Model model;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    private Matrix4 headView;
    private Environment environment;
    private Color backgroundColor = Color.DARK_GRAY;

    @Override
    public void create() {
        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(1f, 1f, 1f,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        final int n = 12;
        for (int i = 0; i < n; i++) {
            final double a = Math.PI * 2.0 / (double) n * (double) i;
            final float r = 10;
            final double a2 = a + Math.PI / 2.0;
            float x = (float) Math.cos(a2) * r;
            float z = (float) Math.sin(a2) * r;
            ModelInstance instance = new ModelInstance(model);
            instance.materials.get(0).set(ColorAttribute.createDiffuse(0.3f, 0.6f, (float) i / n, 1f));
            instance.transform.setTranslation(x, -1f, z);
            instances.add(instance);
        }
        headView = new Matrix4();
    }

    @Override
    public void onNewFrame(GdxHeadTransform gdxHeadTransform) {
        headView.set(gdxHeadTransform.getHeadView());
    }

    @Override
    public void onDrawEye(GdxEye gdxEye) {
        final GdxEye.Viewport viewport = gdxEye.getViewport();
        Gdx.gl.glViewport(viewport.x, viewport.y, viewport.width, viewport.height);
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cam.projection.set(gdxEye.getPerspective());
        cam.view.set(headView);
        cam.view.mulLeft(gdxEye.getView());
        cam.combined.set(cam.projection);
        Matrix4.mul(cam.combined.val, cam.view.val);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }
}
