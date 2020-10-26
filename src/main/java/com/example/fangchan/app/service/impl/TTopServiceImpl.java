package com.example.fangchan.app.service.impl;

import com.example.fangchan.app.entity.TTop;
import com.example.fangchan.app.mapper.TTopMapper;
import com.example.fangchan.app.service.TTopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author weizihao
 * @since 2020-10-26
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TTopServiceImpl extends ServiceImpl<TTopMapper, TTop> implements TTopService {

}
