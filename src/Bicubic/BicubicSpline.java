package Bicubic;
import ADT_Matrix.*;
import java.util.Scanner;

public class BicubicSpline {
    public static float interpolation (Matrix m, float targetX, float targetY) {
        int i, j, x, y;
        float result;
        Matrix mY = new Matrix(16,1);
        Matrix mX = new Matrix(16,16);
        Matrix ma = new Matrix(16,1);

        // ubah matrix m jadi column matriks mY
        for(i=0;i<16;i++) {
            mY.memory[i][0] = m.memory[i / 4][i % 4];
        }

        // isi matrix mX
        // iterasi per row
        for(i=0;i<16;i++) {
            // tentukan nilai x dan y
            x = i % 2;
            if (i % 4  < 2) {
                y = 0;
            } else {
                y = 1;
            }

            if (0 <= i && i <= 3) {
                for(j=0;j<16;j++) {
                    mX.memory[i][j] = (float) (Math.pow(x,j % 4) * Math.pow(y,j/4));
                }
            } else if (4 <= i && i <=7) {
                for(j=0;j<16;j++) {
                    if (j % 4 != 0) {
                        mX.memory[i][j] = (float) ((j % 4) * Math.pow(x,(j%4)-1) * Math.pow(y,j/4));
                    } else {
                        mX.memory[i][j] = 0;
                    }
                }
            } else if (8 <= i && i <=11) {
                for(j=0;j<16;j++) {
                    if (j / 4 != 0) {
                        mX.memory[i][j] = (float) ((j / 4) * Math.pow(x,j%4) * Math.pow(y,j/4-1));
                    } else {
                        mX.memory[i][j] = 0;
                    }
                }
            } else {
                for(j=0;j<16;j++) {
                    mX.memory[i][j] = (float) ((j%4) * (j/4) * Math.pow(x,Math.abs(j%4 -1)) * Math.pow(y,Math.abs(j/4 - 1)));
                } 
            }
        }

        // temukan matriks ma
        Matrix inversemX = Invers.InverseWithGaussJordan(mX);
        ma = Matrix.multiplyMatrix(inversemX, mY);

        result = 0;
        for(i=0;i<16;i++) {
            result += (ma.memory[i][0] * (Math.pow(targetX,i%4) * Math.pow(targetY,i/4)));
        }
        System.out.println("f(" + targetX + "," + targetY + ")=" + result);
        return result;
    }
}