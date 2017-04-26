package net.spritti.recyclerviewtools.scroll.transformation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class VerticalParallaxTransformer extends VerticalTransformer {

	private final float parallaxAmount;

	public VerticalParallaxTransformer(float parallaxAmount, Mode mode) {
		super(mode);
		this.parallaxAmount = parallaxAmount;
	}

	@Override
	public void transformTopMode(@NonNull RecyclerView recyclerView, @NonNull View view) {
		view.setScrollY((int) (view.getTop() * parallaxAmount));
	}

	@Override
	public void transformBottomMode(@NonNull RecyclerView recyclerView, @NonNull View view) {
		view.setScrollY((int) ((recyclerView.getHeight() - view.getBottom()) * parallaxAmount));
	}
}