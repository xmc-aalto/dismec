/* blasp.h  --  C prototypes for BLAS                         Ver 1.0 */
/* Jesse Bennett                                       March 23, 2000 */

/* Functions  listed in alphabetical order */

#ifdef F2C_COMPAT

void cdotc_(fcomplex *dotval, long long *n, fcomplex *cx, long long *incx,
            fcomplex *cy, long long *incy);

void cdotu_(fcomplex *dotval, long long *n, fcomplex *cx, long long *incx,
            fcomplex *cy, long long *incy);

double sasum_(long long *n, float *sx, long long *incx);

double scasum_(long long *n, fcomplex *cx, long long *incx);

double scnrm2_(long long *n, fcomplex *x, long long *incx);

double sdot_(long long *n, float *sx, long long *incx, float *sy, long long *incy);

double snrm2_(long long *n, float *x, long long *incx);

void zdotc_(dcomplex *dotval, long long *n, dcomplex *cx, long long *incx,
            dcomplex *cy, long long *incy);

void zdotu_(dcomplex *dotval, long long *n, dcomplex *cx, long long *incx,
            dcomplex *cy, long long *incy);

#else

fcomplex cdotc_(long long *n, fcomplex *cx, long long *incx, fcomplex *cy, long long *incy);

fcomplex cdotu_(long long *n, fcomplex *cx, long long *incx, fcomplex *cy, long long *incy);

float sasum_(long long *n, float *sx, long long *incx);

float scasum_(long long *n, fcomplex *cx, long long *incx);

float scnrm2_(long long *n, fcomplex *x, long long *incx);

float sdot_(long long *n, float *sx, long long *incx, float *sy, long long *incy);

float snrm2_(long long *n, float *x, long long *incx);

dcomplex zdotc_(long long *n, dcomplex *cx, long long *incx, dcomplex *cy, long long *incy);

dcomplex zdotu_(long long *n, dcomplex *cx, long long *incx, dcomplex *cy, long long *incy);

#endif

/* Remaining functions listed in alphabetical order */

long long caxpy_(long long *n, fcomplex *ca, fcomplex *cx, long long *incx, fcomplex *cy,
           long long *incy);

long long ccopy_(long long *n, fcomplex *cx, long long *incx, fcomplex *cy, long long *incy);

long long cgbmv_(char *trans, long long *m, long long *n, long long *kl, long long *ku,
           fcomplex *alpha, fcomplex *a, long long *lda, fcomplex *x, long long *incx,
           fcomplex *beta, fcomplex *y, long long *incy);

long long cgemm_(char *transa, char *transb, long long *m, long long *n, long long *k,
           fcomplex *alpha, fcomplex *a, long long *lda, fcomplex *b, long long *ldb,
           fcomplex *beta, fcomplex *c, long long *ldc);

long long cgemv_(char *trans, long long *m, long long *n, fcomplex *alpha, fcomplex *a,
           long long *lda, fcomplex *x, long long *incx, fcomplex *beta, fcomplex *y,
           long long *incy);

long long cgerc_(long long *m, long long *n, fcomplex *alpha, fcomplex *x, long long *incx,
           fcomplex *y, long long *incy, fcomplex *a, long long *lda);

long long cgeru_(long long *m, long long *n, fcomplex *alpha, fcomplex *x, long long *incx,
           fcomplex *y, long long *incy, fcomplex *a, long long *lda);

long long chbmv_(char *uplo, long long *n, long long *k, fcomplex *alpha, fcomplex *a,
           long long *lda, fcomplex *x, long long *incx, fcomplex *beta, fcomplex *y,
           long long *incy);

long long chemm_(char *side, char *uplo, long long *m, long long *n, fcomplex *alpha,
           fcomplex *a, long long *lda, fcomplex *b, long long *ldb, fcomplex *beta,
           fcomplex *c, long long *ldc);

long long chemv_(char *uplo, long long *n, fcomplex *alpha, fcomplex *a, long long *lda,
           fcomplex *x, long long *incx, fcomplex *beta, fcomplex *y, long long *incy);

