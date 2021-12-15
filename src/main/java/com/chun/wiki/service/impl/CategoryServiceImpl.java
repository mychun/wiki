package com.chun.wiki.service.impl;

import com.chun.wiki.domain.Category;
import com.chun.wiki.mapper.CategoryMapper;
import com.chun.wiki.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chun
 * @since 2021-12-15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
