#include <stdio.h>
#include "matrix.h"
#include "boolean.h"

/* *** Konstruktor membentuk Matrix *** */
/* Membentuk sebuah Matrix "kosong" yang siap diisi berukuran nRow x nCol di "ujung kiri" memori */
/* I.S. nRow dan nCol adalah valid untuk memori matriks yang dibuat */
/* F.S. Matriks m sesuai dengan definisi di atas terbentuk */
void createMatrix(int nRows, int nCols, Matrix *m){ // ini gausah
    ROW_EFF(*m) = nRows;
    COL_EFF(*m) = nCols;
    
}


/* *** Selektor "Dunia Matrix" *** */
boolean isMatrixIdxValid(int i, int j){ // gw
    return(i<ROW_CAP && j < COL_CAP && i >= 0 && j>= 0);
}
/* Mengirimkan true jika i, j adalah index yang valid untuk matriks apa pun */


/* *** Selektor: Untuk sebuah matriks m yang terdefinisi: *** */
IdxType getLastIdxRow(Matrix m){
    return(ROW_EFF(m)-1);
}
/* Mengirimkan Index baris terbesar m */

IdxType getLastIdxCol(Matrix m){
    return(COL_EFF(m)-1);
}
/* Mengirimkan Index kolom terbesar m */

boolean isIdxEff(Matrix m, IdxType i, IdxType j){ // bryan
    return ((i >= 0 && i <= getLastIdxRow(m)) && (j >= 0 && j <= getLastIdxCol(m)));
}
/* Mengirimkan true jika i, j adalah Index efektif bagi m */

ElType getElmtDiagonal(Matrix m, IdxType i){ //sean
    if(isIdxEff(m,i,i)){
    return(ELMT(m,i,i));
    }
}
/* Mengirimkan elemen m(i,i) */

void copyMatrix(Matrix mIn, Matrix *mOut){ //bryan
    createMatrix(ROW_EFF(mIn), COL_EFF(mIn), mOut);
    for (int i = 0; i < ROW_EFF(mIn); i++)
    {
        for (int j = 0; j < COL_EFF(mIn); j++)
        {
            /* code */
            ELMT(*mOut,i,j) = ELMT(mIn,i,j);
        }
        
    }

}

void readMatrix(Matrix *m, int nRow, int nCol){
    createMatrix(nRow,nCol,m);
    for (int i = 0; i < nRow; i++)
    {
        for (int j = 0; j < nCol; j++)
        {
            scanf("%d",&ELMT(*m,i,j));
        }
    }
}

void displayMatrix(Matrix m){
    for (int i = 0; i < ROW_EFF(m); i++)
    {
        for (int j = 0; j < COL_EFF(m); j++)
        {
            if (j == getLastIdxCol(m))
            {
                /* code */
                printf("%d\n",ELMT(m,i,j));
            }
            else{
                printf("%d ",ELMT(m,i,j));
            }
            
        }
    }

}

Matrix addMatrix(Matrix m1, Matrix m2){ //sean
    Matrix result;
    createMatrix(ROW_EFF(m1),COL_EFF(m1),&result);
    for (int i = 0; i < ROW_EFF(m1); i++)
    {
        for (int j = 0; j < COL_EFF(m1); j++)
        {
            ELMT(result,i,j) = ELMT(m1,i,j) + ELMT(m2,i,j);
        }
    }   
    return(result);
}

/* Prekondisi : m1 berukuran sama dengan m2 */
/* Mengirim hasil pengurangan matriks: salinan m1 – m2 */
Matrix subtractMatrix(Matrix m1, Matrix m2){ //bryan
    Matrix result;
    createMatrix(ROW_EFF(m1),COL_EFF(m1),&result);
    for (int i = 0; i < ROW_EFF(m1); i++)
    {
        for (int j = 0; j < COL_EFF(m1); j++)
        {
            ELMT(result,i,j) = ELMT(m1,i,j) - ELMT(m2,i,j);
        }
    }   
    return(result);   
}

/* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
/* Mengirim hasil perkalian matriks: salinan m1 * m2 */
Matrix multiplyMatrix(Matrix m1, Matrix m2){ //bryan
    Matrix result;
    createMatrix(ROW_EFF(m1),COL_EFF(m2),&result);
    int idx =0;
    for (int i = 0; i < ROW_EFF(m1); i++)
    {
        for (int x = 0; x < COL_EFF(m2); x++)
        {
            ELMT(result,i,x)=0;
            for (int j = 0; j < COL_EFF(m1); j++)
            {
                ELMT(result,i,x) += ELMT(m1,i,j) *ELMT(m2,j,x);  
            }
        }
    }   
    return(result);     
}

/* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
/* Mengirim hasil perkalian matriks: salinan (m1 * m2)%mod, artinya setiap elemen matrix hasil perkalian m1 * m2 dilakukan modulo terhadap mod */
Matrix multiplyMatrixWithMod(Matrix m1,Matrix m2,int mod){ //bryan
    Matrix result;
    result = multiplyMatrix(m1,m2);
    for (int i = 0; i < ROW_EFF(result); i++)
    {
        for (int j = 0; j < COL_EFF(result); j++)
        {
            ELMT(result,i,j) = ELMT(result,i,j) % mod;
        }
        
    }
    return(result);
}

