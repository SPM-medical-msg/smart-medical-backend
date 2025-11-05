#!/bin/bash
# 推送到GitHub - smart-medical-backend仓库

cd /mnt/M/ZZX/Text/idea/DoctorControl

echo "=========================================="
echo "推送到GitHub - smart-medical-backend"
echo "=========================================="
echo ""

# 更新远程仓库地址
GIT_REPO="https://github.com/SPM-medical-msg/smart-medical-backend.git"
git remote set-url origin "$GIT_REPO"

echo "远程仓库地址:"
git remote -v
echo ""

# 确认当前分支
CURRENT_BRANCH=$(git branch --show-current)
echo "当前分支: $CURRENT_BRANCH"

if [ "$CURRENT_BRANCH" != "develop" ]; then
    echo "切换到develop分支..."
    git checkout develop
fi

echo ""
echo "提交历史（最近7条）："
git log --oneline -7

echo ""
echo "准备推送到: $GIT_REPO"
echo ""
echo "⚠️  注意：推送需要GitHub认证"
echo "   如果使用HTTPS，需要输入用户名和Personal Access Token"
echo "   如果使用SSH，需要配置SSH密钥"
echo ""

read -p "是否现在推送？(y/n) " -n 1 -r
echo

if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "正在推送..."
    git push -u origin develop
    
    if [ $? -eq 0 ]; then
        echo ""
        echo "✅ 推送成功！"
        echo ""
        echo "查看仓库: https://github.com/SPM-medical-msg/smart-medical-backend"
    else
        echo ""
        echo "❌ 推送失败，可能的原因："
        echo "   1. 需要GitHub认证（用户名和Token）"
        echo "   2. 仓库不存在或无权限"
        echo ""
        echo "解决方法："
        echo "   1. 使用Personal Access Token代替密码"
        echo "   2. 或配置SSH密钥"
        echo "   3. 或手动在GitHub上操作"
    fi
else
    echo "已取消推送"
    echo ""
    echo "可以稍后手动执行:"
    echo "  git push -u origin develop"
fi

