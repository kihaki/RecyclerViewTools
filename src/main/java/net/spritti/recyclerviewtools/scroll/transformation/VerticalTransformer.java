package net.spritti.recyclerviewtools.scroll.transformation;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class VerticalTransformer implements ViewTransformer {

	private final Mode mode;

	protected VerticalTransformer(@NonNull Mode mode) {
		this.mode = mode;
	}

	@Override
	public void transform(@NonNull RecyclerView recyclerView, @NonNull View view) {
		if (mode == Mode.Top) {
			transformTopMode(recyclerView, view);
		} else if (mode == Mode.Bottom) {
			transformBottomMode(recyclerView, view);
		}
	}

	public abstract void transformTopMode(@NonNull RecyclerView recyclerView, @NonNull View view);

	public abstract void transformBottomMode(@NonNull RecyclerView recyclerView, @NonNull View view);

	public enum Mode {
		Top, Bottom
	}
}