long long cher_(char *uplo, long long *n, float *alpha, fcomplex *x, long long *incx,
          fcomplex *a, long long *lda);

long long cher2_(char *uplo, long long *n, fcomplex *alpha, fcomplex *x, long long *incx,
           fcomplex *y, long long *incy, fcomplex *a, long long *lda);

long long cher2k_(char *uplo, char *trans, long long *n, long long *k, fcomplex *alpha,
            fcomplex *a, long long *lda, fcomplex *b, long long *ldb, float *beta,
            fcomplex *c, long long *ldc);

long long cherk_(char *uplo, char *trans, long long *n, long long *k, float *alpha,
           fcomplex *a, long long *lda, float *beta, fcomplex *c, long long *ldc);

long long chpmv_(char *uplo, long long *n, fcomplex *alpha, fcomplex *ap, fcomplex *x,
           long long *incx, fcomplex *beta, fcomplex *y, long long *incy);

long long chpr_(char *uplo, long long *n, float *alpha, fcomplex *x, long long *incx,
          fcomplex *ap);

long long chpr2_(char *uplo, long long *n, fcomplex *alpha, fcomplex *x, long long *incx,
           fcomplex *y, long long *incy, fcomplex *ap);

long long crotg_(fcomplex *ca, fcomplex *cb, float *c, fcomplex *s);

long long cscal_(long long *n, fcomplex *ca, fcomplex *cx, long long *incx);

long long csscal_(long long *n, float *sa, fcomplex *cx, long long *incx);

long long cswap_(long long *n, fcomplex *cx, long long *incx, fcomplex *cy, long long *incy);

long long csymm_(char *side, char *uplo, long long *m, long long *n, fcomplex *alpha,
           fcomplex *a, long long *lda, fcomplex *b, long long *ldb, fcomplex *beta,
           fcomplex *c, long long *ldc);

long long csyr2k_(char *uplo, char *trans, long long *n, long long *k, fcomplex *alpha,
            fcomplex *a, long long *lda, fcomplex *b, long long *ldb, fcomplex *beta,
            fcomplex *c, long long *ldc);

long long csyrk_(char *uplo, char *trans, long long *n, long long *k, fcomplex *alpha,
           fcomplex *a, long long *lda, fcomplex *beta, fcomplex *c, long long *ldc);

long long ctbmv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           fcomplex *a, long long *lda, fcomplex *x, long long *incx);

long long ctbsv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           fcomplex *a, long long *lda, fcomplex *x, long long *incx);

long long ctpmv_(char *uplo, char *trans, char *diag, long long *n, fcomplex *ap,
           fcomplex *x, long long *incx);

long long ctpsv_(char *uplo, char *trans, char *diag, long long *n, fcomplex *ap,
           fcomplex *x, long long *incx);

long long ctrmm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, fcomplex *alpha, fcomplex *a, long long *lda, fcomplex *b,
           long long *ldb);

long long ctrmv_(char *uplo, char *trans, char *diag, long long *n, fcomplex *a,
           long long *lda, fcomplex *x, long long *incx);

long long ctrsm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, fcomplex *alpha, fcomplex *a, long long *lda, fcomplex *b,
           long long *ldb);

long long ctrsv_(char *uplo, char *trans, char *diag, long long *n, fcomplex *a,
           long long *lda, fcomplex *x, long long *incx);

long long daxpy_(long long *n, double *sa, double *sx, long long *incx, double *sy,
           long long *incy);

long long dcopy_(long long *n, double *sx, long long *incx, double *sy, long long *incy);

long long dgbmv_(char *trans, long long *m, long long *n, long long *kl, long long *ku,
           double *alpha, double *a, long long *lda, double *x, long long *incx,
           double *beta, double *y, long long *incy);

long long dgemm_(char *transa, char *transb, long long *m, long long *n, long long *k,
           double *alpha, double *a, long long *lda, double *b, long long *ldb,
           double *beta, double *c, long long *ldc);

long long dgemv_(char *trans, long long *m, long long *n, double *alpha, double *a,
           long long *lda, double *x, long long *incx, double *beta, double *y, 
           long long *incy);

