# OpenCV-Learnning

Image-Calibration: 图像梯形畸变校正
=================
+ 梯形畸变校正利用的是图像处理中的透视变换（Perspective Transformation）（[参考链接](http://blog.csdn.net/xiaowei_cqu/article/details/26471527)）；
+ 透视变换的本质为代表原始图像的矩阵到代表目标图像的矩阵的变换（SrcMat*TransformMat=DesMat）；
+ 求目标图像的关键即为求出变换矩阵（TransformMat),OpenCV提供了方法：

1. 在原始图像中找出四个点（A1, B1, C1, D1)构建矩阵Mat1,在目标图像中找出与原始图像四个点对应的四个点（A2, B2, C2, D2)构建矩阵Mat2；

`注意: 现有程序中原始图像的四个点坐标是利用Mark Man手动量出来的，已在程序中写死，目标是利用边缘检测、直线检测辅以用户手动移动来计算出四个点的坐标；而目标图像的四个点则是大小为图像大小的矩形的四个顶点`

2. 通过Imgproc.getPerspectiveTransform(Mat1, Mat2)求出变换矩阵TransformMat；

+ 利用求出的变换矩阵TransformMat及Imgproc.warpPerspective(srcMat, desMat, transMat, new Size(width, height))即可求出目标图像对应矩阵desMat

