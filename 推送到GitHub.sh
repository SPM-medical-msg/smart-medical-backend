#!/bin/bash
# 推送到GitHub的develop分支

cd /mnt/M/ZZX/Text/idea/DoctorControl

echo "=========================================="
echo "推送到GitHub - develop分支"
echo "=========================================="
echo ""

# 确认当前分支
CURRENT_BRANCH=$(git branch --show-current)
echo "当前分支: $CURRENT_BRANCH"

if [ "$CURRENT_BRANCH" != "develop" ]; then
    echo "切换到develop分支..."
    git checkout develop
fi

echo ""
echo "提交历史（最近10条）："
git log --oneline -10

echo ""
echo "准备推送到: https://github.com/SPM-medical-msg/SPM-medical-msg.git"
echo ""
read -p "确认推送？(y/n) " -n 1 -r
echo

if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "正在推送..."
    git push -u origin develop
    echo ""
    echo "✅ 推送完成！"
    echo ""
    echo "查看仓库: https://github.com/SPM-medical-msg/SPM-medical-msg"
else
    echo "已取消推送"
fi

