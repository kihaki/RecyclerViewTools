package net.spritti.recyclerviewtools.scroll.transformation;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.util.Arrays;
import java.util.List;


public class ItemTransformationController {
	private final boolean                            startCountingFromTheBack;
	private final SparseArray<List<ViewTransformer>> viewTransformers;

	public ItemTransformationController(
			@NonNull SparseArray<List<ViewTransformer>> viewTransformers,
			boolean countFromTheBack) {
		this.startCountingFromTheBack = countFromTheBack;
		this.viewTransformers = viewTransformers;
	}

	public static ItemTransformationController build(boolean countFromTheBack) {
		return new ItemTransformationController(new SparseArray<List<ViewTransformer>>(), countFromTheBack);
	}

	public ItemTransformationController with(int pos, @NonNull ViewTransformer... transformers) {
		viewTransformers.put(pos, Arrays.asList(transformers));
		return this;
	}

	public boolean isStartCountingFromTheBack() {
		return startCountingFromTheBack;
	}

	@Nullable
	public List<ViewTransformer> getTransformers(int position) {
		return viewTransformers.get(position, null);
	}
}