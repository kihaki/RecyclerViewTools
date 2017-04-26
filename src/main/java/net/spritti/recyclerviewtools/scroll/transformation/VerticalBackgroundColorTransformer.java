package net.spritti.recyclerviewtools.scroll.transformation;

import android.animation.ArgbEvaluator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class VerticalBackgroundColorTransformer extends VerticalTransformer {

	private final ArgbEvaluator colorEvaluator = new ArgbEvaluator();
	private final int startColor, endColor;

	public VerticalBackgroundColorTransformer(int startColor, int endColor, Mode mode) {
		super(mode);
		this.startColor = startColor;
		this.endColor = endColor;
	}

	@Override
	public void transformTopMode(@NonNull RecyclerView recyclerView, @NonNull View view) {
		final float fraction = (float) -view.getTop() / (float) view.getHeight();
		view.setBackgroundColor((int) colorEvaluator.evaluate(fraction, startColor, endColor));
	}

	@Override
	public void transformBottomMode(@NonNull RecyclerView recyclerView, @NonNull View view) {
		final float fraction = 1f - (float) (view.getBottom() - recyclerView.getHeight()) / (float) view.getHeight();
		view.setBackgroundColor((int) colorEvaluator.evaluate(fraction, startColor, endColor));
	}
}