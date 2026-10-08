@echo off
cd /d "%~dp0"
javac -d out src\courier\*.java src\courier\clock\*.java src\courier\beacon\*.java src\courier\election\*.java src\courier\mutex\*.java src\courier\unit4\*.java
