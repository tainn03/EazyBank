@echo off
echo Building all microservices with Jib...

echo.
echo Building configserver...
cd /d d:\workspace\MSA\v2-spring-cloud-config\configserver
call mvn clean package jib:dockerBuild -DskipTests
if %errorlevel% neq 0 goto error

echo.
echo Building eurekaserver...
cd /d d:\workspace\MSA\v2-spring-cloud-config\eurekaserver  
call mvn clean package jib:dockerBuild -DskipTests
if %errorlevel% neq 0 goto error

echo.
echo Building accounts...
cd /d d:\workspace\MSA\v2-spring-cloud-config\accounts
call mvn clean package jib:dockerBuild -DskipTests
if %errorlevel% neq 0 goto error

echo.
echo Building loans...
cd /d d:\workspace\MSA\v2-spring-cloud-config\loans
call mvn clean package jib:dockerBuild -DskipTests
if %errorlevel% neq 0 goto error

echo.
echo Building cards...
cd /d d:\workspace\MSA\v2-spring-cloud-config\cards
call mvn clean package jib:dockerBuild -DskipTests
if %errorlevel% neq 0 goto error

echo.
echo Building gatewayserver...
cd /d d:\workspace\MSA\v2-spring-cloud-config\gatewayserver
call mvn clean package jib:dockerBuild -DskipTests
if %errorlevel% neq 0 goto error

echo.
echo All microservices built successfully!
echo.
echo Running docker-compose...
cd /d d:\workspace\MSA\v2-spring-cloud-config\docker-compose\default
docker-compose up -d
goto end

:error
echo.
echo Build failed with error code %errorlevel%
exit /b %errorlevel%

:end
echo.
echo Process completed successfully!
