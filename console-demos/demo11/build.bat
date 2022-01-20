
@echo off

call env.bat

:: compile the code first. it will generate the class directory

javac -cp %sourcedir%;%tplibdir%  -d %classdir%  %sourcedir%\in\conceptarchitect\app\App.java


:: create distdir to hold your fa.jar and fa.bat
md %distdir%


:: move to classdir to hold  to create the jar file
cd %classdir%

:: create the jar file in the distribution directoy for the class directory

jar cfm %distdir%\fa.jar %workdir%\manifest.txt  .

:: go to the workspace root folder
cd %distdir%


:: generate a batch file called fa.bat to run our project fa---> furnitureapp

echo @java -cp fa.jar in.conceptarchitect.app.App > %distdir%\fa.bat
echo @java -jar fa.jar >%distdir%\fa2.bat
:: We don't need our classes folder any more 

rd %classdir% /q/s





