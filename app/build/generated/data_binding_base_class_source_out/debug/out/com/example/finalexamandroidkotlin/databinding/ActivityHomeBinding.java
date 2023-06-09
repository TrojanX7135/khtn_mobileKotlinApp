// Generated by view binder compiler. Do not edit!
package com.example.finalexamandroidkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.example.finalexamandroidkotlin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView iconHome;

  @NonNull
  public final ImageView iconProfile;

  @NonNull
  public final ImageView imageAdd;

  @NonNull
  public final LinearLayout itemHome;

  @NonNull
  public final LinearLayout itemProfile;

  @NonNull
  public final ConstraintLayout layoutBottom;

  @NonNull
  public final ViewPager2 layoutContainerMain;

  @NonNull
  public final TextView textHome;

  @NonNull
  public final TextView textProfile;

  private ActivityHomeBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView iconHome,
      @NonNull ImageView iconProfile, @NonNull ImageView imageAdd, @NonNull LinearLayout itemHome,
      @NonNull LinearLayout itemProfile, @NonNull ConstraintLayout layoutBottom,
      @NonNull ViewPager2 layoutContainerMain, @NonNull TextView textHome,
      @NonNull TextView textProfile) {
    this.rootView = rootView;
    this.iconHome = iconHome;
    this.iconProfile = iconProfile;
    this.imageAdd = imageAdd;
    this.itemHome = itemHome;
    this.itemProfile = itemProfile;
    this.layoutBottom = layoutBottom;
    this.layoutContainerMain = layoutContainerMain;
    this.textHome = textHome;
    this.textProfile = textProfile;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iconHome;
      ImageView iconHome = ViewBindings.findChildViewById(rootView, id);
      if (iconHome == null) {
        break missingId;
      }

      id = R.id.iconProfile;
      ImageView iconProfile = ViewBindings.findChildViewById(rootView, id);
      if (iconProfile == null) {
        break missingId;
      }

      id = R.id.imageAdd;
      ImageView imageAdd = ViewBindings.findChildViewById(rootView, id);
      if (imageAdd == null) {
        break missingId;
      }

      id = R.id.itemHome;
      LinearLayout itemHome = ViewBindings.findChildViewById(rootView, id);
      if (itemHome == null) {
        break missingId;
      }

      id = R.id.itemProfile;
      LinearLayout itemProfile = ViewBindings.findChildViewById(rootView, id);
      if (itemProfile == null) {
        break missingId;
      }

      id = R.id.layoutBottom;
      ConstraintLayout layoutBottom = ViewBindings.findChildViewById(rootView, id);
      if (layoutBottom == null) {
        break missingId;
      }

      id = R.id.layoutContainerMain;
      ViewPager2 layoutContainerMain = ViewBindings.findChildViewById(rootView, id);
      if (layoutContainerMain == null) {
        break missingId;
      }

      id = R.id.textHome;
      TextView textHome = ViewBindings.findChildViewById(rootView, id);
      if (textHome == null) {
        break missingId;
      }

      id = R.id.textProfile;
      TextView textProfile = ViewBindings.findChildViewById(rootView, id);
      if (textProfile == null) {
        break missingId;
      }

      return new ActivityHomeBinding((ConstraintLayout) rootView, iconHome, iconProfile, imageAdd,
          itemHome, itemProfile, layoutBottom, layoutContainerMain, textHome, textProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
