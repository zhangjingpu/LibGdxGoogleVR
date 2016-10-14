package org.masonapps.gdxgvr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import org.masonapps.gdxgvr.vr.GdxEye;
import org.masonapps.gdxgvr.vr.GdxHeadTransform;
import org.masonapps.gdxgvr.vr.VrApplicationListener;
import org.masonapps.gdxgvr.vr.VrCamera;

public class VrTestGame extends VrApplicationListener {
    private final Vector3 tempV = new Vector3();
    public VrCamera cam;
    public ModelBatch modelBatch;
    public Model model;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    private Environment environment;
    private Color backgroundColor = Color.DARK_GRAY;
    private DecalBatch decalBatch;
    private Decal cursor;
    private Color cursorColor = Color.WHITE;

    @Override
    public void create() {
        cam = new VrCamera();
        cam.position.set(0, 1.5f, 0);

        modelBatch = new ModelBatch();
        decalBatch = new DecalBatch(new CameraGroupStrategy(cam));
        final Pixmap circle = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        circle.setColor(Color.CLEAR);
        circle.fill();
        circle.setColor(cursorColor);
        circle.drawCircle(16, 16, 14);
        final Texture circleTexture = new Texture(circle);
        cursor = Decal.newDecal(new TextureRegion(circleTexture));

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(1f, 1f, 1f,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        {
            ModelInstance instance = new ModelInstance(model);
            instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.WHITE));
            instance.transform.setTranslation(0, 0, 5);
            instances.add(instance);
        }
        {
            ModelInstance instance = new ModelInstance(model);
            instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.RED));
            instance.transform.setTranslation(0, 0, -5);
            instances.add(instance);
        }
        {
            ModelInstance instance = new ModelInstance(model);
            instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.CYAN));
            instance.transform.setTranslation(5, 0, 0);
            instances.add(instance);
        }
        {
            ModelInstance instance = new ModelInstance(model);
            instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.BLUE));
            instance.transform.setTranslation(-5, 0, 0);
            instances.add(instance);
        }
        {
            ModelInstance instance = new ModelInstance(model);
            instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.YELLOW));
            instance.transform.setTranslation(0, 7, 0);
            instances.add(instance);
        }
        {
            ModelInstance instance = new ModelInstance(model);
            instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.BROWN));
            instance.transform.setTranslation(0, -3, 0);
            instances.add(instance);
        }
    }

    @Override
    public void onNewFrame(GdxHeadTransform gdxHeadTransform) {
        cursor.setPosition(tempV.set(gdxHeadTransform.forward).scl(2).add(cam.position));
        cursor.lookAt(cam.position, gdxHeadTransform.up);
    }

    @Override
    public void onDrawEye(GdxEye gdxEye) {
        final GdxEye.Viewport viewport = gdxEye.getViewport();
        Gdx.gl.glViewport(viewport.x, viewport.y, viewport.width, viewport.height);
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cam.onDrawEye(gdxEye);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();

        decalBatch.add(cursor);
        decalBatch.flush();
    }

    @Override
    public void onCardboardTrigger() {

    }

    @Override
    public void onCardboardBack() {

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
        decalBatch.dispose();
        modelBatch.dispose();
        model.dispose();
    }
}
