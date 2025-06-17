package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.common.utils.DataUtil;
import com.shdata.health.gzss.common.utils.UserUtils;
import com.shdata.health.gzss.sys.entity.SfFyxx;
import com.shdata.health.gzss.sys.mapper.DaJbqkMapper;
import com.shdata.health.gzss.sys.mapper.SfFyxxMapper;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.bo.SfFyxxBo;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkVo;
import com.shdata.health.gzss.sys.vo.resp.PgSffyDto;
import com.shdata.health.gzss.sys.vo.resp.SfFyDto;
import com.shdata.health.gzss.sys.vo.resp.SfFyxxDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 随访服药信息  Service服务
 *
 * @author dwt
 * @date 2024-07-19
 */
@Service
@Transactional(readOnly = true)
public class SfFyxxService extends BaseService<SfFyxxMapper, SfFyxx> {
    @Autowired
    private SfFyxxMapper sfFyxxMapper;

    @Autowired
    private DictService dictService;

    @Autowired
    private DaJbqkMapper daJbqkMapper;
    @Autowired
    private PgJgService pgJgService;

    /**
     * 保存或更新
     *
     * @param bo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(SfFyxxBo bo) {
        String daId = bo.getDaId();
        Date cfrq = bo.getCfrq();
        String sfysid = bo.getSfysid();
//        List<String> userIds = new ArrayList<>();
//        userIds.add(sfysid);
//        Map<String, String> sfysidMap = DataUtil.getUserNameForMap(userIds);
        String sfys = bo.getSfys();
        String cfyljg = bo.getCfyljg();
        String sffs = bo.getSffs();
        String gljg = bo.getGljg();
        String ywblfy = bo.getYwblfy();
        List<SfFyxx> sfFyxxs = this.baseMapper.selectList(new LambdaQueryWrapper<SfFyxx>().eq(SfFyxx::getDaId, daId).eq(SfFyxx::getCfrq, cfrq).eq(SfFyxx::getDelFlag, "0"));
        Map<String, SfFyxx> sfFyxxMap = new HashMap<>();
        if (sfFyxxs != null && !sfFyxxs.isEmpty()) {
            sfFyxxMap = sfFyxxs.stream().collect(Collectors.toMap(SfFyxx::getId, Function.identity()));
        }
        List<SfFyDjxxVo> vos = bo.getSfFyxxVoList();
        if (vos == null || vos.isEmpty()) {
            // 删除所有得记录
            deleteUnprocessedRecords(sfFyxxMap);
            return "保存成功";
        }

        for (SfFyDjxxVo vo : vos) {
            validate(vo);
            String id = vo.getId();
            if (id == null || !sfFyxxMap.containsKey(id)) {
                // 新增去重
                /* List<SfFyxx> sfFyxxks = this.baseMapper.selectList(new LambdaQueryWrapper<SfFyxx>()
                        .eq(StrUtil.isNotBlank(daId), SfFyxx::getDaId, daId)
                        .eq(StrUtil.isNotBlank(vo.getYwfl()), SfFyxx::getYwfl, vo.getYwfl())
                        .eq(StrUtil.isNotBlank(vo.getYwdm()), SfFyxx::getYwdm, vo.getYwdm())
                        .eq(StrUtil.isNotBlank(vo.getJx()), SfFyxx::getJx, vo.getJx())
                        .eq(StrUtil.isNotBlank(vo.getGg()), SfFyxx::getGg, vo.getGg())
                        .eq(StrUtil.isNotBlank(vo.getDw()), SfFyxx::getDw, vo.getDw())
                        .eq(cfrq != null, SfFyxx::getCfrq, cfrq)
                        .eq(StrUtil.isNotBlank(cfyljg), SfFyxx::getCfyljg, cfyljg)
                        .eq(StrUtil.isNotBlank(sffs), SfFyxx::getSffs, sffs)
                        .eq(StrUtil.isNotBlank(sfysid), SfFyxx::getSfysid, sfysid)
                        .eq(StrUtil.isNotBlank(gljg), SfFyxx::getGljg, gljg)
                        .eq(StrUtil.isNotBlank(ywblfy), SfFyxx::getYwblfy, ywblfy)
                        .eq(SfFyxx::getDelFlag, "0"));
                if (sfFyxxks != null && !sfFyxxks.isEmpty()) {
                    throw new ParameterException("该档案已存在该随访服药记录");
                } */
                // 执行新增操作
                SfFyxx po = BeanUtil.toBean(vo, SfFyxx.class);
                po.setId(IdUtil.objectId());
                po.setDaId(daId);
                po.setCfrq(cfrq);
                po.setSfysid(sfysid);
                po.setCfyljg(cfyljg);
                po.setSffs(sffs);
                po.setSfys(sfys);
                po.setYwblfy(ywblfy);
                po.setGljg(gljg);
                po.init();
                save(po);
            } else {
                // 执行修改操作
                SfFyxx existingPo = sfFyxxMap.get(id);
                if (existingPo == null) {
                    throw new ParameterException("该随访服药记录不存在");
                }
                BeanUtil.copyProperties(vo, existingPo);
                existingPo.initByUpdate();
                updateById(existingPo);
                // 从map中移除已经处理的记录
                sfFyxxMap.remove(id);
            }
        }
        // 处理删除操作，剩余在map中的记录为需要删除得记录
        deleteUnprocessedRecords(sfFyxxMap);
        return "保存成功";
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(SfFyDjxxVo vo) {
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
    public PageData<SfFyxxVo> findByPage(SfFyxxSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 根据档案id查询日期分类下的随访服药信息
     *
     * @param
     * @return
     */
    public List<SfFyxxDto> getSfFysDataByDaId(SfFysSearchVo vo) {
        List<SfFyxxDto> sfFyxxDtos = new ArrayList<>();
        List<SfFyxxVo> sfFyxxVos = sfFyxxMapper.findSffsDataByDaId(vo);
        if (sfFyxxVos.isEmpty()) {
            return sfFyxxDtos;
        }
        // 处方医疗机构
        List<String> codeList = new ArrayList<>();

        HashMap<String, List<SfFyxxVo>> map = new HashMap<>();
        // 定义日期格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (SfFyxxVo sfFyxxVo : sfFyxxVos) {
            // 不良反应字典值赋值
            sfFyxxVo.setYwblfy(getDictName("YW", sfFyxxVo.getYwblfy()));
            // 根据数据字典给药物分类字段赋值上中文名称
            sfFyxxVo.setYwfl(getDictName("GBYWFL", sfFyxxVo.getYwfl()));
            codeList.add(sfFyxxVo.getCfyljg());
            Map<String, String> cfyljgMap = DataUtil.getOrganNameForMap(codeList);
            sfFyxxVo.setCfyljg(cfyljgMap.get(sfFyxxVo.getCfyljg()));
            // 将随访服药史根据处方日期进行关系绑定
            String cfrq = sfFyxxVo.getCfrq().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
            if (!map.containsKey(cfrq)) {
                map.put(cfrq, new ArrayList<>());
                map.get(cfrq).add(sfFyxxVo);
            }
        }
        // 将服药信息根据处方日期进行分组
        for (Map.Entry<String, List<SfFyxxVo>> stringListEntry : map.entrySet()) {
            SfFyxxDto sfFyxxDto = new SfFyxxDto()
                    .setCfrq(stringListEntry.getKey())
                    .setSfFyxxVos(stringListEntry.getValue());
            sfFyxxDtos.add(sfFyxxDto);
        }
        return sfFyxxDtos;
    }


    /**
     * 随访一览-服药信息的多条件查询功能
     */
    public PageData<SfFyDto> getSfFysDataByCriterias(SfFyxxSearchVo vo) {
        IPage<SfFyDto> ipage = sfFyxxMapper.findSfFyDataByCriterias(vo);
//        List<String> userIds = new ArrayList<>();
//        List<String> codeList = new ArrayList<>();
//        for (SfFyDto record : ipage.getRecords()) {
//            userIds.add(record.getSfysid());
//            codeList.add(record.getCfyljg());
//        }
//        Map<String, String> userMap = DataUtil.getUserNameForMap(userIds);
//        Map<String, String> jcyljgMap = DataUtil.getOrganNameForMap(codeList);
        for (SfFyDto record : ipage.getRecords()) {
//            record.setSfysid(userMap.get(record.getSfysid()));
//            record.setCfyljg(jcyljgMap.get(record.getCfyljg()));
            try {
                record.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return PageData.of(ipage);
    }


    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }

    /**
     * 评估结果-新建服药信息的查询功能
     */
    public List<PgSffyDto> getPgJgFysDataByCriterias(PgjgFyxxSearchVo vo) {
        List<PgSffyDto> results = new ArrayList<>();
        if (vo == null || vo.getDaId() == null) {
            throw new ParameterException("档案id参数不能为空");
        }

        LocalDateTime startTime;
        LocalDateTime endTime;

        // 如果前端未传递起始时间和结束时间，则使用默认值
        if (vo.getStartTime() != null && vo.getEndTime() != null) {
            if (vo.getStartTime().toInstant().isAfter(vo.getEndTime().toInstant())) {
                throw new ParameterException("开始时间不能大于结束时间");
            }
            startTime = LocalDateTime.ofInstant(vo.getStartTime().toInstant(), ZoneId.systemDefault());
            endTime = LocalDateTime.ofInstant(vo.getEndTime().toInstant(), ZoneId.systemDefault());
        } else {
            // 使用默认值
            endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
            startTime = endTime.minusYears(1);
        }

        try {
            // 将 LocalDateTime 转为 Date 以适配数据库查询
            Date startDate = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());

            LambdaQueryWrapper<SfFyxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SfFyxx::getDaId, vo.getDaId())
                    .between(startDate != null && endDate != null, SfFyxx::getCfrq, startDate, endDate)
                    .eq(SfFyxx::getDelFlag, "0")
                    .orderByDesc(SfFyxx::getCfrq);
            List<SfFyxx> sfFyxxs = this.baseMapper.selectList(lambdaQueryWrapper);

            // 处理药品信息
            Map<Date, List<SfFyxx>> visitsMap = new LinkedHashMap<>();
            for (SfFyxx sfFyxx : sfFyxxs) {
                visitsMap.computeIfAbsent(sfFyxx.getCfrq(), k -> new ArrayList<>()).add(sfFyxx);
            }

            List<Date> visitDates = new ArrayList<>(visitsMap.keySet());

            if (visitDates.size() == 1) {
                List<SfFyxx> lastVisitMedications = visitsMap.get(visitDates.get(0));
                List<PgSffyDto> resultList = new ArrayList<>();
                for (SfFyxx lastVisitMedication : lastVisitMedications) {
                    PgSffyDto pgSffyDto = new PgSffyDto();
                    pgSffyDto.setId(lastVisitMedication.getId())
                            .setDaId(lastVisitMedication.getDaId())
                            .setYpmc(lastVisitMedication.getYpmc())
                            .setCfrq(lastVisitMedication.getCfrq())
                            .setTzqk("新增"); // 新增
                    resultList.add(pgSffyDto);
                }
                return resultList;
            }

            if (visitDates.size() >= 2) {
                // 获取最近一次和最近第二次的药品目录集合
                List<SfFyxx> lastVisitMedications = visitsMap.get(visitDates.get(0));
                List<SfFyxx> secondLastVisitMedications = visitsMap.get(visitDates.get(1));

                // 对比两个随访药品目录的差异
                Map<String, PgSffyDto> medicationMap = new HashMap<>();
                // 处理最近第二次的药品信息（先放入地图中以便对比）
                for (SfFyxx secondLast : secondLastVisitMedications) {
                    PgSffyDto dto = new PgSffyDto();
                    dto.setId(secondLast.getId())
                            .setDaId(secondLast.getDaId())
                            .setYpmc(secondLast.getYpmc())
                            .setCfrq(secondLast.getCfrq());
                    // 最近第二次药品不打标签,将最近第二次的药品信息加入到药品地图中
                    medicationMap.put(secondLast.getYpmc(), dto);
                }
                // 第二步：标记停用的药品
                for (SfFyxx secondLast : secondLastVisitMedications) {
                    String medicationName = secondLast.getYpmc();
                    // 检查最近一次处方中是否有该药品
                    boolean existInlastvist = lastVisitMedications.stream().anyMatch(lastVisitMedication -> lastVisitMedication.getYpmc().equals(medicationName));
                    if (!existInlastvist) {
                        PgSffyDto pgSffyDto = new PgSffyDto();
                        pgSffyDto.setId(secondLast.getId())
                                .setDaId(secondLast.getDaId())
                                .setYpmc(medicationName)
                                .setTzqk("停用")
                                .setCfrq(visitDates.get(0)); // 设置为最新一次的日期
                        // 将最近第二次处方中停药的药品更新到medicationMap中
                        medicationMap.put(medicationName, pgSffyDto);
                    }


                }
                // 第三步：处理最新一次处方中药品是新增还是延续
                for (SfFyxx lastVisitMedication : lastVisitMedications) {
                    PgSffyDto pgSffyDto = new PgSffyDto();
                    pgSffyDto.setId(lastVisitMedication.getId())
                            .setDaId(lastVisitMedication.getDaId())
                            .setYpmc(lastVisitMedication.getYpmc())
                            .setCfrq(visitDates.get(0));// 设置为最新一次的日期
                    // 检查第二次处方中的药品是否存在于最新的一次处方中
                    boolean isInsecondLast = secondLastVisitMedications.stream().anyMatch(secondLastVisitMedication -> secondLastVisitMedication.getYpmc().equals(lastVisitMedication.getYpmc()));
                    if (isInsecondLast) {
                        pgSffyDto.setTzqk("延续");
                    } else {
                        pgSffyDto.setTzqk("新增");
                    }
                    // 更新medicationMap,确保最新的一次处方药品信息在其中
                    medicationMap.put(lastVisitMedication.getYpmc(), pgSffyDto);
                }
                results.addAll(medicationMap.values());
                // 将第二次处方中的药品信息添加到集合中并且返回
                for (SfFyxx secondLastVisitMedication : secondLastVisitMedications) {
                    PgSffyDto dto = new PgSffyDto();
                    dto.setId(secondLastVisitMedication.getId())
                            .setDaId(secondLastVisitMedication.getDaId())
                            .setYpmc(secondLastVisitMedication.getYpmc())
                            .setCfrq(secondLastVisitMedication.getCfrq());
                    results.add(dto);
                }
                // 返回结果列表，只对最新一次随访的药品进行标记
                return results;
            }
        } catch (Exception e) {
            throw new RuntimeException("时间转换出错", e);
        }
        return Collections.emptyList();
    }

    /**
     * 档案登记_历史服药信息数据的保存或更新
     *
     * @param bo 提交参数
     * @return
     */
    @Transactional
    public String historySaveOrUpdate(SfFyxxBo bo) {
        String daId = bo.getDaId();
        Date cfrq = bo.getCfrq();
        String sfysid = UserUtils.getUser().getId();
        List<String> userIds = new ArrayList<>();
        userIds.add(sfysid);
        Map<String, String> sfysidMap = DataUtil.getUserNameForMap(userIds);
        String sfys = sfysidMap.get(sfysid);
        String cfyljg = UserUtils.getOrgan().getCode();
        String sffs = bo.getSffs();
        // 如果有值就传值，如果没有值就赋值01
        if (bo.getSffs() == null) {
            sffs = "01";
        }
        String gljg = UserUtils.getOrgan().getCode();
        String ywblfy = bo.getYwblfy();
        List<SfFyDjxxVo> vos = bo.getSfFyxxVoList();
        SfFyxx po = null;
        List<SfFyxx> sfFyxxs = new ArrayList<>();
        // 获取当前数据库中与该档案ID登记前服药的相关的记录
        List<SfFyxxVo> sfFyxxVos = baseMapper.fetchHistoryFyxxDataByDaId(daId);
        if (!sfFyxxVos.isEmpty()) {
            for (SfFyxxVo sfFyxxVo : sfFyxxVos) {
                po = BeanUtil.copyProperties(sfFyxxVo, SfFyxx.class);
                sfFyxxs.add(po);
            }
        }
        Map<String, SfFyxx> sfFyxxsMap = new HashMap<>();
        if (sfFyxxs != null && !sfFyxxs.isEmpty()) {
            sfFyxxsMap = sfFyxxs.stream().collect(Collectors.toMap(SfFyxx::getId, Function.identity()));
        }
        // 集合为空删除档案下所有服药记录
        if (vos == null || vos.isEmpty()) {
            deleteUnprocessedRecords(sfFyxxsMap);
            return "保存成功";
        }
        for (SfFyDjxxVo vo : vos) {
            validate(vo);
            String id = vo.getId();
            if (id == null || !sfFyxxsMap.containsKey(id)) {
                // 获取档案的登记时间
                DaJbqkVo daJbqkVo = daJbqkMapper.findByDaId(daId);
                // Date cfrq = null;
                if (daJbqkVo != null) {
                    Date dadjsj = daJbqkVo.getDadjsj();
                    // 获取档案登记的前一天时间为处方时间
                    cfrq = DateUtil.offsetDay(dadjsj, -1);
                } else {
                    // 设置档案登记前一天的日期作为既往历史服药信息日期
                    LocalDate today = LocalDate.now();
                    LocalDate yesterday = today.minusDays(1);
                    cfrq = Date.from(yesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());
                }
                // 新增去重
                List<SfFyxx> sfFyxxks = this.baseMapper.selectList(new LambdaQueryWrapper<SfFyxx>()
                        .eq(StrUtil.isNotBlank(vo.getDaId()), SfFyxx::getDaId, vo.getDaId())
                        .eq(StrUtil.isNotBlank(vo.getYwdm()), SfFyxx::getYwdm, vo.getYwdm())
                        .eq(StrUtil.isNotBlank(vo.getJx()), SfFyxx::getJx, vo.getJx())
                        .eq(StrUtil.isNotBlank(vo.getGg()), SfFyxx::getGg, vo.getGg())
                        .eq(StrUtil.isNotBlank(vo.getYwfl()), SfFyxx::getYwfl, vo.getYwfl())
                        .eq(StrUtil.isNotBlank(vo.getDw()), SfFyxx::getDw, vo.getDw())
                        .eq(cfrq != null, SfFyxx::getCfrq, cfrq)
                        .eq(StrUtil.isNotBlank(vo.getSffs()), SfFyxx::getSffs, vo.getSffs())
                        .eq(StrUtil.isNotBlank(vo.getSfysid()), SfFyxx::getSfysid, vo.getSfysid())
                        .eq(StrUtil.isNotBlank(vo.getGljg()), SfFyxx::getGljg, vo.getGljg())
                        .eq(StrUtil.isNotBlank(vo.getYwblfy()), SfFyxx::getYwblfy, vo.getYwblfy())
                        .eq(SfFyxx::getCfrq, cfrq)
                        .eq(SfFyxx::getDelFlag, "0")
                );
                if (sfFyxxks.size() > 0) {
                    throw new ParameterException("该药品已存在,请勿重复添加");
                }

                // 执行新增操作
                po = BeanUtil.toBean(vo, SfFyxx.class);
                po.setId(IdUtil.objectId());
                po.setDaId(daId);
                po.setCfrq(cfrq);
                po.setSfysid(sfysid);
                po.setSfys(sfys);
                po.setSffs(sffs);
                po.setCfyljg(cfyljg);
                po.setGljg(gljg);
                po.init();
                save(po);
            } else {
                // 执行修改操作
                SfFyxx existingPo = sfFyxxsMap.get(id);
                if (existingPo == null) {
                    throw new ParameterException("该既往历史服药信息已不存在，无法进行修改操作");
                }
                BeanUtil.copyProperties(vo, existingPo);
                existingPo.initByUpdate();
                updateById(existingPo);
                // 从map中移除已经处理的记录
                sfFyxxsMap.remove(id);
            }
        }
        // 处理删除操作，剩余在map中的记录为需要删除得记录
        deleteUnprocessedRecords(sfFyxxsMap);
        return "保存成功";
    }

    private void deleteUnprocessedRecords(Map<String, SfFyxx> sfFyxxsMap) {
        if (!sfFyxxsMap.isEmpty()) {
            new ArrayList<>(sfFyxxsMap.keySet()).forEach(this.baseMapper::deleteById);
        }
    }


    /**
     * 通过档案ID查询建档前服药信息
     */
    public List<SfFyxxVo> fetchHistoryFyxxDataByDaId(String daId) {
        if (StrUtil.isBlank(daId)) {
            throw new ParameterException("档案ID不能为空");
        }
        List<SfFyxxVo> sfFyxxVos = baseMapper.fetchHistoryFyxxDataByDaId(daId);
//        if (sfFyxxVos != null && !sfFyxxVos.isEmpty()) {
//            for (SfFyxxVo sfFyxxVo : sfFyxxVos) {
//                sfFyxxVo.setSffs(getDictName("SFFS", sfFyxxVo.getSffs()));
//            }
//        }

        return sfFyxxVos;
    }

    /**
     * 评估结果-服药信息子页面的查询功能
     */
    public List<PgSffyDto> getPgJgFysDataByDaId(PgSearchVo vo) {
        List<PgSffyDto> results = new ArrayList<>();
        if (StrUtil.isBlank(vo.getDaId())) {
            throw new ParameterException("档案ID不能为空");
        }

        try {
            PgJgDataVo pgJgDataVo = pgJgService.getPgjgDataByDaIdandPgrq(vo);
            if (pgJgDataVo == null || pgJgDataVo.getPgJgVo() == null) {
                throw new ParameterException("未找到对应档案的数据");
            }
            PgJgVo pgJgVo = pgJgDataVo.getPgJgVo();
            Date fyxxks = pgJgVo.getFyxxks();
            Date fyxxjs = pgJgVo.getFyxxjs();

            if (fyxxks == null || fyxxjs == null) {
                throw new ParameterException("起止时间不能为空");
            }

            LambdaQueryWrapper<SfFyxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SfFyxx::getDaId, vo.getDaId())
                    .between(fyxxks != null && fyxxjs != null, SfFyxx::getCfrq, fyxxks, fyxxjs)
                    .eq(SfFyxx::getDelFlag, "0")
                    .orderByDesc(SfFyxx::getCfrq);
            List<SfFyxx> sfFyxxes = this.baseMapper.selectList(lambdaQueryWrapper);

            if (sfFyxxes.isEmpty()) {
                return Collections.emptyList();
            }

            // 处理药品信息
            Map<Date, List<SfFyxx>> visitsMap = new LinkedHashMap<>();
            for (SfFyxx sfFyxx : sfFyxxes) {
                visitsMap.computeIfAbsent(sfFyxx.getCfrq(), k -> new ArrayList<>()).add(sfFyxx);
            }

            List<Date> visitDates = new ArrayList<>(visitsMap.keySet());

            if (visitDates.size() == 1) {
                List<SfFyxx> lastVisitMedications = visitsMap.get(visitDates.get(0));
                for (SfFyxx lastVisitMedication : lastVisitMedications) {
                    PgSffyDto pgSffyDto = new PgSffyDto();
                    pgSffyDto.setId(lastVisitMedication.getId())
                            .setDaId(lastVisitMedication.getDaId())
                            .setYpmc(lastVisitMedication.getYpmc())
                            .setCfrq(lastVisitMedication.getCfrq())
                            .setTzqk("新增"); // 新增
                    results.add(pgSffyDto);
                }
                return results;
            } else if (visitDates.size() >= 2) {

                // 获取最近一次和最近第二次的药品目录集合
                List<SfFyxx> lastVisitMedications = visitsMap.get(visitDates.get(0));
                List<SfFyxx> secondLastVisitMedications = visitsMap.get(visitDates.get(1));

                // 对比两个随访药品目录的差异
                Map<String, PgSffyDto> medicationMap = new HashMap<>();
                // 处理最近第二次的药品信息（先放入地图中以便对比）
                for (SfFyxx secondLast : secondLastVisitMedications) {
                    PgSffyDto dto = new PgSffyDto();
                    dto.setId(secondLast.getId())
                            .setDaId(secondLast.getDaId())
                            .setYpmc(secondLast.getYpmc())
                            .setCfrq(secondLast.getCfrq());
                    // 最近第二次药品不打标签,将最近第二次的药品信息加入到药品地图中
                    medicationMap.put(secondLast.getYpmc(), dto);
                }
                // 第二步：标记停用的药品
                for (SfFyxx secondLast : secondLastVisitMedications) {
                    String medicationName = secondLast.getYpmc();
                    // 检查最近一次处方中是否有该药品
                    boolean existInlastvist = lastVisitMedications.stream().anyMatch(lastVisitMedication -> lastVisitMedication.getYpmc().equals(medicationName));
                    if (!existInlastvist) {
                        PgSffyDto pgSffyDto = new PgSffyDto();
                        pgSffyDto.setId(secondLast.getId())
                                .setDaId(secondLast.getDaId())
                                .setYpmc(medicationName)
                                .setTzqk("停用")
                                .setCfrq(visitDates.get(0)); // 设置为最新一次的日期
                        // 将最近第二次处方中停药的药品更新到medicationMap中
                        medicationMap.put(medicationName, pgSffyDto);
                    }
                }
                // 第三步：处理最新一次处方中药品是新增还是延续
                for (SfFyxx lastVisitMedication : lastVisitMedications) {
                    PgSffyDto pgSffyDto = new PgSffyDto();
                    pgSffyDto.setId(lastVisitMedication.getId())
                            .setDaId(lastVisitMedication.getDaId())
                            .setYpmc(lastVisitMedication.getYpmc())
                            .setCfrq(visitDates.get(0));// 设置为最新一次的日期
                    // 检查第二次处方中的药品是否存在于最新的一次处方中
                    boolean isInsecondLast = secondLastVisitMedications.stream().anyMatch(secondLastVisitMedication -> secondLastVisitMedication.getYpmc().equals(lastVisitMedication.getYpmc()));
                    if (isInsecondLast) {
                        pgSffyDto.setTzqk("延续");
                    } else {
                        pgSffyDto.setTzqk("新增");
                    }
                    // 更新medicationMap,确保最新的一次处方药品信息在其中
                    medicationMap.put(lastVisitMedication.getYpmc(), pgSffyDto);
                }
                results.addAll(medicationMap.values());
                // 将第二次处方中的药品信息添加到集合中并且返回
                for (SfFyxx secondLastVisitMedication : secondLastVisitMedications) {
                    PgSffyDto dto = new PgSffyDto();
                    dto.setId(secondLastVisitMedication.getId())
                            .setDaId(secondLastVisitMedication.getDaId())
                            .setYpmc(secondLastVisitMedication.getYpmc())
                            .setCfrq(secondLastVisitMedication.getCfrq());
                    results.add(dto);
                }
            }
            return results;
        } catch (Exception e) {
            throw new ParameterException("获取数据失败：" + e.getMessage());
        }
    }

    /**
     * 通过档案ID查询最新服药信息
     */
    public SfFyxxdjVo fetchLatestFyxxDataByDaId(String daId) {
        if (StrUtil.isBlank(daId)) {
            throw new ParameterException("档案ID不能为空");
        }
        SfFyxxdjVo sfFyxxdjVo = new SfFyxxdjVo();
        Date cfrq = null;
        String cfyljg = null;
        String cfyljgmc = null;
        String ywblfy = null;
        String ywblfyName = null;
        String sffs = null;
        String sffsName = null;
        String sfysid = null;
        // String sfys = null;
        String gljg = null;
        String gljgmc = null;

        List<SfFyxxVo> sfFyxxVos = this.baseMapper.fetchLatestFyxxDataByDaId(daId);
        if (sfFyxxVos.size() != 0) {
            SfFyxxVo sfFyxxVo = sfFyxxVos.get(0);
            cfrq = sfFyxxVo.getCfrq();
            cfyljg = sfFyxxVo.getCfyljg();
            cfyljgmc = sfFyxxVo.getCfyljgmc();
            ywblfy = sfFyxxVo.getYwblfy();
            ywblfyName = sfFyxxVo.getYwblfyName();
            sffs = sfFyxxVo.getSffs();
            sffsName = sfFyxxVo.getSffsName();
            sfysid = sfFyxxVo.getSfysid();
            gljg = sfFyxxVo.getGljg();
            gljgmc = sfFyxxVo.getGljgmc();
        }
//        List<String> sfysIds = new ArrayList<>();
//       sfysIds.add(sfysid);
//        Map<String, String> sfysidMap = DataUtil.getUserNameForMap(sfysIds);
//        sfysid = sfysidMap.get(sfysid);
//        List<String> cfyljgIds = new ArrayList<>();
//        cfyljgIds.add(cfyljg);
//        Map<String, String> cfyljgMap = DataUtil.getOrganNameForMap(cfyljgIds);
//        cfyljg = cfyljgMap.get(cfyljg);
//        List<String> gljgIds = new ArrayList<>();
//        gljgIds.add(gljg);
//        Map<String, String> gljgMap = DataUtil.getOrganNameForMap(gljgIds);
//        gljg = gljgMap.get(gljg);
        sfFyxxdjVo.setCfrq(cfrq)
                .setCfyljg(cfyljg)
                .setCfyljgmc(cfyljgmc)
                .setYwblfy(ywblfy)
                .setYwblfyName(ywblfyName)
                .setSffs(sffs)
                .setSffsName(sffsName)
                .setSfysid(sfysid)
                .setGljg(gljg)
                .setGljgmc(gljgmc);
        sfFyxxdjVo.setSfFyxxVos(sfFyxxVos);
        return sfFyxxdjVo;
    }

    /**
     * 通过档案ID和开方日期查询服药信息
     */
    public SfFyxxdjVo fetchFyxxDataByDaIdandDate(SfFyxxylSearchVo vo) {
        SfFyxxdjVo sfFyxxdjVo = new SfFyxxdjVo();
        Date cfrq = null;
        String cfyljg = null;
        String cfyljgmc = null;
        String ywblfy = null;
        String ywblfyName = null;
        String sffs = null;
        String sffsName = null;
        String sfysid = null;
        // String sfys = null;
        String gljg = null;
        String gljgmc = null;

        List<SfFyxxVo> sfFyxxVos = this.baseMapper.fetchFyxxDataByDaIdandDate(vo);
        if (sfFyxxVos.size() != 0) {
            SfFyxxVo sfFyxxVo = sfFyxxVos.get(0);
            cfrq = sfFyxxVo.getCfrq();
            cfyljg = sfFyxxVo.getCfyljg();
            cfyljgmc = sfFyxxVo.getCfyljgmc();
            ywblfy = sfFyxxVo.getYwblfy();
            ywblfyName = sfFyxxVo.getYwblfyName();
            sffs = sfFyxxVo.getSffs();
            sffsName = sfFyxxVo.getSffsName();
            sfysid = sfFyxxVo.getSfysid();
            gljg = sfFyxxVo.getGljg();
            gljgmc = sfFyxxVo.getGljgmc();
        }
        sfFyxxdjVo.setCfrq(cfrq)
                .setCfyljg(cfyljg)
                .setCfyljgmc(cfyljgmc)
                .setYwblfy(ywblfy)
                .setYwblfyName(ywblfyName)
                .setSffs(sffs)
                .setSffsName(sffsName)
                .setSfysid(sfysid)
                .setGljg(gljg)
                .setGljgmc(gljgmc);
        sfFyxxdjVo.setSfFyxxVos(sfFyxxVos);
        return sfFyxxdjVo;
    }
}

