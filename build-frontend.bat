call yarn --cwd web-vue install
if errorlevel 1 exit /B 1
call yarn --cwd web-vue run build
if errorlevel 1 exit /B 1