Matrix multiplyByConst(Matrix m, ElType x){ //sean
    Matrix result;
    createMatrix(ROW_EFF(m),COL_EFF(m),&result);
    for (int i = 0; i < ROW_EFF(result); i++)
    {
        for (int j = 0; j < COL_EFF(result); j++)
        {
            ELMT(result,i,j) = ELMT(m,i,j) * x;
        }
        
    }
    return(result); 
}

void pMultiplyByConst(Matrix *m, ElType k){ //sean
    for (int i = 0; i < ROW_EFF(*m); i++)
    {
        for (int j = 0; j < COL_EFF(*m); j++)
        {
            ELMT(*m,i,j) = ELMT(*m,i,j) * k;
        }
        
    }    
}

boolean isMatrixEqual(Matrix m1, Matrix m2){ // bryan
    if ((ROW_EFF(m1)== ROW_EFF(m2))&& (COL_EFF(m1)==COL_EFF(m2)))
    {
        for (int i = 0; i < ROW_EFF(m1); i++)
        {
            for (int j = 0; j < COL_EFF(m1); j++)
            {
                
                if (ELMT(m1,i,j)!= ELMT(m2,i,j))
                {
                    return(false);
                }
                
            }
            
        }
        return(true);    
    }
    else{
        return(false);
    }
    
}

boolean isMatrixNotEqual(Matrix m1, Matrix m2){ //bryan
    return !isMatrixEqual(m1,m2);
}

boolean isMatrixSizeEqual(Matrix m1, Matrix m2){ //sean
    return(ROW_EFF(m1)== ROW_EFF(m2))&& (COL_EFF(m1)==COL_EFF(m2));
}

int countElmt(Matrix m){ //sean
    return ROW_EFF(m) * COL_EFF(m);
}

boolean isSquare(Matrix m){ //bryan
    return(ROW_EFF(m)==COL_EFF(m));
}

boolean isSymmetric(Matrix m){ //bryan
    if (isSquare(m))
    {
        for (int i = 0; i < ROW_EFF(m); i++)
        {
            for (int j = 0; j < COL_EFF(m); j++)
            {
                ELMT(m,i,j) == ELMT(m,j,i);
            }
            
        } 
    }
    return(false);
}

boolean isIdentity(Matrix m){ //sean
    if (isSquare(m))
    {
        Matrix identity;
        createMatrix(ROW_EFF(m),COL_EFF(m),&m);
        for (int i = 0; i < ROW_EFF(m); i++)
        {
            for (int j = 0; j < COL_EFF(m); j++)
            {
                if (i == j)
                {
                    ELMT(identity,i,j) = 1;
                }
                
            } 
        }
        return isMatrixEqual(m,identity);
        
    }
    return(false);

}

boolean isSparse(Matrix m){
    float percent;
    for (int i = 0; i < ROW_EFF(m); i++)
    {
        for (int j = 0; j < COL_EFF(m); j++)
        {
            percent += ELMT(m,i,j);   
        } 
    }
    percent /= countElmt(m);
    return (percent<=0.05);
    

}

Matrix negation(Matrix m){
    Matrix result;
    for (int i = 0; i < ROW_EFF(m); i++)
    {
        for (int j = 0; j < COL_EFF(m); j++)
        {
            ELMT(m,i,j) *= -1;   
        } 
    }
    return(result);
}

void pNegation(Matrix *m){
    for (int i = 0; i < ROW_EFF(*m); i++)
    {
        for (int j = 0; j < COL_EFF(*m); j++)
        {
            ELMT(*m,i,j) *= -1;   
        } 
    }   
}

float determinant(Matrix m){
    float det = 0;
    if (ROW_EFF(m)==2 && COL_EFF(m)==2)
    {
        return((ELMT(m,0,0)*ELMT(m,1,1))-(ELMT(m,0,1)*ELMT(m,1,0)));
    }
    else{
        for (int i = 0; i < COL_EFF(m); i++) //bikin kofaktor
        {
            Matrix mr;
            createMatrix(ROW_EFF(m)-1,COL_EFF(m)-1,&mr);
            int baris =0;
            for (int j = 1; j < ROW_EFF(m); j++) // baris mr
            {   
                int kolom =0;
                for (int k = 0; k < COL_EFF(m); k++) //kolom mr
                {   
                    
                    if (k != i)
                    {
                        ELMT(mr,baris,kolom)=ELMT(m,j,k);
                        kolom++;
                    }       
                }
                baris++;
            }
            if (i%2==0)
            {
                det+= ELMT(m,0,i)*determinant(mr);
                
            }
            else{
                det+= (-1)*ELMT(m,0,i)*determinant(mr);
                
            }
            
        }

        
    }
    return(det);
}

Matrix transpose(Matrix m){ //be
    Matrix result;
    createMatrix(COL_EFF(m),ROW_EFF(m),&result);
    for (int i = 0; i < ROW_EFF(result); i++)
    {
        for (int j = 0; j < COL_EFF(result); j++)
        {
            ELMT(result,i,j)= ELMT(m,j,i);
        }
        
    }
    return(result);
}

void pTranspose(Matrix *m){ //be
    Matrix result = transpose(*m);
    createMatrix(ROW_EFF(result),COL_EFF(result),m);
    copyMatrix(result,m);
      
}

