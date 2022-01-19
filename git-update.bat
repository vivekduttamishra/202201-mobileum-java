@echo off

git add .
echo committing: %*
git commit -m "%*"
git push -u origin main
