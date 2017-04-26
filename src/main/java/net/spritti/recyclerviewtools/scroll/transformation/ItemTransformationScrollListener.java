package net.spritti.recyclerviewtools.scroll.transformation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;


public class ItemTransformationScrollListener extends RecyclerView.OnScrollListener {

	private ItemTransformationController frontModule, backModule;

	public ItemTransformationScrollListener(
			@Nullable ItemTransformationController frontModule,
			@Nullable ItemTransformationController backModule) {
		this.frontModule = frontModule;
		this.backModule = backModule;
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);
		LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
		if (frontModule != null) {
			dispatchToFrontModule(recyclerView, layoutManager);
		}
		if (backModule != null) {
			dispatchToBackModule(recyclerView, layoutManager);
		}
	}

	private void dispatchToFrontModule(@NonNull RecyclerView recyclerView, @NonNull LinearLayoutManager layoutManager) {
		int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
		List<ViewTransformer> frontTransformers = frontModule.getTransformers(firstItemPosition);
		if (frontTransformers != null && !frontTransformers.isEmpty()) {
			View firstView = layoutManager.findViewByPosition(firstItemPosition);
			for (ViewTransformer frontTransformer : frontTransformers) {
				frontTransformer.transform(recyclerView, firstView);
			}
		}
	}

	private void dispatchToBackModule(@NonNull RecyclerView recyclerView, @NonNull LinearLayoutManager layoutManager) {
		int lastItemPosition = layoutManager.findLastVisibleItemPosition();
		int mappingItemPosition = lastItemPosition;
		if (backModule.isStartCountingFromTheBack()) {
			int itemCount = recyclerView.getAdapter().getItemCount();
			mappingItemPosition = itemCount - (lastItemPosition + 1);
		}
		List<ViewTransformer> backTransformers = backModule.getTransformers(mappingItemPosition);
		if (backTransformers != null && !backTransformers.isEmpty()) {
			View lastView = layoutManager.findViewByPosition(lastItemPosition);
			for (ViewTransformer backTransformer : backTransformers) {
				backTransformer.transform(recyclerView, lastView);
			}
		}
	}
}