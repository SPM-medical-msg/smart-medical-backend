#!/bin/bash
# 一键推送脚本 - 支持多种认证方式

cd /mnt/M/ZZX/Text/idea/DoctorControl

echo "=========================================="
echo "推送到GitHub - smart-medical-backend"
echo "=========================================="
echo ""

# 确认远程仓库
git remote set-url origin https://github.com/SPM-medical-msg/smart-medical-backend.git
echo "远程仓库:"
git remote -v
echo ""

# 确认分支
CURRENT_BRANCH=$(git branch --show-current)
if [ "$CURRENT_BRANCH" != "develop" ]; then
    git checkout develop
fi

echo "当前分支: $(git branch --show-current)"
echo ""
echo "提交历史（最近7条）："
git log --oneline -7
echo ""

echo "=========================================="
echo "选择认证方式："
echo "=========================================="
echo "1. HTTPS（使用用户名和Personal Access Token）"
echo "2. SSH（如果已配置SSH密钥）"
echo "3. 查看帮助文档"
echo ""
read -p "请选择 (1/2/3): " choice

case $choice in
    1)
        echo ""
        echo "使用HTTPS推送..."
        echo "提示: 用户名输入GitHub用户名，密码输入Personal Access Token"
        echo ""
        git push -u origin develop
        ;;
    2)
        echo ""
        echo "切换到SSH方式..."
        git remote set-url origin git@github.com:SPM-medical-msg/smart-medical-backend.git
        echo "使用SSH推送..."
        git push -u origin develop
        ;;
    3)
        echo ""
        echo "查看详细说明:"
        cat GitHub认证设置.md
        ;;
    *)
        echo "无效选择"
        exit 1
        ;;
esac

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ 推送成功！"
    echo ""
    echo "查看仓库: https://github.com/SPM-medical-msg/smart-medical-backend"
    echo "分支: develop"
else
    echo ""
    echo "❌ 推送失败"
    echo ""
    echo "请查看 GitHub认证设置.md 了解详细设置方法"
fi

