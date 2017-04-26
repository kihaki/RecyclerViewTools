package net.spritti.recyclerviewtools.scroll.transformation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;


public class DirectChildViewAlphaTransformer extends VerticalTransformer {

	private final Interpolator interpolator;
	private final float        startAlpha, endAlpha;

	public DirectChildViewAlphaTransformer(float startAlpha, float endAlpha, @Nullable Interpolator interpolator, Mode mode) {
		super(mode);
		this.startAlpha = startAlpha;
		this.endAlpha = endAlpha;
		this.interpolator = interpolator == null ? new LinearInterpolator() : interpolator;
	}

	@Override
	public void transformTopMode(@NonNull RecyclerView recyclerView, @NonNull View view) {
		if (view instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) view;
			final float fraction = (float) -view.getTop() / (float) view.getHeight();
			setChildViewAlpha(viewGroup, fractionToAlpha(fraction));
		}
	}

	@Override
	public void transformBottomMode(@NonNull RecyclerView recyclerView, @NonNull View view) {
		if (view instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) view;
			final float fraction = 1f - (float) (view.getBottom() - recyclerView.getHeight()) / (float) view.getHeight();
			setChildViewAlpha(viewGroup, fractionToAlpha(fraction));
		}
	}

	private float fractionToAlpha(float fraction) {
		return (endAlpha - startAlpha) * interpolator.getInterpolation(fraction) + startAlpha;
	}

	private void setChildViewAlpha(@NonNull ViewGroup viewGroup, float alpha) {
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View childView = viewGroup.getChildAt(i);
			childView.setAlpha(alpha);
		}
	}
}