long long dger_(long long *m, long long *n, double *alpha, double *x, long long *incx,
          double *y, long long *incy, double *a, long long *lda);

long long drot_(long long *n, double *sx, long long *incx, double *sy, long long *incy,
          double *c, double *s);

long long drotg_(double *sa, double *sb, double *c, double *s);

long long dsbmv_(char *uplo, long long *n, long long *k, double *alpha, double *a,
           long long *lda, double *x, long long *incx, double *beta, double *y, 
           long long *incy);

long long dscal_(long long *n, double *sa, double *sx, long long *incx);

long long dspmv_(char *uplo, long long *n, double *alpha, double *ap, double *x,
           long long *incx, double *beta, double *y, long long *incy);

long long dspr_(char *uplo, long long *n, double *alpha, double *x, long long *incx,
          double *ap);

long long dspr2_(char *uplo, long long *n, double *alpha, double *x, long long *incx,
           double *y, long long *incy, double *ap);

long long dswap_(long long *n, double *sx, long long *incx, double *sy, long long *incy);

long long dsymm_(char *side, char *uplo, long long *m, long long *n, double *alpha,
           double *a, long long *lda, double *b, long long *ldb, double *beta,
           double *c, long long *ldc);

long long dsymv_(char *uplo, long long *n, double *alpha, double *a, long long *lda,
           double *x, long long *incx, double *beta, double *y, long long *incy);

long long dsyr_(char *uplo, long long *n, double *alpha, double *x, long long *incx,
          double *a, long long *lda);

long long dsyr2_(char *uplo, long long *n, double *alpha, double *x, long long *incx,
           double *y, long long *incy, double *a, long long *lda);

long long dsyr2k_(char *uplo, char *trans, long long *n, long long *k, double *alpha,
            double *a, long long *lda, double *b, long long *ldb, double *beta,
            double *c, long long *ldc);

long long dsyrk_(char *uplo, char *trans, long long *n, long long *k, double *alpha,
           double *a, long long *lda, double *beta, double *c, long long *ldc);

long long dtbmv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           double *a, long long *lda, double *x, long long *incx);

long long dtbsv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           double *a, long long *lda, double *x, long long *incx);

long long dtpmv_(char *uplo, char *trans, char *diag, long long *n, double *ap,
           double *x, long long *incx);

long long dtpsv_(char *uplo, char *trans, char *diag, long long *n, double *ap,
           double *x, long long *incx);

long long dtrmm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, double *alpha, double *a, long long *lda, double *b, 
           long long *ldb);

long long dtrmv_(char *uplo, char *trans, char *diag, long long *n, double *a,
           long long *lda, double *x, long long *incx);

long long dtrsm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, double *alpha, double *a, long long *lda, double *b, 
           long long *ldb);

long long dtrsv_(char *uplo, char *trans, char *diag, long long *n, double *a,
           long long *lda, double *x, long long *incx);


long long saxpy_(long long *n, float *sa, float *sx, long long *incx, float *sy, long long *incy);

long long scopy_(long long *n, float *sx, long long *incx, float *sy, long long *incy);

long long sgbmv_(char *trans, long long *m, long long *n, long long *kl, long long *ku,
           float *alpha, float *a, long long *lda, float *x, long long *incx,
           float *beta, float *y, long long *incy);

long long sgemm_(char *transa, char *transb, long long *m, long long *n, long long *k,
           float *alpha, float *a, long long *lda, float *b, long long *ldb,
           float *beta, float *c, long long *ldc);

long long sgemv_(char *trans, long long *m, long long *n, float *alpha, float *a,
           long long *lda, float *x, long long *incx, float *beta, float *y, 
           long long *incy);

long long sger_(long long *m, long long *n, float *alpha, float *x, long long *incx,
          float *y, long long *incy, float *a, long long *lda);

long long srot_(long long *n, float *sx, long long *incx, float *sy, long long *incy,
          float *c, float *s);

long long srotg_(float *sa, float *sb, float *c, float *s);

