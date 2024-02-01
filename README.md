# Introduction..
This is the back-end code repository for the REST API that takes a plain text file and find the top k most frequent words

# Tech Stack
Java 17
Spring Boot 3.2.2

# How to start the application
1. from IntelliJ IDEA

   go to the main class and click on start button

   ![img.png](img.png)

2. from cmd

   navigate to the project directory from cmd

   run the cmd -> mvn spring-boot:run

# How to run the tests

1. open the project from IntelliJ IDEA 

2. go to the test package

   ![img_1.png](img_1.png)

3. start the tests by clicking on start button

   ![img_2.png](img_2.png)

# Documentation
http://localhost:8080/swagger-ui/index.html

# Processing Method
1. Validations
   basic validations for the request 

2. Download
   text file will download from http url and saved to local machine
   if file is too large, this will take some time. so user will need to wait some time.
   to avoid that, we can improve this to process will continue in background thread and user will notify later on email with details.

3. FileProcessor
   this will check previous availability of same processed file in cache and if existed, return available data. otherwise will continue to calculation
   in the algorithm file will be processed line by line. so it will not be saved in memory. this will improve the performance and can be process large files.
   read line will split to words and will only filter words. then word count will capture in a hash map.
   finally will process the map, it will sort by word count and get the top k amount of words and return
