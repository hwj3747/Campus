package com.haiyangroup.scampus.view;

import com.haiyangroup.scampus.entity.ScoreEntity;

import java.util.ArrayList;

/**
 * 实现MVP设计模式P与V交互的接口
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public interface ScoreResultView {
    void showScoreResult(ArrayList<ScoreEntity> entities);
}
