
@echo off

call env.bat

call clean.bat

javac -cp %sourcedir%;%tplibdir%  -d %classdir%  %sourcedir%\App.java