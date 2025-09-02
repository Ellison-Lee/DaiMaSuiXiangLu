@echo off
chcp 65001 > nul
echo 按回车键执行 git status
pause > nul
git status
echo ========================

echo 按回车键执行 git add .
pause > nul
git add .
echo ========================

echo 按回车键执行 git commit -m update
pause > nul
git commit -m "update"
echo ========================

echo 按回车键执行 git push
pause > nul
git push
echo ========================

echo 所有Git操作已完成！
pause > nul
