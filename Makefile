run:
	javac -cp . -sourcepath src -d out/ src/*.java
	java -Dfile.encoding=UTF-8 -cp out Main
