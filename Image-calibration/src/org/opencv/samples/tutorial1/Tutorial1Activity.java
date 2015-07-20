package org.opencv.samples.tutorial1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Tutorial1Activity extends Activity implements CvCameraViewListener2 {
    private static final String TAG = "OCVSample::Activity";
    private ImageView imageView = null;
    private CameraBridgeViewBase mOpenCvCameraView;
    private boolean              mIsJavaCamera = true;
    private MenuItem             mItemSwitchCamera = null;

    private String mPath = null;
    private Bitmap mSrcBitMap = null;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    //mOpenCvCameraView.enableView();
                    findViewById(R.id.process).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            process();
                        }
                    });

                    findViewById(R.id.recover).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            recover();
                        }
                    });


                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public Tutorial1Activity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.tutorial1_surface_view);
        imageView = (ImageView) findViewById(R.id.image);
        mPath = Environment.getExternalStorageDirectory() + "/4.png";
        //mPath = "file:///android_asset/4.png";
        mSrcBitMap = BitmapFactory.decodeFile(mPath);
        imageView.setImageBitmap(mSrcBitMap);
        /*if (mIsJavaCamera)
            mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial1_activity_java_surface_view);
        else
            mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial1_activity_native_surface_view);

        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

        mOpenCvCameraView.setCvCameraViewListener(this);*/
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "called onCreateOptionsMenu");
        mItemSwitchCamera = menu.add("Toggle Native/Java camera");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String toastMesage = new String();
        Log.i(TAG, "called onOptionsItemSelected; selected item: " + item);

        /*if (item == mItemSwitchCamera) {
            mOpenCvCameraView.setVisibility(SurfaceView.GONE);
            mIsJavaCamera = !mIsJavaCamera;

            if (mIsJavaCamera) {
                mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial1_activity_java_surface_view);
                toastMesage = "Java Camera";
            } else {
                mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial1_activity_native_surface_view);
                toastMesage = "Native Camera";
            }

            mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
            mOpenCvCameraView.setCvCameraViewListener(this);
            mOpenCvCameraView.enableView();
            Toast toast = Toast.makeText(this, toastMesage, Toast.LENGTH_LONG);
            toast.show();
        }*/

        return true;
    }

    public void onCameraViewStarted(int width, int height) {
    }

    public void onCameraViewStopped() {
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        return inputFrame.rgba();
    }

    private void process() {
        //String path = Environment.getExternalStorageDirectory() + "/3.png";
        File file = new File(mPath);
        if (file.exists()) {


            List<Point> srcVector = new ArrayList<Point>();
            Point srcPoint1 = new Point(1051, 899);
            // srcVector.add(srcPoint1);
            Point srcPoint2 = new Point(1196, 1565);
            // srcVector.add(srcPoint2);
            Point srcPoint3 = new Point(291, 1921);
            //srcVector.add(srcPoint3);
            Point srcPoint4 = new Point(80, 904);
            // srcVector.add(srcPoint4);
            Mat srcMat = new MatOfPoint2f(srcPoint1, srcPoint2,
                    srcPoint3, srcPoint4); // Converters.vector_Point2d_to_Mat(srcVector);

            List<Point> disVector = new ArrayList<Point>();
            Point disPoint1 = new Point(0, 0);
            // disVector.add(disPoint1);
            Point disPoint2 = new Point(1440, 0);
            // disVector.add(disPoint2);
            Point disPoint3 = new Point(1440, 2560);
            // disVector.add(disPoint3);
            Point disPoint4 = new Point(0, 2560);
            // disVector.add(disPoint4);
            Mat disMat = new MatOfPoint2f(disPoint1, disPoint2, disPoint3, disPoint4
            ); // Converters.vector_Point2d_to_Mat(disVector);

                        /*MatOfPoint2f m2f = new MatOfPoint2f(disPoint1, disPoint2);
                        Imgproc.getPerspectiveTransform(m2f, m2f);*/

            Mat transMat = Imgproc.getPerspectiveTransform(srcMat, disMat);
            int nCols = transMat.cols();
            int nRows = transMat.rows();
            for (int i = 0; i < nRows; i++) {

                for (int j=0; j < nCols; j++) {
                    double[] data = transMat.get(i, j);
                    Log.e("mat", "" + i + "" + j + " " + data[0]);
                }
            }

            Mat imgSrcMat = Highgui.imread(mPath);
            int width = imgSrcMat.cols();
            int height = imgSrcMat.rows();
            Mat imgDisMat = new Mat(width, height, CvType.CV_8UC1);
            Imgproc.warpPerspective(imgSrcMat, imgDisMat, transMat, new Size(width, height));
            Bitmap bt3 = Bitmap.createBitmap(imgDisMat.cols(), imgDisMat.rows(), Bitmap.Config.ARGB_8888);
            if (bt3 != null) {
                Utils.matToBitmap(imgDisMat, bt3);
                imageView.setImageBitmap(bt3);
            }

        }

    }


    private void recover() {
        imageView.setImageBitmap(mSrcBitMap);
    }

}
