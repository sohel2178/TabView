package com.example.nlpc06.tabview.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nlpc06.tabview.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorFragment extends Fragment implements OnClickListener {
    private static final int CAMERA_REQ=1100;

    private Button btnOpenCamera;
    private ImageView ivImage;




    public DoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Log.d("GGG",root);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        btnOpenCamera = view.findViewById(R.id.open_camera);
        ivImage = view.findViewById(R.id.image);
        btnOpenCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        /*Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQ);*/

       /* CropImage.activity()
                .start(getContext(), this);*/

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == getActivity().RESULT_OK) {
                Uri resultUri = result.getUri();

                ivImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }*/
    }

    private void saveBitmap(Bitmap bitmap){
        String root = Environment.getExternalStorageDirectory().toString();

        String appName =getString(R.string.app_name);

        File myDir = new File(root + "/"+appName);

        if(!myDir.exists()){
            myDir.mkdirs();
        }

        long time = System.currentTimeMillis();

        File file = new File(myDir,time+".jpg");

        if(file.exists()){
            file.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
