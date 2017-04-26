package net.spritti.recyclerviewtools.scroll.transformation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public interface ViewTransformer {
	void transform(@NonNull RecyclerView recyclerView, @NonNull View view);
}