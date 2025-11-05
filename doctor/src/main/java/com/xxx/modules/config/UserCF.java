package com.xxx.modules.config;

import java.util.*;

public class UserCF {

    // 用户-物品评分矩阵：<用户ID, <物品ID, 评分>>
    private Map<Integer, Map<Integer, Double>> userItemMatrix;
    // 用户相似度矩阵：<用户ID, <用户ID, 相似度>>
    private Map<Integer, Map<Integer, Double>> userSimilarityMatrix;

    public UserCF(Map<Integer, Map<Integer, Double>> userItemMatrix) {
        this.userItemMatrix = userItemMatrix;
        this.userSimilarityMatrix = new HashMap<>();
        calculateAllUserSimilarities();
    }

    /**
     * 计算所有用户之间的相似度（余弦相似度）
     */
    private void calculateAllUserSimilarities() {
        List<Integer> users = new ArrayList<>(userItemMatrix.keySet());
        for (int i = 0; i < users.size(); i++) {
            Integer u1 = users.get(i);
            for (int j = i + 1; j < users.size(); j++) {
                Integer u2 = users.get(j);
                double sim = cosineSimilarity(u1, u2);
                // 存储双向相似度
                userSimilarityMatrix.computeIfAbsent(u1, k -> new HashMap<>()).put(u2, sim);
                userSimilarityMatrix.computeIfAbsent(u2, k -> new HashMap<>()).put(u1, sim);
            }
        }
    }

    /**
     * 计算两个用户的余弦相似度
     */
    private double cosineSimilarity(Integer u1, Integer u2) {
        Map<Integer, Double> u1Ratings = userItemMatrix.get(u1);
        Map<Integer, Double> u2Ratings = userItemMatrix.get(u2);

        // 获取共同评分物品
        Set<Integer> commonItems = new HashSet<>(u1Ratings.keySet());
        commonItems.retainAll(u2Ratings.keySet());
        if (commonItems.isEmpty()) return 0.0;

        // 计算点积和模长
        double dotProduct = 0.0;
        double normU1 = 0.0;
        double normU2 = 0.0;

        for (Integer item : commonItems) {
            double score1 = u1Ratings.get(item);
            double score2 = u2Ratings.get(item);
            dotProduct += score1 * score2;
            normU1 += score1 * score1;
            normU2 += score2 * score2;
        }

        if (normU1 == 0 || normU2 == 0) return 0.0;
        return dotProduct / (Math.sqrt(normU1) * Math.sqrt(normU2));
    }

    /**
     * 生成推荐列表
     * @param targetUserId 目标用户ID
     * @param k 选取最相似的k个用户
     * @param topN 返回前N个推荐物品
     * @return 推荐物品列表（按评分降序）
     */
    public List<Integer> recommend(Integer targetUserId, int k, int topN) {
        // 获取目标用户未评分的物品
        Set<Integer> targetRatedItems = userItemMatrix.get(targetUserId).keySet();
        Set<Integer> allItems = new HashSet<>();
        for (Map<Integer, Double> ratings : userItemMatrix.values()) {
            allItems.addAll(ratings.keySet());
        }
        Set<Integer> unratedItems = new HashSet<>(allItems);
        unratedItems.removeAll(targetRatedItems);
        if (unratedItems.isEmpty()) return Collections.emptyList();

        // 获取相似度最高的k个用户
        Map<Integer, Double> similarUsers = userSimilarityMatrix.getOrDefault(targetUserId, new HashMap<>());
        PriorityQueue<Map.Entry<Integer, Double>> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );
        pq.addAll(similarUsers.entrySet());
        List<Integer> topKUsers = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            topKUsers.add(pq.poll().getKey());
        }

        // 计算推荐分数
        Map<Integer, Double> itemScores = new HashMap<>();
        for (Integer user : topKUsers) {
            double sim = similarUsers.get(user);
            Map<Integer, Double> ratings = userItemMatrix.get(user);
            for (Integer item : unratedItems) {
                if (ratings.containsKey(item)) {
                    double score = ratings.get(item);
                    itemScores.put(item, itemScores.getOrDefault(item, 0.0) + sim * score);
                }
            }
        }

        // 按分数排序
        List<Map.Entry<Integer, Double>> sortedItems = new ArrayList<>(itemScores.entrySet());
        sortedItems.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // 返回topN
        List<Integer> recommendations = new ArrayList<>();
        for (int i = 0; i < Math.min(topN, sortedItems.size()); i++) {
            recommendations.add(sortedItems.get(i).getKey());
        }
        return recommendations;
    }

    public static void main(String[] args) {
        // 示例数据
        Map<Integer, Map<Integer, Double>> data = new HashMap<>();
        Map<Integer,Double> map = new HashMap<>();
        map.put(1,3.0);
        map.put(2,4.0);
        map.put(3,2.0);
        data.put(1,map);

        Map<Integer,Double> map2 = new HashMap<>();
        map2.put(1,4.0);
        map2.put(2,3.0);
        map2.put(3,4.0);
        map2.put(5,5.0);
        data.put(2,map2);


        Map<Integer,Double> map3 = new HashMap<>();
        map3.put(1,2.0);
        map3.put(2,3.0);
        map3.put(4,3.0);
        data.put(3,map3);


        UserCF userCF = new UserCF(data);
        List<Integer> recommendations = userCF.recommend(3, 2, 3);
        System.out.println("推荐物品列表：" + recommendations);
    }
}
