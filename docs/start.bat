@echo off
set launchLine=java -jar ECServer_JRE7.jar

:launch
start /high /wait %launchLine%
goto launch
