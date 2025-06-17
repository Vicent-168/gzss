package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.utils.DataUtil;
import com.shdata.health.gzss.common.utils.UserUtils;
import com.shdata.health.gzss.sys.entity.DaSftx;
import com.shdata.health.gzss.sys.mapper.DaSftxMapper;
import com.shdata.health.gzss.sys.mapper.SfGmdjcMapper;
import com.shdata.health.gzss.sys.mapper.SfXshjcMapper;
import com.shdata.health.gzss.sys.vo.DaSftxSearchVo;
import com.shdata.health.gzss.sys.vo.DaSftxVo;
import com.shdata.health.gzss.sys.vo.SfGmdjctxVo;
import com.shdata.health.gzss.sys.vo.SfXshjctxVo;
import com.shdata.health.gzss.sys.vo.resp.DaSftxDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 档案随访提醒  Service服务
 *
 * @author 丁文韬
 * @date 2024-08-06
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class DaSftxService extends BaseService<DaSftxMapper, DaSftx> {
    @Autowired
    private SfGmdjcMapper sfGmdjcMapper;
    @Autowired
    private SfXshjcMapper sfXshjcMapper;

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(DaSftxVo vo) {
        validate(vo);
        DaSftx po = null;
        if (StrUtil.isNotBlank(vo.getId())) {
            po = getById(vo.getId());
        }
        if (po == null) { // 新增
            po = BeanUtil.toBean(vo, DaSftx.class);
            po.setId(IdUtil.objectId());
            po.init();
            save(po);
            return "保存成功";
        } else {
            BeanUtil.copyProperties(vo, po);
            po.initByUpdate();
            updateById(po);
            return "更新成功";
        }
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(DaSftxVo vo) {
        if (vo == null) {
            throw new ParameterException("参数不能为空");
        }

    }

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return
     */
    public PageData<DaSftxVo> findByPage(DaSftxSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 随访提醒——多条件的列表分页查询查询
     */
    public PageData<DaSftxDto> findSftxDataByCriterias(DaSftxSearchVo vo) {
        PageData<DaSftxDto> pageData = new PageData<>();

        if (vo.getGlyljg() == null || vo.getGlyljg().equals("")) {
            vo.setGlyljg(UserUtils.getOrgan().getCode());
        }
        if (vo.getZrys() == null || vo.getZrys().equals("")) {
            vo.setZrys(UserUtils.getUser().getId());
        }
        if (vo.getWfsjCodes() == null || vo.getWfsjCodes().size() == 0) {
            vo.setWfsjCodes(Arrays.asList("01", "02", "03"));
        }
        if (vo.getTxnrCodes() == null || vo.getTxnrCodes().size() == 0) {
            vo.setTxnrCodes(Arrays.asList("01", "02"));
        }
        List<DaSftxDto> daSftxDtos = new ArrayList<>();
        // 骨密度检查过滤的数据
        List<SfGmdjctxVo> sfGmdjctxVos = sfGmdjcMapper.findSftxGmdDataByCriterias(vo);
        sfGmdjctxVos = (sfGmdjctxVos != null) ? sfGmdjctxVos : Collections.emptyList();
        List<String> zrysIds = new ArrayList<>();
        Map<String, SfXshjctxVo> zrysMap = new HashMap<>();
        // 依据未访问时间过滤数据
        List<SfGmdjctxVo> filteredGmdList = filterDataByWfsjCodes(sfGmdjctxVos, vo.getWfsjCodes());
        for (SfGmdjctxVo sfGmdjctxVo : filteredGmdList) {
            DaSftxDto daSftxDto = new DaSftxDto();
            // 给未访问天数赋值
            daSftxDto.setWfts(ChronoUnit.DAYS.between(sfGmdjctxVo.getJcrq().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()));
            // 设置DXA骨密度检查提醒
            daSftxDto.setTxnr("01");
            BeanUtil.copyProperties(sfGmdjctxVo, daSftxDto);
            daSftxDto.setJcrq(sfGmdjctxVo.getJcrqAsString());
            // 给年龄赋值
            try {
                daSftxDto.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            zrysIds.add(sfGmdjctxVo.getZrys());
            daSftxDtos.add(daSftxDto);
        }
        // 血生化过滤的数据
        List<SfXshjctxVo> sfXshjctxVos = sfXshjcMapper.findSftxXshDataByCriterias(vo);
        sfXshjctxVos = (sfXshjctxVos != null) ? sfXshjctxVos : Collections.emptyList();
        List<SfXshjctxVo> xshjctxVos = filterDataByWfsjCodes(sfXshjctxVos, vo.getWfsjCodes());

        for (SfXshjctxVo xshjctxVo : xshjctxVos) {
            DaSftxDto daSftxDto = new DaSftxDto();
            // 给未访问天数赋值
            daSftxDto.setWfts(ChronoUnit.DAYS.between(xshjctxVo.getJcrq().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()));
            // 设置随访血生化检查提醒
            daSftxDto.setTxnr("02");
            BeanUtil.copyProperties(xshjctxVo, daSftxDto);
            // 需要保留年月日的时间，并且转换为string类型的字符串
            daSftxDto.setJcrq(xshjctxVo.getJcrqAsString());
            // 给年龄赋值
            try {
                daSftxDto.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            zrysIds.add(xshjctxVo.getZrys());
            daSftxDtos.add(daSftxDto);
        }
        Map<String, String> userNameForMap = DataUtil.getUserNameForMap(zrysIds);
        if (daSftxDtos.size() > 0) {
            for (DaSftxDto daSftxDto : daSftxDtos) {
                String zrysName = userNameForMap.get(daSftxDto.getZrys());
                daSftxDto.setZrysName(zrysName);
            }
        }
        Map<String, DaSftxDto> uniquedaSftxDtoMap = new LinkedHashMap<>();
        // 依据 sfzh Jcrq 组合去重过滤
        if (daSftxDtos.size() > 0) {
            uniquedaSftxDtoMap = daSftxDtos.stream()
                    .collect(Collectors.toMap(
                            dto -> dto.getSfzh() + "_" + dto.getJcrq() + "_" + dto.getTxnr(),// 使用 sfzh 和 Jcrq 作为唯一键
                            dto -> dto, (existing, replacement) -> existing,// 如果有重复，保留原对象
                            LinkedHashMap::new));// 保持插入顺序
        }
        // 转换为去重后的列表
        List<DaSftxDto> uniqueDaSftxDtos = new ArrayList<>(uniquedaSftxDtoMap.values());
        // 针对提醒内容进行过滤
        uniqueDaSftxDtos = filterDataByTxnrCodes(uniqueDaSftxDtos, vo.getTxnrCodes());
        // 针对uniqueDaSftxDtos集合对象中的wfts字段进行倒叙排序
        uniqueDaSftxDtos.sort(Comparator.comparing(DaSftxDto::getWfts).reversed());
        // 分页处理
        long currentPage = vo.getCurrPage();
        long pageSize = vo.getPageSize();
        int totalItems = uniqueDaSftxDtos.size();
        int startItem = (int) ((currentPage - 1) * pageSize);
        int endItem = (int) Math.min(startItem + pageSize, totalItems);
        List<DaSftxDto> paginatedList = (startItem < endItem) ? uniqueDaSftxDtos.subList(startItem, endItem) : Collections.emptyList();
        pageData.setData(paginatedList);
        pageData.setPageSize(pageSize);
        pageData.setCurrPage(totalItems);
        pageData.setTotal(totalItems);
        // 确保页数正确计算
        pageData.setPages((totalItems + pageSize - 1) / pageSize);
        return pageData;
    }

    private List<DaSftxDto> filterDataByTxnrCodes(List<DaSftxDto> uniqueDaSftxDtos, List<String> txnrCodes) {
        if (uniqueDaSftxDtos.isEmpty()) {
            return Collections.emptyList();
        }
        List<DaSftxDto> daSftxDtos = uniqueDaSftxDtos.stream().filter(dto -> txnrCodes.contains(dto.getTxnr())).collect(Collectors.toList());
        return daSftxDtos;

    }

    // 过滤出符合未访问天数的数据
    private <T> List<T> filterDataByWfsjCodes(List<T> list, List<String> wfsjCodes) {
        return list.stream().filter(item -> {
            LocalDate date = null;
            if (item instanceof SfGmdjctxVo) {
                //  getDxaJcrq() 返回的是 java.util.Date
                Date dxaJcrqDate = ((SfGmdjctxVo) item).getJcrq();
                if (dxaJcrqDate != null) {
                    date = dxaJcrqDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
            } else if (item instanceof SfXshjctxVo) {
                //  getJyrq() 返回的是 java.util.Date
                Date jyrqDate = ((SfXshjctxVo) item).getJcrq();
                if (jyrqDate != null) {
                    date = jyrqDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
            }

            if (date == null) return false;
            long daysBetween = ChronoUnit.DAYS.between(date, LocalDate.now());
            if (wfsjCodes.size() == 1 && wfsjCodes.contains("01")) {
                return daysBetween >= 90 && daysBetween <= 183;
            } else if (wfsjCodes.size() == 1 && wfsjCodes.contains("02")) {
                return daysBetween >= 183 && daysBetween <= 365;
            } else if (wfsjCodes.size() == 1 && wfsjCodes.contains("03")) {
                return daysBetween >= 365;
            } else if (wfsjCodes.size() == 2 && wfsjCodes.contains("01") && wfsjCodes.contains("02")) {
                return daysBetween >= 90 && daysBetween <= 365;
            } else if (wfsjCodes.size() == 2 && wfsjCodes.contains("01") && wfsjCodes.contains("03")) {
                return (daysBetween >= 90 && daysBetween <= 183) || daysBetween >= 365;
            } else if (wfsjCodes.size() == 2 && wfsjCodes.contains("02") && wfsjCodes.contains("03")) {
                return daysBetween >= 183;
            } else if (wfsjCodes.size() == 3 && wfsjCodes.contains("01") && wfsjCodes.contains("02") && wfsjCodes.contains("03")) {
                return daysBetween >= 90;
            }
            return false;
        }).collect(Collectors.toList());
    }
}
