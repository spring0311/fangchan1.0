package com.example.fangchan.app.service.impl;

import com.example.fangchan.app.entity.TScore;
import com.example.fangchan.app.mapper.TScoreMapper;
import com.example.fangchan.app.service.TScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weizihao
 * @since 2020-10-24
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TScoreServiceImpl extends ServiceImpl<TScoreMapper, TScore> implements TScoreService {

}
