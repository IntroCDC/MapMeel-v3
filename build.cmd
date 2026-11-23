@echo off
rem Compila o MapMeel v3 e copia para F:\Organizar\servers\MMv3\plugins
rem Defina NOPAUSE=1 para nao pausar no final.
powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0build.ps1" %*
set CODE=%ERRORLEVEL%
if not "%NOPAUSE%"=="1" pause
exit /b %CODE%
