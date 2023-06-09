// Generated by view binder compiler. Do not edit!
package com.example.finalexamandroidkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import me.relex.circleindicator.CircleIndicator3;

public final class ActivityBoardingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView buttonNextBoarding;

  @NonNull
  public final ViewPager2 layoutContainerBoarding;

  @NonNull
  public final CircleIndicator3 progressBoarding;

  private ActivityBoardingBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView buttonNextBoarding, @NonNull ViewPager2 layoutContainerBoarding,
      @NonNull CircleIndicator3 progressBoarding) {
    this.rootView = rootView;
    this.buttonNextBoarding = buttonNextBoarding;
    this.layoutContainerBoarding = layoutContainerBoarding;
    this.progressBoarding = progressBoarding;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBoardingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBoardingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_boarding, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBoardingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonNextBoarding;
      TextView buttonNextBoarding = ViewBindings.findChildViewById(rootView, id);
      if (buttonNextBoarding == null) {
        break missingId;
      }

      id = R.id.layoutContainerBoarding;
      ViewPager2 layoutContainerBoarding = ViewBindings.findChildViewById(rootView, id);
      if (layoutContainerBoarding == null) {
        break missingId;
      }

      id = R.id.progressBoarding;
      CircleIndicator3 progressBoarding = ViewBindings.findChildViewById(rootView, id);
      if (progressBoarding == null) {
        break missingId;
      }

      return new ActivityBoardingBinding((ConstraintLayout) rootView, buttonNextBoarding,
          layoutContainerBoarding, progressBoarding);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
