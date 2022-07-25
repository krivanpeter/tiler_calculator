// Generated by view binder compiler. Do not edit!
package com.pk.tiler_buddy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.pk.tiler_buddy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCalculatedBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-land/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final ImageView fullscreenView;

  @NonNull
  public final TextView pieces;

  @NonNull
  public final TextView squareM1;

  @NonNull
  public final TextView tilesNum;

  @NonNull
  public final TextView tilesNumVal;

  @NonNull
  public final TextView toBeTiledAreValue;

  @NonNull
  public final TextView toBeTiledAreaTxt;

  private ActivityCalculatedBinding(@NonNull ConstraintLayout rootView,
      @Nullable ImageView fullscreenView, @NonNull TextView pieces, @NonNull TextView squareM1,
      @NonNull TextView tilesNum, @NonNull TextView tilesNumVal,
      @NonNull TextView toBeTiledAreValue, @NonNull TextView toBeTiledAreaTxt) {
    this.rootView = rootView;
    this.fullscreenView = fullscreenView;
    this.pieces = pieces;
    this.squareM1 = squareM1;
    this.tilesNum = tilesNum;
    this.tilesNumVal = tilesNumVal;
    this.toBeTiledAreValue = toBeTiledAreValue;
    this.toBeTiledAreaTxt = toBeTiledAreaTxt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCalculatedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCalculatedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_calculated, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCalculatedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fullscreen_view;
      ImageView fullscreenView = ViewBindings.findChildViewById(rootView, id);

      id = R.id.pieces;
      TextView pieces = ViewBindings.findChildViewById(rootView, id);
      if (pieces == null) {
        break missingId;
      }

      id = R.id.square_m1;
      TextView squareM1 = ViewBindings.findChildViewById(rootView, id);
      if (squareM1 == null) {
        break missingId;
      }

      id = R.id.tiles_num;
      TextView tilesNum = ViewBindings.findChildViewById(rootView, id);
      if (tilesNum == null) {
        break missingId;
      }

      id = R.id.tiles_num_val;
      TextView tilesNumVal = ViewBindings.findChildViewById(rootView, id);
      if (tilesNumVal == null) {
        break missingId;
      }

      id = R.id.to_be_tiled_are_value;
      TextView toBeTiledAreValue = ViewBindings.findChildViewById(rootView, id);
      if (toBeTiledAreValue == null) {
        break missingId;
      }

      id = R.id.to_be_tiled_area_txt;
      TextView toBeTiledAreaTxt = ViewBindings.findChildViewById(rootView, id);
      if (toBeTiledAreaTxt == null) {
        break missingId;
      }

      return new ActivityCalculatedBinding((ConstraintLayout) rootView, fullscreenView, pieces,
          squareM1, tilesNum, tilesNumVal, toBeTiledAreValue, toBeTiledAreaTxt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}