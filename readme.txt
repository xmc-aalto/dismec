DiSMEC - Distributed Sparse Machines for Extreme multi-label classification problems 

=====================
Some features of DiSMEC
=====================
- C++ code built over the Liblinear-64 [1] bit code-base to handle multi-label datasets in LibSVM format, and handle parallel training and prediction using openMP

- Handles extreme multi-label classification problems consisting of millions of labels in datasets which can be downloaded from Extreme Classification Repository 

- Takes only a few minutes on EURLex-4K (eurlex) dataset consisting of about 4,000 labels and a few hours on WikiLSHTC-325K datasets consisting of about 325,000 labels

- Learns models in the batch of 1000 (by default, can be changed to suit your settings) labels

- Allows two-folds parallel training (a) A single batch of labels (say 1000 labels) is learnt in parallel using openMP while exploiting multiple cores on a single machine/node/computer (b) Can be invoked on multiple computers/machines/nodes simultaneously such that the successive launch starts from the next batch of 1000 labels(see -i option below)

- Tested on 64-bit Ubuntu only, not not very clean code but runs

Detailed instructions for using DiSMEC are given below.  These instructions are to reproduce the results for Eurlex data only.
For more details, check our paper "DiSMEC - Distributed Sparse Machines for Extreme Multi-label Classification" [2] , or email me if anything is unclear

===================
CONTENTS
===================
There are directories
1) ./dismec contains the DiSMEC code

2) ./eurlex consists of data for EURLex-4k downloaded from XMC repository

3) ./prepostprocessing consists of Java code for (a) pre-processing data to get into tf-idf format and remapping labels and features, and (b) Evaluation of precision@k and nDCG@k corresponding to the prediction results.

===================
USAGE
===================

Data Pre-processing (in Java)
0) Download the eurlex dataset from XMC repository, and remove the first line from the train and test files downloaded, call them train.txt and test.txt

1) Change feature ID's so that they start from 1..to..number_of_features, using the code provided in FeatureRemapper.java using the following command
javac FeatureRemapper.java
java FeatureRemapper ../eurlex/train.txt ../eurlex/train-remapped.txt ../eurlex/test.txt ../eurlex/test-remapped.txt

2) Convert to tf-idf format using the code in file TfIdfCalculator.java
javac TfIdfCalculator.java
java TfIdfCalculator ../eurlex/train-remapped.txt ../eurlex/train-remapped-tfidf.txt ../eurlex/test-remapped.txt ../eurlex/test-remapped-tfidf.txt

3) Change labels ID's so that they also start from 1..to..number_of_labels, using the code provided in LabelRelabeler.java 
javac LabelRelabeler.java 
java LabelRelabeler ../eurlex/train-remapped-tfidf.txt ../eurlex/train-remapped-tfidf-relabeled.txt ../eurlex/test-remapped-tfidf.txt ../eurlex/test-remapped-tfidf-relabeled.txt ../eurlex/label-mappings.txt

===================

Building DiSMEC
Just run make command in the ../dismec/ directory. This will build the train and predict executable

===================

Training model with DiSMEC (in C++)

Training models with DiSMEC with 2-level parallelism
// make the directory to write the model files
mkdir ../eurlex/models 
../dismec/train -s 2 -B 1 -i 1 ../eurlex/train-remapped-tfidf-relabeled.txt ../eurlex/models/1.model
../dismec/train -s 2 -B 1 -i 2 ../eurlex/train-remapped-tfidf-relabeled.txt ../eurlex/models/2.model
../dismec/train -s 2 -B 1 -i 3 ../eurlex/train-remapped-tfidf-relabeled.txt ../eurlex/models/3.model
../dismec/train -s 2 -B 1 -i 4 ../eurlex/train-remapped-tfidf-relabeled.txt ../eurlex/models/4.model

If run in parallel on multiple machines, the models might take around 5 mins to build on this dataset.

===================

Predicting with DiSMEC in parallel (in C++)

Since the base Liblinear code does not understand the comma separated labels. We need to zero out labels in the test file, and put that in a separate file (called GS.txt) consisting of only the labels. 
javac LabelExtractor.java
java LabelExtractor ../eurlex/test-remapped-tfidf-relabeled.txt ../eurlex/test-remapped-tfidf-relabeled-zeroed.txt ../eurlex/GS.txt

 
mkdir ../eurlex/output // make the directory to write output files
../dismec/predict ../eurlex/test-remapped-tfidf-relabeled-zeroed.txt ../eurlex/models/1.model ../eurlex/output/1.out
../dismec/predict ../eurlex/test-remapped-tfidf-relabeled-zeroed.txt ../eurlex/models/2.model ../eurlex/output/2.out
../dismec/predict ../eurlex/test-remapped-tfidf-relabeled-zeroed.txt ../eurlex/models/3.model ../eurlex/output/3.out
../dismec/predict ../eurlex/test-remapped-tfidf-relabeled-zeroed.txt ../eurlex/models/4.model ../eurlex/output/4.out

===================

Performance evaluation (in Java)

Computation of Precision@k and nDCG@k for k=1,3,5
Now, we need to get final top-1, top-3 and top-5 from the output of individual models. This is done by the following :

****** IMPORTANT : Change the number of test points in DistributedPredictor.java (at line number 138) based on number of test points in the datasets ******

mkdir ../eurlex/final-output
javac DistributedPredictor.java
java DistributedPredictor ../eurlex/output/ ../eurlex/final-output/top1.out ../eurlex/final-output/top3.out ../eurlex/final-output/top5.out

javac MultiLabelMetrics.java
java MultiLabelMetrics ../eurlex/GS.txt ../eurlex/final-output/top1.out ../eurlex/final-output/top3.out ../eurlex/final-output/top5.out

The expected output should be something like this:
 precision at 1 is 82.51380489087562
 precision at 3 is 69.48023490227014
 precision at 5 is 57.94372863528793

 ndcg at 1 is 82.51380489087562
 ndcg at 3 is 72.89528751985883
 ndcg at 5 is 67.05303357275555

===================

Other Datasets from XMC repository:
If you would like to build for another dataset, please change the number of labels and replace with appropriate number at line number 2301, and then run make

long long nr_class = 3786; //3786 for eurlex

If you would like to change the batch size (1000 by default) for you settings, please replace with appropriate number at line number 2427, and then run make

int batchSize = 1000;

======================
References
[1] R.-E. Fan, K.-W. Chang, C.-J. Hsieh, X.-R. Wang, and C.-J. Lin. LIBLINEAR: A library for large linear classification Journal of Machine Learning Research 9(2008), 1871-1874.
[2] R. Babbar, B. Schölkopf. DiSMEC: Distributed Sparse Machines for Extreme Multi-label Classification, WSDM 2017