long long ssbmv_(char *uplo, long long *n, long long *k, float *alpha, float *a,
           long long *lda, float *x, long long *incx, float *beta, float *y, 
           long long *incy);

long long sscal_(long long *n, float *sa, float *sx, long long *incx);

long long sspmv_(char *uplo, long long *n, float *alpha, float *ap, float *x,
           long long *incx, float *beta, float *y, long long *incy);

long long sspr_(char *uplo, long long *n, float *alpha, float *x, long long *incx,
          float *ap);

long long sspr2_(char *uplo, long long *n, float *alpha, float *x, long long *incx,
           float *y, long long *incy, float *ap);

long long sswap_(long long *n, float *sx, long long *incx, float *sy, long long *incy);

long long ssymm_(char *side, char *uplo, long long *m, long long *n, float *alpha,
           float *a, long long *lda, float *b, long long *ldb, float *beta,
           float *c, long long *ldc);

long long ssymv_(char *uplo, long long *n, float *alpha, float *a, long long *lda,
           float *x, long long *incx, float *beta, float *y, long long *incy);

long long ssyr_(char *uplo, long long *n, float *alpha, float *x, long long *incx,
          float *a, long long *lda);

long long ssyr2_(char *uplo, long long *n, float *alpha, float *x, long long *incx,
           float *y, long long *incy, float *a, long long *lda);

long long ssyr2k_(char *uplo, char *trans, long long *n, long long *k, float *alpha,
            float *a, long long *lda, float *b, long long *ldb, float *beta,
            float *c, long long *ldc);

long long ssyrk_(char *uplo, char *trans, long long *n, long long *k, float *alpha,
           float *a, long long *lda, float *beta, float *c, long long *ldc);

long long stbmv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           float *a, long long *lda, float *x, long long *incx);

long long stbsv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           float *a, long long *lda, float *x, long long *incx);

long long stpmv_(char *uplo, char *trans, char *diag, long long *n, float *ap,
           float *x, long long *incx);

long long stpsv_(char *uplo, char *trans, char *diag, long long *n, float *ap,
           float *x, long long *incx);

long long strmm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, float *alpha, float *a, long long *lda, float *b, 
           long long *ldb);

long long strmv_(char *uplo, char *trans, char *diag, long long *n, float *a,
           long long *lda, float *x, long long *incx);

long long strsm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, float *alpha, float *a, long long *lda, float *b, 
           long long *ldb);

long long strsv_(char *uplo, char *trans, char *diag, long long *n, float *a,
           long long *lda, float *x, long long *incx);

long long zaxpy_(long long *n, dcomplex *ca, dcomplex *cx, long long *incx, dcomplex *cy,
           long long *incy);

long long zcopy_(long long *n, dcomplex *cx, long long *incx, dcomplex *cy, long long *incy);

long long zdscal_(long long *n, double *sa, dcomplex *cx, long long *incx);

long long zgbmv_(char *trans, long long *m, long long *n, long long *kl, long long *ku,
           dcomplex *alpha, dcomplex *a, long long *lda, dcomplex *x, long long *incx,
           dcomplex *beta, dcomplex *y, long long *incy);

long long zgemm_(char *transa, char *transb, long long *m, long long *n, long long *k,
           dcomplex *alpha, dcomplex *a, long long *lda, dcomplex *b, long long *ldb,
           dcomplex *beta, dcomplex *c, long long *ldc);

long long zgemv_(char *trans, long long *m, long long *n, dcomplex *alpha, dcomplex *a,
           long long *lda, dcomplex *x, long long *incx, dcomplex *beta, dcomplex *y,
           long long *incy);

long long zgerc_(long long *m, long long *n, dcomplex *alpha, dcomplex *x, long long *incx,
           dcomplex *y, long long *incy, dcomplex *a, long long *lda);

long long zgeru_(long long *m, long long *n, dcomplex *alpha, dcomplex *x, long long *incx,
           dcomplex *y, long long *incy, dcomplex *a, long long *lda);

