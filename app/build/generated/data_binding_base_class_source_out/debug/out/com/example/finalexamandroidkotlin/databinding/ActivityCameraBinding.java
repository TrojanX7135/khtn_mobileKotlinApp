// Generated by view binder compiler. Do not edit!
package com.example.finalexamandroidkotlin.databinding;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.finalexamandroidkotlin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCameraBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button cameraCancelButton;

  @NonNull
  public final Button cameraCaptureButton;

  @NonNull
  public final SurfaceView surfaceView;

  @NonNull
  public final PreviewView viewFinder;

  private ActivityCameraBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button cameraCancelButton, @NonNull Button cameraCaptureButton,
      @NonNull SurfaceView surfaceView, @NonNull PreviewView viewFinder) {
    this.rootView = rootView;
    this.cameraCancelButton = cameraCancelButton;
    this.cameraCaptureButton = cameraCaptureButton;
    this.surfaceView = surfaceView;
    this.viewFinder = viewFinder;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCameraBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCameraBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_camera, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCameraBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cameraCancelButton;
      Button cameraCancelButton = ViewBindings.findChildViewById(rootView, id);
      if (cameraCancelButton == null) {
        break missingId;
      }

      id = R.id.cameraCaptureButton;
      Button cameraCaptureButton = ViewBindings.findChildViewById(rootView, id);
      if (cameraCaptureButton == null) {
        break missingId;
      }

      id = R.id.surfaceView;
      SurfaceView surfaceView = ViewBindings.findChildViewById(rootView, id);
      if (surfaceView == null) {
        break missingId;
      }

      id = R.id.viewFinder;
      PreviewView viewFinder = ViewBindings.findChildViewById(rootView, id);
      if (viewFinder == null) {
        break missingId;
      }

      return new ActivityCameraBinding((ConstraintLayout) rootView, cameraCancelButton,
          cameraCaptureButton, surfaceView, viewFinder);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
