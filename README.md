# Java Project for Compact Programming Course - Team 13 - Java-CPC

# Home Task 1 Description:
Create the class for exception handling base on the Capstone project.

| Task | Assignee | Matrikel-Nr | Demonstration link |
| -------- | -------- | -------- | -------- |
a. Handling Multiple Exceptions | Nhat Quang Nguyen | 7219288 | https://youtu.be/UkRASlcOeUA |
b. Re-throwing Exceptions | Nhat Lam Nguyen | 7219037 | [drive link](https://drive.google.com/file/d/17FEiHO4Bsbj-lorNGRmnLRRh38OwNMfC/view?usp=sharing) |
c. Resource Management: | Akash Cuntur Shrinivasmurthy | 721964 | [YouTube](https://youtu.be/r4N748miUKs) |
d. Chaining Exceptions| Anguiga Hermann | 7218003| https://youtu.be/1mKDkRs0pDU

# Hometask 2 Description: 
Basic I/O:
Questions Answer: 
1. What class and method would you use to read a 
few pieces of data that are at known positions near 
the end of a large file?

 Files.newByteChannel returns an instance of SeekableByteChannel which allows you to read from (or write to) any position in the file.

2. When invoking format, what is the best way to 
indicate a new line?

Use the %n conversion — the \n escape is not platform independent!

3. How would you determine the MIME type of a 
file?

The Files.probeContentType method uses the platform's underlying file type detector to evaluate and return the MIME type.

4. What method(s) would you use to determine 
whether a file is a symbolic link?

You would use the Files.isSymbolicLink method.


Youtube Link for a+b: https://youtu.be/HWeQnS0kvxk 

Link for the Youtube video Hermann A: https://youtu.be/uHVh92hpZTk
