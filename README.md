# OpenCV-Learnning
OpenCV Learnning

Image-Calibration: 图像梯形畸变校正

+ 梯形畸变校正利用的是图像处理中的透视变换（Perspective Transformation）；
+ 透视变换的本质为代表原始图像的矩阵到代表目标图像的矩阵的变换（SrcMat*TransformMat=DesMat）；
+ 求目标图像的关键即为求出变换矩阵（TransformMat),OpenCV提供了方法：
1. 在原始图像中找出四个点（A1, B1, C1, D1)构建矩阵Mat1,在目标图像中找出与原始图像四个点对应的四个点（A2, B2, C2, D2)构建矩阵Mat2；

