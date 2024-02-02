# Introduction.
This is the back-end code repository for the REST API that takes a plain text file and find the top k most frequent words

# Tech Stack
Java 17
Spring Boot 3.2.2

# How to start the application
1. Download the repo
   https://github.com/amiladarshana1994/filereader.git

2. from IntelliJ IDEA

   1. open from IntelliJ IDEA
   
   2. go to the main class and click on start button

      ![img.png](img.png)

3. from cmd

   1. navigate to the project directory from cmd
   
   2. run the cmd -> mvn spring-boot:run

# How to run the tests

1. from IntelliJ IDEA

   1. open the project from IntelliJ IDEA

   2. go to the test package

      ![img_1.png](img_1.png)

   3. start the tests by clicking on start button

      ![img_2.png](img_2.png)
   
2. from cmd

   1. navigate to the project directory from cmd
   
   2. run the cmd -> mvn clean install
   
3. test cases
   1. countWordsForValidUrl_smallFile_withContent_receivedResultIsCorrect
      1. sample file stored in google drive
      2. correct response json stored in sample_1_response.json
      
   2. countWordsForValidUrl_largeFile_receivedResultIsCorrect
      1. sample file stored in aws s3 bucket
      2. size is larger than 1GB. so it will take some time to run this test case
      3. correct response json stored in sample_2_response.json
      
   3. countWordsForValidUrl_smallFile_withoutContent_receivedResultIsCorrect
      1. sample file stored in google drive
      2. correct response json stored in sample_3_response.json
      
   4. countWordsForValidUrl_smallFile_withContent_frequentWordCountLargerThanAvailable_receivedResultIsCorrect
      1. this will check when requested word count larger than available word count scenario
   
# Documentation
http://localhost:8080/swagger-ui/index.html

![img_3.png](img_3.png)

# Processing Method
1. Validations
   1. basic validations for the request 

2. Download
   1. text file will download from http url and saved to local machine
   2. if file is too large, this will take some time. so user will need to wait some time.
   3. to avoid that, we can improve this to process will continue in background thread and user will notify later on email with details.

3. FileProcessor
   1. this will check previous availability of same processed file in cache and if existed, return available data. otherwise will continue to calculation
   2. use caffeine cache library for caching
   3. in the algorithm file will be processed line by line. so whole file will not be saved in memory. this will improve the performance and can be process large files.
   4. read line will split to words by spaces and will only filter words. 
   5. then word count will capture in a hash map.
   6. finally will process the map, it will sort by word count and get the top k amount of words and return

# Improvements

1. Background file processing for large files
2. Implement user authentication and authorization
3. File type validation to only process text files