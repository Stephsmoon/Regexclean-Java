echo "Compile and Running the Java Files"
javac -cp lib/commons-io-2.15.1/commons-io-2.15.1.jar src/wordfreq.java src/bookdata.java
java -cp lib/commons-io-2.15.1/commons-io-2.15.1.jar:src src/wordfreq.java