long long zhbmv_(char *uplo, long long *n, long long *k, dcomplex *alpha, dcomplex *a,
           long long *lda, dcomplex *x, long long *incx, dcomplex *beta, dcomplex *y,
           long long *incy);

long long zhemm_(char *side, char *uplo, long long *m, long long *n, dcomplex *alpha,
           dcomplex *a, long long *lda, dcomplex *b, long long *ldb, dcomplex *beta,
           dcomplex *c, long long *ldc);

long long zhemv_(char *uplo, long long *n, dcomplex *alpha, dcomplex *a, long long *lda,
           dcomplex *x, long long *incx, dcomplex *beta, dcomplex *y, long long *incy);

long long zher_(char *uplo, long long *n, double *alpha, dcomplex *x, long long *incx,
          dcomplex *a, long long *lda);

long long zher2_(char *uplo, long long *n, dcomplex *alpha, dcomplex *x, long long *incx,
           dcomplex *y, long long *incy, dcomplex *a, long long *lda);

long long zher2k_(char *uplo, char *trans, long long *n, long long *k, dcomplex *alpha,
            dcomplex *a, long long *lda, dcomplex *b, long long *ldb, double *beta,
            dcomplex *c, long long *ldc);

long long zherk_(char *uplo, char *trans, long long *n, long long *k, double *alpha,
           dcomplex *a, long long *lda, double *beta, dcomplex *c, long long *ldc);

long long zhpmv_(char *uplo, long long *n, dcomplex *alpha, dcomplex *ap, dcomplex *x,
           long long *incx, dcomplex *beta, dcomplex *y, long long *incy);

long long zhpr_(char *uplo, long long *n, double *alpha, dcomplex *x, long long *incx,
          dcomplex *ap);

long long zhpr2_(char *uplo, long long *n, dcomplex *alpha, dcomplex *x, long long *incx,
           dcomplex *y, long long *incy, dcomplex *ap);

long long zrotg_(dcomplex *ca, dcomplex *cb, double *c, dcomplex *s);

long long zscal_(long long *n, dcomplex *ca, dcomplex *cx, long long *incx);

long long zswap_(long long *n, dcomplex *cx, long long *incx, dcomplex *cy, long long *incy);

long long zsymm_(char *side, char *uplo, long long *m, long long *n, dcomplex *alpha,
           dcomplex *a, long long *lda, dcomplex *b, long long *ldb, dcomplex *beta,
           dcomplex *c, long long *ldc);

long long zsyr2k_(char *uplo, char *trans, long long *n, long long *k, dcomplex *alpha,
            dcomplex *a, long long *lda, dcomplex *b, long long *ldb, dcomplex *beta,
            dcomplex *c, long long *ldc);

long long zsyrk_(char *uplo, char *trans, long long *n, long long *k, dcomplex *alpha,
           dcomplex *a, long long *lda, dcomplex *beta, dcomplex *c, long long *ldc);

long long ztbmv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           dcomplex *a, long long *lda, dcomplex *x, long long *incx);

long long ztbsv_(char *uplo, char *trans, char *diag, long long *n, long long *k,
           dcomplex *a, long long *lda, dcomplex *x, long long *incx);

long long ztpmv_(char *uplo, char *trans, char *diag, long long *n, dcomplex *ap,
           dcomplex *x, long long *incx);

long long ztpsv_(char *uplo, char *trans, char *diag, long long *n, dcomplex *ap,
           dcomplex *x, long long *incx);

long long ztrmm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, dcomplex *alpha, dcomplex *a, long long *lda, dcomplex *b,
           long long *ldb);

long long ztrmv_(char *uplo, char *trans, char *diag, long long *n, dcomplex *a,
           long long *lda, dcomplex *x, long long *incx);

long long ztrsm_(char *side, char *uplo, char *transa, char *diag, long long *m,
           long long *n, dcomplex *alpha, dcomplex *a, long long *lda, dcomplex *b,
           long long *ldb);

long long ztrsv_(char *uplo, char *trans, char *diag, long long *n, dcomplex *a,
           long long *lda, dcomplex *x, long long *incx);
