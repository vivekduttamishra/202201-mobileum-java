
@echo off

call env.bat

call clean.bat

javac -cp %sourcedir%  -d %classdir%  %sourcedir%\App.java