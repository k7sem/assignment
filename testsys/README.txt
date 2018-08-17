This project intends to demonstrate the solution of interview assignment Online Test system.


The main part is implements a way to submmit code to backend from webpage and run those code at backend. Then, push the reuslt back to frontend. 




Solution Steps:

1. Create .java file with code content.  the code content were post from frontend.
2. Execute javac xxx.java through JAVA Runntime.exec() API. to generate .class file.
3. Execute command like 'cmd cp.bat' to invoke external bat file.
    The bat file content will be following
     > cd C:\\bat ' switch to work folder
     > java Test >> r3.txt  ' run java class and save result to txt file
4. Then,  read r3.txt file, and sending the file content to frontend through websocket.