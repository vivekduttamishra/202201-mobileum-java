
@echo off

call env.bat

:: compile the code first. it will generate the class directory

javac -cp %sourcedir%;%tplibdir%  -d %classdir%  %sourcedir%\in\conceptarchitect\app\App.java


:: create distdir to hold your fa.jar and fa.bat
md %distdir%


:: move to classdir to hold  to create the jar file
cd %classdir%

:: create the jar file in the distribution directoy for the class directory

jar cfe %distdir%\fa2.jar  in.conceptarchitect.app.App .

:: go to the workspace root folder
cd %distdir%


:: generate a batch file called fa.bat to run our project fa---> furnitureapp

echo @java -jar fa2.jar >%distdir%\fa2.bat
:: We don't need our classes folder any more 

rd %classdir% /q/s





