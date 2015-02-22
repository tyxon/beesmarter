set launchLine=java -jar ECServer_JRE7.jar 2> log

:launch
start /high /wait %launchLine%
goto launch
